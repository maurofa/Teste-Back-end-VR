package br.com.cadmus.vrbeneficios.exception;

@SuppressWarnings("serial")
public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Operação não realizada. Senha inválida!");
	}
}
