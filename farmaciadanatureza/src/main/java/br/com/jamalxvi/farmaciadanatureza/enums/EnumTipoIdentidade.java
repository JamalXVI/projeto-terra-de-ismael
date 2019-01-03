package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;


/**
 * Define o tipo de identidade
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Getter
@Deprecated
public enum EnumTipoIdentidade {
    CPF(1),
    RG(2);
    private int tipo;

    EnumTipoIdentidade(int tipo) {
        this.tipo = tipo;
    }
}
