package br.com.denisklein.controlefinanceiro.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, of="pk")
@Builder
public class PagamentoCustoPlanejado extends BaseEntityModel {

	private static final long serialVersionUID = 7801913110816369397L;

	@EmbeddedId
	private PagamentoCustoPlanejadoPK pk;
}
