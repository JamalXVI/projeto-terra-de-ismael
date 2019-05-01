package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRINCIPIO_ATIVO")
@AttributeOverrides(value = {@AttributeOverride(name = "id", column = @Column(name = "ID_PRI_ATV")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_PRI_ATV")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_PRI_ATV"))})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipioAtivo extends EntidadeBase {
  @Column(name = "NOM_PRI_ATV")
  private String nome;

  @Column(name = "NOM_CIE_PRI_ATV")
  private String nomeCientifico;

  @OneToMany(targetEntity = MedicamentoPrincipioAtivo.class, fetch = FetchType.LAZY,
      mappedBy = "principioAtivo")
  private List<MedicamentoPrincipioAtivo> medicamentos;

  public String getNomePrincipal(){
    if (nomeCientifico != null && !nomeCientifico.isEmpty()) {
      return nomeCientifico;
    }
    return nome;
  }
}
