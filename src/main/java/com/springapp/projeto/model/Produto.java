package com.springapp.projeto.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Produto implements Serializable {
	// atributos
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long codigoProduto;
	@NotEmpty
	private String codBarras;
	@NotEmpty
	private String descricao;
	@NotEmpty
	private String marca;
	@NotEmpty
	private String modelo;
	@NotEmpty
	private String valorC;
	@NotEmpty
	private double valorV;
	@NotEmpty
	private String peso;
	@NotEmpty
	private String fornecedor;
	@NotEmpty
	private String imagem;
		
	// métodos get e set
	public long getCodigoProduto() {
		return codigoProduto;
	}
	
	public void setCodigoProduto(long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getValorC() {
		return valorC;
	}

	public void setValorC(String valorC) {
		this.valorC = valorC;
	}

	public double getValorV() {
		return valorV;
	}

	public void setValorV(double valorV) {
		this.valorV = valorV;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}