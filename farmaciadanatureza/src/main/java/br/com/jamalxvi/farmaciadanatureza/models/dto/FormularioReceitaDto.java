package br.com.jamalxvi.farmaciadanatureza.models.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FormularioReceitaDto {
  private Long paciente;
  private Long medico;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate dataReceita;
  private List<ReceitaMedicamentoDto> receita;
}
