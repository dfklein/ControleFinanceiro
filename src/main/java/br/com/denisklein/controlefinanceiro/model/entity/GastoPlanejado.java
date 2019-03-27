package br.com.denisklein.controlefinanceiro.model.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of= {"id"})
@JsonInclude(Include.NON_EMPTY)
public class GastoPlanejado extends BaseEntityModel {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@JsonIgnore
	private Long id;
	private String descricao;
	private BigDecimal valorProvisionado;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<PagamentoCustoPlanejado> listPagamentoCustoPlanejado;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="listCustoFixo")
	@JsonIgnore
	private Set<ExercicioMensal> listExercicioMensal = new HashSet<>(); 

}
