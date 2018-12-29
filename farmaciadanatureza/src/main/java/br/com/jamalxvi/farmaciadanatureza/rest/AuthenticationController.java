package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.models.EstadoDoToken;
import br.com.jamalxvi.farmaciadanatureza.security.TokenHelper;
import br.com.jamalxvi.farmaciadanatureza.service.impl.DetalhesDoUsuarioCustomizadoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController {

    @Autowired
    private DetalhesDoUsuarioCustomizadoServiceImp detalhesDoUsuarioCustomizadoServiceImp;

    @Autowired
    TokenHelper tokenHelper;

    @Value("${jwt.expira_em}")
    private int EXPIRA_EM;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {

        String authToken = tokenHelper.getToken( request );
        if (authToken != null && tokenHelper.tokenPodeSerAtualizado(authToken)) {
            // TODO check user senha last update
            String refreshedToken = tokenHelper.refreshToken(authToken);

            Cookie authCookie = new Cookie( TOKEN_COOKIE, ( refreshedToken ) );
            authCookie.setPath( "/" );
            authCookie.setHttpOnly( true );
            authCookie.setMaxAge(EXPIRA_EM);
            // Add cookie to response
            response.addCookie( authCookie );

            EstadoDoToken estadoDoToken = new EstadoDoToken(refreshedToken, EXPIRA_EM);
            return ResponseEntity.ok(estadoDoToken);
        } else {
            EstadoDoToken estadoDoToken = new EstadoDoToken();
           return ResponseEntity.accepted().body(estadoDoToken);
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        detalhesDoUsuarioCustomizadoServiceImp.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }

}
