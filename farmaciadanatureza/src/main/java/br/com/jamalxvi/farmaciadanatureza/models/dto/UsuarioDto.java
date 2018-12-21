package br.com.jamalxvi.farmaciadanatureza.models.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Esta classe é responsável por retornar para view os dados relevantes
 *
 * @version 0.1
 * @since 0.1
 * @author Jamal XVI <henriquearantest@gmail.com>
 */
@Builder
@Data
public class UsuarioDto {

  /** Id do Usuário */
  private Long id;

  /** Nome da Pessoa que esta ligada ao Usuário */
  private String nome;

  /** Sobrenome da Pessoa ligada ao Usuário */
  private String sobrenome;

  /** Nome do Usuário */
  private String usuario;
}
