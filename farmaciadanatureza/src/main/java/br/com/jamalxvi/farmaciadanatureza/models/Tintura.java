package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.TipoDosagem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Esta classe é a controlodadora de Tinturas. É a classe que define os tipos de homeopatias
 * possiveis e recolhe suas dosagens. Toda Tintura possuí:<br/>
 * -Entidade Base;<br/>
 * -Nome popular e Científico;<br/>
 * -Lista de Dosagens;<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "TINTURA")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_TIN")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_TIN")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_TIN")),
    @AttributeOverride(name = "nome", column = @Column(name = "NOM_TIN")),
    @AttributeOverride(name = "nomeCientifico", column = @Column(name = "NOM_CIE_TIN")),
})
@Builder
@Data
public class Tintura extends Cientifica implements TipoDosagem<TinturaDosagens> {
  @OneToMany(mappedBy="tintura", targetEntity = TinturaDosagens.class, fetch = FetchType.LAZY)
  @JsonManagedReference
  public List<TinturaDosagens> dosagens;

}
