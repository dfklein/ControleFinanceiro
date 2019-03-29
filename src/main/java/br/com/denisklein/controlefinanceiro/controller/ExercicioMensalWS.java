package br.com.denisklein.controlefinanceiro.controller;

import java.math.BigDecimal;
import java.time.YearMonth;

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
import br.com.denisklein.controlefinanceiro.service.ExercicioService;

@RestController
@RequestMapping("/exercicio")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ExercicioMensalWS {

	@Autowired
	private ExercicioService exService;
	
	@RequestMapping(value="/add/{ano}/{mes}",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> add(@PathVariable Integer ano, 
			@PathVariable Integer mes, 
			@RequestBody BigDecimal valorInicial) throws BusinessException {
		
		ExercicioMensal exercicio = exService.criar(ano, mes, valorInicial);
		return new ResponseEntity<>(exercicio, HttpStatus.CREATED);
			
	}
	
	@RequestMapping(value="/find/{ano}/{mes}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> find(@PathVariable Integer ano, 
			@PathVariable Integer mes) throws BusinessException {
		
		ExercicioMensal exercicio = exService.findById(ExercicioMensal.converterParaId(ano, mes));
		return new ResponseEntity<>(exercicio, HttpStatus.CREATED);
		
	}
}
