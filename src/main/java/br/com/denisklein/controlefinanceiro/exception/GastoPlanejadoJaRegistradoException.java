package br.com.denisklein.controlefinanceiro.exception;

public class GastoPlanejadoJaRegistradoException extends BusinessException {

	private static final long serialVersionUID = -8780846636886732067L;
	
	public GastoPlanejadoJaRegistradoException() {
		super("Já consta um gasto planejado com este nome nos registros.");
	}

}
