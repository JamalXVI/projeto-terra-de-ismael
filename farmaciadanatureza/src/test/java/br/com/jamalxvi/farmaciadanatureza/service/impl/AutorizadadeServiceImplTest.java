package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.repository.AutoridadeRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_BUSCAR_AUTORIDADE;
import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_IMPOSSIVEL_SALVAR_AUTORIDADE;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class AutorizadadeServiceImplTest extends BaseTest {

    private Validator validator;
    @Mock
    private AutoridadeRepository authRepository;

    private AutoridadeService autoridadeService;

    @Before
    public void setUp() throws Exception {
        autoridadeService = new AutorizadadeServiceImpl(authRepository);
        when(authRepository.findAll()).thenReturn(bancoDeAutoridade());
        when(authRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Autoridade>>() {
            @Override
            public Optional<Autoridade> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return bancoFindById((Long) invocationOnMock.getArgument(0));
            }
        });
        when(authRepository.findByAutorizacao(any())).thenAnswer(new Answer<Autoridade>() {
            @Override
            public Autoridade answer(InvocationOnMock invocationOnMock) throws Throwable {
                return bancoFindByAutoridade(invocationOnMock.getArgument(0));
            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }


    //region salvar
    @Test
    public void salvarPeloNome() {
        Autoridade admin = this.autoridadeService.salvar("ROLE_ADMIN");
        Autoridade usr = this.autoridadeService.salvar("ROLE_USUARIO");
        assertTrue("Autoridade Admin deve Existir", admin != null &&
                admin.getAutorizacao() == EnumAutorizacaoUsuario.ROLE_ADMIN);
        assertTrue("Autoridade Usuário deve Existir", usr != null &&
                usr.getAutorizacao() == EnumAutorizacaoUsuario.ROLE_USUARIO);

    }

    @Test
    public void salvarPeloNomeFalha() {
        this.esperarErroGenerico(ERRO_IMPOSSIVEL_SALVAR_AUTORIDADE.getMensagem());
        Autoridade jose = this.autoridadeService.salvar("ROLE_JOSE");
    }

    ;


    @Test
    public void salvarPeloEnum() {
        Autoridade admin = this.autoridadeService.salvar(EnumAutorizacaoUsuario.ROLE_ADMIN);
        assertTrue("Autoridade Admin deve Existir", admin != null &&
                admin.getAutorizacao() == EnumAutorizacaoUsuario.ROLE_ADMIN);

    }
    //end

    //region encontrarPeloId
    @Test
    public void encontrarPeloId() {
        List<Autoridade> admin = this.autoridadeService.encontrarPeloId(1L);
        List<Autoridade> usr = this.autoridadeService.encontrarPeloId(2L);
        assertTrue("Autoridade Admin deve Existir", admin != null &&
                admin.size() == 1);
        assertTrue("Autoridade Usuário deve Existir", usr != null &&
                usr.size() == 1);
    }

    @Test
    public void encontrarPeloIdFalha() {
        this.esperarErroGenerico(ERRO_BUSCAR_AUTORIDADE.getMensagem());
        List<Autoridade> admin = this.autoridadeService.encontrarPeloId(99L);
    }
    //end

    //region encontrarPelaAutorizacao
    @Test
    public void encontrarPelaAutorizacao() {
        List<Autoridade> admin = this.autoridadeService.encontrarPelaAutorizacao("ROLE_ADMIN");
        List<Autoridade> usr = this.autoridadeService.encontrarPelaAutorizacao("ROLE_USUARIO");
        assertTrue("Autoridade Admin deve Existir", admin != null &&
                admin.size() == 1);
        assertTrue("Autoridade Usuário deve Existir", usr != null &&
                usr.size() == 1);
    }

    @Test
    public void encontrarPelaAutorizacaoVazio() {
        List<Autoridade> res = this.autoridadeService.encontrarPelaAutorizacao("");
        assertTrue("Resultado deve existir e deve estar vazio",
                res != null && res.size() == 0);
    }
    //end

    private Optional<Autoridade> bancoFindById(Long id) {
        return bancoDeAutoridade().stream().filter(autoridade -> autoridade.getId().equals(id))
                .findFirst();
    }

    private Autoridade bancoFindByAutoridade(EnumAutorizacaoUsuario autorizacaoUsuario) {
        return bancoDeAutoridade().stream()
                .filter(autoridade -> autoridade.getAutorizacao().equals(autorizacaoUsuario))
                .findFirst().orElse(null);
    }

    private List<Autoridade> bancoDeAutoridade() {
        List<Autoridade> autoridades = new ArrayList<>();
        autoridades.add(Autoridade.builder().id(new Long(1))
                .autorizacao(EnumAutorizacaoUsuario.ROLE_ADMIN).build());
        autoridades.add(Autoridade.builder().id(new Long(2))
                .autorizacao(EnumAutorizacaoUsuario.ROLE_USUARIO).build());
        return autoridades;
    }

}