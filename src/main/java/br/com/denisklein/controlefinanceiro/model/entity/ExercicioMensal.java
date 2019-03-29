package br.com.denisklein.controlefinanceiro.model.entity;

import java.math.BigDecimal;
import java.time.YearMonth;
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
@JsonInclude(Include.NON_EMPTY)
public class ExercicioMensal extends BaseEntityModel {

	private static final long serialVersionUID = 3559739544588116526L;

	@Id
	private YearMonth id;
	private BigDecimal caixaInicial;
	
	@OneToMany(mappedBy="exercicioMes", cascade={CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval=false)
	private Set<Movimentacao> listMovimentacao;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="GASTO_PLANEJADO_LIST_EXERCICIO_MENSAL",
			joinColumns=@JoinColumn(name="LIST_CUSTO_FIXO_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="LIST_EXERCICIO_MENSAL_ID", referencedColumnName="ID")
		)
	private Set<GastoPlanejado> listGastoPlanejado;

	public static YearMonth converterParaId(Integer ano, Integer mes) {
		try {
			return YearMonth.of(ano, mes);
		} catch (Exception e) {
			throw new IllegalArgumentException("Formato inválido nos parâmetros informados: ano=" + ano + ", mês=" + mes);
		}

	}
	

//	public YearMonth getId() {
//		return YearMonth.of(Integer.valueOf(id.substring(0, 4)), Integer.valueOf(id.substring(3)));
//	}
//	
//	public void setId(YearMonth id) {
//		this.id = id.toString().replace("-", "");
//	}
	
}
