package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Medicamento;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Esta classe abstrata define os medicamentos que terão nome científico e nome popular.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Data
@MappedSuperclass
public abstract class Cientifica extends EntidadeBase implements Medicamento {
  @NotNull @NotEmpty
  private String nome;
  @NotNull @NotEmpty
  private String nomeCientifico;
}
