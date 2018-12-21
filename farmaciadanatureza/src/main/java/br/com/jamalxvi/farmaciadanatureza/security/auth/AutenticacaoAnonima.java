package br.com.jamalxvi.farmaciadanatureza.security.auth;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumCodigoHashAutenticacao;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Classe de que representa a autenticação anônima
 * @version     0.1
 * @since       0.1
 * @author      Jamal XVI <henriquearantest@gmail.com>
 */
public class AutenticacaoAnonima extends AbstractAuthenticationToken {

    public AutenticacaoAnonima() {
        super( null );
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = EnumCodigoHashAutenticacao.CODIGO_HASH_ANONIMO.getCodigo();
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        return true;
    }


}
