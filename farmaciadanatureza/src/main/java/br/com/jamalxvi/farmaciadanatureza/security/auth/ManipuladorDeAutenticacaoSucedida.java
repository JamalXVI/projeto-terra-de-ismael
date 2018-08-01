package br.com.jamalxvi.farmaciadanatureza.security.auth;

/**
 * Created by fan.jin on 2016-11-07.
 */

import br.com.jamalxvi.farmaciadanatureza.models.EstadoDoToken;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.security.TokenHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe utilizada quando o usuário consegue fazer seu login com sucesso.
 * Ela também é responsável por inserir o cookie e o header de autenticação no navegador do
 * usuário
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
@Component
public class ManipuladorDeAutenticacaoSucedida extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${jwt.expira_em}")
    private int EXPIRA_EM;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

	@Autowired
    TokenHelper tokenHelper;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication ) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		Usuario usuario = (Usuario)authentication.getPrincipal();

		String jws = tokenHelper.gerarToken( usuario.getUsername() );

        // Cria o token de autorização para o cookie
        Cookie authCookie = new Cookie( TOKEN_COOKIE, ( jws ) );

		authCookie.setHttpOnly( true );

		authCookie.setMaxAge(EXPIRA_EM);

		authCookie.setPath( "/" );
		// Adiciona o cookie na resposta
		response.addCookie( authCookie );

		// Adiciona o JWT no header também
		EstadoDoToken userTokenState = new EstadoDoToken(jws, EXPIRA_EM);
		String jwtResponse = objectMapper.writeValueAsString( userTokenState );
		response.setContentType("application/json");
		response.getWriter().write( jwtResponse );

	}
}
