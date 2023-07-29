package br.com.cadmus.vrbeneficios.cmd;

import java.time.LocalDateTime;

import br.com.cadmus.vrbeneficios.entity.Cartao;
import br.com.cadmus.vrbeneficios.entity.Transacao;
import br.com.cadmus.vrbeneficios.repository.ICartaoDAO;
import br.com.cadmus.vrbeneficios.repository.ITransacaoDAO;
import br.com.cadmus.vrbeneficios.to.TransacaoTO;
import br.com.cadmus.vrbeneficios.validation.TransacaoRule;

public class FazerTransacaoCmd {
	
	private ICartaoDAO cartaoDAO;
	private TransacaoRule transacaoRule;
	private ITransacaoDAO transacaoDAO;

	public FazerTransacaoCmd(ICartaoDAO cartaoDAO, TransacaoRule transacaoRule, ITransacaoDAO transacaoDAO) {
		this.cartaoDAO = cartaoDAO;
		this.transacaoRule = transacaoRule;
		this.transacaoDAO = transacaoDAO;
	}

	public void faz(TransacaoTO transacao) {
		transacaoRule.validar(transacao);
		Cartao cartao = cartaoDAO.findByNumeroCartao(transacao.numeroCartao());
		cartao.diminuiSaldo(transacao.valor());
		cartaoDAO.save(cartao);
		// Assumi que depois que o sistema autoriza a transação ele faz a transação
		transacaoDAO.save(new Transacao(cartao, transacao.valor(), LocalDateTime.now()));
	}

}
