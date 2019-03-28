package br.com.denisklein.controlefinanceiro.exception;

public class MovimentacaoNaoEncontradaException extends BusinessException {

	private static final long serialVersionUID = 2892429421981307737L;
	
	public MovimentacaoNaoEncontradaException() {
		super("Não foi encontrada movimentação com o código de identificação informado.");
	}

}
