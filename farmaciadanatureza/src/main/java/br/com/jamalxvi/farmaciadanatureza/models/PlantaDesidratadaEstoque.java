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
import java.time.LocalDate;

/**
 * Esta classe é responsável por controlar o estoque das plantas desidratas.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -A Planta Desidratada de Origem.<br/>
 * -Data de criação do lote.<br/>
 * -Duração máxima de um lote.<br/>
 * <p>Unidade Padrão da planta desidratada: sacos.</p>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "PLANTA_DESIDRATADA_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_PLT_DES_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_PLT_DES_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_PLT_DES_EST"))
})
@Builder
@Data
public class PlantaDesidratadaEstoque extends EntidadeBase implements Estocavel, Lotavel{
  @Column(name = "QTD_PLT_DES_EST")
  private BigDecimal quantidade;
  @Column(name = "LOT_PLT_DES_EST")
  private Long lote;
  @Column(name = "DAT_CRI_LOT_PLT_DES_EST")
  private LocalDate dataCriacaoLote;
  @Column(name = "DAT_VEN_LOT_PLT_DES_EST")
  private LocalDate dataVencimentoLote;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = PlantaDesidratada.class)
  @JoinColumn(name = "ID_PLT_DES")
  private PlantaDesidratada plantaDesidratada;

  @Override
  public EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.GRAMAS;
  }
}
