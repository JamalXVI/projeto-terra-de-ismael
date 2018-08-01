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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Esta classe é responsável por controlar o estoque das capsulas.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -A Capsula de Origem.<br/>
 * -Data de criação do lote.<br/>
 * -Duração máxima de um lote.
 * <p>Unidade Padrão da Cápsula: gramas</p>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "CAPSULA_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_CAP_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_CAP_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_CAP_EST"))
})
@Builder
@Data
public class CapsulaEstoque extends EntidadeBase implements Estocavel, Lotavel{
  @Column(name = "QTD_CAP_EST")
  private BigDecimal quantidade;
  @Column(name = "LOT_CAP_EST")
  private Long lote;
  @Column(name = "DAT_CRI_LOT_CAP_EST")
  private LocalDate dataCriacaoLote;
  @Column(name = "DAT_VEN_LOT_CAP_EST")
  private LocalDate dataVencimentoLote;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Capsula.class)
  @JoinColumn(name = "ID_CAP")
  private Capsula capsula;
  @OneToMany(fetch = FetchType.LAZY, targetEntity = CapsulaUsoEstoque.class)
  private List<CapsulaUsoEstoque> usoEstoques;
  @Override
  public EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.GRAMAS;
  }
}
