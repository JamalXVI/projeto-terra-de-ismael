package br.com.jamalxvi.farmaciadanatureza.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Excecao {

    private String codigoDeErro;
    private String mensagemDeErro;
}
