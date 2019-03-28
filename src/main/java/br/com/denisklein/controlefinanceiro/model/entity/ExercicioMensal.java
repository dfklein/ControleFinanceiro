package br.com.denisklein.controlefinanceiro.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of="id")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ExercicioMensal extends BaseEntityModel {

	private static final long serialVersionUID = 3559739544588116526L;

	@Id
	private Long id;
	private BigDecimal caixaInicial;
	
	@OneToMany(mappedBy="exercicioMes", cascade={CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval=false)
	private Set<Movimentacao> listMovimentacao;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="GASTO_PLANEJADO_LIST_EXERCICIO_MENSAL",
			joinColumns=@JoinColumn(name="LIST_CUSTO_FIXO_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="LIST_EXERCICIO_MENSAL_ID", referencedColumnName="ID")
		)
	private Set<GastoPlanejado> listGastoPlanejado;
	
	public void setIdFromDate(LocalDate date) {
		this.id = converterDataEmExercicioId(date);
	}

	public static Long converterDataEmExercicioId(LocalDate date) {
		return Long.valueOf(date.getYear() + "" + formatarDoisDigitos(date.getMonthValue()));
	}

	
	public static Long converterDataEmExercicioId(Integer ano, Integer mes) {
		return Long.valueOf(ano + "" + formatarDoisDigitos(mes));
	}
	
	private static String formatarDoisDigitos(Integer valor) {
		return String.format("%02d", valor);
	}
	
}
