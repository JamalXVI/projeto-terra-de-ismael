package br.com.jamalxvi.farmaciadanatureza.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReceitaMedicamentoDto {
  private Integer id;
  private List<MedicamentoPrincipioAtivoDto> principioAtivos;
  private BigDecimal quantidade;
  private BigDecimal peso;
  private String posologia;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate validade;
  private Integer tipo;
  private Long lote;
  private String nome;
}
