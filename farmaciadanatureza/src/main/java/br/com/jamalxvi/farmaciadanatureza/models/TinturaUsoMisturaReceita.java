package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Esta classe é a classe que registra quando há mistura de tinturas.
 * Ela armezana a quantidade original, as tinturas usadas e suas respectivas proporções.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "TINTURA_USO_MISTURA_RECEITA")
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "ID_TIN_USO_MIS_REC")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_TIN_USO_MIS_REC")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_TIN_USO_MIS_REC"))
})
@Builder
@Data
public class TinturaUsoMisturaReceita extends EntidadeBase{
    @OneToMany(mappedBy = "receita", fetch = FetchType.LAZY,
            targetEntity = TinturaUsoMistura.class)
    private List<TinturaUsoMistura> usoMistura;
    @Column(name = "QTD_TIN_USO_MIS_REC")
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Receita.class)
    @JoinColumn(name = "ID_REC")
    private Receita receita;
}
