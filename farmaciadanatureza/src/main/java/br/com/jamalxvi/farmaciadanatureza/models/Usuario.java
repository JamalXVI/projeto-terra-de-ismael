/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jamalxvi.farmaciadanatureza.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Esta é a classe do usuário (representada pelo atendente). O usuário é o coração do sistema.
 * Esta classe também implementa a interface UserDetails (Utilizada para integrar com a segurança
 * do Spring Secutiry, pois boa parte do processo de autenticação é feito pelo Spring).
 * Todos os usuários possuem de atributos:<br/>
 * - id;<br/>
 * - usuario;<br/>
 * - senha;<br/>
 * - ativo;<br/>
 * - a pessoa que representa o usuário;<br/>
 * - todas as fotos de perfis do usuário;<br/>
 * - as permissões do usuário (Autoridade);<br/>
 * - as receitas que o usuário cadastrou no sistema;<br/>
 * - os registros dos logs do usuário.<br/>
 * Além destes atributos existem os médotos exigiros pela interface do Spring Boot.
 * @author Jamal XVI <henriquearantest@gmail.com>
 * @version 0.1
 * @since 0.1
 */
@Entity
@Table(name="USUARIO", uniqueConstraints = {@UniqueConstraint(columnNames = "USR_USR")})
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "ID_USR")),
		@AttributeOverride(name = "versao", column = @Column(name = "VER_USR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "DAT_CRI_USR")),
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario extends EntidadeBase implements UserDetails, Serializable {

	private static final long serialVersionUID = -4936434517036231231L;
	@NotNull
	@NotEmpty
	@Column(name="USR_USR")
	private String usuario;
	@NotEmpty @NotNull @Size(min = 3)
	@Column(name="SEN_USR")
	private String senha;
	@Column(name="ATV_USR")
	private Boolean ativo;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="ID_PES")
	@JsonBackReference
	private Pessoa pessoa;
    @OneToMany(mappedBy="usuario", targetEntity = FotoPerfil.class, fetch = FetchType.LAZY)
	@JsonManagedReference
    private List<FotoPerfil> fotos;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_autoridade",
			joinColumns = @JoinColumn(name = "ID_USR", referencedColumnName = "ID_USR"),
			inverseJoinColumns = @JoinColumn(name = "ID_AUT", referencedColumnName = "ID_AUT"))
	private List<Autoridade> autoridades;
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Receita.class, mappedBy = "usuario")
    private List<Receita> receitas;
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Log.class, mappedBy = "usuario")
	@JsonManagedReference
    private List<Log> logs;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autoridades;
	}

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    //TODO: IMPLEMENTAR OS MÉTODOS FALTANTES
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

}
