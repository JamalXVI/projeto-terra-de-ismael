package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.PodeUsarEstoque;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Receitavel;
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
 * Esta classe por relacionar o estoque dos demais medicamentos com o seu uso.
 * Este estoque apresenta:<br/>
 * -Entidade Base;<br/>
 * -A quantidade;<br/>
 * -O medicamento de Origem.<br/>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "OUTROS_MEDICAMENTOS_USO_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_OUT_MED_USO_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_OUT_MED_USO_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_OUT_MED_USO_EST"))
})
@Builder
@Data
public class OutrosMedicamentosUsoEstoque extends EntidadeBase
    implements PodeUsarEstoque, Receitavel {
  @Column(name = "QTD_OUT_MED_USO_EST")
  private BigDecimal quantidade;
  @Column(name = "VAL_OUT_MED_USO_EST")
  private LocalDate validadeReceita;
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = OutrosMedicamentosEstoque.class)
  @JoinColumn(name = "ID_OUT_MED_EST")
  private OutrosMedicamentosEstoque estoque;
}
