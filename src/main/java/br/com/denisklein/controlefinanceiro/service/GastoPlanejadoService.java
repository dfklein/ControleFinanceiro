package br.com.denisklein.controlefinanceiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;

@Service
public class GastoPlanejadoService {

	@Autowired
	private ExercicioService exService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal salvarNovoGastoPlanejado(GastoPlanejado gasto, Integer ano, Integer mes) throws BusinessException {
		ExercicioMensal ex = exService.findById(ano, mes);
		
		gasto.getListExercicioMensal().add(ex);
		ex.getListGastoPlanejado().add(gasto);
 
		exService.salvar(ex);
		
		return ex;
	}
	
}
