package br.com.jamalxvi.farmaciadanatureza.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReceitaMedicamentoDto {
    private Integer id;
    private List<MedicamentoPrincipioAtivoDto> principioAtivos;
    private BigDecimal quantidade;
    private BigDecimal peso;
    private String posologia;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate validade;
    private Integer tipo;
    private String nome;
}
