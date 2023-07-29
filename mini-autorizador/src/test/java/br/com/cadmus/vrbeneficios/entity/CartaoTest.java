package br.com.cadmus.vrbeneficios.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartaoTest {

	private Cartao cartao;
	
	@BeforeEach
	void inicio() {
		cartao = new Cartao("1111222233334444", "123123");
	}
	
	@Test
	void testSaldoInicial500() {
		assertEquals(500.0, cartao.getSaldo());
	}

	@Test
	void testDiminuiSaldo() {
		var valor = 125.32;
		cartao.diminuiSaldo(valor);
		
		assertEquals(500.0 - valor, cartao.getSaldo());
	}
}
