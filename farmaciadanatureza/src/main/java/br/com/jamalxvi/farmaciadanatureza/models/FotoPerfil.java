package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="FOTO_PERFIL")
@Data
public class FotoPerfil{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_FOT")
	private Long id;
	@ManyToOne
	@JoinColumn(name="ID_USR")
	private Usuario usuario;
	@Column(name = "IMG_CLI", unique = false, nullable = false, length = 41943040)
	private byte[] img;
	@Column(name="ATV_FOT")
	private Boolean ativo;
	
}
