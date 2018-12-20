package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Etiquetavel;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.PodeUsarEstoque;
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
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Esta classe por relacionar o estoque das dosagens de tinturas com seu uso.
 * Este estoque apresenta:<br/>
 * -Entidade Base;<br/>
 * -Lote (Lotável);<br/>
 * -A Dosagem da Tintura de Origem;<br/>
 * -Data de criação do lote;<br/>
 * -A quantidade;<br/>
 * -Duração máxima de um lote.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "TINTURA_DOSAGEM_USO_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_TIN_DOS_USO_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_TIN_DOS_USO_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_TIN_DOS_USO_EST"))
})
@Builder
@Data
public class TinturaDosagensUsoEstoque extends EntidadeBase implements Etiquetavel,
    PodeUsarEstoque {
    @Column(name = "POS_TIN_DOS_USO_EST")
    private String posologia;
    @Column(name = "VAL_TIN_DOS_USO_EST")
    private LocalDate validadeReceita;
    @Column(name = "QTD_TIN_DOS_USO_EST")
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TinturaDosagensEstoque.class)
    @JoinColumn(name = "ID_TIN_DOS_EST")
    @JsonBackReference
    private TinturaDosagensEstoque estoque;
}
