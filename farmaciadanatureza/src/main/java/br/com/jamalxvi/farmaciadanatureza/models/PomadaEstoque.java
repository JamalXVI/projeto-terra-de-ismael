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
 * Esta classe é responsável por controlar o estoque das pomadas.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -A pomada de origem.<br/>
 * <p>Unidade Padrão de pomadas: unidades.</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "POMADA_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_POM_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_POM_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_POM_EST"))
})
@Builder
@Data
public class PomadaEstoque extends EntidadeBase implements Estocavel {
  @Column(name = "QTD_POM_EST")
  private BigDecimal quantidade;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pomada.class)
  @JoinColumn(name = "ID_POM")
  private Pomada pomada;

  @Override
  public EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.UNIDADES;
  }
}
