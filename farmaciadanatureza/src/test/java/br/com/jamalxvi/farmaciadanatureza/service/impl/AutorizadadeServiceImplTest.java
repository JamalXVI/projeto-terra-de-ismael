package br.com.jamalxvi.farmaciadanatureza.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class AutorizadadeServiceImplTest {

    private Validator validator;
    @Mock
    private AutoridadeRepository authRepository;

    @Before
    public void setUp() throws Exception {
        when(authRepository.findAll()).thenReturn(bancoDeAutoridade());
        when(authRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Autoridade>>() {
          @Override
          public Optional<Autoridade> answer(InvocationOnMock invocationOnMock) throws Throwable {
            return bancoFindById((Long)invocationOnMock.getArgument(0));
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


    @Test
    public void saveByRole() {
        Autoridade usuario = save(EnumAutorizacaoUsuario.ROLE_USUARIO);
        Autoridade admin = save(EnumAutorizacaoUsuario.ROLE_ADMIN);
        Autoridade jose = save((EnumAutorizacaoUsuario) null);
        assertTrue(usuario != null && admin != null && jose == null);
    }

    @Test
    public void saveByName() {
        Autoridade admin = save("ROLE_ADMIN");
        Autoridade usuario = save("ROLE_USUARIO");
        Autoridade jose = save("ROLE_JOSE");
        assertTrue(usuario != null && admin != null && jose == null);
    }

    @Test
    public void findById() {
        List<Autoridade> admin = findById(new Long(1));
        List<Autoridade> usuario = findById(new Long(2));

    }

    @Test
    public void findByAutorizacao() {
        List<Autoridade> role_admin = findByAutorizacao("ROLE_ADMIN");
        List<Autoridade> role_usuario = findByAutorizacao("ROLE_USUARIO");
        List<Autoridade> role_jose = findByAutorizacao("ROLE_JOSE");
        List<Autoridade> admin = findByAutorizacao(EnumAutorizacaoUsuario.ROLE_ADMIN);
        List<Autoridade> usuario = findByAutorizacao(EnumAutorizacaoUsuario.ROLE_USUARIO);
        List<Autoridade> jose = findByAutorizacao((EnumAutorizacaoUsuario) null);
        assertTrue(!admin.isEmpty() && !role_usuario.isEmpty() && jose.isEmpty()
        && !role_admin.isEmpty() && role_jose.isEmpty() && !usuario.isEmpty() && role_jose.isEmpty());
    }
    private Optional<Autoridade> bancoFindById(Long id){
      return bancoDeAutoridade().stream().filter(autoridade -> autoridade.getId().equals(id))
          .findFirst();
    }
    private Autoridade bancoFindByAutoridade(EnumAutorizacaoUsuario autorizacaoUsuario){
      return bancoDeAutoridade().stream()
          .filter(autoridade -> autoridade.getAutorizacao().equals(autorizacaoUsuario))
          .findFirst().orElse(null);
    }
    private List<Autoridade> bancoDeAutoridade(){
      List<Autoridade> autoridades = new ArrayList<>();
      autoridades.add(Autoridade.builder().id(new Long(1))
          .autorizacao(EnumAutorizacaoUsuario.ROLE_ADMIN).build());
      autoridades.add(Autoridade.builder().id(new Long(2))
          .autorizacao(EnumAutorizacaoUsuario.ROLE_USUARIO).build());
      return autoridades;
    }
    public Autoridade save(String autorizacao) {

      EnumAutorizacaoUsuario enumRole = EnumAutorizacaoUsuario.getEnum(autorizacao);
      return criarAutoridade(enumRole);
    }
  private Autoridade criarAutoridade(EnumAutorizacaoUsuario role) {
    if(role != null){
      Autoridade autoridade = Autoridade.builder().autorizacao(role).build();
      Autoridade encontrada = getAutoridade(autoridade);
      if(encontrada == null){
        authRepository.save(autoridade);
      }else{
        autoridade = encontrada;
      }
      return autoridade;
    }else{
      return null;
    }
  }

  private Autoridade getAutoridade(Autoridade autoridade) {
    return authRepository.findByAutorizacao(
        autoridade.getAutorizacao());
  }
  public List<Autoridade> findByAutorizacao(EnumAutorizacaoUsuario autorizacao) {
    return getAutoridades(autorizacao);
  }

  private List<Autoridade> getAutoridades(EnumAutorizacaoUsuario enumAutorizacaoUsuario) {
    List<Autoridade> auths = new ArrayList<>();
    if(enumAutorizacaoUsuario != null){
      Autoridade auth = this.authRepository.findByAutorizacao(enumAutorizacaoUsuario);
      if(auth == null){
        auth = Autoridade.builder().autorizacao(enumAutorizacaoUsuario).build();
        this.authRepository.save(auth);
      }
      auths.add(auth);
    }
    return auths;
  }
  public List<Autoridade> findByAutorizacao(String autorizacao) {
    EnumAutorizacaoUsuario enumAutorizacaoUsuario = EnumAutorizacaoUsuario.getEnum(autorizacao);
    return getAutoridades(enumAutorizacaoUsuario);
  }
  public List<Autoridade> findById(Long id) {
    Autoridade autoridade = this.authRepository.findById(id).orElse(null);
    List<Autoridade> autoridades = new ArrayList<>();
    autoridades.add(autoridade);
    return autoridades;
  }
  public Autoridade save(EnumAutorizacaoUsuario autorizacaoUsuario) {
    return criarAutoridade(autorizacaoUsuario);
  }
}