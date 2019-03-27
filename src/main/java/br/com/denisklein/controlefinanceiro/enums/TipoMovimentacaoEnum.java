package br.com.denisklein.controlefinanceiro.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMovimentacaoEnum {

	RECEBIMENTO(1, "Depósito em conta"),
	PAGAMENTO_CREDITO(2, "Crédito"),
	PAGAMENTO_DEBITO(3, "Débito"),
	SAQUE(4, "Saque");
	
	private Integer codTipoMov;
	private String descricao;

	public static TipoMovimentacaoEnum getFromCod(Integer cod) {
		return Arrays.stream(TipoMovimentacaoEnum.values())
				.filter(tipo -> tipo.codTipoMov.equals(cod))
				.findFirst()
				.orElse(null);
	}
}
