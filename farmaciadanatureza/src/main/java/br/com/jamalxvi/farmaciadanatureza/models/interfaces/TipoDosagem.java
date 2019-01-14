package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

import java.util.List;

public interface TipoDosagem<K extends Diluivel> {
    public List<K> getDosagens();
}
