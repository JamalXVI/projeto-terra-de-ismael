package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Receitavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
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
@Table(name = "MULTIMISTURA_USO")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_MUM_USO")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_MUM_USO")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_MUM_USO"))
})
@Builder
@Data
public class MultiMisturaUso extends EntidadeBase implements Receitavel {
    @Column(name = "QTD_MUM_USO")
    private BigDecimal quantidade;
    @Column(name = "VAL_MUM_USO")
    private LocalDate validadeReceita;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MultiMistura.class)
    @JoinColumn(name = "ID_MUM")
    @JsonBackReference
    private MultiMistura origem;
}
