package br.com.denisklein.controlefinanceiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoInexistenteException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;
import br.com.denisklein.controlefinanceiro.repository.GastoPlanejadoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private ExercicioService mesService;
	
//	@Autowired
//	private GastoPlanejadoRepository gastoService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Movimentacao add(Movimentacao m) throws BusinessException {
		
		ExercicioMensal exercicio = mesService.findById(m.getDataMovimentacao().getYear(), m.getDataMovimentacao().getMonthValue());
		
		m.setExercicioMes(exercicio);
		exercicio.getListMovimentacao().add(m);
		
		mesService.salvar(exercicio);
		
		return m;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Movimentacao add(Movimentacao m, Long idGastoPlanejado) throws BusinessException {
//		if(!gastoService.existsById(idGastoPlanejado)) {
//			throw new GastoPlanejadoInexistenteException();
//		}
		
		ExercicioMensal exercicio = mesService.findById(m.getDataMovimentacao().getYear(), m.getDataMovimentacao().getMonthValue());
		
		return null;
	}
}
