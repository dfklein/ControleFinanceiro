package br.com.denisklein.controlefinanceiro.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1954860285296052482L;

	public BusinessException(String msg) {
		super(msg);
	}
}
