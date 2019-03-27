package br.com.denisklein.controlefinanceiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoInexistenteException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoPagoNesteExercicioException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;
import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;
import br.com.denisklein.controlefinanceiro.model.entity.PagamentoCustoPlanejado;
import br.com.denisklein.controlefinanceiro.model.entity.PagamentoCustoPlanejadoPK;
import br.com.denisklein.controlefinanceiro.repository.GastoPlanejadoRepository;

@Service
public class MovimentacaoService {

	@Autowired
	private ExercicioService mesService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal add(Movimentacao m) throws BusinessException {
		
		ExercicioMensal exercicio = mesService.findById(m.getDataMovimentacao().getYear(), m.getDataMovimentacao().getMonthValue());
		
		m.setExercicioMes(exercicio);
		exercicio.getListMovimentacao().add(m);
		
		mesService.salvar(exercicio);
		
		return exercicio;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal add(Movimentacao movimentacao, Long idGastoPlanejado) throws BusinessException {
		
		ExercicioMensal exercicio = mesService.findById(movimentacao.getDataMovimentacao().getYear(), movimentacao.getDataMovimentacao().getMonthValue());
		
		GastoPlanejado gastoPlan = exercicio.getListGastoPlanejado().stream()
			.filter(gasto -> gasto.getId().equals(idGastoPlanejado))
			.findFirst()
			.orElseThrow(() -> new GastoPlanejadoInexistenteException());
		
		PagamentoCustoPlanejadoPK pgmtoPk = PagamentoCustoPlanejadoPK.builder()
			.gastoPlanejado(gastoPlan)
			.movimentacao(movimentacao)
			.build();
		
		PagamentoCustoPlanejado pgmto = PagamentoCustoPlanejado.builder().pk(pgmtoPk).build();
		
		gastoPlan.getListPagamentoCustoPlanejado().add(pgmto);
		
		return add(movimentacao);
		
	}
}
