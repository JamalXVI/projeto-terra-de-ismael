package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Etiquetavel;
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
 * Esta classe por relacionar o estoque das dosagens de homeopatia com seu uso.
 * Este estoque apresenta:<br/>
 * -Entidade Base;<br/>
 * -Lote (Lotável);<br/>
 * -A Dosagem de Homeopatia de Origem;<br/>
 * -Data de criação do lote;<br/>
 * -A quantidade;<br/>
 * -Duração máxima de um lote.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "HOMEOPATIA_DOSAGEM_USO_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_HOM_DOS_USO_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_HOM_DOS_USO_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_HOM_DOS_USO_EST"))
})
@Builder
@Data
public class HomeopatiaDosagensUsoEstoque extends EntidadeBase implements Etiquetavel,
    PodeUsarEstoque, Receitavel {
    @Column(name = "POS_HOM_DOS_USO_EST")
    private String posologia;
    @Column(name = "VAL_HOM_DOS_USO_EST")
    private LocalDate validadeReceita;
    @Column(name = "QTD_HOM_DOS_USO_EST")
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = HomeopatiaDosagensEstoque.class)
    @JoinColumn(name = "ID_HOM_DOS_EST")
    private HomeopatiaDosagensEstoque estoque;
}
