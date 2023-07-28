package br.com.cadmus.vrbeneficios.to;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Coloquei a senha e o número do cartão como obrigatórios
// Coloquei como tamanho máximo e mínimo 16 para o número do cartão
// Coloquei que o cartão só deve conter dígitos e sem parte fracionária
public record CartaoTO(
		@NotBlank(message = "Número do cartão é obrigatório") @Size(min = 16, max = 16, message = "O número do cartão deve ter 16 algorismos") @Digits(message = "O número só deve ter dígitos", fraction = 0, integer = 16) String numeroCartao,
		@NotBlank(message = "A senha é obrigatória") String senha) {}
