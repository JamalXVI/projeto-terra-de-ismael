package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.repository.CapsulaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.FloralRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.HomeopatiaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.MedicamentoRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.OutrosMedicamentosRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PlantaDesidratadaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.PomadaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.TinturaRepository;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_BUSCAR_TIPO_MEDICAMENTO;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class MedicamentoServiceImplTest extends BaseTest {

    @Mock
    private CapsulaRepository capsulaRepository;
    @Mock
    private FloralRepository floralRepository;
    @Mock
    private HomeopatiaRepository homeopatiaRepository;
    @Mock
    private OutrosMedicamentosRepository outrosMedicamentosRepository;
    @Mock
    private PlantaDesidratadaRepository plantaDesidratadaRepository;
    @Mock
    private PomadaRepository pomadaRepository;
    @Mock
    private TinturaRepository tinturaRepository;

    @Mock
    private MedicamentoRepository medicamentoRepository;

    private MedicamentoService medicamentoService;

    @Before
    public void setUp() throws Exception {
        when(capsulaRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(floralRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(homeopatiaRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(outrosMedicamentosRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(plantaDesidratadaRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(pomadaRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(tinturaRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        when(medicamentoRepository.findAll()).thenReturn(criarMedicamentoGenerico());
        medicamentoService = new MedicamentoServiceImpl(capsulaRepository, floralRepository,
                homeopatiaRepository, outrosMedicamentosRepository, plantaDesidratadaRepository,
                pomadaRepository, tinturaRepository, medicamentoRepository);
    }

    @Test
    public void retornarItensDoMedicamento() {
        List<ElementoDeListaDto> lista = medicamentoService.retornarItensDoMedicamento(1);
        assertTrue("O tamanho da lista deve ser 2!", lista.size() == 2);
    }

    @Test
    public void retornarItensDoMedicamentoFalha() {
        esperarErroGenerico(ERRO_BUSCAR_TIPO_MEDICAMENTO.getMensagem());
        List<ElementoDeListaDto> lista = medicamentoService.retornarItensDoMedicamento(99);
    }
    private <T extends Medicamento> List<T> criarMedicamentoGenerico() {
        List<T> medicamentos = new ArrayList<>();
        Medicamento medicamentoUm = new Medicamento() {
            @Override
            public String getNome() {
                return "Medicamento 01";
            }

            @Override
            public Long getId() {
                return 1L;
            }
        };
        Medicamento medicamentoDois = new Medicamento() {
            @Override
            public String getNome() {
                return "Medicamento 02";
            }

            @Override
            public Long getId() {
                return 2L;
            }
        };
        medicamentos.add((T)medicamentoUm);
        medicamentos.add((T)medicamentoDois);
        return medicamentos;
    }
}