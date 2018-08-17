package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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
  private Medico medico;
  @ManyToOne(targetEntity = Usuario.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_USR")
  private Usuario usuario;
  @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_PES")
  private Pessoa pessoa;
  @Column(name = "VAL_REC")
  private LocalDate validadeReceita;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_TINTURA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_TIN_DOS_USO_EST",
          referencedColumnName = "ID_TIN_DOS_USO_EST"))
  private List<TinturaDosagensUsoEstoque> usoTintura;
  @OneToMany(mappedBy = "receita", fetch = FetchType.LAZY,
          targetEntity = TinturaUsoMisturaReceita.class)
  private List<TinturaUsoMisturaReceita> usoMistura;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_POMADA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_POM_USO_EST",
          referencedColumnName = "ID_POM_USO_EST"))
  private List<PomadaUsoEstoque> usoPomada;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_PLANTA_DESIDRATADA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_PLT_DES_USO_EST",
          referencedColumnName = "ID_PLT_DES_USO_EST"))
  private List<PlantaDesidratadaUsoEstoque> usoPlantaDesidratada;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_HOMEOPATIA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_HOM_DOS_USO",
          referencedColumnName = "ID_HOM_DOS_USO"))
  private List<HomeopatiaDosagensUso> usoHomeopatia;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_FLORAL",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_FLO_USO",
          referencedColumnName = "ID_FLO_USO"))
  private List<FloralUso> usoFloral;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_CAPSULA",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_CAP_USO_EST",
          referencedColumnName = "ID_CAP_USO_EST"))
  private List<CapsulaUsoEstoque> usoCapsula;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "RECEITA_OUTROS_MEDICAMENTOS",
      joinColumns = @JoinColumn(name = "ID_REC", referencedColumnName = "ID_REC"),
      inverseJoinColumns = @JoinColumn(name = "ID_OUT_MED_USO_EST",
          referencedColumnName = "ID_OUT_MED_USO_EST"))
  private List<OutrosMedicamentosUsoEstoque> usoOutrosMedicamentos;

}
