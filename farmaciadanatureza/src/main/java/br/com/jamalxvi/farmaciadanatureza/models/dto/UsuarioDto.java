package br.com.jamalxvi.farmaciadanatureza.models.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioDto {
  private String nome;
  private String sobrenome;
  private String usuario;
}
