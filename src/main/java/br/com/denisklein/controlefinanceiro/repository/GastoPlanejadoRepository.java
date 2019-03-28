package br.com.denisklein.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;

public interface GastoPlanejadoRepository extends CrudRepository<GastoPlanejado, Long> {

	@Query(" SELECT CASE WHEN (COUNT(g) > 0) THEN TRUE ELSE FALSE END FROM GastoPlanejado g WHERE g.descricao = :descricao")
	boolean nomeGastoRegistrado(@Param(value = "descricao") String descricao);

	GastoPlanejado findByDescricao(String descricao);
	
//	@Query("DELETE FROM GastoPlanejado g WHERE g.id NOT IN (SELECT g1.id FROM GastoPlanejado g1 INNER JOIN g1.listExercicioMensal)")
//	void removerSeInutilizado(@Param(value = "id") Long id);

}
