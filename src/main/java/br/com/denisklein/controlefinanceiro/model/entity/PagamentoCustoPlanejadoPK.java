package br.com.denisklein.controlefinanceiro.model.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.servlet.annotation.HandlesTypes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of={"gastoPlanejado", "movimentacao"})
public class PagamentoCustoPlanejadoPK implements Serializable {

	private static final long serialVersionUID = -8235037738541811209L;
	
	private GastoPlanejado gastoPlanejado;
	private Movimentacao movimentacao;
	
}
