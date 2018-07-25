package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;
import lombok.Setter;

public enum EnumCodigoHashAutenticacao {
    CODIGO_HASH_ANONIMO(7);
    @Getter @Setter
    private int codigo;

    EnumCodigoHashAutenticacao(int codigo) {
        this.codigo = codigo;
    }
}
