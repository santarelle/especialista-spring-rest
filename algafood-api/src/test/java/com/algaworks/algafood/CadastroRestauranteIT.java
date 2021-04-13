package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        databaseCleaner.clearTables();
        prepareDatabase();
    }

    @Test
    public void givenShouldActiveRestaurante() {
        given().pathParam("restauranteId", restaurante.getId()).accept(ContentType.JSON).when()
                .put("/{restauranteId}/ativo").then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void givenShouldInactiveRestaurante() {
        given().pathParam("restauranteId", restaurante.getId()).accept(ContentType.JSON).when()
                .delete("/{restauranteId}/ativo").then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void prepareDatabase() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Cozinha 1");
        cozinhaRepository.save(cozinha);

        restaurante = new Restaurante();
        restaurante.setNome("Restaurante 1");
        restaurante.setTaxaFrete(BigDecimal.valueOf(5.10));
        restaurante.setCozinha(cozinha);
        restaurante.setAtivo(true);
        restaurante.setDataAtualizacao(OffsetDateTime.now());
        restaurante.setDataAtualizacao(OffsetDateTime.now());
        restauranteRepository.save(restaurante);
    }

}
