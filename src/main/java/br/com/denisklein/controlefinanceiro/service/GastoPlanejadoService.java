package br.com.denisklein.controlefinanceiro.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoJaRegistradoException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;
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
		
		ExercicioMensal ex = exService.findById(ExercicioMensal.converterParaId(ano, mes));
//		Set<ExercicioMensal> gastoParcelas = criarExerciciosParcelaGastoPrevisto(gasto, ex);
		
		gasto.getListExercicioMensal().add(ex);
		ex.getListGastoPlanejado().add(gasto);
 
		gastoRepo.save(gasto);
		
		return ex;
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
	
	private Set<ExercicioMensal> criarExerciciosParcelaGastoPrevisto(GastoPlanejado gasto, ExercicioMensal exercicio) {
		Set<ExercicioMensal> listExercicios = new HashSet<>();
		listExercicios.add(exercicio);

		if(gasto.getNumParcelas() != null && gasto.getNumParcelas() > 1) {
			
			IntStream.rangeClosed(1, gasto.getNumParcelas()-1).forEach(num -> { 
			
				var formatter = DateTimeFormatter.ofPattern("yyyyMM");
				var yearMonth = YearMonth.parse(String.valueOf(exercicio.getId()), formatter).plusMonths(num);
				var novoId = Long.parseLong(formatter.format(yearMonth));
				
//				exService.criar(yearMonth.getYear(), yearMonth.getMonthValue(), valorInicial)
				
			});
		}
		
		return listExercicios;
		
	}
	
}
