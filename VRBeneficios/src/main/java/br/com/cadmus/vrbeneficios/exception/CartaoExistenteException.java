package br.com.cadmus.vrbeneficios.exception;

// Não vale a pena colocar o serial porque isto será feito automaticamente
@SuppressWarnings("serial")
public class CartaoExistenteException extends RuntimeException {

	public CartaoExistenteException() {
		super("Operação não realizada. Cartão já existe.");
	}

}
