package br.com.jamalxvi.farmaciadanatureza.config;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumRotasEntrada;
import br.com.jamalxvi.farmaciadanatureza.security.auth.*;
import br.com.jamalxvi.farmaciadanatureza.service.impl.DetalhesDoUsuarioCustomizadoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Classe que representa as configurações do projeto, como o controle de acesso pela autenticação
 * 
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.cookie}")
  private String TOKEN_COOKIE;

  @Bean
  public FiltroDeAutenticacaoDoToken jwtAuthenticationTokenFilter() throws Exception {
    return new FiltroDeAutenticacaoDoToken();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private DetalhesDoUsuarioCustomizadoServiceImp detalhesDoUsuarioCustomizadoServiceImp;

  @Autowired
  private PontoDeEntradaAutenticacaoRest pontoDeEntradaAutenticacaoRest;

  @Autowired
  private SucessoEmLogout sucessoEmLogout;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(detalhesDoUsuarioCustomizadoServiceImp)
        .passwordEncoder(passwordEncoder());

  }

  @Autowired
  private ManipuladorDeAutenticacaoSucedida manipuladorDeAutenticacaoSucedida;

  @Autowired
  private ManipuladorDeAutenticacaoFalhada manipuladorDeAutenticacaoFalhada;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();
    httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .exceptionHandling().authenticationEntryPoint(pontoDeEntradaAutenticacaoRest).and()
        .authorizeRequests().anyRequest().authenticated().and()
        .addFilterBefore(jwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
        .formLogin().loginPage(EnumRotasEntrada.ROTA_LOGIN.getRota())
        .successHandler(manipuladorDeAutenticacaoSucedida)
        .failureHandler(manipuladorDeAutenticacaoFalhada).and().logout()
        .logoutRequestMatcher(new AntPathRequestMatcher(EnumRotasEntrada.ROTA_DESLOGAR.getRota()))
        .logoutSuccessHandler(sucessoEmLogout).deleteCookies(TOKEN_COOKIE);
    super.configure(httpSecurity);

  }

}
