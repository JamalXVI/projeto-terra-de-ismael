package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="PESSOA", uniqueConstraints = {@UniqueConstraint(columnNames = "CPF_PES")})
@Data
@Builder
public class Pessoa {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID_PES")
    private Long id;
    @NotNull @NotEmpty
    @Size(max=255)
    @Column(name="NOM_PES")
    private String nome;
    @NotNull @Size(max=255) @NotEmpty
    @Column(name="SOB_PES")
    private String sobrenome;
    @NotNull @Size(max=255)
    @Column(name="CPF_PES")
    @NotEmpty @NotNull @Size(max = 14)
    @Pattern(regexp = "([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}-[0-9]{2})")
    private String cpf;

    @OneToOne(mappedBy = "pessoa",fetch = FetchType.LAZY)
    private Usuario usuario;

    @OneToOne(mappedBy = "pessoa",fetch = FetchType.LAZY)
    private Medico medico;
}
