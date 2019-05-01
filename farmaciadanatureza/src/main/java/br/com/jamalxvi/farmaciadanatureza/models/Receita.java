package br.com.jamalxvi.farmaciadanatureza.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Esta classe é a controlodadora de Tinturas. É a classe que define os tipos de homeopatias
 * possiveis e recolhe suas dosagens. Toda Tintura possuí:<br/>
 * -Entidade Base;<br/>
 * -Nome popular e Científico;<br/>
 * -Lista de Dosagens;<br/>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "RECEITA")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_REC")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_REC")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_REC")),
})
@Builder
@Data
public class Receita extends EntidadeBase {

  @ManyToOne(targetEntity = Medico.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_MED")
  @JsonBackReference
  private Medico medico;

  @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_USR")
  @JsonBackReference
  private Usuario usuario;

  @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_PES")
  @JsonBackReference
  private Pessoa pessoa;

  @Column(name = "VAL_REC")
  @JsonBackReference
  private LocalDate validadeReceita;

  @OneToMany(targetEntity = ReceitaMedicamento.class, fetch = FetchType.LAZY,
          mappedBy = "receita")
  private List<ReceitaMedicamento> receita;

}
