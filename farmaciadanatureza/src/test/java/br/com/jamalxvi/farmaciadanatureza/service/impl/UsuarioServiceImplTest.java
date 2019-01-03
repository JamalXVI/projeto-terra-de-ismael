package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RequisicaoDoUsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_INSERIR_USUARIO;
import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_USUARIO_NAO_ENCONTRADO;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsuarioServiceImplTest extends BaseTest {

    List<Usuario> usuarios;
    List<Usuario> bancoUsuarios;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PessoaService pessoaService;
    @Mock
    private AutoridadeService autoridadeService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioService usuarioService;

    @Before
    public void setUp() throws Exception {
        this.usuarioService = new UsuarioServiceImpl(usuarioRepository, passwordEncoder,
                pessoaService, autoridadeService);
        this.bancoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
        when(usuarioRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Usuario>>() {
            @Override
            public Optional<Usuario> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return pesquisarUsuarioBanco((Long) invocationOnMock.getArguments()[0]);
            }
        });
        when(usuarioRepository.findByUsuario(anyString())).thenAnswer(new Answer<Optional<Usuario>>() {
            @Override
            public Optional<Usuario> answer(InvocationOnMock invocationOnMock) throws Throwable {
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
        when(pessoaService.salvar(any())).thenAnswer(new Answer<Pessoa>() {
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

    // region encontrarPeloNomeDeUsuario
    @Test
    public void encontrarPeloNomeDeUsuarioDto() {
        UsuarioDto usuario = usuarioService.encontrarPeloNomeDeUsuarioDto("jsilva");
        assertTrue("Usuario não é nulo", usuario != null);
        assertTrue("Usuário possuí o mesmo nome de usuário",
                usuario.getUsuario().equals("jsilva"));
        assertTrue("Usuário possuí o mesmo id",
                usuario.getId().equals(1L));
    }
    @Test
    public void encontrarPeloNomeDeUsuarioDtoFalha() {
        this.esperarErroGenerico(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem());
        UsuarioDto usuario = usuarioService.encontrarPeloNomeDeUsuarioDto("inexistente");
    }
    //


    // region encontrarUsuarioId
    @Test
    public void encontrarPeloNomeDeUsuario() {
        Usuario usuario = usuarioService.encontrarPeloNomeDeUsuario("jsilva");
        assertTrue("Usuario não é nulo", usuario != null);
        assertTrue("Usuário possuí o mesmo nome de usuário",
                usuario.getUsername().equals("jsilva"));
        assertTrue("Usuário possuí o mesmo id",
                usuario.getId().equals(1L));
        assertTrue("Usuário e a Pessoa estão ativos",
                usuario.getAtivo() && usuario.getPessoa().getAtivo());
    }
    @Test
    public void encontrarPeloNomeDeUsuarioFalha() {
        this.esperarErroGenerico(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem());
        Usuario usuario = usuarioService.encontrarPeloNomeDeUsuario("inexistente");
    }
    //end

    // region encontrarUsuarioId
    @Test
    public void encontrarUsuarioId() {
        Usuario usuario = this.usuarioService.encontrarPeloId(1L);
        assertTrue("Usuário não é nulo", usuario != null);
        assertTrue("Usuário é igual ao encontrado no banco", usuario.equals(this.bancoUsuarios
                .get(0)));
    }
    @Test
    public void usuarioNaoEncontrado() {
        this.esperarErroGenerico(ERRO_USUARIO_NAO_ENCONTRADO.getMensagem());
        Usuario usuario = this.usuarioService.encontrarPeloId(99L);
    }
    //end

    // region listarTodos
    @Test
    public void listarTodos() {
        List<UsuarioDto> result = usuarioService.listarTodos();
        assertTrue("O resultado não deve ser nulo", result != null);
        assertTrue("A lista deve conter elementos", result.size() == 1);
    }

    @Test
    public void listarTodosVazio() {
        when(this.usuarioRepository.findAll()).thenReturn(new ArrayList<>());
        List<UsuarioDto> result = usuarioService.listarTodos();
        assertTrue("O resultado não deve ser nulo", result != null);
        assertTrue("A lista não deve conter elementos", result.size() == 0);
    }
    //end

    // region salvar
    @Test
    public void salvarFalhaValidacoes() {
        List<RequisicaoDoUsuarioDto> requisicaoDoUsuarioDtos = bancoDeRequisicaoUsuarios();
        this.esperarErroGenerico(ERRO_INSERIR_USUARIO.getMensagem());
        requisicaoDoUsuarioDtos.parallelStream().forEach(usr -> {
            this.usuarios.add(this.usuarioService.salvar(usr));
        });
    }

    @Test
    public void salvar() {
        RequisicaoDoUsuarioDto usuario = criarRequisicaoDoUsuario(new Long(2),
                "Joana", "da Silva", "465.456.789-25", "josilva",
                "123456");
        Usuario us = this.usuarioService.salvar(usuario);
        assertTrue("Usuário deve existir", us != null);
        assertTrue("Usuário deve ter o mesmo usuário", usuario.getUsuario().equals(us
                .getUsuario()));
        assertTrue("Usuário deve ter a mesma senha", passwordEncoder.encode(usuario.getSenha())
                .equals(us.getSenha()));
        assertTrue("Usuário deve ter o mesmo cpf", usuario.getCpf().equals(us
                .getPessoa().getCpf()));
        assertTrue("Usuário deve ter o mesmo nome", usuario.getNome().equals(us
                .getPessoa().getNome()));
        assertTrue("Usuário deve ter o mesmo sobrenome", usuario.getSobrenome().equals(us
                .getPessoa().getSobrenome()));
    }
    //end

    private Optional<Usuario> pesquisarUsuarioBanco(Long id) {
        return bancoDeUsuarios().stream().filter(usr -> usr.getId().equals(id.longValue()))
                .findFirst();
    }

    private Optional<Usuario> pesquisarUsuarioBanco(String usuario) {
        return bancoDeUsuarios().stream().filter(usr -> usr.getUsuario().equals(usuario))
                .findFirst();
    }

    private List<RequisicaoDoUsuarioDto> bancoDeRequisicaoUsuarios() {
        List<RequisicaoDoUsuarioDto> usuarios = new ArrayList<>();
        usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João", "da Silva",
                "123.456.789-25", "jsilva", "123456"));
        usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João",
                "da Silva", "123.456.789-93", "jsilva", "12"));
        usuarios.add(criarRequisicaoDoUsuario(new Long(1), "João",
                "da Silva", "123.456.789-93", "jsilva", ""));
        usuarios.add(criarRequisicaoDoUsuario(new Long(3), "João",
                "da Silva", "123.456.789-93", "abc", ""));
        return usuarios;
    }

    private List<Usuario> bancoDeUsuarios() {
        this.bancoUsuarios.add(retornarUsuarioMockado(new Long(1), "João", "da Silva",
                "123.456.789-25", "jsilva", "123456", true));
        bancoUsuarios.add(retornarUsuarioMockado(new Long(4),
                "Guilhermina", "da Silva", "654.123.987-85", "guiSilva",
                "123456", false));
        return bancoUsuarios;
    }

    private RequisicaoDoUsuarioDto criarRequisicaoDoUsuario(Long id, String nome, String sobrenome, String cpf,
                                                            String usuario, String senha) {
        return RequisicaoDoUsuarioDto.builder().id(id).nome(nome).sobrenome(sobrenome).cpf(cpf)
                .usuario(usuario).senha(senha).build();
    }

    private Usuario retornarUsuarioMockado(Long id, String nome, String sobrenome, String cpf, String usr,
                                           String senha, Boolean ativo) {
        Pessoa pessoa = Pessoa.builder().nome(nome).sobrenome(sobrenome).cpf(cpf).id(id).ativo(ativo)
                .build();
        Usuario usuario = Usuario.builder().id(id).usuario(usr).ativo(ativo)
                .senha(senha).pessoa(pessoa).build();
        return usuario;
    }
}