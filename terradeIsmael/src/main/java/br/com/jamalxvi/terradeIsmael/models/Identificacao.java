package br.com.jamalxvi.terradeIsmael.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="IDENTIFICACAO_CLIENTE")
public class Identificacao implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_IDE")
	private Long id;
	@OneToOne
	@Column(name="CLI_IDE")
	private Cliente cliente;
	@Column(name="CPF_IDE")
	private String cpf;
	@Column(name="TIPO_IDE")
	private String tipo;
	@Column(name="CNPJ_IDE")
	private String cnpj;
	@Column(name="IE_IDE")
	private String inscricao_estadual;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricao_estadual() {
		return inscricao_estadual;
	}
	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}
	
}
