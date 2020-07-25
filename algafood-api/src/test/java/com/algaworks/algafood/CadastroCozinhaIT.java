package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Test
    public void shouldSuccess_WhenSaveCozinhaWithValidName() {
        Cozinha newCozinha = new Cozinha();
        newCozinha.setNome("Chinesa");

        newCozinha = cadastroCozinhaService.salvar(newCozinha);

        assertThat(newCozinha).isNotNull();
        assertThat(newCozinha.getId()).isNotNull();
        assertThat(newCozinha.getNome()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldFail_WhenSaveCozinhaWithoutNome() {
        var newCozinha = new Cozinha();
        newCozinha = cadastroCozinhaService.salvar(newCozinha);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void shouldFail_WhenDeleteCozinhaUsed() {
        cadastroCozinhaService.excluir(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void shouldFail_WhenDeleteCozinhaNonexistent() {
        cadastroCozinhaService.excluir(0L);
    }

    @Test
    public void shouldReturnStatusOK_WhenFindCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .basePath("/cozinhas")
            .port(port)
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldHave5Cozinhas_WhenFindCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                    .body("", Matchers.hasSize(5))
                    .body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
    }

}
