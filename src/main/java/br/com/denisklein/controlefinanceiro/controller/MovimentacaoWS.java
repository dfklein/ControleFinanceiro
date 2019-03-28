package br.com.denisklein.controlefinanceiro.controller;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.model.entity.ExercicioMensal;
import br.com.denisklein.controlefinanceiro.model.entity.Movimentacao;
import br.com.denisklein.controlefinanceiro.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacao")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class MovimentacaoWS {
	
	@Autowired
	private MovimentacaoService movService;

	@RequestMapping(value="/add", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> add(@RequestBody Movimentacao movimentacao) throws BusinessException {
		ExercicioMensal ex = movService.add(movimentacao);
		return new ResponseEntity<>(ex, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/add/fixo/{idGastoPlanejado}", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> addPagamentoGastoPlanejado(@RequestBody Movimentacao movimentacao, @PathVariable Long idGastoPlanejado) throws BusinessException {
		ExercicioMensal ex = movService.add(movimentacao, idGastoPlanejado);
		return new ResponseEntity<>(ex, HttpStatus.CREATED);
	}
	

	@RequestMapping(value="/remove/{idMovimentacao}", method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExercicioMensal> add(@PathVariable Long idMovimentacao) throws BusinessException {
		ExercicioMensal ex = movService.remove(idMovimentacao);
		return new ResponseEntity<>(ex, HttpStatus.OK);
	}
	
}
