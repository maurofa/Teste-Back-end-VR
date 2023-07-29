package br.com.cadmus.vrbeneficios.exception;

@SuppressWarnings("serial")
public class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException() {
		super("Operação não realizada. Saldo insuficiente!");
	}
}
