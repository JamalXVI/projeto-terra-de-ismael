package br.com.jamalxvi.farmaciadanatureza.enums;

import lombok.Getter;
import lombok.Setter;

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
