package br.com.cadmus.vrbeneficios.cmd;

import java.util.Optional;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.exception.CartaoInexistenteException;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;

public class ObterSaldoCmd {
	private ICartaoDAO cartaoDAO;

	public ObterSaldoCmd(ICartaoDAO cartaoDAO) {
		this.cartaoDAO = cartaoDAO;
	}
	
	public Double get(String numeroCartao) {
		return Optional.ofNullable(cartaoDAO.findByNumeroCartao(numeroCartao))
					.map(Cartao::getSaldo)
					.orElseThrow(CartaoInexistenteException::new);
	}
}
