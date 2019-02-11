package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.util.List;

public interface TipoEstoque {
    public <K extends Estocavel> List<K> getEstoque();
}
