package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    private static final int COZINHA_ID_NONEXISTENT = 0;

    @LocalServerPort
    private int port;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private int quantityCozinhasRegistered;
    private Cozinha cozinhaIndiana;
    private String jsonCozinhaChinesa;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        jsonCozinhaChinesa = ResourceUtils.getContentFromResource("/json/cozinha-chinesa.json");

        databaseCleaner.clearTables();
        prepareDatabase();
    }

    @Test
    public void shouldSuccess_WhenSaveCozinhaWithValidName() {
        Cozinha newCozinha = new Cozinha();
        newCozinha.setNome("Chinesa");

        newCozinha = cadastroCozinhaService.salvar(newCozinha);

        assertThat(newCozinha).isNotNull();
        assertThat(newCozinha.getId()).isNotNull();
        assertThat(newCozinha.getNome()).isNotNull();
    }

    @Test
    public void shouldFail_WhenSaveCozinhaWithoutNome() {
        var newCozinha = new Cozinha();
        assertThrows(DataIntegrityViolationException.class, () -> cadastroCozinhaService.salvar(newCozinha));
    }


    @Test
    public void shouldFail_WhenDeleteCozinhaNonexistent() {
        assertThrows(CozinhaNaoEncontradaException.class, () -> cadastroCozinhaService.excluir((long) COZINHA_ID_NONEXISTENT));
    }

    @Test
    public void shouldReturnStatusOK_WhenFindCozinhas() {
        given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnQuantityCozinhasRegisteredCorrectly_WhenFindCozinhas() {
        given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(quantityCozinhasRegistered));
    }

    @Test
    public void shouldReturnStatusCREATED_WhenCreateCozinha() {
        given().body(jsonCozinhaChinesa).contentType(ContentType.JSON).accept(ContentType.JSON).when().post().then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldResponseStatusOKAndNomeIndiana_WhenFindCozinhaWithId2() {
        given().pathParam("cozinhaId", cozinhaIndiana.getId()).accept(ContentType.JSON).when().get("/{cozinhaId}")
                .then().statusCode(HttpStatus.OK.value()).body("nome", Matchers.equalTo(cozinhaIndiana.getNome()));
    }

    @Test
    public void shouldResponseStatusNotFound_WhenFindCozinhaNonexistent() {
        given().pathParam("cozinhaId", COZINHA_ID_NONEXISTENT).when().get("/{cozinhaId}").then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepareDatabase() {
        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa");
        cozinhaRepository.save(cozinha1);

        cozinhaIndiana = new Cozinha();
        cozinhaIndiana.setNome("Indiana");
        cozinhaRepository.save(cozinhaIndiana);

        quantityCozinhasRegistered = (int) cozinhaRepository.count();
    }
}
