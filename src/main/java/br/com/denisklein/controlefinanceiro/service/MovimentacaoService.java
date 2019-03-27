package br.com.denisklein.controlefinanceiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;

@Service
public class MovimentacaoService {

	@Autowired
	private ExercicioService mesService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Movimentacao salvar(Movimentacao m) throws BusinessException {
		
		ExercicioMensal exercicio = mesService.findById(m.getDataMovimentacao().getYear(), m.getDataMovimentacao().getMonthValue());
		
		m.setExercicioMes(exercicio);
		exercicio.getListMovimentacao().add(m);
		
		mesService.salvar(exercicio);
		
		return m;
	}
	
}
