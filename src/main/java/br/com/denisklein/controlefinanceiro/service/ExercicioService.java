package br.com.denisklein.controlefinanceiro.service;

import java.math.BigDecimal;
import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioJaExistenteException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioNaoEncontradoException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.repository.ExercicioMensalRepository;

@Service
public class ExercicioService {

	@Autowired
	private ExercicioMensalRepository exRepo;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal criar(Integer ano, Integer mes, BigDecimal valorInicial) throws BusinessException {
		return criar(ExercicioMensal.converterParaId(ano, mes), valorInicial);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal criar(YearMonth id, BigDecimal valorInicial) throws BusinessException {
		validarExistenciaExercicio(id);
		
		ExercicioMensal exercicio = new ExercicioMensal();
		exercicio.setId(id);
		exercicio.setCaixaInicial(valorInicial);
		
		exRepo.save(exercicio);
		
		return exercicio;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public ExercicioMensal findById(YearMonth yearMonth) throws ExercicioNaoEncontradoException {
		ExercicioMensal ex = exRepo.findByIdFetch(yearMonth);
		if (ex != null) return ex; else throw new ExercicioNaoEncontradoException();

	}

	@Transactional(propagation=Propagation.REQUIRED)
	public ExercicioMensal salvar(ExercicioMensal exercicio) {
		return exRepo.save(exercicio);
	}


	private boolean validarExistenciaExercicio(YearMonth id) throws ExercicioJaExistenteException {
		boolean exercicioExistente = exRepo.verificarExercicioExistente(id);
		
		if(exercicioExistente) {
			throw new ExercicioJaExistenteException();
		} else {
			return exercicioExistente;
		}
		
	}
}
