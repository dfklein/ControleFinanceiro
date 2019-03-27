package br.com.denisklein.controlefinanceiro.exception;

public class GastoPlanejadoPagoNesteExercicioException extends BusinessException {

	private static final long serialVersionUID = 4623508051525909475L;
	
	public GastoPlanejadoPagoNesteExercicioException() {
		super("Este gasto planejado já foi reportado como pago no exercício do mês/ano informado.");
	}

}
