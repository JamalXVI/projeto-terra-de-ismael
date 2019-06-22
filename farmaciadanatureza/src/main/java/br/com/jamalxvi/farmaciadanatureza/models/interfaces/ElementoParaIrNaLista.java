package br.com.jamalxvi.farmaciadanatureza.models.interfaces;

/**
 * Interface responsável por fornecer a transformação de um item em um Elemento da Lista
 * {@link br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto}
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public interface ElementoParaIrNaLista {
    Integer getId();
    String getDesc();
}
