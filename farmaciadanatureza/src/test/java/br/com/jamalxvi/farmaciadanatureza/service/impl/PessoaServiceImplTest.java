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

        when(pessoaRepository.findAll()).thenReturn(bancoDePessoas());

        pessoas = new ArrayList<>();

        //
        this.pessoaService = new PessoaServiceImpl(pessoaRepository);
    }

    @After
    public void tearDown() throws Exception {

    }

    //region salvar
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

    //end

    //region encontrarPeloCpf
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
    //end

    // region: listarTodos
    @Test
    public void listarTodos() {
        List<Pessoa> pessoas = pessoaService.listarTodos();
        assertTrue(pessoas.size() == 1);
    }

    @Test
    public void findAll_Error() {
        this.pessoasBanco.add(Pessoa.builder().nome("Júlio").build());
        this.esperarErroGenerico(ERRO_LISTAR_PESSOA_ATRIBUTO_NULO.getMensagem());
        List<Pessoa> pessoas = pessoaService.listarTodos();
    }
    //end

    // region: listarTodosDto
    @Test
    public void listarTodosDto() {
        List<PessoaDto> pessoas = pessoaService.listarTodosDto();
        assertTrue(pessoas.size() == 1);
    }
    //end

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
        return bancoDePessoas().stream().filter(pessoa -> pessoa.getId().equals(id))
                .findFirst();
    }

    private Optional<Pessoa> encontrarPeloCpf(String cpf) {
        return bancoDePessoas().stream().filter(pessoa -> pessoa.getCpf().equals(cpf))
                .findFirst();
    }

    private List<Pessoa> bancoDePessoas() {
        pessoasBanco.add(Pessoa.builder().cpf("012.345.678-90").nome("João").sobrenome("da Silva")
                .id(1L).ativo(true).build());
        pessoasBanco.add(Pessoa.builder().cpf("210.345.678-90").nome("Guilheme").sobrenome("da Silva")
                .id(1L).ativo(false).build());
        return pessoasBanco;
    }
}