package br.com.jamalxvi.farmaciadanatureza.models;

import br.com.jamalxvi.farmaciadanatureza.enums.EnumOutrosMedicamentos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Esta classe é a responsável por definir os outros tipos de medicamentos. Estes medicamentos
 * são considerados diversos pois eles tem apenas um controle de estoque com a saída (Sem
 * impressão de etiquetas). São definidos como outros tipos de medicamentos:
 * -Xarope;<br/>
 * -Solução Nasal;<br/>
 * -Extrato Aquoso;<br/>
 * -Óleos Medicinais;<br/>
 * -Shampoo;<br/>
 * -Sabonete;<br/>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "OUTROS_MEDICAMENTOS")
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_OUT_MED")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_OUT_MED")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_OUT_MED")),
})
@Builder
@Data
public class OutrosMedicamentos extends EntidadeBase {
  @Column(name = "NOM_OUT_MED")
  @NotNull
  @NotEmpty
  @Size(max = 255)
  private String nome;
  @Enumerated(EnumType.STRING)
  @Column(name = "TIP_MED_OUT_MED", columnDefinition = "enum('EXTRATO_AQUOSO', " +
      "'OLEOS_MEDICINAIS','SABONETE', 'SHAMPOO', 'SOLUCAO_NASAL', 'XAROPE', 'CHA', 'CAPSULA','MEDICAMENTOS', 'OUTROS'")
  private EnumOutrosMedicamentos tipoMedicamento;
  @OneToMany(mappedBy = "outrosMedicamentos", fetch = FetchType.LAZY,
      targetEntity = OutrosMedicamentosEstoque.class)
  @JsonManagedReference
  private List<OutrosMedicamentosEstoque> estoque;
}
