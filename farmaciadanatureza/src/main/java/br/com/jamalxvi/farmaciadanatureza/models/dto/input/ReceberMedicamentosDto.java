package br.com.jamalxvi.farmaciadanatureza.models.dto.input;


import lombok.Data;

/**
 * Essa classe tem a responsábilidade de receber do front-end informações de um medicamento
 * específico
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
public class ReceberMedicamentosDto {
    private Integer tipo;
    private Long id;
}
