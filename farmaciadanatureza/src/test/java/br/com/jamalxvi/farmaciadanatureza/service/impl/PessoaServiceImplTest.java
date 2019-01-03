package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.PessoaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.PessoaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_LISTAR_PESSOA_ATRIBUTO_NULO;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class PessoaServiceImplTest  extends BaseTest {
    @Mock
    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasBanco;

    @Before
    public void setUp() throws Exception {
        this.pessoas = new ArrayList<>();
        pessoasBanco = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
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
                Optional<Pessoa> pessoa = bancoFindByCpf(cpf);
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

    @Test
    public void save() {
        String palavrao = "";
        for (int i = 0; i < 256; i++) {
            palavrao += "a";
        }
        Pessoa pessoa = criarPessoa("012.344.678-90", "João", "da Silva");
        Pessoa erro1 = criarPessoa("01234567890", "João", "da Silva");
        Pessoa erro2 = criarPessoa("", "João", "da Silva");
        Pessoa erro3 = criarPessoa("012.345.678-90", "", "da Silva");
        Pessoa erro4 = criarPessoa("012.345.678-90", "João", "");
        Pessoa erro5 = criarPessoa("012.345.678-90", "João", null);
        Pessoa erro6 = criarPessoa(null, "João", "da Silva");
        Pessoa erro7 = criarPessoa("012.345.678-90", null, "da Silva");
        Pessoa erro8 = criarPessoa("012.345.678-90", palavrao, "da Silva");
        Pessoa erro9 = criarPessoa("012.345.678-90", "João", palavrao);
        assertTrue(pessoa != null && erro1 == null && erro2 == null && erro3 == null && erro4 == null &&
                erro5 == null && erro6 == null && erro7 == null && erro7 == null && erro8 == null && erro9 == null);
    }

    @Test
    public void bancoFindByCpf() {
        Pessoa pessoa = this.pessoaService.findByCpf("012.345.678-90");
        pessoas.add(this.pessoaService.findByCpf(null));
        pessoas.add(this.pessoaService.findByCpf(""));
        pessoas.add(this.pessoaService.findByCpf("01234567890"));
        List<Pessoa> pessoas = filtrarErros();
        assertPessoaEErros(pessoa, pessoas);
    }

    // region: listarTodos
    @Test
    public void findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        assertTrue(pessoas.size() == 1);
    }

    @Test
    public void findAll_Error() {
        this.pessoasBanco.add(Pessoa.builder().nome("Júlio").build());
        this.esperarErroGenerico(ERRO_LISTAR_PESSOA_ATRIBUTO_NULO.getMensagem());
        List<Pessoa> pessoas = pessoaService.findAll();
    }
    //endRegion

    @Test
    public void findAllDto() {
        List<PessoaDto> pessoas = pessoaService.findAllDto();
        assertTrue(pessoas.size() == 1);
    }

    @Test
    public void bancoFindById() {
        Pessoa pessoa = criarPessoa("012.345.678-90", "João", "da Silva");
        pessoa = this.pessoaService.findById(pessoa.getId());
        pessoas.add(this.pessoaService.findById(new Long(0)));
        pessoas.add(this.pessoaService.findById(new Long(-1)));
        pessoas.add(this.pessoaService.findById(new Long(100)));
        pessoas.add(this.pessoaService.findById(null));
        List<Pessoa> pessoas = filtrarErros();
        assertPessoaEErros(pessoa, pessoas);
    }

    private Pessoa criarPessoa(String cpf, String nome, String sobrenome) {
        Pessoa pessoa = Pessoa.builder().cpf(cpf).nome(nome).sobrenome(sobrenome).build();
        pessoa = this.pessoaService.save(pessoa);
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

    private Optional<Pessoa> bancoFindByCpf(String cpf) {
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