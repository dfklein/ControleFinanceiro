package br.com.denisklein.controlefinanceiro.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;

public interface GastoPlanejadoRepository extends CrudRepository<GastoPlanejado, Long > {

//	@Query("SELECT "
//		+ " CASE WHEN "
//		+ "    COUNT (gasto) > 0 "
//		+ " THEN "
//		+ "    TRUE "
//		+ " ELSE "
//		+ "    FALSE "
//		+ " END "
//		+ " FROM "
//		+ "     GastoPlanejado gastoPlan "
//
//	)
//	boolean gastoPlanejadoPagoNesteExercicio(Long idGastoPlanejado, LocalDate dataMovimentacao);

}
