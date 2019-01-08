package br.com.jamalxvi.farmaciadanatureza.util;

import br.com.jamalxvi.farmaciadanatureza.models.dto.ElementoDeListaDto;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.ElementoParaIrNaLista;


/**
 * Classe com Métodos Utilitários
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
public class MetodosGenericosUtils {
    /**
     * Transforma um objeto em um elemento para ir em uma lista
     *
     * @param item objeto a ser transformado
     * @param <K>  o tipo do objeto a ser transformado (Deve implementara {@link ElementoParaIrNaLista}
     *
     * @return o elemento transformado
     */
    public static <K extends ElementoParaIrNaLista> ElementoDeListaDto transformarEnumEmDTO(K item) {
        return new ElementoDeListaDto(item.getDesc(), item.getId().toString());
    }
}
