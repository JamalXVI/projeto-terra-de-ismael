package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.util.List;

public interface TipoEstoque<K extends Estocavel> {
    public List<K> getEstoque();
}
