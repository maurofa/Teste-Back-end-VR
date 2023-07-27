package br.com.cadmus.vrbeneficios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cartao")
public class Cartao {

	private static final double VALOR_INICIAL = 500.0;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NUMERO_CARTAO")
	private String numeroCartao;
	@Column(name = "SENHA_CARTAO")
	private String senha;
	@Column(name = "SALDO_CARTAO")
	private Double saldo;

	public Cartao(String numeroCartao, String senha) {
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		saldo = VALOR_INICIAL;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public Double getSaldo() {
		return saldo;
	}
}
