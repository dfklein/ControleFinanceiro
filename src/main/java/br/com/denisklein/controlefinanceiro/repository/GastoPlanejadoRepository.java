package br.com.denisklein.controlefinanceiro.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;

public interface GastoPlanejadoRepository extends CrudRepository<GastoPlanejado, Long > {

}
