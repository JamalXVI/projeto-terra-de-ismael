package br.com.jamalxvi.farmaciadanatureza.security.auth;

import br.com.jamalxvi.farmaciadanatureza.security.TokenHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
* Filtro de Autenticação do Token. A função desta classe é escutar todas as requisições,
* verificar o token ou estabelecer um token como usuário anônimo, além de verificar as rotas
* que devem ser escutadas. Esta classe extende a interfaces OncePerRequestFilter, pois é através
* desta que será indentificado o filtro. Métodos:<br/>
* -<b>Fazer Filtro</b><br/><code>doFilterInternal(HttpServletRequest request, HttpServletResponse
 * response, FilterChain chain)</code>
* @author      Jamal XVI <henriquearantest@gmail.com>
* @version     0.1
* @since       0.1
*/
public class FiltroDeAutenticacaoDoToken extends OncePerRequestFilter {

   private final Log logger = LogFactory.getLog(this.getClass());

   @Autowired
   TokenHelper tokenHelper;

   @Autowired
   UserDetailsService userDetailsService;

   /*
    * As rotas abaixo serão ignoradas pelo filtro
    */
   public static final String ROOT_MATCHER = "/";
   public static final String FAVICON_MATCHER = "/favicon.ico";
   public static final String HTML_MATCHER = "/**/*.html";
   public static final String CSS_MATCHER = "/**/*.css";
   public static final String JS_MATCHER = "/**/*.js";
   public static final String IMG_MATCHER = "/images/*";
   public static final String LOGIN_MATCHER = "/auth/login";
   public static final String LOGOUT_MATCHER = "/auth/logout";

   private List<String> rotasParaEsquivar = Arrays.asList(
           ROOT_MATCHER,
           HTML_MATCHER,
           FAVICON_MATCHER,
           CSS_MATCHER,
           JS_MATCHER,
           IMG_MATCHER,
           LOGIN_MATCHER,
           LOGOUT_MATCHER
   );

    /**
     * Sobreescreve o método abstratato que implementa o filtro
     * @param request equivale a requisição
     * @param response equivale a resposta (retornando o resultado do filtro)
     * @param chain equivale a cadeia de filtros anteriores
     * @throws IOException
     * @throws ServletException
     */
   @Override
   public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

       String tokenDeAutorizacao = tokenHelper.getToken(request);
       if (tokenDeAutorizacao != null && !rotasParaEsquivar(request, rotasParaEsquivar)) {
           // retorna o usuário do token
           try {
               String usuario = tokenHelper.getUsuarioDoToken(tokenDeAutorizacao);
               // pega o usuário
               UserDetails detalhesDoUsuario = userDetailsService.loadUserByUsername(usuario);
               // Cria Autenticação
               AutenticacaoBaseadaEmToken autenticacao = new AutenticacaoBaseadaEmToken(detalhesDoUsuario);
               autenticacao.setToken(tokenDeAutorizacao);
               SecurityContextHolder.getContext().setAuthentication(autenticacao);
           } catch (Exception e) {
               SecurityContextHolder.getContext().setAuthentication(new AutenticacaoAnonima());
           }
       } else {
           SecurityContextHolder.getContext().setAuthentication(new AutenticacaoAnonima());
       }

       chain.doFilter(request, response);
   }

   private boolean rotasParaEsquivar(HttpServletRequest requisicao, List<String> rotasParaEsquivar ) {
       Assert.notNull(rotasParaEsquivar, "rota não pode ser nula;");
       List<RequestMatcher> rotasParaSeremEsquivadas = rotasParaEsquivar.stream().map(path ->
               new AntPathRequestMatcher(path)).collect(Collectors.toList());
       OrRequestMatcher matchers = new OrRequestMatcher(rotasParaSeremEsquivadas);
       return matchers.matches(requisicao);
   }

}