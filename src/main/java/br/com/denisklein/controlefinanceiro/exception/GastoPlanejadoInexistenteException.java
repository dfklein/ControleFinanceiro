package br.com.denisklein.controlefinanceiro.exception;

public class GastoPlanejadoInexistenteException extends BusinessException {

	private static final long serialVersionUID = 8714004700102950616L;
	
	public GastoPlanejadoInexistenteException() {
		super("Não foi encontrado gasto planejado com o código informado pertencente ao mês ao mês/ano informado.");
	}

}
