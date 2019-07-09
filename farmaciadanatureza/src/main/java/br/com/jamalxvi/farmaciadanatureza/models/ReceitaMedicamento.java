package br.com.jamalxvi.farmaciadanatureza.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RECEITA_MEDICAMENTO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaMedicamento{
    @EmbeddedId
    private ReceitaMedicamentoPk chave;

    @JoinColumn(name = "ID_REC")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Receita.class)
    @MapsId("chave.idReceita")
    private Receita receita;

    @JoinColumn(name = "ID_MED")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Medicamento.class)
    @MapsId("chave.idMedicamento")
    private Medicamento medicamento;


    @Column(name = "QTD_REC_MED")
    private BigDecimal quantidade;

    @Column(name = "PES_REC_MED")
    private BigDecimal peso;

    @Column(name = "LOT_REC_MED")
    private Long lote;

    @Column(name = "POS_REC_MED")
    private String posologia;

    @Column(name = "VAL_REC_MED")
    private LocalDate validadeReceita;
}
