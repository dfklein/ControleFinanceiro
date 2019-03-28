package br.com.denisklein.controlefinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.GastoPlanejado;
import br.com.denisklein.controlefinanceiro.service.GastoPlanejadoService;

@RestController
@RequestMapping("/planejado")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class GastoPlanejadoWS {
	
	@Autowired
	private GastoPlanejadoService gService;

	@RequestMapping(value="/add/{ano}/{mes}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> add(@RequestBody GastoPlanejado gasto,
			@PathVariable Integer ano, 
			@PathVariable Integer mes) throws BusinessException {
		
		ExercicioMensal exercicioMensal = gService.salvarNovoGastoPlanejado(gasto, ano, mes);
		return new ResponseEntity<>(exercicioMensal, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/remove/{ano}/{mes}/{idGasto}",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> add(@PathVariable Integer ano, 
			@PathVariable Integer mes,
			@PathVariable Long idGasto) throws BusinessException {
		
		ExercicioMensal exercicioMensal = gService.removerCustoPlanejado(idGasto, ano, mes);
		return new ResponseEntity<>(exercicioMensal, HttpStatus.CREATED);
		
	}
}
