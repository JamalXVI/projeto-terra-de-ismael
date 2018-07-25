package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Data;

@Data
public class EstadoDoToken {
    private String tokenDeAcesso;
    private Long expiraEm;

    public EstadoDoToken() {
        this.tokenDeAcesso = null;
        this.expiraEm = null;
    }

    public EstadoDoToken(String tokenDeAcesso, long expiraEm) {
        this.tokenDeAcesso = tokenDeAcesso;
        this.expiraEm = expiraEm;
    }

}
