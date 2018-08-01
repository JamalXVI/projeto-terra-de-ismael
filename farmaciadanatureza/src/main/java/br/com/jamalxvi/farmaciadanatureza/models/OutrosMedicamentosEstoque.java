package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Esta classe é responsável por controlar o estoque dos demais medicamentos.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -O medicamento de Origem.<br/>
 * <p>Unidade Padrão destes tipos de medicamentos: unidades</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "OUTROS_MEDICAMENTOS_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_OUT_MED_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_OUT_MED_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_OUT_MED_EST"))
})
@Builder
@Data
public class OutrosMedicamentosEstoque extends EntidadeBase implements Estocavel {
  @Column(name = "QTD_OUT_MED_EST")
  private BigDecimal quantidade;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = OutrosMedicamentos.class)
  @JoinColumn(name = "ID_OUT_MED")
  private OutrosMedicamentos outrosMedicamentos;

  @Override
  public EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.UNIDADES;
  }
}
