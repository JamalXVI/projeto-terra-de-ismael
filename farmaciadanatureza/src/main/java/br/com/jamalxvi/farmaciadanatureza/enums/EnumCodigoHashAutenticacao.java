package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;

/**
 * Define um código para os usuários não autenticados
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public enum EnumCodigoHashAutenticacao {
    CODIGO_HASH_ANONIMO(7);

    @Getter
    private int codigo;

    EnumCodigoHashAutenticacao(int codigo) {
        this.codigo = codigo;
    }
}
