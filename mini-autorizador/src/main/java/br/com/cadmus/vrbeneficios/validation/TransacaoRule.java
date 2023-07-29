package br.com.cadmus.vrbeneficios.validation;

import java.util.Optional;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.exception.CartaoInexistenteException;
import br.com.cadmus.vrbeneficios.exception.SaldoInsuficienteException;
import br.com.cadmus.vrbeneficios.exception.SenhaInvalidaException;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.to.TransacaoTO;

public class TransacaoRule {
	private ICartaoDAO cartaoDAO;

	public TransacaoRule(ICartaoDAO cartaoDAO) {
		this.cartaoDAO = cartaoDAO;
	}
	
	public void validar(TransacaoTO transacao) {
		Cartao cartao = Optional.ofNullable(cartaoDAO.findByNumeroCartao(transacao.numeroCartao())).orElseThrow(CartaoInexistenteException::new);
		if(!transacao.senhaCartao().equals(cartao.getSenha())) {
			throw new SenhaInvalidaException();
		}
		if(cartao.getSaldo() < transacao.valor()) {
			throw new SaldoInsuficienteException();
		}
	}
}
