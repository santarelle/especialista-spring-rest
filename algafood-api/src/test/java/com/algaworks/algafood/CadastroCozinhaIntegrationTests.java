package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Test
	public void shouldSuccessWhenSaveCozinhaWithValidName() {
		Cozinha newCozinha = new Cozinha();
		newCozinha.setNome("Chinesa");

		newCozinha = cadastroCozinhaService.salvar(newCozinha);

		assertThat(newCozinha).isNotNull();
		assertThat(newCozinha.getId()).isNotNull();
		assertThat(newCozinha.getNome()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void shouldFailWhenSaveCozinhaWithoutNome() {
		var newCozinha = new Cozinha();
		newCozinha = cadastroCozinhaService.salvar(newCozinha);
	}

}
