package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementação do Serviço de usuário
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */

@Service
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
  @PreAuthorize("hasRole('USER')")
  public Usuario findByUsuario(String usuario) throws UsernameNotFoundException {
    Usuario u = usuarioRepository.findByUsuario(usuario);
    if (u != null && !u.getAtivo()) {
      return null;
    }
    return u;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public Usuario findById(Long id) throws AccessDeniedException {
    Usuario u = usuarioRepository.findById(id).orElse(null);
    if (u != null && !u.getAtivo()) {
      return null;
    }
    return u;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<UsuarioDto> findAll() throws AccessDeniedException {
    List<Usuario> result = usuarioRepository.findAll();
    List<UsuarioDto> ftr = result.stream().filter(u -> u.getAtivo())
        .map(usuario -> UsuarioDto.builder().nome(usuario.getPessoa().getNome())
            .sobrenome(usuario.getPessoa().getSobrenome())
            .usuario(usuario.getUsuario()).id(usuario.getId()).build())
        .collect(Collectors.toList());
    return ftr;
  }

  @Override
  public Usuario save(RequisicaoDoUsuario requisicaoDoUsuario) {
    Usuario usuario = Usuario.builder().usuario(requisicaoDoUsuario.getUsuario())
        .senha(requisicaoDoUsuario.getSenha()).build();
    Set<ConstraintViolation<Usuario>> validate = validator.validate(usuario);
    Usuario jaTemUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
    if (!validate.isEmpty() || jaTemUsuario != null) {
      return null;
    }
    Pessoa pessoa = Pessoa.builder().cpf(requisicaoDoUsuario.getCpf())
        .nome(requisicaoDoUsuario.getNome()).sobrenome(requisicaoDoUsuario.getSobrenome())
        .build();
    pessoa = pessoaService.save(pessoa);
    if (pessoa == null) {
      return null;
    }
    usuario.setPessoa(pessoa);
    List<Autoridade> auth = autoridadeService.findByAutorizacao(
        EnumAutorizacaoUsuario.ROLE_USUARIO.name());
    usuario.setAutoridades(auth);
    usuario.setAtivo(true);
    usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
    usuario = this.usuarioRepository.save(usuario);
    pessoa.setUsuario(usuario);
    pessoaService.save(pessoa);
    return usuario;
  }

}
