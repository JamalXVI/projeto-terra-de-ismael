package br.com.jamalxvi.farmaciadanatureza.rest;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RequisicaoDoUsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;
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


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    private UsuarioService userService;


    @RequestMapping(method = GET, value = "/user/{userId}")
    public Usuario loadById(@PathVariable Long userId) {
        return this.userService.encontrarPeloId(userId);
    }

    @RequestMapping(method = GET, value = "/user/find")
    @PreAuthorize("hasRole('USUARIO')")
    public UsuarioDto encontrarUsuario(String usuario) {
        return this.userService.encontrarPeloNomeDeUsuarioDto(usuario);
    }
    @RequestMapping(method = GET, value = "/user/all")
    @PreAuthorize("hasRole('USUARIO')")
    public List<UsuarioDto> loadAll() {
        return this.userService.listarTodos();
    }

    @RequestMapping(method = GET, value = "/user/reset-credentials")
    public ResponseEntity<Map> resetCredentials() {
        this.userService.resetCredentials();
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    @RequestMapping("/quemsou")
    @PreAuthorize("hasRole('USUARIO')")
    public RequisicaoDoUsuarioDto user() {
        Usuario usuario = (Usuario)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return RequisicaoDoUsuarioDto.builder().usuario(usuario.getUsuario())
                .cpf(usuario.getPessoa().getCpf()).nome(usuario.getPessoa().getNome())
                .id(usuario.getId()).sobrenome(usuario.getPessoa().getSobrenome()).build();
    }


    @RequestMapping(method = POST, value = "/signup")
    public ResponseEntity<?> addUser(@RequestBody RequisicaoDoUsuarioDto requisicaoDoUsuarioDto,
                                     UriComponentsBuilder ucBuilder) {

        Usuario usuarioExiste = this.userService.encontrarPeloNomeDeUsuario(requisicaoDoUsuarioDto.getUsuario());
        if (usuarioExiste != null) {
            throw new MensagemExcecao("Usuário Já Existe", requisicaoDoUsuarioDto.getId(),
                    EnumExcecaoDto.ATRIBUTO_EXISTE);
        }
        Usuario user = this.userService.salvar(requisicaoDoUsuarioDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

}
