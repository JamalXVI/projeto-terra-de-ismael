package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.BaseTest;
import br.com.jamalxvi.farmaciadanatureza.models.Capsula;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.repository.CapsulaRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.FloralRepository;
import br.com.jamalxvi.farmaciadanatureza.repository.HomeopatiaRepository;
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

  private MedicamentoService medicamentoService;

  @Before
  public void setUp() throws Exception {
    medicamentoService = new MedicamentoServiceImpl(capsulaRepository, floralRepository,
        homeopatiaRepository, outrosMedicamentosRepository, plantaDesidratadaRepository,
        pomadaRepository, tinturaRepository);
  }

  @Test
  public void retornarItensDoMedicamento() {
    List<Capsula> medicamentos = new ArrayList<>();
    Capsula capsula = new Capsula();
    capsula.setId(1L);
    capsula.setNomeCientifico("Abeas Medicamento 3");
    capsula.setNome("Medicamento 3");
    medicamentos.add(capsula);
    when(capsulaRepository.findAll()).thenReturn(medicamentos);
    List<ElementoDeListaDto> lista = medicamentoService.retornarItensDoMedicamento(1);
    assertTrue("O tamanho da lista deve ser 1!", lista.size() == 1);
    assertTrue("O nome do medicamento deve ser o nome científico",
        lista.get(0).getNome().equals(capsula.getNomeCientifico()));
    assertTrue("O id deve ser 1!", lista.get(0).getId().equals(capsula.getId().toString()));
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
    medicamentos.add((T) medicamentoUm);
    medicamentos.add((T) medicamentoDois);
    return medicamentos;
  }
}
