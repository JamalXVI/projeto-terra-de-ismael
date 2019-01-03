package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Enum que define as rotas de entradas do sistema
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public enum EnumRotasEntrada {
    ROTA_LOGIN("/api/login"),
    ROTA_CADASTRAR("/api/signup"),
    ROTA_DESLOGAR("/api/logout");

    @Getter @Setter
    private String rota;

    EnumRotasEntrada(String rota) {
        this.rota = rota;
    }
}
