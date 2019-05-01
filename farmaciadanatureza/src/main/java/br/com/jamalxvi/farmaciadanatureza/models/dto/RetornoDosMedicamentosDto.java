package br.com.jamalxvi.farmaciadanatureza.models.dto;


import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    /** O nome popular do medicamento */
    private String nome;
    /** O nome científico do medicamento */
    private String nomeCientifico;
    /** O id do PrincipioAtivo */
    private Long id;
    /** Lista de Estoques do PrincipioAtivo */
    List<MedicamentoEmEstoqueDto> estoques;
    private Boolean misturavel;
    /** Verifica se Existe Estoque */
    private Boolean existeEstoque;
    public RetornoDosMedicamentosDto(){
        this.quantidade = BigDecimal.ZERO;
        this.estoques = new ArrayList<>();
        this.nomeCientifico = "";
    }
}
