package br.com.jamalxvi.farmaciadanatureza.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Serviço auxiliar de verificação do Token, funções:<br/>
 * - <b>Retornar o Usuário do Token</b><br/><code>getUsuarioDoToken(String token)</code><br/>
 * - <b>Gerar Token</b><br/><code>gerarToken(String usuario)</code><br/>
 * - <b>Verificar se pode atualizar o Token</b><br/>tokenPodeSerAtualizado(String token) <br/>
 * - <b>Retornar Token da URL</b><br/>getToken( HttpServletRequest request )<br/>
 * - <b>Encontrar um Cookie Específico</b><br/>getValorDoCookiePeloNome(HttpServletRequest request,
 * String name)
 * @author      Jamal XVI <henriquearantest@gmail.com>
 * @version     0.1
 * @since       0.1
 */
@Component
public class TokenHelper {
    //Nome da aplicação (Coletada do proprieties)
    @Value("${app.autorizacao}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expira_em}")
    private int EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    @Value("${jwt.cookie}")
    private String AUTH_COOKIE;

    @Autowired
    UserDetailsService userDetailsService;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * Retorna o usuário do Token
     * <p>
     *     Através do token retorna o usuário, descriptografando com JWT e a chave secreta
     * </p>
     * @param token string é o token para ser descriptografado
     * @return o usuário da aplicação
     */
    public String getUsuarioDoToken(String token) {
        String nomeDoUsuario;
        try {
            //Retornar o usuário do token, a partir da chave secreta
            final Claims claims = this.getClaimsDoToken(token);
            nomeDoUsuario = claims.getSubject();
        } catch (Exception e) {
            nomeDoUsuario = null;
        }
        return nomeDoUsuario;
    }

    /**
     * Gera o token com base no usuário
     * @param username nomePopular do usuário para criptografar com JWT
     * @return o JWT gerado em String
     */
    public String gerarToken(String username) {
        return Jwts.builder()
                .setIssuer( APP_NAME )
                .setSubject(username)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
    }

    /**
     *  Faz efetivamente a descriptografia do token
     * @param token o token para descriptografar
     * @return os itens descriptografado (dentro do Claims)
     */
    private Claims getClaimsDoToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    String gerarToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
    }

    /**
     * Retorna se o token pode ser atualizado
     * @param token retorna o token para ser verificado
     * @return booleano com o resultado se o token pode ser atualizado ou não
     */
    public Boolean tokenPodeSerAtualizado(String token) {
        try {
            final Date expirationDate = getClaimsDoToken(token).getExpiration();
            String username = getUsuarioDoToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return expirationDate.compareTo(generateCurrentDate()) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Efetivamente renova o token
     * @param token o token para ser renovado
     * @return o token atualizado ou <code>null</code> (caso não possível de renovar
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsDoToken(token);
            claims.setIssuedAt(generateCurrentDate());
            refreshedToken = gerarToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private long getCurrentTimeMillis() {
        return DateTime.now().getMillis();
    }

    private Date generateCurrentDate() {
        return new Date(getCurrentTimeMillis());
    }

    private Date generateExpirationDate() {

        return new Date(getCurrentTimeMillis() + this.EXPIRES_IN * 1000);
    }

    /**
     * Pega cookie da requisição
     * <p>
     *     A partir da requisição pega o token armazenado na HttpServelet seja nos Cookies ou
     *     no próprio cabeçalho
     * </p>
     * @param request é própriamente a requisição
     * @return O token como <code>String</code> ou <code>null</code>
     */
    public String getToken( HttpServletRequest request ) {
        /**
         *  Pegar token do Cookie armazenado
         */
        Cookie authCookie = getValorDoCookiePeloNome( request, AUTH_COOKIE );
        if ( authCookie != null ) {
            return authCookie.getValue();
        }
        /**
         *  Pegar token do header
         *  Exemplo: Barear + Token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if ( authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    /**
     * Encontra um Cookie específico na requisição HTTP
     *
     * @param request
     *            O objeto de requisição HTTP
     * @param name
     *            O nomePopular do Cookie de autorização para se procurar
     * @return O Cookie ou <code>null</code> se for encontrado.
     */
    public Cookie getValorDoCookiePeloNome(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals(name))
                .findFirst().orElse(null);
    }
}
