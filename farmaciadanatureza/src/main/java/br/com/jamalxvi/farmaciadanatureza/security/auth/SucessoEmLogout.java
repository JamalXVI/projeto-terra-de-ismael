package br.com.jamalxvi.farmaciadanatureza.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por manipular o sucesso em logout
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Component
public class SucessoEmLogout implements LogoutSuccessHandler {

  @Autowired
  ObjectMapper objectMapper;

  @Override
  public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                              HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    Map<String, String> result = new HashMap<>();
    result.put("result", "success");
    response.setContentType("application/json");
    response.getWriter().write(objectMapper.writeValueAsString(result));
    response.setStatus(HttpServletResponse.SC_OK);

  }

}