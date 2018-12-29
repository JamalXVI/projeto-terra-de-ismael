package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.FarmaciadanaturezaApplication;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.RequisicaoDoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Usuario;
import br.com.jamalxvi.farmaciadanatureza.models.dto.UsuarioDto;
import br.com.jamalxvi.farmaciadanatureza.repository.UsuarioRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import br.com.jamalxvi.farmaciadanatureza.service.UsuarioService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FarmaciadanaturezaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceImplTest {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    private List<Usuario> erros;

    @Before
    public void setUp() throws Exception {
        erros = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        for (Usuario u : usuarioRepository.findAll()) {
            usuarioRepository.delete(u);
        }
    }

    @Test
    public void resetCredentials() {
    }

    @Test
    public void findByUsuario() {
        Usuario usuario = retornaUsuario("João", "jsilva", "senha",
                "123.456.789-01", "da Silva");
        Usuario achou = this.usuarioService.findByUsuario(usuario.getUsuario());
        assert (achou != null);
        achou.setAtivo(false);
        this.usuarioRepository.save(achou);
        Usuario naoAchou = this.usuarioService.findByUsuario(usuario.getUsuario());
        assert (naoAchou == null);
    }

    @Test
    public void findById() {
        Usuario usuario = retornaUsuario("João", "jsilva", "senha",
                "123.456.789-01", "da Silva");
        Usuario achou = this.usuarioService.findById(usuario.getId());
        assert (achou != null);
        achou.setAtivo(false);
        this.usuarioRepository.save(achou);
        Usuario naoAchou = this.usuarioService.findById(achou.getId());
        assert (naoAchou == null);
    }

    @Test
    public void findAll() {
        Usuario usuario = retornaUsuario("João", "jsilva", "senha",
                "123.456.789-01", "da Silva");
        List<UsuarioDto> usuarios = this.usuarioService.findAll();
        assert (usuarios.size() > 0);
        usuario.setAtivo(false);
        this.usuarioRepository.save(usuario);
        List<UsuarioDto> erros = this.usuarioService.findAll();
        assert (erros.isEmpty());
        this.usuarioRepository.delete(usuario);
        List<UsuarioDto> semUsuarios = this.usuarioService.findAll();
        assert ( semUsuarios.isEmpty());
    }

    @Test
    public void save() {
        String cpfAlt = "789.456.987-85";
        Usuario usuario = retornaUsuario("João", "jsilva", "senha",
                "123.456.789-01", "da Silva");
        Pessoa joana = Pessoa.builder().nome("Joana").sobrenome("Gomes da Costa")
                .cpf("456.789.123-01").build();
        joana = this.pessoaService.save(joana);
        Usuario jgomes = retornaUsuario(joana.getNome(), "jgomes", "123456", joana.getCpf(),
                joana.getSobrenome());
        this.erros.add(retornaUsuario(joana.getNome(), jgomes.getUsuario(), jgomes.getSenha(), joana.getCpf(),
                joana.getSobrenome()));
        this.erros.add(retornaUsuario(joana.getNome(), "abochi", jgomes.getSenha(),
                joana.getCpf(), joana.getSobrenome()));
        this.erros.add(retornaUsuario("", "mbochi", jgomes.getSenha(),
                cpfAlt, joana.getSobrenome()));
        this.erros.add(retornaUsuario(joana.getNome(), "nbochi", jgomes.getSenha(),
                cpfAlt, ""));
        this.erros.add(retornaUsuario(joana.getNome(), "jbochi", jgomes.getSenha(),
                "", joana.getSobrenome()));
        this.erros.add(retornaUsuario(joana.getNome(), "kbochi", "",
                cpfAlt, joana.getSobrenome()));
        this.erros.add(retornaUsuario(joana.getNome(), "lbochi", "12",
                cpfAlt, joana.getSobrenome()));
        List<Usuario> usuarios = filtrarErros();
        assert (usuario != null && jgomes != null && joana != null &&
                jgomes.getPessoa().getId() == joana.getId() && usuarios.isEmpty());
    }

    private Usuario retornaUsuario(String nome, String usuario, String senha, String cpf,
                                   String sobrenome) {
        RequisicaoDoUsuario requisicaoDoUsuario = RequisicaoDoUsuario.builder().nome(nome)
                .usuario(usuario).cpf(cpf).senha(senha).sobrenome(sobrenome).build();
        Usuario usr = usuarioService.save(requisicaoDoUsuario);
        return usr;
    }

    private List<Usuario> filtrarErros() {
        return erros.stream().filter(p -> p != null).collect(Collectors.toList());
    }
}