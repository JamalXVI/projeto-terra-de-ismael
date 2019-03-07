package br.com.jamalxvi.farmaciadanatureza.models.dto;


import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Essa classe é responsável por retornar informações de um medicamento escolhido
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
public class RetornoDosMedicamentosDto {
    /** A Quantidade disponível do medicamento */
    private BigDecimal quantidade;
    /** A unidade de medida do medicamento */
    private String unidade;
    /** A vlidade sugerida do medicamento */
    private String validadeLote;
    /** O nome popular do medicamento */
    private String nome;
    /** O nome científico do medicamento */
    private String nomeCientifico;
    /** O nome científico do medicamento */
    private String estoqueComVencimentoMaisProximo;
    /** O id do Medicamento */
    private Long id;

}
