package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Estocavel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Lotavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Esta classe é responsável por controlar o estoque das tinturas dosadas.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -A Tintura (Dosagem) de Origem.<br/>
 * <p>Unidade Padrão das Tinturas: mililitros</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "TINTURA_DOSAGEM_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_TIN_DOS_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_TIN_DOS_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_TIN_DOS_EST"))
})
@Builder
@Data
public class TinturaDosagensEstoque extends EntidadeBase implements Estocavel, Lotavel {
  @Column(name = "QTD_TIN_DOS_EST")
  private BigDecimal quantidade;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = HomeopatiaDosagens.class)
  @JoinColumn(name = "ID_TIN_DOS")
  @JsonBackReference
  private TinturaDosagens tinturaDosagens;
  @Column(name = "LOT_TIN_DOS_EST")
  private Long lote;
  @Column(name = "DAT_CRI_LOT_TIN_DOS_EST")
  private LocalDate dataCriacaoLote;
  @Column(name = "DAT_VEN_LOT_TIN_DOS_EST")
  private LocalDate dataVencimentoLote;
  @OneToMany(mappedBy = "estoque", fetch = FetchType.LAZY,
      targetEntity = TinturaDosagensUsoEstoque.class)
  private List<TinturaDosagensUsoEstoque> usoEstoques;

  public static EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.MILILITROS;
  }
}
