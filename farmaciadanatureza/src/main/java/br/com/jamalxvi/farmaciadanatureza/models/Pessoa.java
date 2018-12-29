package br.com.jamalxvi.farmaciadanatureza.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Esta classe representa o coração de todas as pessoas do sistema. Isso vale tanto para o:<br/>
 * - Usuário;<br/>
 * - Paciente (Própria pessoa);<br/>
 * - Médico; <br/>
 * Além disso a pessoa, como paciente, também possui o histórico de todas as suas receitas.
 * Também é importante definir os atributos das pessoas:<br/>
 * -Nome;<br/>
 * -Sobrenome;<br/>
 * -Cpf;<br/>
 * -Receitas;<br/>
 *
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name="PESSOA", uniqueConstraints = {@UniqueConstraint(columnNames = "CPF_PES")})
@AttributeOverrides(value = {
    @AttributeOverride(name = "id", column = @Column(name = "ID_PES")),
    @AttributeOverride(name = "versao", column = @Column(name = "VER_PES")),
    @AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_PES"))
})
@Data
@Builder
public class Pessoa extends EntidadeBase {

    @NotNull @NotEmpty
    @Size(max=255)
    @Column(name="NOM_PES")
    private String nome;

    @NotNull @Size(max=255) @NotEmpty
    @Column(name="SOB_PES")
    private String sobrenome;

    @Column(name = "ATV_PES")
    private Boolean ativo;

    @NotNull @Size(max=255)
    @Column(name="CPF_PES")
    @NotEmpty @NotNull @Size(max = 14)
    @Pattern(regexp = "([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}-[0-9]{2})")
    private String cpf;

    @OneToOne(mappedBy = "pessoa",fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    private Usuario usuario;

    @OneToOne(mappedBy = "pessoa",fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    private Medico medico;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Receita.class, mappedBy = "pessoa")
    private List<Receita> receitas;

}
