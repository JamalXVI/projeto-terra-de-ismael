package br.com.jamalxvi.farmaciadanatureza.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Esta classe é a representante dos médicos dentro do sistema.
 * Todos os médicos apresentam:<br/>
 * -Entidade Base.<br/>
 * - CRM;<br/>
 * - a pessoa que representa o médico;<br/>
 * - todas as receitas de sua reponsabilidade;
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name = "MEDICO", uniqueConstraints = {@UniqueConstraint(columnNames = "CRM_MED")})
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_MED")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_MED")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_MED")),
})
@Builder
@Data
public class Medico extends EntidadeBase {
  @NotEmpty @NotNull @Size(max = 13)
  @Column(name = "CRM_MED")
  private String crm;
  @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JoinColumn(name="ID_PES")
  @JsonBackReference
  private Pessoa pessoa;
  @ManyToMany(mappedBy = "medico", targetEntity = Receita.class, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Receita> receitas;
}
