package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RequisicaoDoUsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsuarioServiceImplTest {

  private Validator validator;
  List<Usuario> usuarios;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private PessoaService pessoaService;
  @Mock
  private AutoridadeService autoridadeService;
  @Mock
  private PasswordEncoder passwordEncoder;

  @Before
  public void setUp() throws Exception {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    usuarios = new ArrayList<>();
    when(usuarioRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Usuario>>() {
      @Override
      public Optional<Usuario> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return pesquisarUsuarioBanco((Long) invocationOnMock.getArguments()[0]);
      }
    });
    when(usuarioRepository.findByUsuario(anyString())).thenAnswer(new Answer<Usuario>() {
      @Override
      public Usuario answer(InvocationOnMock invocationOnMock) throws Throwable {
        return pesquisarUsuarioBanco((String) invocationOnMock.getArguments()[0]);
      }
    });
    when(usuarioRepository.findAll()).thenReturn(bancoDeUsuarios());
    when(usuarioRepository.save(any())).thenAnswer(new Answer<Usuario>() {
      @Override
      public Usuario answer(InvocationOnMock invocationOnMock) throws Throwable {
        return (Usuario) invocationOnMock.getArguments()[0];
      }
    });
    when(pessoaService.save(any())).thenAnswer(new Answer<Pessoa>() {
      @Override
      public Pessoa answer(InvocationOnMock invocationOnMock) throws Throwable {
        return (Pessoa) invocationOnMock.getArguments()[0];
      }
    });
    when(this.autoridadeService.findByAutorizacao((EnumAutorizacaoUsuario) any()))
        .thenAnswer(new Answer<Autoridade>() {
          @Override
          public Autoridade answer(InvocationOnMock invocationOnMock) throws Throwable {
            return Autoridade.builder()
                .autorizacao((EnumAutorizacaoUsuario) invocationOnMock.getArguments()[0])
                .id(new Long(1)).build();
          }
        });
    when(this.passwordEncoder.encode(any()))
        .thenReturn("$2a$04$UIeR96XNyDwQHJE4G2DWquYMZmopzZ0z5o5f1bc2KA.YgFhGU7/aW");
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void resetCredentials() {
  }

  @Test
  public void findByUsuario() {
    List<Usuario> usrs = bancoDeUsuarios();
    usrs.parallelStream().forEach(usr -> {
      usuarios.add(acharUsuario(usr.getUsuario()));
    });
    usuarios =this.filtrarErros();
    assert (usuarios.size() == 1);
  }
  public Usuario acharUsuario(String usuario) throws UsernameNotFoundException {
    Usuario u = usuarioRepository.findByUsuario(usuario);
    if (u != null && !u.getAtivo()) {
      return null;
    }
    return u;
  }
  @Test
  public void findById() {
    List<Usuario> usrs = bancoDeUsuarios();
    usrs.parallelStream().forEach(usr -> {
      usuarios.add(encontrarUsuarioId(usr.getId()));
    });
    usuarios = this.filtrarErros();
    assert (usuarios.size() == 1);
  }

  private Usuario encontrarUsuarioId(Long id) {
    Usuario u = usuarioRepository.findById(id).orElse(null);
    if (u != null && !u.getAtivo()) {
      return null;
    }
    return u;
  }

  @Test
  public void findAll() {
    List<Usuario> result = usuarioRepository.findAll();
    List<Usuario> ftr = result.stream().filter(u -> u.getAtivo()).collect(Collectors.toList());
    assert(!ftr.isEmpty());
  }

  @Test
  public void save() {
    List<RequisicaoDoUsuarioDto> requisicaoDoUsuarioDtos = bancoDeRequisicaoUsuarios();
    requisicaoDoUsuarioDtos.parallelStream().forEach(usr -> {
      this.usuarios.add(salvarUsuario(usr));
    });
    this.usuarios = filtrarErros();
    assert (this.usuarios.size() == 1);
  }

  private Usuario salvarUsuario(RequisicaoDoUsuarioDto usr) {
    Usuario usuario = Usuario.builder().usuario(usr.getUsuario())
        .senha(usr.getSenha()).build();
    Set<ConstraintViolation<Usuario>> validate = validator.validate(usuario);
    Usuario jaTemUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
    if (!validate.isEmpty() || jaTemUsuario != null) {
      return null;
    }
    Pessoa pessoa = Pessoa.builder().cpf(usr.getCpf())
        .nome(usr.getNome()).sobrenome(usr.getSobrenome())
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

  private Optional<Usuario> pesquisarUsuarioBanco(Long id) {
    return bancoDeUsuarios().stream().filter(usr -> usr.getId().equals(id.longValue()))
        .findFirst();
  }

  private Usuario pesquisarUsuarioBanco(String usuario) {
    return bancoDeUsuarios().stream().filter(usr -> usr.getUsuario().equals(usuario))
        .findFirst().orElse(null);
  }

  private List<RequisicaoDoUsuarioDto> bancoDeRequisicaoUsuarios() {
    List<RequisicaoDoUsuarioDto> usuarios = new ArrayList<>();
    usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João", "da Silva",
        "123.456.789-25", "jsilva", "123456"));
    usuarios.add(criarRequisicaoDoUsuario(new Long(2),
        "Joana", "da Silva", "465.456.789-25", "josilva",
        "123456"));
    usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João",
        "da Silva", "123.456.789-93", "jsilva", "12"));
    usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João",
        "da Silva", "123.456.789-93", "jsilva", ""));
    usuarios.add(criarRequisicaoDoUsuario(new Long(3), "João",
        "da Silva", "123.456.789-93", "abc", ""));
    return usuarios;
  }

  private List<Usuario> bancoDeUsuarios() {
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(retornarUsuarioMockado(new Long(1), "João", "da Silva",
        "123.456.789-25", "jsilva", "123456", true));
    usuarios.add(retornarUsuarioMockado(new Long(4),
        "Guilhermina", "da Silva", "654.123.987-85", "guiSilva",
        "123456", false));
    return usuarios;
  }

  private List<Usuario> filtrarErros() {
    return usuarios.stream().filter(p -> p != null).collect(Collectors.toList());
  }

  private RequisicaoDoUsuarioDto criarRequisicaoDoUsuario(Long id, String nome, String sobrenome, String cpf,
                                                          String usuario, String senha) {
    return RequisicaoDoUsuarioDto.builder().id(id).nome(nome).sobrenome(sobrenome).cpf(cpf)
        .usuario(usuario).senha(senha).build();
  }

  private Usuario retornarUsuarioMockado(Long id, String nome, String sobrenome, String cpf, String usr,
                                         String senha, Boolean ativo) {
    Pessoa pessoa = criarPessoa(id, nome, sobrenome, cpf);
    Usuario usuario = Usuario.builder().id(id).usuario(usr).ativo(ativo)
        .senha(senha).pessoa(pessoa).build();
    return usuario;
  }

  private Pessoa criarPessoa(Long id, String nome, String sobrenome, String cpf) {
    Pessoa pessoa = Pessoa.builder().nome(nome).sobrenome(sobrenome).cpf(cpf).build();
    pessoa.setId(id);
    return pessoa;
  }
}