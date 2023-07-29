package br.com.cadmus.vrbeneficios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadmus.vrbeneficios.cmd.FazerTransacaoCmd;
import br.com.cadmus.vrbeneficios.cmd.MantemCartaoCmd;
import br.com.cadmus.vrbeneficios.cmd.ObterSaldoCmd;
import br.com.cadmus.vrbeneficios.exception.CartaoExistenteException;
import br.com.cadmus.vrbeneficios.exception.CartaoInexistenteException;
import br.com.cadmus.vrbeneficios.exception.SaldoInsuficienteException;
import br.com.cadmus.vrbeneficios.exception.SenhaInvalidaException;
import br.com.cadmus.vrbeneficios.to.CartaoTO;
import br.com.cadmus.vrbeneficios.to.TransacaoTO;
import jakarta.validation.Valid;

@RestController
public class MiniAutorizadorController {

	private static final Logger LOG = LoggerFactory.getLogger(MiniAutorizadorController.class);
	private MantemCartaoCmd mantemCartaoCmd;
	private ObterSaldoCmd obterSaldoCmd;
	private FazerTransacaoCmd fazerTransacaoCmd;

	public MiniAutorizadorController(MantemCartaoCmd mantemCartaoCmd, ObterSaldoCmd obterSaldoCmd, FazerTransacaoCmd fazerTransacaoCmd) {
		this.mantemCartaoCmd = mantemCartaoCmd;
		this.obterSaldoCmd = obterSaldoCmd;
		this.fazerTransacaoCmd = fazerTransacaoCmd;
	}

	@Transactional
	@PostMapping("/cartoes")
	public ResponseEntity<CartaoTO> criaCartao(@Valid @RequestBody CartaoTO cartao) {
		try {
			return new ResponseEntity<>(mantemCartaoCmd.criaCartao(cartao), HttpStatus.CREATED);
		} catch (CartaoExistenteException ex) {
			LOG.error("Cartão Existente", ex);
			return new ResponseEntity<>(cartao, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Transactional
	@GetMapping("/cartoes/{numeroCartao}")
	public ResponseEntity<Double> getSaldo(@PathVariable String numeroCartao) {
		try {
			return new ResponseEntity<>(obterSaldoCmd.get(numeroCartao), HttpStatus.OK);
		} catch(CartaoInexistenteException ex) {
			LOG.error("Cartão inexistente", ex);
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PostMapping("/transacoes")
	public ResponseEntity<String> fazTransacao(@Valid @RequestBody TransacaoTO transacao) {
		String body;
		try {
			fazerTransacaoCmd.faz(transacao);
			return new ResponseEntity<>("OK", HttpStatus.OK);
		} catch(CartaoInexistenteException ex) {
			LOG.error("Cartão inexistente", ex);
			body = "CARTAO_INEXISTENTE";
		} catch(SenhaInvalidaException ex) {
			LOG.error("Senha Inválida", ex);
			body = "SENHA_INVALIDA";
		} catch(SaldoInsuficienteException ex) {
			LOG.error("Saldo Insuficiente", ex);
			body = "SALDO_INSUFICIENTE";
		}
		return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
