package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.math.BigDecimal;

public interface PodeUsarEstoque {
    BigDecimal getQuantidade();
    Estocavel getEstoque();
}
