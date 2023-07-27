package br.com.cadmus.vrbeneficios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne @JoinColumn(name = "ID_CARTAO", nullable = false)
	private Cartao cartao;
	@Column(name = "VALOR_TRANSACAO")
	private Double valor;
	@Column(name = "DATA_TRANSACAO")
	private LocalDateTime dataHora;

	public Transacao(Cartao cartao, Double valor, LocalDateTime dataHora) {
		this.cartao = cartao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
}
