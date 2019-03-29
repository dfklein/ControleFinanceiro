package br.com.denisklein.controlefinanceiro.model.entity;

import java.io.Serializable;
import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false, of= {"exercicioPrimeiraParcela", "gastoPlanejadoId"})
public class ParcelamentoPK implements Serializable {

	private static final long serialVersionUID = 2725357828780161194L;
	
	private YearMonth exercicioPrimeiraParcela;
	@Column(name="GASTO_PLANEJADO_ID")
	private Long gastoPlanejadoId;
}
