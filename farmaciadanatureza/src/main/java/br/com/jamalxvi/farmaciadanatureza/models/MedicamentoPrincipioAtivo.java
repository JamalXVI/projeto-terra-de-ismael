package br.com.jamalxvi.farmaciadanatureza.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEDICAMENTO_PRINCIPIO_ATIVO")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoPrincipioAtivo{
    @EmbeddedId
    private MedicamentoPrincipioAtivoPk chave;

    @JoinColumn(name = "ID_PRI_ATV")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PrincipioAtivo.class)
    @MapsId("chave.idPrincipioAtivo")
    private PrincipioAtivo principioAtivo;

    @JoinColumn(name = "ID_MED")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Medicamento.class)
    @MapsId("chave.idMedicamento")
    private Medicamento medicamento;

    @Column(name = "PROP_MED_PRI_ATV")
    @NotNull
    private Integer proporcao;

    public String getNome(){
        if (!getPrincipioAtivo().getNomeCientifico().isEmpty()){
            return getPrincipioAtivo().getNomeCientifico();
        }
        return getPrincipioAtivo().getNome();
    }
}
