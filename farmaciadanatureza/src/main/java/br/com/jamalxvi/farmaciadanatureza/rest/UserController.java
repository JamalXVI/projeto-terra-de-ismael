package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.exception.ExcecaoDeConflitoDeRecurso;
import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by fan.jin on 2016-10-15.
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UsuarioService userService;


  @RequestMapping(method = GET, value = "/user/{userId}")
  public Usuario loadById(@PathVariable Long userId) {
    return this.userService.findById(userId);
  }

  @RequestMapping(method = GET, value = "/user/all")
  public List<Usuario> loadAll() {
    return this.userService.findAll();
  }

  @RequestMapping(method = GET, value = "/user/reset-credentials")
  public ResponseEntity<Map> resetCredentials() {
    this.userService.resetCredentials();
    Map<String, String> result = new HashMap<>();
    result.put("result", "success");
    return ResponseEntity.accepted().body(result);
  }


  @RequestMapping(method = POST, value = "/signup")
  public ResponseEntity<?> addUser(@RequestBody RequisicaoDoUsuario requisicaoDoUsuario,
      UriComponentsBuilder ucBuilder) {

    Usuario usuarioExiste = this.userService.findByUsuario(requisicaoDoUsuario.getUsuario());
    if (usuarioExiste != null) {
      throw new ExcecaoDeConflitoDeRecurso(requisicaoDoUsuario.getId(),
              "Usuário Já Existe");
    }
    Usuario user = this.userService.save(requisicaoDoUsuario);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
  }

  /*
   * We are not using userService.findByUsername here(we could), so it is good that we are making
   * sure that the user has role "ROLE_USER" to access this endpoint.
   */
  @RequestMapping("/whoami")
  @PreAuthorize("hasRole('USUARIO')")
  public Usuario user() {
    return (Usuario)
            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
