package br.com.jamalxvi.farmaciadanatureza.security.auth;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A função desta classe é de ser chamada quando um usuário descredenciado tenta acessar esta
 * casse, atualmente o usuário é condicionado a um erro 401 ( Sem Autorização).
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Component
public class PontoDeEntradaAutenticacaoRest implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request,
                       HttpServletResponse response,
                       AuthenticationException authException) throws IOException {
    // This is invoked when user tries to access a secured REST resource without supplying any credentials
    // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
  }
}

