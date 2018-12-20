package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoDiluicao;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Diluivel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.DuracaoLotavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Esta classe é a controloda por homeopatias. Esta classe representa as dosagens de homeopatias.
 * Toda Dosagem de homeopatia apresenta:<br/>
 * -Entidade Base.<br/>
 * -Diluição (Diluivel);<br/>
 * <p>Unidade Padrão da Dosagem de Homeopatia: ml</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "HOMEOPATIA_DOSAGEM")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_HOM_DOS")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_HOM_DOS")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_HOM_DOS")),
})
@Builder
@Data
public class HomeopatiaDosagens extends EntidadeBase implements Diluivel {
  @Column(name = "DIL_HOM_DOS")
  private Integer diluicao;
  @ManyToOne
  @JoinColumn(name = "ID_HOM")
  @JsonBackReference
  private Homeopatia homeopatia;
  @OneToMany(targetEntity = HomeopatiaDosagensUso.class, fetch = FetchType.LAZY,
      mappedBy = "origem")
  @JsonManagedReference
  private List<HomeopatiaDosagensUso> homeopatiaDosagensUsos;
}
