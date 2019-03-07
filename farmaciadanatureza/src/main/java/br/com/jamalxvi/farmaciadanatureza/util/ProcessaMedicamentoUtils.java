package br.com.jamalxvi.farmaciadanatureza.util;

import static br.com.jamalxvi.farmaciadanatureza.enums.EnumMesagens.ERRO_SEM_MECAMENTO_VALIDO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumExcecaoDto;
import br.com.jamalxvi.farmaciadanatureza.exception.MensagemExcecao;
import br.com.jamalxvi.farmaciadanatureza.models.Cientifica;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.DuracaoLotavel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Lotavel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.TipoEstoque;

/**
 * Classe responsável por definir atributos dos medicamentos para o dto com base em interfaces
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public class ProcessaMedicamentoUtils {
  /**
   * Faz o processamento de medicamentos preenchendo o com base em interfaces comuns
   *
   * @param dto o dto de entrega e retorno
   * @param medicamento o medicamento que possuí os dados a serem transformados
   * @param <K> O tipo do medicamento
   * @return o dto preenchido
   */
  public static <K extends Medicamento> RetornoDosMedicamentosDto processar(
      RetornoDosMedicamentosDto dto, K medicamento) {
    if (medicamento instanceof DuracaoLotavel) {
      processarLote(dto, (DuracaoLotavel) medicamento);
    } else if (medicamento instanceof TipoEstoque) {
      processarEstoque(dto, (TipoEstoque) medicamento);
    }

    if (medicamento instanceof Cientifica) {
      processarCientifica(dto, (Cientifica) medicamento);
    }
    dto.setNome(medicamento.getNome());
    dto.setId(medicamento.getId());
    return dto;
  }

  /**
   * Preenche o Dto com base no lote do medicamento {@link DuracaoLotavel}
   *
   * @param dto o dto para ser preenchido
   * @param medicamento o medicamento com a interface de DuracaoLotavel
   */
  private static void processarLote(RetornoDosMedicamentosDto dto, DuracaoLotavel medicamento) {

    dto.setValidadeLote(AjudandeDeData
        .localDateParaString(LocalDate.now().plusMonths(medicamento.getDuracaoLote())));

    // Pegar medicamentos que ainda estão válidos
    LocalDate diaAtual = LocalDate.now();
    List<Lotavel> medicamentosAindaNaoVencidos = medicamento.getEstoque().stream()
        .filter(est -> est != null && est.getDataVencimentoLote() != null
            && diaAtual.isBefore(est.getDataVencimentoLote()))
        .collect(Collectors.toList());

    // Retornar a a data de vencimento mais próxima
    LocalDate data = medicamentosAindaNaoVencidos.stream().map(est -> est.getDataVencimentoLote())
        .min(LocalDate::compareTo)
        .orElseThrow(() -> new MensagemExcecao(ERRO_SEM_MECAMENTO_VALIDO.getMensagem(),
            EnumExcecaoDto.NAO_ENCONTRADO));
    dto.setEstoqueComVencimentoMaisProximo(AjudandeDeData.localDateParaString(data));
    if (medicamentosAindaNaoVencidos instanceof TipoEstoque) {
      processarEstoque(dto, (TipoEstoque) medicamentosAindaNaoVencidos);
    }
  }

  /**
   * Preenche o Dto com base na classe Científica {@link Cientifica}
   *
   * @param dto o dto para ser preenchido
   * @param medicamento o medicamento com a interface de Cientifica
   */
  private static void processarCientifica(RetornoDosMedicamentosDto dto, Cientifica medicamento) {
    dto.setNomeCientifico(medicamento.getNomeCientifico());
  }

  /**
   * Preenche o Dto com base no estoque do medicamento {@link TipoEstoque}
   *
   * @param dto o dto para ser preenchido
   * @param medicamento o medicamento com a interface de TipoEstoque
   */
  private static void processarEstoque(RetornoDosMedicamentosDto dto, TipoEstoque medicamento) {
    BigDecimal quantidade = medicamento.getEstoque().stream()
        .map(est -> est.getQuantidade()
            .subtract(est.getUsoEstoques().stream().map(qtd -> qtd.getQuantidade())
                .reduce(BigDecimal.ZERO, BigDecimal::add)))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    dto.setQuantidade(quantidade);
  }
}
