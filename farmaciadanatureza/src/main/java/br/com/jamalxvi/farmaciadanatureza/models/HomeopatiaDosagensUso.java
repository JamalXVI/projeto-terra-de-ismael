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
 * Esta classe refere-se somente ao uso da homeopatia.
 * Este uso Aprenta:<br/>
 * -Entidade Base;<br/>
 * -A Dosagem de Homeopatia de Origem;<br/>
 * -A quantidade;<br/>
 * -A Posologia;<br/>
 * -A Validade;<br/>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "HOMEOPATIA_DOSAGEM_USO")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_HOM_DOS_USO")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_HOM_DOS_USO")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_HOM_DOS_USO"))
})
@Builder
@Data
public class HomeopatiaDosagensUso extends EntidadeBase implements Etiquetavel, Receitavel {
    @Column(name = "POS_HOM_DOS_USO")
    private String posologia;
    @Column(name = "VAL_HOM_DOS_USO")
    private LocalDate validadeReceita;
    @Column(name = "QTD_HOM_DOS_USO")
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = HomeopatiaDosagens.class)
    @JoinColumn(name = "ID_HOM_DOS")
    private HomeopatiaDosagens origem;
}
