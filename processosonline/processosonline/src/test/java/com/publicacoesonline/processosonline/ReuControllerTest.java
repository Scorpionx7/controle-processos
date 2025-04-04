package com.publicacoesonline.processosonline;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import com.publicacoesonline.processosonline.dto.ProcessoRequest;
import com.publicacoesonline.processosonline.dto.ReuRequest;
import com.publicacoesonline.processosonline.entity.Processo;
import com.publicacoesonline.processosonline.entity.Reu;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReuControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }

    private Long criarProcesso() {
        ProcessoRequest dto = new ProcessoRequest();
        dto.setNumeroProcesso("987654321");
        dto.setDescricao("Processo para réu");
        dto.setDataAbertura(LocalDate.now());

        ResponseEntity<Processo> response = restTemplate.postForEntity(getUrl("/api/processos"), dto, Processo.class);
        return response.getBody().getId();
    }

    @Test
    @Order(1)
    void deveAdicionarReuValido() {
        Long processoId = criarProcesso();

        ReuRequest reu = new ReuRequest();
        reu.setNome("João Silva");
        reu.setProcessoId(processoId);

        ResponseEntity<Reu> response = restTemplate.postForEntity(getUrl("/api/reus"), reu, Reu.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getNome()).isEqualTo("João Silva");
    }

    @Test
    @Order(2)
    void naoDeveAdicionarReuComProcessoInexistente() {
        ReuRequest reu = new ReuRequest();
        reu.setNome("José Nulo");
        reu.setProcessoId(99999L);

        ResponseEntity<String> response = restTemplate.postForEntity(getUrl("/api/reus"), reu, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("Processo não encontrado");
    }



}
