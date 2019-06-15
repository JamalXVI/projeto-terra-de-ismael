package br.com.jamalxvi.farmaciadanatureza.models;

import javax.persistence.*;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumTipoMedicamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "MEDICAMENTO")
@AttributeOverrides(value = {@AttributeOverride(name = "id", column = @Column(name = "ID_MED")),
        @AttributeOverride(name = "versao", column = @Column(name = "VER_MED")),
        @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_MED"))})
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento extends EntidadeBase{
    @Enumerated(value = EnumType.STRING)
    @Column(name = "TIP_MED")
    private EnumTipoMedicamento tipoMedicamento;

    @OneToMany(targetEntity = MedicamentoPrincipioAtivo.class, fetch = FetchType.LAZY,
            mappedBy = "medicamento")
    private List<MedicamentoPrincipioAtivo> principioAtivo;

    @Column(name = "PES_MED")
    private BigDecimal peso;

    @OneToMany(targetEntity = ReceitaMedicamento.class, fetch = FetchType.LAZY,
            mappedBy = "medicamento")
    private List<ReceitaMedicamento> receitaMedicamentos;

}
