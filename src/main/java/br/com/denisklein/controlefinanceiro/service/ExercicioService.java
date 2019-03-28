package br.com.denisklein.controlefinanceiro.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioJaExistenteException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioNaoEncontradoException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.repository.ExercicioMensalRepository;
import br.com.denisklein.controlefinanceiro.validacoes.ExercicioValidacoes;

@Service
public class ExercicioService {

	@Autowired
	private ExercicioMensalRepository exRepo;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal criar(Integer ano, Integer mes, BigDecimal valorInicial) throws BusinessException {
		ExercicioValidacoes.validarAnoMes(ano, mes);
		Long id = ExercicioMensal.converterDataEmExercicioId(ano, mes);
		
		if(exRepo.existsById(id)) {
			throw new ExercicioJaExistenteException();
		}
		
		ExercicioMensal exercicio = ExercicioMensal.builder()
			.id(id)
			.caixaInicial(valorInicial)
			.build();
		
		exRepo.save(exercicio);
		
		return exercicio;
	}
	

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ExercicioMensal findById(Integer ano, Integer mes) throws BusinessException {
		Long id = ExercicioMensal.converterDataEmExercicioId(ano, mes);
		return findById(id);
	}


	private ExercicioMensal findById(Long id) throws ExercicioNaoEncontradoException {
		ExercicioMensal ex = exRepo.findByIdFetch(id);
		if (ex != null) return ex; else throw new ExercicioNaoEncontradoException();

	}

	public ExercicioMensal salvar(ExercicioMensal exercicio) {
		return exRepo.save(exercicio);
	}


}
