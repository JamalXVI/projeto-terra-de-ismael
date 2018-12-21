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
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_TINTURA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_TIN_DOS_USO_EST",
          referencedColumnName = "ID_TIN_DOS_USO_EST"))
  @JsonBackReference
  private List<TinturaDosagensUsoEstoque> usoTintura;
  @OneToMany(mappedBy = "receita", fetch = FetchType.LAZY,
      targetEntity = TinturaUsoMisturaReceita.class)
  private List<TinturaUsoMisturaReceita> usoMistura;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_POMADA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_POM_USO_EST",
          referencedColumnName = "ID_POM_USO_EST"))
  @JsonBackReference
  private List<PomadaUsoEstoque> usoPomada;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_POMADA_SEM_CONTROLE",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_POM_SEM_CTL",
          referencedColumnName = "ID_POM_SEM_CTL"))
  @JsonBackReference
  private List<PomadaSemControle> usoPomadaSemControle;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_PLANTA_DESIDRATADA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_PLT_DES_USO_EST",
          referencedColumnName = "ID_PLT_DES_USO_EST"))
  @JsonBackReference
  private List<PlantaDesidratadaUsoEstoque> usoPlantaDesidratada;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_HOMEOPATIA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_HOM_DOS_USO",
          referencedColumnName = "ID_HOM_DOS_USO"))
  @JsonBackReference
  private List<HomeopatiaDosagensUso> usoHomeopatia;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_FLORAL",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_FLO_USO",
          referencedColumnName = "ID_FLO_USO"))
  @JsonBackReference
  private List<FloralUso> usoFloral;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_MULTIMISTURA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_MUM_USO",
          referencedColumnName = "ID_MUM_USO"))
  @JsonBackReference
  private List<MultiMisturaUso> usoMultimistura;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_CAPSULA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_CAP_USO_EST",
          referencedColumnName = "ID_CAP_USO_EST"))
  @JsonBackReference
  private List<CapsulaUsoEstoque> usoCapsula;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_OUTROS_MEDICAMENTOS",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_OUT_MED_USO_EST",
          referencedColumnName = "ID_OUT_MED_USO_EST"))
  @JsonBackReference
  private List<OutrosMedicamentosUsoEstoque> usoOutrosMedicamentos;

}
