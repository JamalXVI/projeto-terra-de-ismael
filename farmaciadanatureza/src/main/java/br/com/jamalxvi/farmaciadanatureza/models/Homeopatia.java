package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Esta classe é a controlodadora de homeopatias. É a classe que define os tipos de homeopatias
 * possiveis e recolhe suas dosagens
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "HOMEOPATIA")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_HOM")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_HOM")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_HOM")),
})
@Builder
@Data
public class Homeopatia extends EntidadeBase {
  @Column(name = "NOM_HOM")
  private String nome;
  @OneToMany(mappedBy="homeopatia", targetEntity = HomeopatiaDosagens.class, fetch = FetchType.LAZY)
  private List<HomeopatiaDosagens> dosagens;

}
