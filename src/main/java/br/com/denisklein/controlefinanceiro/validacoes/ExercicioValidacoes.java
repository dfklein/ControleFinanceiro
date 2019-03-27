package br.com.denisklein.controlefinanceiro.validacoes;

import java.time.Month;
import java.time.Year;

import br.com.denisklein.controlefinanceiro.repository.ExercicioMensalRepository;

public class ExercicioValidacoes {

	private ExercicioValidacoes() {
		
	}
	
	public static void validarAnoMes(Integer ano, Integer mes) {
		try {
			Year.of(ano);
			Month.of(mes);
		} catch (Exception e) {
			throw new IllegalArgumentException("Formato inválido nos parâmetros informados: ano=" + ano + ", mês=" + mes);
		}
		
	}
}