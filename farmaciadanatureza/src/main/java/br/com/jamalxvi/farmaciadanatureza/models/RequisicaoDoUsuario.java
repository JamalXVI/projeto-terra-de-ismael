package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequisicaoDoUsuario {

  private Long id;

  private String usuario;

  private String senha;

  private String nome;

  private String sobrenome;

  private String cpf;

}
