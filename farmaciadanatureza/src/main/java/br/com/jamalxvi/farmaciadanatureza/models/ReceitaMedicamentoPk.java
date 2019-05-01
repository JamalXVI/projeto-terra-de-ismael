package br.com.jamalxvi.farmaciadanatureza.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ReceitaMedicamentoPk implements Serializable {
  @Column(name = "ID_REC")
  private Long idReceita;

  @Column(name = "ID_MED")
  private Long idMedicamento;
}
