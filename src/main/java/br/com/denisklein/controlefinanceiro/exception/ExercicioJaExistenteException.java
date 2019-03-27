package br.com.denisklein.controlefinanceiro.exception;

public class ExercicioJaExistenteException extends BusinessException {

	private static final long serialVersionUID = 2613672419427289735L;
	
	public ExercicioJaExistenteException() {
		super("Já existe exercício criado para o ano e mês informados.");
	}

}
