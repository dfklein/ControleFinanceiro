package br.com.denisklein.controlefinanceiro.exception.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.denisklein.controlefinanceiro.exception.BusinessException;
import br.com.denisklein.controlefinanceiro.exception.ExercicioNaoEncontradoException;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro de servidor: " + e.getMessage());
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleException(BusinessException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(ExercicioNaoEncontradoException.class)
	public ResponseEntity<String> handleException(ExercicioNaoEncontradoException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
}
