package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Medico;
import br.com.jamalxvi.farmaciadanatureza.models.Pessoa;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicoDto;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicoRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MedicoServiceImplTest extends BaseTest {

    @Mock
    private MedicoRepository medicoRepository;

    private MedicoService medicoService;

    @Before
    public void setUp() throws Exception {
        this.medicoService = new MedicoServiceImpl(medicoRepository);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void listarTodosDto() {
        List<Medico> medicos = criarMedicos();
        when(medicoRepository.findAll()).thenReturn(medicos);
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
        return medicos;
    }
}