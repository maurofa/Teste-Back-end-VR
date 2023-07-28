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

import br.com.cadmus.vrbeneficios.cmd.MantemCartaoCmd;
import br.com.cadmus.vrbeneficios.exception.CartaoExistenteException;
import br.com.cadmus.vrbeneficios.to.CartaoTO;
import jakarta.validation.Valid;

@RestController
public class MiniAutorizadorController {

	private static final Logger LOG = LoggerFactory.getLogger(MiniAutorizadorController.class);
	private MantemCartaoCmd mantemCartaoCmd;

	public MiniAutorizadorController(MantemCartaoCmd mantemCartaoCmd) {
		this.mantemCartaoCmd = mantemCartaoCmd;
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
		return null;
	}
}
