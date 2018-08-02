package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Esta interface define as classes de medicamentos que representam a utilização do mesmo.
 *  O medicamento não precisa ser estocável, mas deve apresentar para saída:<br/>
 *  -Quantidade;<br/>
 *  -Vencimento da Receita;<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Receitavel {
  public BigDecimal getQuantidade();
  public LocalDate getValidadeReceita();
}
