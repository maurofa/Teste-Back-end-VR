package br.com.cadmus.vrbeneficios.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cadmus.vrbeneficios.entity.Cartao;

public interface ICartaoDAO extends CrudRepository<Cartao, Long> {

	Cartao findByNumeroCartao(String numeroCartao);
}
