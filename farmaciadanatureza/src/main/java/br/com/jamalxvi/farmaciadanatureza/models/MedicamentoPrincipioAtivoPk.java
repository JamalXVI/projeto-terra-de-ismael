package br.com.jamalxvi.farmaciadanatureza.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@Builder
public class MedicamentoPrincipioAtivoPk implements Serializable {
  @Column(name = "ID_PRI_ATV")
  private Long idPrincipioAtivo;

  @Column(name = "ID_MED")
  private Long idMedicamento;
}
