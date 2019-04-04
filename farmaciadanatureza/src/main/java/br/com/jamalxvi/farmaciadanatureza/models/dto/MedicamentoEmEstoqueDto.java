package br.com.jamalxvi.farmaciadanatureza.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Dto do medicamento em estoque
 * @author Henrique Arantes Tiraboschi
 * @version 0.1
 * @since  0.1
 */
@Data
public class MedicamentoEmEstoqueDto {
    private Long id;
    private BigDecimal quantidade;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataVencimento;
}
