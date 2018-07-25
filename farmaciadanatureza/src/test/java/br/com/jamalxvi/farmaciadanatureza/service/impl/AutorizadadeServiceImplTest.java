package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.FarmaciadanaturezaApplication;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumAutorizacaoUsuario;
import br.com.jamalxvi.farmaciadanatureza.models.Autoridade;
import br.com.jamalxvi.farmaciadanatureza.repository.AutoridadeRepository;
import br.com.jamalxvi.farmaciadanatureza.service.AutoridadeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FarmaciadanaturezaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutorizadadeServiceImplTest {

    @Autowired
    private AutoridadeService autoridadeService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveByRole() {
        Autoridade usuario = autoridadeService.save(EnumAutorizacaoUsuario.ROLE_USUARIO);
        Autoridade admin = autoridadeService.save(EnumAutorizacaoUsuario.ROLE_ADMIN);
        Autoridade jose = autoridadeService.save((EnumAutorizacaoUsuario) null);
        assertTrue(usuario != null && admin != null && jose == null);
    }

    @Test
    public void saveByName() {
        Autoridade admin = autoridadeService.save("ROLE_ADMIN");
        Autoridade usuario = autoridadeService.save("ROLE_USUARIO");
        Autoridade jose = autoridadeService.save("ROLE_JOSE");
        assertTrue(usuario != null && admin != null && jose == null);
    }

    @Test
    public void findById() {
        List<Autoridade> admin = this.autoridadeService.findById(new Long(1));
        List<Autoridade> usuario = this.autoridadeService.findById(new Long(2));

    }

    @Test
    public void findByAutorizacao() {
        List<Autoridade> role_admin = this.autoridadeService.findByAutorizacao("ROLE_ADMIN");
        List<Autoridade> role_usuario = this.autoridadeService.findByAutorizacao("ROLE_USUARIO");
        List<Autoridade> role_jose = this.autoridadeService.findByAutorizacao("ROLE_JOSE");
        List<Autoridade> admin = this.autoridadeService.findByAutorizacao(EnumAutorizacaoUsuario.ROLE_ADMIN);
        List<Autoridade> usuario = this.autoridadeService.findByAutorizacao(EnumAutorizacaoUsuario.ROLE_USUARIO);
        List<Autoridade> jose = this.autoridadeService.findByAutorizacao((EnumAutorizacaoUsuario) null);
        assertTrue(!admin.isEmpty() && !role_usuario.isEmpty() && jose.isEmpty()
        && !role_admin.isEmpty() && role_jose.isEmpty() && !usuario.isEmpty() && role_jose.isEmpty());
    }
}