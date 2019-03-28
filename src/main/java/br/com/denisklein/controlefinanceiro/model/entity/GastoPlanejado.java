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
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of= {"id"})
@ToString(exclude= {"listExercicioMensal", "listMovimentacao"})
@JsonInclude(Include.NON_EMPTY)
public class GastoPlanejado extends BaseEntityModel {

	private static final long serialVersionUID = 3380660991822639732L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Long id;
	private String descricao;
	private BigDecimal valorProvisionado;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="listGastoPlanejado")
	@JsonIgnore
	private Set<ExercicioMensal> listExercicioMensal = new HashSet<>(); 
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="gastoPlanejado")
	@JsonIgnore
	private Set<Movimentacao> listMovimentacao = new HashSet<>(); 

}
