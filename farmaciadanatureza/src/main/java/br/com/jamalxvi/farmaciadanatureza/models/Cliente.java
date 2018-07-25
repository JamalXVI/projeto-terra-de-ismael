/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jamalxvi.farmaciadanatureza.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

//@Entity
//@Table(name="CLIENTE")
//@Data
@Deprecated
public class Cliente{
//	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="ID_CLI")
//    private Long id;
//	@OneToOne(mappedBy="cliente")
//	@JoinColumn(name="IDE_CLI")
//    private Identificacao identificacao;
//	@NotNull @Size(max=255)
//	@Column(name="NOM_CLI")
//    private String nome;
//    @NotNull @Size(max=255)
//    @Column(name="SOB_CLI")
//    private String sobrenome;
//	@Column(name="RUA_CLI")
//    private String rua;
//	@Column(name="NUM_CLI")
//    private String num;
//	@Column(name="COM_CLI")
//    private String complemento;
//	@Column(name="BAI_CLI")
//    private String bairro;
//	@Column(name="INF_CLI")
//    private String informacao;
//	@Column(name="CEP_CLI")
//    private String cep;
//	@Column(name="CID_CLI")
//    private String cidade;
//	@Column(name="EST_CLI")
//    private String estado;
//	@Column(name="ATV_CLI")
//    private Boolean ativo;
//    @Email
//	@Column(name="EML_CLI")
//	private String email;
//    @OneToMany(mappedBy="cliente")
//    private List<TelefoneCliente> telefones;
//    @OneToMany(mappedBy="cliente", targetEntity = FotoPerfil.class)
//    private List<FotoPerfil> fotos;
}
