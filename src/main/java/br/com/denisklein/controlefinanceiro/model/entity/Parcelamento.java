package br.com.denisklein.controlefinanceiro.model.entity;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of= {"gastoPlanejado", "exercicioPrimeiraParcela"})
@JsonInclude(Include.NON_EMPTY)
public class Parcelamento extends BaseEntityModel {

	private static final long serialVersionUID = 2549441569593380383L;

	@EmbeddedId
	private ParcelamentoPK id;
	private Integer numParcelas;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GASTO_PLANEJADO_ID", referencedColumnName="ID", insertable=false, updatable=false)
	private GastoPlanejado gastoPlanejado;
	
}
