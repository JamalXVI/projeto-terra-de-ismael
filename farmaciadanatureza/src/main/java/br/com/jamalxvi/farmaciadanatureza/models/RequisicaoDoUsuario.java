package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

/**
 * Essa classe é responsável de devolver para o front-end as informações do usuário que está
 * logado. Os atributos para serem enviados são:<br/>
 * -id;<br/>
 * -usuario;<br/>
 * -senha;<br/>
 * -nome;<br/>
 * -sobrenome;<br/>
 * -cpf;<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
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
