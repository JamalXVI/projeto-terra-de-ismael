/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jamalxvi.terradeIsmael.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author henrique
 */
@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CLI")
    private Long id;
	@OneToOne(mappedBy="cliente")
	@Column(name="IDE_CLI")
    private Identificacao identificacao;
    @NotBlank @NotNull @Size(max=255)
	@Column(name="NOM_CLI")
    private String nome;
	@Column(name="RUA_CLI")
    private String rua;
	@Column(name="NUM_CLI")
    private String num;
	@Column(name="COM_CLI")
    private String complemento;
	@Column(name="BAI_CLI")
    private String bairro;
	@Column(name="INF_CLI")
    private String informacao;
	@Column(name="CEP_CLI")
    private String cep;
	@Column(name="CID_CLI")
    private String cidade;
	@Column(name="EST_CLI")
    private String estado;
	@Column(name="ATV_CLI")
    private Boolean ativo;
    @Email
	@Column(name="EML_CLI")
	private String email;
    @OneToMany(mappedBy="cliente")
	@Column(name="TEL_CLI")
    private List<TelefoneCliente> telefones;
    @OneToMany(mappedBy="cliente")
	@Column(name="FOT_CLI")
    private List<FotoPerfil> fotos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Identificacao getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(Identificacao identificacao) {
		this.identificacao = identificacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getInformacao() {
		return informacao;
	}
	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<TelefoneCliente> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<TelefoneCliente> telefones) {
		this.telefones = telefones;
	}
	public List<FotoPerfil> getFotos() {
		return fotos;
	}
	public void setFotos(List<FotoPerfil> fotos) {
		this.fotos = fotos;
	}
    
}
