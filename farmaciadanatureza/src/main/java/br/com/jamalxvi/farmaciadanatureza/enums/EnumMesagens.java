package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Lista todas mensagens do sistema
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@AllArgsConstructor
public enum EnumMesagens {
    ERRO_LISTAR_PESSOA_ATRIBUTO_NULO("Alguma pessoa está com atributo Nulo");
    @Getter
    private String mensagem;
}
