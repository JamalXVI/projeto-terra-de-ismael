package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RequisicaoDoUsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.*;

/**
 * Implementação do Serviço de usuário
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioServiceImpl extends BaseService implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private PessoaService pessoaService;

  @Autowired
  private AutoridadeService autoridadeService;

  public void resetCredentials() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    usuarios.stream().forEach(usr -> {
      usr.setSenha(passwordEncoder.encode("123"));
      usuarioRepository.save(usr);
    });
  }

  @Override
  public Usuario encontrarPeloNomeDeUsuario(String usuario) throws UsernameNotFoundException {
    Optional<Usuario> u = usuarioRepository.findByUsuario(usuario);
    return u.filter(usr -> usr.getAtivo())
        .orElseThrow(() -> new MensagemExcecao(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem(),
            EnumExcecaoDto.ATRIBUTOS_VAZIOS_OU_NAO_ENCONTRADO));
  }

  @Override
  public UsuarioDto encontrarPeloNomeDeUsuarioDto(String usuario) {
    Optional<Usuario> u = usuarioRepository.findByUsuario(usuario);
    return u.filter(usr -> usr.getAtivo())
        .map(usr -> UsuarioDto.builder().id(usr.getId()).usuario(usr.getUsuario())
            .nome(usr.getPessoa().getNome()).sobrenome(usr.getPessoa().getSobrenome()).build())
        .orElseThrow(() -> new MensagemExcecao(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem(),
            EnumExcecaoDto.ATRIBUTOS_VAZIOS_OU_NAO_ENCONTRADO));
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Usuario encontrarPeloId(Long id) throws AccessDeniedException {
    Usuario u = usuarioRepository.findById(id).orElse(null);
    if (u == null || u.getAtivo() == null || !u.getAtivo()) {
      throw new MensagemExcecao(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem(),
          EnumExcecaoDto.NAO_ENCONTRADO);
    }
    return u;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<UsuarioDto> listarTodos() throws AccessDeniedException {
    List<Usuario> result = usuarioRepository.findAll();
    List<UsuarioDto> ftr = result.stream().filter(u -> u.getAtivo())
        .map(usuario -> UsuarioDto.builder().nome(usuario.getPessoa().getNome())
            .sobrenome(usuario.getPessoa().getSobrenome()).usuario(usuario.getUsuario())
            .id(usuario.getId()).build())
        .collect(Collectors.toList());
    return ftr;
  }

  @Override
  public Usuario salvar(RequisicaoDoUsuarioDto requisicaoDoUsuarioDto) {
    Usuario usuario = Usuario.builder().usuario(requisicaoDoUsuarioDto.getUsuario())
        .senha(requisicaoDoUsuarioDto.getSenha()).build();
    Set<ConstraintViolation<Usuario>> validate = validator.validate(usuario);
    Optional<Usuario> jaTemUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
    if (!validate.isEmpty() || jaTemUsuario.isPresent()) {
      throw new MensagemExcecao(ERRO_INSERIR_PESSOA.getMensagem(), EnumExcecaoDto.SALVAR);
    }
    Pessoa pessoa =
        Pessoa.builder().cpf(requisicaoDoUsuarioDto.getCpf()).nome(requisicaoDoUsuarioDto.getNome())
            .sobrenome(requisicaoDoUsuarioDto.getSobrenome()).build();
    pessoa = pessoaService.salvar(pessoa);
    if (pessoa == null) {
      throw new MensagemExcecao(ERRO_SALVAR_PESSOA.getMensagem(), EnumExcecaoDto.SALVAR);
    }
    usuario.setPessoa(pessoa);
    List<Autoridade> auth =
        autoridadeService.encontrarPelaAutorizacao(EnumAutorizacaoUsuario.ROLE_USUARIO.name());
    usuario.setAutoridades(auth);
    usuario.setAtivo(true);
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    usuario = this.usuarioRepository.save(usuario);
    pessoa.setUsuario(usuario);
    pessoaService.salvar(pessoa);
    return usuario;
  }

}
