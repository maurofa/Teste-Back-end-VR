package br.com.cadmus.vrbeneficios.to;

import jakarta.annotation.Nonnull;

public record TransacaoTO(@Nonnull String numeroCartao, @Nonnull String senhaCartao, @Nonnull Double valor) { }
