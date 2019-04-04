package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.util.List;

public interface TipoEstoque {
    <L extends Estocavel,K extends L> List<K> getEstoque();
}
