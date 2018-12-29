package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.math.BigDecimal;

/**
 * Esta interface define a assinatura relativo ao Estoque. Classes que
 * implementam esta infertace devem possuir atributos de quantidade e estoque
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface PodeUsarEstoque {

  BigDecimal getQuantidade();

  Estocavel getEstoque();
}
