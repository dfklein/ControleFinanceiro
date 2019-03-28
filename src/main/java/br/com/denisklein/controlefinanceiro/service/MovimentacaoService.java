package br.com.denisklein.controlefinanceiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.GastoPlanejadoInexistenteException;
import br.com.denisklein.controlefinanceiro.exception.PagamentoCustoFixoExistenteException;
import br.com.denisklein.controlefinanceiro.exception.ValorInsuficienteException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;
import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;

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
		
		GastoPlanejado gastoPlan = obterGastoPlanejadoInformado(idGastoPlanejado, exercicio);
		validarPagamentoJaRealizado(exercicio, gastoPlan);
		validarValorSuficiente(movimentacao, gastoPlan);
		
		gastoPlan.getListMovimentacao().add(movimentacao);
		movimentacao.setGastoPlanejado(gastoPlan);
		movimentacao.setDescricao(gastoPlan.getDescricao());
		
		return add(movimentacao);
		
	}

	private GastoPlanejado obterGastoPlanejadoInformado(Long idGastoPlanejado, ExercicioMensal exercicio)
			throws BusinessException {
		
		GastoPlanejado gastoPlan = exercicio.getListGastoPlanejado().stream()
			.filter(gasto -> gasto.getId().equals(idGastoPlanejado))
			.findFirst()
			.orElseThrow(() -> new GastoPlanejadoInexistenteException());
		
		return gastoPlan;
	}

	private void validarPagamentoJaRealizado(ExercicioMensal exercicio, GastoPlanejado gastoPlan)
			throws PagamentoCustoFixoExistenteException {
		
		if(exercicio.getListMovimentacao().stream().anyMatch(mov -> mov.getGastoPlanejado().equals(gastoPlan))) {
			throw new PagamentoCustoFixoExistenteException();
		}
		
	}
	
	private void validarValorSuficiente(Movimentacao movimentacao, GastoPlanejado gastoPlan)
			throws ValorInsuficienteException {
		if (movimentacao.getValor().compareTo(gastoPlan.getValorProvisionado()) < 0) {
			throw new ValorInsuficienteException();
		}
	}
	
}
