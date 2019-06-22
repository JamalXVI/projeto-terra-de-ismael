package br.com.jamalxvi.farmaciadanatureza.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FormularioReceitaDto {
    private Long paciente;
    private Long medico;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataReceita;
    private List<ReceitaMedicamentoDto> receita;
}
