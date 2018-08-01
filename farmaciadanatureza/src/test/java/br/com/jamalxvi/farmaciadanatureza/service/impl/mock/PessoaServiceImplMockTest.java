package br.com.jamalxvi.farmaciadanatureza.service.impl.mock;

import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class PessoaServiceImplMockTest {
  @Mock
  private PessoaRepository pessoaRepository;

  private Validator validator;

  private List<Pessoa> pessoas;

  @Before
  public void setUp() throws Exception {
    this.pessoas = new ArrayList<>();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    when(pessoaRepository.save(any())).then(new Answer<Pessoa>() {
      @Override
      public Pessoa answer(InvocationOnMock invocationOnMock) throws Throwable {
        return (Pessoa) invocationOnMock.getArgument(0);
      }
    });
    when(pessoaRepository.findByCpf(anyString())).thenAnswer(new Answer<Pessoa>() {
      @Override
      public Pessoa answer(InvocationOnMock invocationOnMock) throws Throwable {
        return bancoFindByCpf((String) invocationOnMock.getArgument(0));
      }
    });
    when(pessoaRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Pessoa>>() {
      @Override
      public Optional<Pessoa> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return bancoFindById((Long) invocationOnMock.getArgument(0));
      }
    });
    when(pessoaRepository.findAll()).thenReturn(bancoDePessoas());

    pessoas = new ArrayList<>();
  }

  @After
  public void tearDown() throws Exception {

  }

  public Pessoa save(Pessoa p) {
    Set<ConstraintViolation<Pessoa>> violations = validator.validate(p);
    if (!violations.isEmpty()) {
      return null;
    }
    Pessoa existeCpf = pessoaRepository.findByCpf(p.getCpf());
    if (existeCpf != null) {
      if (existeCpf.getUsuario() != null) {
        return null;
      }
      p = Pessoa.builder().usuario(p.getUsuario()).nome(p.getNome())
          .sobrenome(p.getSobrenome()).cpf(existeCpf.getCpf()).id(existeCpf.getId())
          .build();
    }
    Pessoa pessoa = pessoaRepository.save(p);

    return pessoa;
  }

  @Test
  public void save() {
    String palavrao = "";
    for (int i = 0; i < 256; i++) {
      palavrao += "a";
    }
    Pessoa pessoa = criarPessoa("233.255.678-93", "João", "da Silva");
    Pessoa erro1 = criarPessoa("23325767893", "João", "da Silva");
    Pessoa erro2 = criarPessoa("", "João", "da Silva");
    Pessoa erro3 = criarPessoa("233.257.678-93", "", "da Silva");
    Pessoa erro4 = criarPessoa("233.257.678-93", "João", "");
    Pessoa erro5 = criarPessoa("233.257.678-93", "João", null);
    Pessoa erro6 = criarPessoa(null, "João", "da Silva");
    Pessoa erro7 = criarPessoa("233.257.678-93", null, "da Silva");
    Pessoa erro8 = criarPessoa("233.257.678-93", palavrao, "da Silva");
    Pessoa erro9 = criarPessoa("233.257.678-93", "João", palavrao);
    assertTrue(pessoa != null && erro1 == null && erro2 == null && erro3 == null && erro4 == null &&
        erro5 == null && erro6 == null && erro7 == null && erro7 == null && erro8 == null && erro9 == null);
  }

  private Pessoa criarPessoa(String cpf, String nome, String sobrenome) {
    Pessoa pessoa = Pessoa.builder().cpf(cpf).nome(nome).sobrenome(sobrenome).build();
    pessoa = save(pessoa);
    return pessoa;
  }

  @Test
  public void bancoFindByCpf() {
    Pessoa pessoa = criarPessoa("233.257.678-93", "João", "da Silva");
    pessoa = findByCpf("233.257.678-93");
    pessoas.add(findByCpf(null));
    pessoas.add(findByCpf(""));
    pessoas.add(findByCpf("2325767893"));
    List<Pessoa> pessoas = filtrarErros();
    assertPessoaEErros(pessoa, pessoas);
  }

  private Pessoa findByCpf(String cpf) {
    Pessoa pessoa = pessoaRepository.findByCpf(cpf);
    return pessoa;
  }

  private void assertPessoaEErros(Pessoa pessoa, List<Pessoa> pessoas) {
    assertTrue(pessoa != null && pessoas.isEmpty());
  }

  private List<Pessoa> filtrarErros() {
    return pessoas.stream().filter(p -> p != null).collect(Collectors.toList());
  }

  @Test
  public void findAll() {
    List<Pessoa> pessoas = pessoaRepository.findAll();
    assertTrue(pessoas == null || pessoas.size() == 1);
  }

  @Test
  public void bancoFindById() {
    Pessoa pessoa = criarPessoa("233.257.678-93", "João", "da Silva");
    pessoa = findById(pessoa.getId());
    pessoas.add(findById(new Long(0)));
    pessoas.add(findById(new Long(-1)));
    pessoas.add(findById(new Long(100)));
    pessoas.add(findById(null));
    List<Pessoa> pessoas = filtrarErros();
    assertPessoaEErros(pessoa, pessoas);
  }

  private Pessoa findById(Long id) {
    Pessoa pessoa = null;
    try {
      pessoa = pessoaRepository.findById(id).orElse(null);
    } catch (Exception e) {
      return null;
    }
    return pessoa;
  }

  private Optional<Pessoa> bancoFindById(Long id) {
    return bancoDePessoas().stream().filter(pessoa -> pessoa.getId().equals(id))
        .findFirst();
  }

  private Pessoa bancoFindByCpf(String cpf) {
    return bancoDePessoas().stream().filter(pessoa -> pessoa.getCpf().equals(cpf))
        .findFirst().orElse(null);
  }

  private List<Pessoa> bancoDePessoas() {
    List<Pessoa> pessoas = new ArrayList<>();
    pessoas.add(Pessoa.builder().cpf("233.257.678-93").nome("João").sobrenome("da Silva")
        .id(new Long(1))
        .build());
    return pessoas;
  }
}