package br.com.jamalxvi.farmaciadanatureza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipulacaoDeExcecaoDoController {

  @ExceptionHandler(ExcecaoDeConflitoDeRecurso.class)
  public ResponseEntity<RespostaDeExcecao> resourceConflict(ExcecaoDeConflitoDeRecurso ex) {
    RespostaDeExcecao response = new RespostaDeExcecao();
    response.setCodigoDeErro("Conflito");
    response.setMensagemDeErro(ex.getMessage());
    return new ResponseEntity<RespostaDeExcecao>(response, HttpStatus.CONFLICT);
  }
}
