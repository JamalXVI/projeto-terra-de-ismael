package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;

public enum EnumCodigoHashAutenticacao {
    CODIGO_HASH_ANONIMO(7);

    @Getter
    private int codigo;

    EnumCodigoHashAutenticacao(int codigo) {
        this.codigo = codigo;
    }
}
