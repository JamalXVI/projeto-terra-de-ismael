package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;
import lombok.Setter;

public enum EnumTipoIdentidade {
    CPF(1),
    RG(1);
    @Getter @Setter
    private int tipo;

    EnumTipoIdentidade(int tipo) {
        this.tipo = tipo;
    }
}
