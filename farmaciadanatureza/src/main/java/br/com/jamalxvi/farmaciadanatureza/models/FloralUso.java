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
 * Esta classe é responsável por registrar a saida de florais (sem controle de estoque)
 * Este estoque apresenta:<br/>
 * -Entidade Base;<br/>
 * -A quantidade;<br/>
 * -O floral de Origem.<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "FLORAL_USO")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_FLO_USO")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_FLO_USO")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_FLO_USO"))
})
@Builder
@Data
public class FloralUso extends EntidadeBase implements Receitavel {
    @Column(name = "QTD_FLO_USO")
    private BigDecimal quantidade;
    @Column(name = "VAL_FLO_USO")
    private LocalDate validadeReceita;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Floral.class)
    @JoinColumn(name = "ID_FLO")
    private Floral origem;
}
