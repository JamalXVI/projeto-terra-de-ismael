package br.com.jamalxvi.farmaciadanatureza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por lidar com as Exceções
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@ControllerAdvice
public class ManipulacaoDeExcecaoDoController {

    @ExceptionHandler(MensagemExcecao.class)
    public ResponseEntity<Excecao> resourceConflict(MensagemExcecao ex) {
        String complemento = ex.getMostrarId() ? " / Id: " + "" + ex.getIdDoRecurso() : "";
        Excecao response = Excecao.builder().codigoDeErro(ex.getCodigoEnum().getCodigoErro().toString())
                .mensagemDeErro(ex.getCodigoEnum().getMensagemErro() + ": " + ex.getMessage() + complemento).build();
        return new ResponseEntity<Excecao>(response, HttpStatus.CONFLICT);
    }
}
