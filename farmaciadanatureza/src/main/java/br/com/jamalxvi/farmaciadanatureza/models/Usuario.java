/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jamalxvi.farmaciadanatureza.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
 *
 * @author henrique
 */
@Entity
@Table(name="USUARIO", uniqueConstraints = {@UniqueConstraint(columnNames = "USR_USR")})
@Data
public class Usuario implements UserDetails, Serializable {

	private static final long serialVersionUID = -4936434517036231231L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USR")
	private Long id;
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
	private Pessoa pessoa;
    @OneToMany(mappedBy="usuario", targetEntity = FotoPerfil.class)
    private List<FotoPerfil> fotos;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_autoridade",
			joinColumns = @JoinColumn(name = "ID_USR", referencedColumnName = "ID_USR"),
			inverseJoinColumns = @JoinColumn(name = "ID_AUT", referencedColumnName = "ID_AUT"))
	private List<Autoridade> autoridades;
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

    // We can add the below fields in the users table.
	// For now, they are hardcoded.
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
