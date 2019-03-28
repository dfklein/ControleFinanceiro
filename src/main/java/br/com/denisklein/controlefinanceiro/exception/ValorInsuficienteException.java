package br.com.denisklein.controlefinanceiro.exception;

public class ValorInsuficienteException extends BusinessException {

	private static final long serialVersionUID = 6579987289731632114L;

	public ValorInsuficienteException() {
		super("O valor informado é insuficiente para realizar este pagamento.");
	}
	
}
