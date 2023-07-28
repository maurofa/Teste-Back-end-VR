package br.com.cadmus.vrbeneficios.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cadmus.vrbeneficios.entity.Transacao;

public interface ITransacaoDAO extends CrudRepository<Transacao, Long> {

}
