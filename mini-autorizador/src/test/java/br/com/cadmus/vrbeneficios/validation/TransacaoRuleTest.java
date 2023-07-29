package br.com.cadmus.vrbeneficios.validation;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.exception.CartaoInexistenteException;
import br.com.cadmus.vrbeneficios.exception.SaldoInsuficienteException;
import br.com.cadmus.vrbeneficios.exception.SenhaInvalidaException;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.to.TransacaoTO;

@ExtendWith(MockitoExtension.class)
class TransacaoRuleTest {

	@Mock private ICartaoDAO cartaoDAO;
	private TransacaoRule rule;
	private TransacaoTO transacao;
	
	@BeforeEach
	void inicio() {
		rule= new TransacaoRule(cartaoDAO);
		transacao = new TransacaoTO("1111222233334444", "123", 322.0);
	}
	
	@Test
	void testReduzSaldo() {
		Cartao cartao = new Cartao(transacao.numeroCartao(), transacao.senhaCartao());
		when(cartaoDAO.findByNumeroCartao(transacao.numeroCartao())).thenReturn(cartao);
		
		Assertions.assertDoesNotThrow(() -> rule.validar(transacao));
	}

	@Test
	void testCartaoInexistente() {
		CartaoInexistenteException ex = Assertions.assertThrows(CartaoInexistenteException.class, () -> rule.validar(transacao));
		
		Assertions.assertEquals("Operação não realizada. Cartão inexistente!", ex.getMessage());
	}
	
	@Test
	void testSenhaInvalida() {
		Cartao cartao = new Cartao(transacao.numeroCartao(), "123123");
		when(cartaoDAO.findByNumeroCartao(transacao.numeroCartao())).thenReturn(cartao);
		
		SenhaInvalidaException ex = Assertions.assertThrows(SenhaInvalidaException.class, () -> rule.validar(transacao));
		
		Assertions.assertEquals("Operação não realizada. Senha inválida!", ex.getMessage());
	}
	
	@Test
	void testSaldoInsuficiente() {
		Cartao cartao = new Cartao(transacao.numeroCartao(), transacao.senhaCartao());
		when(cartaoDAO.findByNumeroCartao(transacao.numeroCartao())).thenReturn(cartao);
		
		transacao = new TransacaoTO(cartao.getNumeroCartao(), cartao.getSenha(), 500.01);
		SaldoInsuficienteException ex = Assertions.assertThrows(SaldoInsuficienteException.class, () -> rule.validar(transacao));
		
		Assertions.assertEquals("Operação não realizada. Saldo insuficiente!", ex.getMessage());
	}
}
