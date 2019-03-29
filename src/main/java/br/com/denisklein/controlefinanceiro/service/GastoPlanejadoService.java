package br.com.denisklein.controlefinanceiro.service;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioJaExistenteException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoJaRegistradoException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;
import br.com.denisklein.controlefinanceiro.model.entity.Parcelamento;
import br.com.denisklein.controlefinanceiro.model.entity.ParcelamentoPK;
import br.com.denisklein.controlefinanceiro.repository.GastoPlanejadoRepository;

@Service
public class GastoPlanejadoService {

	@Autowired
	private ExercicioService exService;
	
	@Autowired
	private GastoPlanejadoRepository gastoRepo;

	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal salvarNovoGastoPlanejado(GastoPlanejado gasto, Integer ano, Integer mes) throws BusinessException {

		validarInclusaoRegistroGastoPlanejado(gasto);
		
		ExercicioMensal exercicio = exService.findById(ExercicioMensal.converterParaId(ano, mes));
		exercicio.getListGastoPlanejado().add(gasto);
		
//		exService.salvar(exercicio);
		gastoRepo.save(gasto);
		
		if(gasto.getNumParcelas() != null && gasto.getNumParcelas() > 1) {
			Set<ExercicioMensal> exerciciosParcelas = gerarExerciciosParcelas(gasto, exercicio);
			
			exerciciosParcelas.forEach(exFuturo -> exFuturo.getListGastoPlanejado().add(gasto));
	 
			Parcelamento parcelamento = Parcelamento.builder()
				.id(ParcelamentoPK.builder().exercicioPrimeiraParcela(exercicio.getId()).gastoPlanejadoId(gasto.getId()).build())
				.numParcelas(gasto.getNumParcelas())
				.build();
			
			gasto.getListParcelamentos().add(parcelamento);
			
		}
		
		return exercicio;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal removerCustoPlanejado(Long idGasto, Integer ano, Integer mes) throws BusinessException {
		ExercicioMensal ex = exService.findById(ExercicioMensal.converterParaId(ano, mes));

		ex.getListGastoPlanejado().removeIf(gasto -> gasto.getId().equals(idGasto));

		// gastoRepo.removerSeInutilizado(idGasto); // não existe uma implementação específica para remoção de registros órfãos para relacionamentos ManyToMany na JPA.
		
		exService.salvar(ex);
		
		return ex;
	}

	private void validarInclusaoRegistroGastoPlanejado(GastoPlanejado gasto) throws GastoPlanejadoJaRegistradoException {
		if(gastoRepo.nomeGastoRegistrado(gasto.getDescricao())) {
			throw new GastoPlanejadoJaRegistradoException();
		}
	}
	
	private Set<ExercicioMensal> gerarExerciciosParcelas(GastoPlanejado gasto, ExercicioMensal ex) throws BusinessException {
		
		Set<ExercicioMensal> listExercicios = new HashSet<>();
			
		for (int num = 1; num <= gasto.getNumParcelas(); num++) {
			YearMonth idNovoExercicio = ex.getId().plusMonths(num);
			ExercicioMensal exercicioFuturo = null;
			
			try {
				exercicioFuturo = exService.criar(idNovoExercicio, ex.getCaixaInicial());
				
			} catch (ExercicioJaExistenteException e) {
				exercicioFuturo = exService.findById(idNovoExercicio);
				
			}
			
			if (exercicioFuturo.getListGastoPlanejado() == null) {
				exercicioFuturo.setListGastoPlanejado(new HashSet<>());
			}
			
			listExercicios.add(exercicioFuturo);
			
		}
			
		
		return listExercicios;
		
	}
	
}
