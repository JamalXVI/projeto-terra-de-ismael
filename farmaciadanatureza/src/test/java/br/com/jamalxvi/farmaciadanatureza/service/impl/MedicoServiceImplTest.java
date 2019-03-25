package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.PessoaDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MedicoServiceImplTest extends BaseTest {

    @Mock
    private MedicoRepository medicoRepository;

    private MedicoService medicoService;

    @Before
    public void setUp() throws Exception {
        this.medicoService = new MedicoServiceImpl(medicoRepository);
        List<Medico> medicos = criarMedicos();
        when(medicoRepository.findByPesquisa(anyString(), anyInt())).thenAnswer(new Answer<List<Medico>>() {
            @Override
            public List<Medico> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return medicos.stream().limit((int)invocationOnMock.getArgument(1))
                        .collect(Collectors.toList());
            }
        });
        when(medicoRepository.findAll()).thenReturn(medicos);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void listarTodosDto() {
        List<Medico> medicos = criarMedicos();
        List<MedicoDto> medicoDtos = this.medicoService.listarTodosDto();
        assertTrue("A lista não deve ser nula.", medicoDtos != null);
        assertTrue("A lista deve ter o tamanho original", medicoDtos.size() == medicos.size());
        for (int i = 0; i < medicoDtos.size(); i++) {
            assertTrue("O cpf deve ser o mesmo", medicoDtos.get(i).getCpf().equals(medicos
                    .get(i).getPessoa().getCpf()));
            assertTrue("O nome deve ser o mesmo", medicoDtos.get(i).getNome().equals(medicos
                    .get(i).getPessoa().getNome()));
            assertTrue("O sobrenome deve ser o mesmo", medicoDtos.get(i).getSobrenome().equals(medicos
                    .get(i).getPessoa().getSobrenome()));
            assertTrue("O crm deve ser o mesmo", medicoDtos.get(i).getCrm().equals(medicos
                    .get(i).getCrm()) );
            assertTrue("O status deve ser o mesmo", medicoDtos.get(i).getAtivo() == medicos
                    .get(i).getPessoa().getAtivo());
        }
    }

    private List<Medico> criarMedicos() {
        List<Medico> medicos = new ArrayList<>();
        medicos.add(Medico.builder().crm("123456").pessoa(Pessoa.builder().nome("João").sobrenome
                ("Da silva").cpf("123.456.789-01").ativo(true).id(1L).dataCriacao(LocalDate.now())
                .build()).build());
        medicos.add(Medico.builder().crm("456789").pessoa(Pessoa.builder().nome("Guilherme")
                .sobrenome
                        ("Da silva").cpf("456.789.012-34").ativo(true).id(2L).dataCriacao(LocalDate.now())
                .build()).build());
        medicos.add(Medico.builder().crm("145136").pessoa(Pessoa.builder().nome("Jorge")
                .sobrenome
                        ("Da silva").cpf("156.789.012-34").ativo(true).id(2L).dataCriacao(LocalDate.now())
                .build()).build());
        return medicos;
    }
    // region: BuscarPorFiltro
    @Test
    public void buscarPorFiltroCorretoTresResultados() {
        String pesquisa = "da Silva";
        Integer limite = 3;
        List<MedicoDto> medicoDtos = this.medicoService.pesquisar(pesquisa, limite);
        Assert.assertTrue("O tamanho da lista deve ser 3", medicoDtos.size() == limite);
    }

    @Test
    public void buscarPorFiltroCorretoDoisResultados() {
        String pesquisa = "da Silva";
        Integer limite = 2;
        List<MedicoDto> medicoDtos = this.medicoService.pesquisar(pesquisa, limite);
        Assert.assertTrue("O tamanho da lista deve ser 2", medicoDtos.size() == limite);
    }

    @Test
    public void buscarPorFiltroSemPesquisaTresResultados() {
        String pesquisa = "";
        Integer limite = 3;
        List<MedicoDto> medicoDtos = this.medicoService.pesquisar(pesquisa, limite);
        Assert.assertTrue("O tamanho da lista deve ser 3", medicoDtos.size() == limite);
    }
}