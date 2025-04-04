package com.publicacoesonline.processosonline;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

import com.publicacoesonline.processosonline.dto.ProcessoRequest;
import com.publicacoesonline.processosonline.entity.Processo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcessoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    private static final String NUMERO_PROCESSO = "123456789";

    @Test
    @Order(1)
    void deveCriarProcessoValido() {
        ProcessoRequest dto = new ProcessoRequest();
        dto.setNumeroProcesso(NUMERO_PROCESSO);
        dto.setDescricao("Processo de teste");
        dto.setDataAbertura(LocalDate.now());

        ResponseEntity<Processo> response = restTemplate.postForEntity(getUrl("/api/processos"), dto, Processo.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getNumeroProcesso()).isEqualTo(NUMERO_PROCESSO);
    }

    @Test
    @Order(2)
    void naoDeveCriarProcessoDuplicado() {
        ProcessoRequest dto = new ProcessoRequest();
        dto.setNumeroProcesso(NUMERO_PROCESSO);
        dto.setDescricao("Processo duplicado");
        dto.setDataAbertura(LocalDate.now());

        ResponseEntity<String> response = restTemplate.postForEntity(getUrl("/api/processos"), dto, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("já cadastrado");
    }

    @Test
    @Order(3)
    void deveListarProcessos() {
        ResponseEntity<Processo[]> response = restTemplate.getForEntity(getUrl("/api/processos"), Processo[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    @Order(4)
    void deveExcluirProcessoExistente() {

        ResponseEntity<Processo[]> response = restTemplate.getForEntity(getUrl("/api/processos"), Processo[].class);
        Long id = response.getBody()[0].getId();

        restTemplate.delete(getUrl("/api/processos/" + id));

        ResponseEntity<Processo[]> afterDelete = restTemplate.getForEntity(getUrl("/api/processos"), Processo[].class);
        assertThat(afterDelete.getBody()).doesNotContain(response.getBody()[0]);
    }

    @Test
    @Order(5)
    void naoDeveExcluirProcessoInexistente() {
        ResponseEntity<String> response = restTemplate.exchange(
                getUrl("/api/processos/99999"),
                HttpMethod.DELETE,
                null,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("Processo não encontrado");
    }
}
