package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumUnidadesMetricas;
import br.com.jamalxvi.farmaciadanatureza.models.interfaces.Estocavel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Esta classe é responsável por controlar o estoque dos demais medicamentos.
 * Este estoque apresenta:<br/>
 * -Entidade Base.<br/>
 * -O medicamento de Origem.<br/>
 * <p>Unidade Padrão destes tipos de medicamentos: unidades</p>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "OUTROS_MEDICAMENTOS_ESTOQUE")
@AttributeOverrides(value = {
        @AttributeOverride(name = "id", column = @Column(name = "ID_OUT_MED_EST")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_OUT_MED_EST")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_OUT_MED_EST"))
})
@Builder
@Data
public class OutrosMedicamentosEstoque extends EntidadeBase implements Estocavel, Lotavel {
    @Column(name = "QTD_OUT_MED_EST")
    private BigDecimal quantidade;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = OutrosMedicamentos.class)
    @JoinColumn(name = "ID_OUT_MED")
    @JsonBackReference
    private OutrosMedicamentos outrosMedicamentos;
    @OneToMany(mappedBy = "estoque", targetEntity = OutrosMedicamentosUsoEstoque.class,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OutrosMedicamentosUsoEstoque> usoEstoque;
    @Column(name = "LOT_OUT_MED_EST")
    private Long lote;
    @Column(name = "DAT_CRI_LOT_OUT_MED_EST")
    private LocalDate dataCriacaoLote;
    @Column(name = "DAT_VEN_LOT_OUT_MED_EST")
    private LocalDate dataVencimentoLote;

    @Override
    public EnumUnidadesMetricas getUnidade() {
        return EnumUnidadesMetricas.UNIDADES;
    }
}
