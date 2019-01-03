package br.com.jamalxvi.farmaciadanatureza.exception;

import lombok.Builder;
import lombok.Getter;


/**
 * Classe responsável por enviar Exceção para o usuário.
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Builder
@Getter
public class Excecao {

    private String codigoDeErro;
    private String mensagemDeErro;
}
