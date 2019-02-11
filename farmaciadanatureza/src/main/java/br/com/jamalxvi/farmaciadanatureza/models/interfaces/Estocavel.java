package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.math.BigDecimal;
import java.util.List;

/**
 * Esta interfaces define se o produto será estocável ou não. Os atributos necessários para esta
 * interfaces são: -Quantidade; -Unidade;
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Estocavel {

  public BigDecimal getQuantidade();

  <K extends PodeUsarEstoque> List<K> getUsoEstoques();
}
