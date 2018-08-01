package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
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
 * Esta classe por relacionar o estoque de cápsulas com seu uso.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -Lote (Lotável).<br/>
 * -A Capsula de Origem.<br/>
 * -Data de criação do lote.<br/>
 * -Duração máxima de um lote.
 * <p>Unidade Padrão da Cápsula: gramas</p>
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "CAPSULA_USO_ESTOQUE")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_CAP_USO_EST")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_CAP_USO_EST")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_CAP_USO_EST"))
})
@Builder
@Data
public class CapsulaUsoEstoque extends EntidadeBase implements Etiquetavel{
    @Column(name = "POS_CAP_USO_EST")
    private String posologia;
    @Column(name = "VAL_CAP_USO_EST")
    private LocalDate validadeReceita;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CapsulaEstoque.class)
    @JoinColumn(name = "ID_CAP_EST")
    private CapsulaEstoque estoque;
}
