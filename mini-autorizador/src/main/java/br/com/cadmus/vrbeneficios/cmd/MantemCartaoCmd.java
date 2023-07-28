package br.com.cadmus.vrbeneficios.cmd;

import org.springframework.stereotype.Service;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.to.CartaoTO;
import br.com.cadmus.vrbeneficios.validation.CriacaoCartaoRule;

@Service
public class MantemCartaoCmd {

	private ICartaoDAO cartaoDAO;
	private CriacaoCartaoRule criacaoCartaoRule;

	public MantemCartaoCmd(ICartaoDAO cartaoDAO, CriacaoCartaoRule criacaoCartaoRule) {
		this.cartaoDAO = cartaoDAO;
		this.criacaoCartaoRule = criacaoCartaoRule;
	}

	public CartaoTO criaCartao(CartaoTO cartaoTO) {
		criacaoCartaoRule.validar(cartaoTO);
		Cartao cartao = new Cartao(cartaoTO.numeroCartao(), cartaoTO.senha());
		cartaoDAO.save(cartao);
		return cartaoTO;
	}
}
