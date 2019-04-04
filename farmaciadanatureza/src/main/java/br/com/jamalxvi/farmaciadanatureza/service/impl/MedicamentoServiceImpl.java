package br.com.jamalxvi.farmaciadanatureza.service.impl;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.*;
import static br.com.jamalxvi.farmaciadanatureza.util.MetodosGenericosUtils.transformarEnumEmDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jamalxvi.farmaciadanatureza.models.Cientifica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.input.ReceberMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.TipoDosagem;
import br.com.jamalxvi.farmaciadanatureza.repository.*;
import br.com.jamalxvi.farmaciadanatureza.service.MedicamentoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

  @Autowired
  private CapsulaRepository capsulaRepository;
  @Autowired
  private FloralRepository floralRepository;
  @Autowired
  private HomeopatiaRepository homeopatiaRepository;
  @Autowired
  private OutrosMedicamentosRepository outrosMedicamentosRepository;
  @Autowired
  private PlantaDesidratadaRepository plantaDesidratadaRepository;
  @Autowired
  private PomadaRepository pomadaRepository;
  @Autowired
  private TinturaRepository tinturaRepository;

  @Override
  public List<ElementoDeListaDto> retornaListaDePossiveisMedicamentos() {
    return Arrays.asList(EnumTipoMedicamento.values()).stream().map(m -> transformarEnumEmDTO(m))
        .collect(Collectors.toList());
  }

  @Override
  public List<ElementoDeListaDto> retornarItensDoMedicamento(Integer tipoMedicamento) {
    final JpaRepository<? extends Medicamento, Long> repository =
        getRepositoryById(tipoMedicamento);
    List<? extends Medicamento> medicamentos = repository.findAll();
    return medicamentos.stream().map(m -> {
      ElementoDeListaDto dto = new ElementoDeListaDto(m.getNome(), m.getId().toString());
      if (m instanceof Cientifica) {
        dto.setNome(((Cientifica) m).getNomeCientifico());
      }
      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public RetornoDosMedicamentosDto retornarInformacoesDoMedicamento(
      ReceberMedicamentosDto receberMedicamentosDto) {
    final EnumTipoMedicamento enumTipoMedicamento =
        getEnumTipoMedicamento(receberMedicamentosDto.getTipo());
    final JpaRepository<? extends Medicamento, Long> repository =
        getRepository(enumTipoMedicamento);
    final Medicamento medicamento = repository.findById(receberMedicamentosDto.getId())
        .orElseThrow(() -> new MensagemExcecao(ERRO_BUSCAR_TIPO_MEDICAMENTO.getMensagem(),
            EnumExcecaoDto.NAO_ENCONTRADO));
    ClassUtils.getAllInterfacesForClassAsSet(enumTipoMedicamento.getClazz()).forEach(inter -> {
      if (inter == TipoDosagem.class) {

      }
    });
    return null;
  }

  /**
   * Retorna o repositório pelo Id do tipo do Medicamento
   * 
   * @param tipoMedicamento
   * @returno repositório do Medicamento
   */
  private JpaRepository<? extends Medicamento, Long> getRepositoryById(Integer tipoMedicamento) {
    final EnumTipoMedicamento medicamento = getEnumTipoMedicamento(tipoMedicamento);
    return getRepository(medicamento);
  }

  /**
   * Retorna o tipo do Medicamento
   * 
   * @param tipoMedicamento o id do tipo do medicamento
   * @return o enum do medicamento em questão
   * @throws MensagemExcecao exceção na busca: Não Encontrado
   */
  private EnumTipoMedicamento getEnumTipoMedicamento(Integer tipoMedicamento) {
    return EnumTipoMedicamento.encontrarPeloId(tipoMedicamento)
        .orElseThrow(() -> new MensagemExcecao(ERRO_BUSCAR_TIPO_MEDICAMENTO.getMensagem(),
            EnumExcecaoDto.NAO_ENCONTRADO));
  }

  /**
   * Retorna o repositório do tipo do medicamento escolhido.
   *
   * @param medicamento o enum do medicamento
   * @return o repositório do medicamento em questão
   * @throws MensagemExcecao exceção na busca de repositórios
   */
  private JpaRepository<? extends Medicamento, Long> getRepository(
      EnumTipoMedicamento medicamento) {
    final String tipo = "Repository";
    return getVariavelDeMedicamento(medicamento, tipo);
  }

  /**
   * Retorna o serviço do tipo do medicamento escolhido.
   *
   * @param medicamento o enum do medicamento
   * @return o repositório do medicamento em questão
   * @throws MensagemExcecao exceção na busca de repositórios
   */
  private JpaRepository<? extends Medicamento, Long> getServico(EnumTipoMedicamento medicamento) {
    final String tipo = "Service";
    return getVariavelDeMedicamento(medicamento, tipo);
  }

  private JpaRepository<? extends Medicamento, Long> getVariavelDeMedicamento(
      EnumTipoMedicamento medicamento, String tipo) {
    try {
      return (JpaRepository<? extends Medicamento, Long>) this.getClass()
          .getDeclaredField(medicamento.getClazz().getSimpleName().toLowerCase() + tipo).get(this);
    } catch (IllegalAccessException e) {
      throw new MensagemExcecao(ERRO_MEDICAMENTO_ACESSO_INVALIDO.getMensagem(),
          EnumExcecaoDto.NAO_ENCONTRADO);
    } catch (NoSuchFieldException e) {
      throw new MensagemExcecao(ERRO_CAMPO_MEDICAMENTO_NAO_ENCONTRADO.getMensagem(),
          EnumExcecaoDto.NAO_ENCONTRADO);
    }
  }
}
