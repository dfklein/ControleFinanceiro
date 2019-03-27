package br.com.denisklein.controlefinanceiro.exception;

public class ExercicioNaoEncontradoException extends BusinessException {

	private static final long serialVersionUID = -417656443663892555L;
	
	public ExercicioNaoEncontradoException() {
		super("Não foi encontrado exercício para o ano e mês informados");
	}

}
