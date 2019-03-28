package br.com.denisklein.controlefinanceiro.exception;

public class PagamentoCustoFixoExistenteException extends BusinessException {

	private static final long serialVersionUID = -8086856682460718579L;
	
	
	public PagamentoCustoFixoExistenteException() {
		super("Já consta pagamento realizado para este custo planejado no exercício informado.");
	}

}
