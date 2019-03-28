package br.com.denisklein.controlefinanceiro.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;

public interface MovimentacaoRepository extends CrudRepository<Movimentacao, Long> {

}
