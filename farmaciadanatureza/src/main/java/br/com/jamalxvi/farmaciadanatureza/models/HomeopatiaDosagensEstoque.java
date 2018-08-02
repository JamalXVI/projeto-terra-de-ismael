package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Estocavel;
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
import java.util.List;

/**
 * Esta classe é responsável por controlar o estoque das homeopatias dosadas.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -A Homeopatia (Dosagem) de Origem.<br/>
 * <p>Unidade Padrão das Homeopatia: mililitros</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "HOMEOPATIA_DOSAGEM_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_HOM_DOS_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_HOM_DOS_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_HOM_DOS_EST"))
})
@Builder
@Data
public class HomeopatiaDosagensEstoque extends EntidadeBase implements Estocavel {
  @Column(name = "QTD_HOM_DOS_EST")
  private BigDecimal quantidade;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = HomeopatiaDosagens.class)
  @JoinColumn(name = "ID_HOM_DOS")
  private HomeopatiaDosagens homeopatiaDosagem;
  @OneToMany(fetch = FetchType.LAZY, targetEntity = HomeopatiaDosagensUsoEstoque.class,
  mappedBy = "estoque")
  private List<HomeopatiaDosagensUsoEstoque> usoEstoque;
  @Override
  public EnumUnidadesMetricas getUnidade() {
    return EnumUnidadesMetricas.MILILITROS;
  }
}
