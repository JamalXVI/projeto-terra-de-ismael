package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDate;

/**
 * Esta classe representa a entidade base do sistema, ou seja, todos os modelos devem estender
 * a entidade base, exceto:<br/>
 * -Pessoas;<br/>
 * -Usuários;<br/>
 * -Autoridades;<br/>
 * Os atributos de toda entidade base são:<br/>
 * -Id;<br/>
 * -Versão;<br/>
 * -Data de Criação;<br/>
 * -Usuário responsável pela criação do Objeto;
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntidadeBase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Version
  protected Integer versao;

  protected LocalDate dataCriacao;

}
