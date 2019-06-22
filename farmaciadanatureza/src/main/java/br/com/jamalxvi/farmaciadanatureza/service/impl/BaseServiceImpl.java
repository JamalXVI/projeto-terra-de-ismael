package br.com.jamalxvi.farmaciadanatureza.service.impl;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens;
import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.pegarMensagemPeloCodigo;

public abstract class BaseServiceImpl<J, K extends JpaRepository<J, Long>> {

  K repository;

  protected static Validator validator;

  BaseServiceImpl() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  abstract void config();

  public J encontra(Long id) {
    buscaConfig();
    J objeto = null;
    try {
      objeto = repository.findById(id).orElse(null);
    } catch (Exception e) {
      return null;
    }
    return objeto;
  }

  private void buscaConfig() {
    if (repository == null) {
      config();
    }
  }

  public J salva(J obj) {
    buscaConfig();
    try {
      return repository.save(obj);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  /**
   * Transforma um objeto em um elemento para ir em uma lista
   *
   * @param item objeto a ser transformado
   * @param <K> o tipo do objeto a ser transformado (Deve implementara {@link ElementoParaIrNaLista}
   *
   * @return o elemento transformado
   */
  public static <K extends ElementoParaIrNaLista> ElementoDeListaDto transformarEnumEmDTO(K item) {
    if (item == null) {
      return new ElementoDeListaDto(EnumMesagens.NAO_ENCONTRADA.getMensagem(), "-1");
    }

    return new ElementoDeListaDto(pegarMensagemPeloCodigo(item.getDesc()), item.getId().toString());
  }

  public <L extends ElementoParaIrNaLista> List<ElementoDeListaDto> montaDtoDeLista(L[] itens) {
    return Arrays.asList(itens).stream().map(m -> transformarEnumEmDTO(m))
        .collect(Collectors.toList());
  }

  public <L extends ElementoParaIrNaLista> List<ElementoDeListaDto> montaDtoDeLista(List<L> itens) {
    return itens.stream().map(m -> transformarEnumEmDTO(m)).collect(Collectors.toList());
  }
}
