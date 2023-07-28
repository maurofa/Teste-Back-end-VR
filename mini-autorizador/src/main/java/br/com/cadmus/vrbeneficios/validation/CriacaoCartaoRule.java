package br.com.cadmus.vrbeneficios.validation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.cadmus.vrbeneficios.exception.CartaoExistenteException;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.to.CartaoTO;

@Component
public class CriacaoCartaoRule {

	private ICartaoDAO cartaoDAO;

	public CriacaoCartaoRule(ICartaoDAO cartaoDAO) {
		this.cartaoDAO = cartaoDAO;
	}

	public void validar(CartaoTO cartaoTO) {
		Optional.ofNullable(cartaoDAO.findByNumeroCartao(cartaoTO.numeroCartao()))
				.ifPresent(cartao -> new CartaoExistenteException());
	}
}
