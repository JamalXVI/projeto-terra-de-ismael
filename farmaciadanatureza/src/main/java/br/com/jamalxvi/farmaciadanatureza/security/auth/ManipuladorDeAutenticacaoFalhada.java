package br.com.jamalxvi.farmaciadanatureza.security.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe responsável por lidar com a Autenticação
 * @version     0.1
 * @since       0.1
 * @author      Jamal XVI <henriquearantest@gmail.com>
 */
@Component
public class ManipuladorDeAutenticacaoFalhada extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        super.onAuthenticationFailure(request, response, exception);
    }
}