package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Misturavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
/**
 * Esta classe representa quando há mistura de tintura, a classe que faz a ligação do
 * uso da tintura com sua proporção. Seus atributos são:<br/>
 * -Entidade Base;
 * -Qual é a Dosagem de Tintura Escolhida
 * -Qual é a proporção a se usar
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "TINTURA_USO_MISTURA")
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "ID_TIN_USO_MIS")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_TIN_USO_MIS")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_TIN_USO_MIS"))
})
@Builder
@Data
public class TinturaUsoMistura extends EntidadeBase implements Misturavel<TinturaDosagens> {
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TinturaDosagensEstoque.class)
    @JoinColumn(name = "ID_TIN_DOS_USO_EST")
    @JsonBackReference
    private TinturaDosagensUsoEstoque dosagem;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TinturaUsoMisturaReceita.class)
    @JoinColumn(name = "ID_TIN_USO_MIS_REC")
    private TinturaUsoMisturaReceita receita;
    @Column(name = "PROP_TIN_USO_MIS")
    private BigDecimal proporcao;
}
