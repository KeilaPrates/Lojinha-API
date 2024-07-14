package modulo.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Teste de API Rest ")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        //Configurando os dados da API Rest da lojinha

        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";


        //Obter o token do usuario admin
         this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
                .when()
                    .post("/v2/login")
                .then()
                    .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimitesZeradoProibidoValorProduto() {


    given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(0.00))
            .when()
                .post("v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))

             .statusCode(422);

    }

    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComValorIgualA(7000.01))
                .when()
                    .post("v2/produtos")
                .then()
                    .assertThat()
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))

                    .statusCode(422);

    }

}
