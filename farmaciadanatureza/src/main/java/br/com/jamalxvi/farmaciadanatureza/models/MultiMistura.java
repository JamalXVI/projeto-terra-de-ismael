package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Esta classe é a controlodadora de Multi-mistura. <b>Não existe um controle de saída de multi-misturas</b>,
 * apenas um consta a existência deste no sistema.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "MULTIMISTURA")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_MUM")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_MUM")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_MUM")),
})
@Builder
@Data
public class MultiMistura extends EntidadeBase {
  @Column(name = "NOM_MUM")
  private String nome;
  @OneToMany(fetch = FetchType.LAZY, targetEntity = FloralUso.class, mappedBy = "origem")
  private List<MultiMisturaUso> usos;
}
