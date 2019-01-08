package br.com.jamalxvi.farmaciadanatureza.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  Elemento que representa um item de uma lista
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
@AllArgsConstructor
public class ElementoDeListaDto {
    private String nome;
    private String id;
}
