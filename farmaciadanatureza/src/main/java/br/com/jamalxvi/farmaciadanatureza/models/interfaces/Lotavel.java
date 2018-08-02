package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.time.LocalDate;

/**
 * Esta interfaces permite a impletação de Lotes, esta interfaces é implantada nas classes do tipo
 * de estoque. Todos os lotes precisam de ter um número, data de criação e data de vencimento.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Lotavel {
  public Long getLote();
  public LocalDate getDataCriacaoLote();
  public LocalDate getDataVencimentoLote();
}
