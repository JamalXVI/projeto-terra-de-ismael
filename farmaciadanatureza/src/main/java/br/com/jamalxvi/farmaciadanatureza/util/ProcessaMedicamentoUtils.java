package br.com.jamalxvi.farmaciadanatureza.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.jamalxvi.farmaciadanatureza.models.Cientifica;
import br.com.jamalxvi.farmaciadanatureza.models.dto.MedicamentoEmEstoqueDto;
import br.com.jamalxvi.farmaciadanatureza.models.dto.RetornoDosMedicamentosDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.*;

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
    if (medicamento instanceof TipoEstoque) {
      dto.setExisteEstoque(Boolean.TRUE);
      processaEstoque(dto, (TipoEstoque) medicamento);
    } else {
      dto.setExisteEstoque(Boolean.FALSE);
    }
    if (medicamento instanceof Cientifica) {
      processarCientifica(dto, (Cientifica) medicamento);
    }
    dto.setNome(medicamento.getNome());
    dto.setId(medicamento.getId());
    return dto;
  }

  /**
   * Preenche o Dto com base no estoque do medicamento {@link DuracaoLotavel}
   *
   * @param dto o dto para ser preenchido
   * @param medicamento o medicamento com a interface de DuracaoLotavel
   */
  private static void processaEstoque(RetornoDosMedicamentosDto dto, TipoEstoque medicamento) {
    List<MedicamentoEmEstoqueDto> estoqueDtos =
        medicamento.getEstoque().parallelStream().filter(med -> {
          if (med instanceof Lotavel) {
            if (((Lotavel) med).getDataVencimentoLote().isBefore(LocalDate.now())) {
              return false;
            }
          }
          if (retornarTotalEmEstoque(med).compareTo(BigDecimal.ZERO) <= 0) {
            return false;
          }
          return true;
        }).map(med -> {
          MedicamentoEmEstoqueDto medicamentoEmEstoqueDto = new MedicamentoEmEstoqueDto();
          medicamentoEmEstoqueDto.setId(med.getId());
          medicamentoEmEstoqueDto.setQuantidade(retornarTotalEmEstoque(med));
          if (med instanceof Lotavel) {
            LocalDate dataVencimento = ((Lotavel) med).getDataVencimentoLote();
            medicamentoEmEstoqueDto.setDataVencimento(dataVencimento);
          }
          return medicamentoEmEstoqueDto;
        }).collect(Collectors.toList());
    dto.setEstoques(estoqueDtos);
    dto.setQuantidade(estoqueDtos.stream().map(est -> est.getQuantidade()).reduce(BigDecimal.ZERO,
        BigDecimal::add));
  }

  /**
   * Retorna a quantidade ainda em estoque do Medicamento
   * 
   * @param med o estoque do medicamento em questão
   * @return o total
   */
  private static BigDecimal retornarTotalEmEstoque(Estocavel med) {
    return med.getQuantidade().subtract(med.getUsoEstoques().stream()
        .map(est -> est.getQuantidade()).reduce(BigDecimal.ZERO, BigDecimal::add));
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

}
