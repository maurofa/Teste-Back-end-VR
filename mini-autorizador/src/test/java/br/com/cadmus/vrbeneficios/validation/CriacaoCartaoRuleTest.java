package br.com.cadmus.vrbeneficios.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.exception.CartaoExistenteException;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.to.CartaoTO;

@ExtendWith(MockitoExtension.class)
class CriacaoCartaoRuleTest {

	@Mock private ICartaoDAO cartaoDAO;
	private CriacaoCartaoRule rule;
	private CartaoTO cartaoTO;
	
	@BeforeEach
	void inicio() {
		rule = new CriacaoCartaoRule(cartaoDAO);
		cartaoTO = new CartaoTO("numero", "senha");
	}
	
	@Test
	void testLancaExceptionSeJaTemCartao() {
		when(cartaoDAO.findByNumeroCartao(cartaoTO.numeroCartao())).thenReturn(new Cartao("numero", "senha"));
		
		CartaoExistenteException ex = assertThrows(CartaoExistenteException.class, () -> rule.validar(cartaoTO));
		
		assertEquals("Operação não realizada. Cartão já existe.", ex.getMessage());
	}

	@Test
	void testValidaSeNaoExisteCartao() {
		Assertions.assertDoesNotThrow(() -> rule.validar(cartaoTO));
	}
}
