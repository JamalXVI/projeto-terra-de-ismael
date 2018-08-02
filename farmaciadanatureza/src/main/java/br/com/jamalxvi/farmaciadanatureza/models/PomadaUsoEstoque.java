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
 * Esta classe por relacionar o estoque das pomadas com o seu uso.
 * Este estoque apresenta:<br/>
 * -Entidade Base;<br/>
 * -A quantidade;<br/>
 * -A Pomada Origem.<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "POMADA_USO_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_POM_USO_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_POM_USO_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_POM_USO_EST"))
})
@Builder
@Data
public class PomadaUsoEstoque extends EntidadeBase implements PodeUsarEstoque, Receitavel {
    @Column(name = "QTD_POM_USO_EST")
    private BigDecimal quantidade;
    @Column(name = "VAL_POM_USO_EST")
    private LocalDate validadeReceita;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PomadaEstoque.class)
    @JoinColumn(name = "ID_POM_EST")
    private PomadaEstoque estoque;
}
