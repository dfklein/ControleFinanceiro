package br.com.denisklein.controlefinanceiro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;

public interface ExercicioMensalRepository extends CrudRepository<ExercicioMensal, Long > {

	@Query("SELECT e FROM ExercicioMensal e LEFT JOIN FETCH e.listMovimentacao listMov LEFT JOIN FETCH e.listCustoFixo listCusto WHERE e.id = :id")
	ExercicioMensal findByIdFetch(@Param(value = "id") Long id);

	
	
}
