package br.com.denisklein.controlefinanceiro.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.denisklein.controlefinanceiro.enums.TipoMovimentacaoEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of="id")
@ToString
@JsonInclude(Include.NON_EMPTY)
public class Movimentacao extends BaseEntityModel {

	private static final long serialVersionUID = 7724376464880152726L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@JsonIgnore
	private Long id;
	private String descricao;
	private LocalDate dataMovimentacao;
	private Integer tipoMovimentacao;
	private BigDecimal valor;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private ExercicioMensal exercicioMes;
	
	public TipoMovimentacaoEnum getTipoMovimentacao() {
		return TipoMovimentacaoEnum.getFromCod(this.tipoMovimentacao);
	}
	
	public void setTipoMovimentacao(TipoMovimentacaoEnum tipo) {
		this.tipoMovimentacao = tipo.getCodTipoMov();
	}

}
