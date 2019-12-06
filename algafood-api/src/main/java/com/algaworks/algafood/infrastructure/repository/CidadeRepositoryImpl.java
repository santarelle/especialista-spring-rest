package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("FROM Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long cidadeId) {
		return manager.find(Cidade.class, cidadeId);
	}

	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Long cidadeId) {
		Cidade cidade = buscar(cidadeId);
		if (cidade == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cidade);
	}

}
