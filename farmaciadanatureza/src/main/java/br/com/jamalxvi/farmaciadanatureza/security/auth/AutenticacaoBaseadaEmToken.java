package br.com.jamalxvi.farmaciadanatureza.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Classe de que representa o token autenticado
 * @version     0.1
 * @since       0.1
 * @author      Jamal XVI <henriquearantest@gmail.com>
 */
public class AutenticacaoBaseadaEmToken extends AbstractAuthenticationToken {

    private String token;
    private final UserDetails detalhesDoUsuario;

    public AutenticacaoBaseadaEmToken(UserDetails detalhesDoUsuario) {
        super( detalhesDoUsuario.getAuthorities() );
        this.detalhesDoUsuario = detalhesDoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public UserDetails getPrincipal() {
        return detalhesDoUsuario;
    }

}
