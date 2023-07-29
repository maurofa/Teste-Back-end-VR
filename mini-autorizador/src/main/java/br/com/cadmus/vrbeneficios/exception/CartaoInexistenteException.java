package br.com.cadmus.vrbeneficios.exception;

@SuppressWarnings("serial")
public class CartaoInexistenteException extends RuntimeException {
	public CartaoInexistenteException() {
		super("Operação não realizada. Cartão inexistente");
	}
}
