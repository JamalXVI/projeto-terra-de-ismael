package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumExcecaoDto {
    ATRIBUTO_EXISTE(1L, "Atributo Existe"),
    ATRIBUTOS_VAZIOS(2L, "Atributo Nulo");

    @Getter
    Long codigoErro;
    @Getter
    String mensagemErro;
}
