package br.com.denisklein.controlefinanceiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
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
		GastoPlanejado gastoExistente = gastoRepo.findByDescricao(gasto.getDescricao());
		System.out.println(gastoExistente);
		
		ExercicioMensal ex = exService.findById(ano, mes);
		
		gasto.getListExercicioMensal().add(ex);
		ex.getListGastoPlanejado().add(gasto);
 
		gastoRepo.save(gasto);
		
		return ex;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal removerCustoPlanejado(Long idGasto, Integer ano, Integer mes) throws BusinessException {
		ExercicioMensal ex = exService.findById(ano, mes);

		ex.getListGastoPlanejado().removeIf(gasto -> gasto.getId().equals(idGasto));

		// gastoRepo.removerSeInutilizado(idGasto); // não existe uma implementação específica para remoção de registros órfãos para relacionamentos ManyToMany na JPA.
		
		exService.salvar(ex);
		
		return ex;
	}
	
}
