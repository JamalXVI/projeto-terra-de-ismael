package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;

import java.math.BigDecimal;

/**
 * Esta interface define se o produto será estocável ou não. Os atributos necessários para esta
 * interface são:
 * -Quantidade;
 * -Unidade;
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface Estocavel {
  public BigDecimal getQuantidade();
  public EnumUnidadesMetricas getUnidade();
}
