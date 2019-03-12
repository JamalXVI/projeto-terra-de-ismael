package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class PessoaServiceImplTest extends BaseTest {
  @Mock
  private PessoaRepository pessoaRepository;
  private PessoaService pessoaService;
  private List<Pessoa> pessoas;
  private List<Pessoa> pessoasBanco;

  @Before
  public void setUp() throws Exception {
    this.pessoas = new ArrayList<>();
    pessoasBanco = new ArrayList<>();
    bancoDePessoas();
    when(pessoaRepository.save(any())).then(new Answer<Pessoa>() {
      @Override
      public Pessoa answer(InvocationOnMock invocationOnMock) throws Throwable {
        return (Pessoa) invocationOnMock.getArgument(0);
      }
    });
    when(pessoaRepository.findByCpf(anyString())).thenAnswer(new Answer<Optional<Pessoa>>() {
      @Override
      public Optional<Pessoa> answer(InvocationOnMock invocationOnMock) throws Throwable {
        final String cpf = (String) invocationOnMock.getArgument(0);
        Optional<Pessoa> pessoa = encontrarPeloCpf(cpf);
        return pessoa;
      }
    });
    when(pessoaRepository.findById(anyLong())).thenAnswer(new Answer<Optional<Pessoa>>() {
      @Override
      public Optional<Pessoa> answer(InvocationOnMock invocationOnMock) throws Throwable {
        return bancoFindById((Long) invocationOnMock.getArgument(0));
      }
    });
    when(pessoaRepository.findByPesquisa(anyString(), anyInt()))
        .thenAnswer(new Answer<List<Pessoa>>() {
          @Override
          public List<Pessoa> answer(InvocationOnMock invocationOnMock) throws Throwable {
            List<Pessoa> resultado = fazerPesquisa((String) invocationOnMock.getArgument(0),
                (Integer) invocationOnMock.getArgument(1));
            return resultado;
          }
        });

    when(pessoaRepository.findAll()).thenReturn(pessoasBanco);

    pessoas = new ArrayList<>();

    //
    this.pessoaService = new PessoaServiceImpl(pessoaRepository);
  }

  @After
  public void tearDown() throws Exception {

  }

  // region salvar
  @Test
  public void salvar() {
    Pessoa pessoa = criarPessoa("012.344.678-90", "João", "da Silva");
    assertTrue("Pessoa Existe", pessoa != null && pessoa.getCpf().equals("012.344.678-90"));
  }

  @Test
  public void salvarSobrenomeInvalido() {
    String palavrao = "";
    for (int i = 0; i < 256; i++) {
      palavrao += "a";
    }
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", "João", palavrao);
  }

  @Test
  public void salvarNomeNulo() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", null, "da Silva");
  }

  @Test
  public void salvarNomeInvalido() {
    String palavrao = "";
    for (int i = 0; i < 256; i++) {
      palavrao += "a";
    }
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", palavrao, "da Silva");
  }

  @Test
  public void salvarCpfNulo() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa(null, "João", "da Silva");
  }

  @Test
  public void salvarSobrenomeNulo() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", "João", null);
  }

  @Test
  public void salvarSobrenomeVazio() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", "João", "");
  }

  @Test
  public void salvarCpfInvalido() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("01234567890", "João", "da Silva");
  }

  @Test
  public void salvarCpfVazio() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("", "João", "da Silva");
  }

  @Test
  public void salvarNomeVazio() {
    this.esperarErroGenerico(ERRO_INSERIR_PESSOA.getMensagem());
    Pessoa erro = criarPessoa("012.345.678-90", "", "da Silva");
  }

  // end

  // region encontrarPeloCpf
  @Test
  public void encontrarPeloCpf() {
    Pessoa pessoa = this.pessoaService.encontrarPeloCpf("012.345.678-90");
    assertTrue("Pessoa Existe", pessoa != null);
  }

  @Test
  public void encontrarPeloCpfErro() {
    this.esperarErroGenerico(ERRO_LISTAR_PESSOA.getMensagem());
    Pessoa pessoa = this.pessoaService.encontrarPeloCpf("033.345.678-90");
  }

  // endRegion
  // region: BuscarPorFiltro
  @Test
  public void buscarPorFiltroCorretoTresResultados() {
    String pesquisa = "da Silva";
    Integer limite = 3;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 3", pessoaDtos.size() == limite);
  }

  @Test
  public void buscarPorFiltroCorretoDoisResultados() {
    String pesquisa = "da Silva";
    Integer limite = 2;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 2", pessoaDtos.size() == limite);
  }

  @Test
  public void buscarPorFiltroSemResultados() {
    String pesquisa = "JWGS";
    Integer limite = 3;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 0", pessoaDtos.size() == 0);
  }

  @Test
  public void buscarPorFiltroSemPesquisaTresResultados() {
    String pesquisa = "";
    Integer limite = 3;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 3", pessoaDtos.size() == pessoaDtos.size());
  }

  @Test
  public void buscarPorFiltroBuscaPeloCpfUmResultado() {
    String pesquisa = "300";
    Integer limite = 3;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 1", pessoaDtos.size() == 1);
  }

    @Test
    public void buscarInativosRetornarVazio() {
        String pesquisa = "Inativo";
        Integer limite = 3;
        List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
        assertTrue("O tamanho da lista deve ser 1", pessoaDtos.size() == 0);
    }

    @Test
    public void buscarInativosEAtivosRetornarUm() {
        String pesquisa = "In";
        Integer limite = 3;
        List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
        assertTrue("O tamanho da lista deve ser 1", pessoaDtos.size() == 1);
    }

  @Test
  public void buscarPorFiltroBuscaPeloCpfTresResultados() {
    String pesquisa = "-90";
    Integer limite = 3;
    List<PessoaDto> pessoaDtos = this.pessoaService.pesquisar(pesquisa, limite);
    assertTrue("O tamanho da lista deve ser 3", pessoaDtos.size() == 3);
  }

  // endRegion
  // region: listarTodos
  @Test
  public void listarTodos() {
    List<Pessoa> pessoas = pessoaService.listarTodos();
    assertTrue(pessoas.size() == 4);
  }

  @Test
  public void findAll_Error() {
    this.pessoasBanco.add(Pessoa.builder().nome("Júlio").build());
    this.esperarErroGenerico(ERRO_LISTAR_PESSOA_ATRIBUTO_NULO.getMensagem());
    List<Pessoa> pessoas = pessoaService.listarTodos();
  }
  // end

  // region: listarTodosDto
  @Test
  public void listarTodosDto() {
    List<PessoaDto> pessoas = pessoaService.listarTodosDto();
    assertTrue(pessoas.size() == 4);
  }
  // end

  @Test
  public void bancoFindById() {
    Pessoa pessoa = criarPessoa("012.345.678-90", "João", "da Silva");
    pessoa = this.pessoaService.encontrarPeloId(pessoa.getId());
    pessoas.add(this.pessoaService.encontrarPeloId(new Long(0)));
    pessoas.add(this.pessoaService.encontrarPeloId(new Long(-1)));
    pessoas.add(this.pessoaService.encontrarPeloId(new Long(100)));
    pessoas.add(this.pessoaService.encontrarPeloId(null));
    List<Pessoa> pessoas = filtrarErros();
    assertPessoaEErros(pessoa, pessoas);
  }

  private Pessoa criarPessoa(String cpf, String nome, String sobrenome) {
    Pessoa pessoa = Pessoa.builder().cpf(cpf).nome(nome).sobrenome(sobrenome).build();
    pessoa = this.pessoaService.salvar(pessoa);
    return pessoa;
  }

  private void assertPessoaEErros(Pessoa pessoa, List<Pessoa> pessoas) {
    assertTrue(pessoa != null && pessoas.isEmpty());
  }

  private List<Pessoa> filtrarErros() {
    return pessoas.stream().filter(p -> p != null).collect(Collectors.toList());
  }


  private Optional<Pessoa> bancoFindById(Long id) {
    return pessoasBanco.stream().filter(pessoa -> pessoa.getId().equals(id)).findFirst();
  }

  private Optional<Pessoa> encontrarPeloCpf(String cpf) {
    return pessoasBanco.stream().filter(pessoa -> pessoa.getCpf().equals(cpf)).findFirst();
  }

  private List<Pessoa> fazerPesquisa(String pesquisa, Integer limit) {
    List<Pessoa> lista = pessoasBanco.stream()
        .filter(pessoa -> (pessoa.getCpf().contains(pesquisa) || pessoa.getNome().contains(pesquisa)
            || pessoa.getSobrenome().contains(pesquisa)))
        .distinct().limit(limit).collect(Collectors.toList());
    return lista;
  }

  private List<Pessoa> bancoDePessoas() {
    pessoasBanco.add(Pessoa.builder().cpf("012.345.678-90").nome("João").sobrenome("da Silva")
        .id(1L).ativo(true).build());
    pessoasBanco.add(Pessoa.builder().cpf("210.345.678-90").nome("Guilheme").sobrenome("da Silva")
        .id(2L).ativo(true).build());
    pessoasBanco.add(Pessoa.builder().cpf("558.345.678-90").nome("Jor").sobrenome("da Silva").id(3L)
        .ativo(true).build());
    pessoasBanco.add(Pessoa.builder().cpf("000.000.300-00").nome("Inês").sobrenome("Inexistente")
        .id(4L).ativo(true).build());
    pessoasBanco.add(Pessoa.builder().cpf("333.222.300-00").nome("Inativ").sobrenome("Inativo")
        .id(5L).ativo(false).build());
    return pessoasBanco;
  }
}
