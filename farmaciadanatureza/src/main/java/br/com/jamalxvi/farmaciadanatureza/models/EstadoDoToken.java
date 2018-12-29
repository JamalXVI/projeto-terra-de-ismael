package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Data;

/**
 * Classe que define o token com informações como o valor do acesso e quando expira
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
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
