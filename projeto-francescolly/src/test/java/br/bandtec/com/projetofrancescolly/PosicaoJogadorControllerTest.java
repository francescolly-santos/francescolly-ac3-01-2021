package br.bandtec.com.projetofrancescolly;

import br.bandtec.com.projetofrancescolly.controle.PosicaoJogadorController;
import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
import br.bandtec.com.projetofrancescolly.repositorio.PosicaoJogadorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PosicaoJogadorControllerTest {

    @Autowired
    PosicaoJogadorController controller;

    @MockBean
    PosicaoJogadorRepository repository;

    @Test
    void getPosicaoComValor() {

        ResponseEntity resposta = controller.getPosicao();

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void getPosicaoVazio() {

        ResponseEntity resposta = controller.getPosicao();

        assertEquals(400, resposta.getStatusCodeValue());
        assertEquals("lista de posições vazia", resposta.getBody());
    }

    @Test
    void postPosicaoNaoInserido() {

        PosicaoJogador posicaoJogador = new PosicaoJogador();



        ResponseEntity resposta = controller.postPosicao(posicaoJogador);

        assertEquals(400, resposta.getStatusCodeValue());
        assertEquals("Posição não inserida com sucesso", resposta.getBody());
    }

    @Test
    void postPosicaoInserido() {

        PosicaoJogador novaPosicao = new PosicaoJogador();
        novaPosicao.setNome("Atacante");

        ResponseEntity resposta = controller.postPosicao(novaPosicao);

        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals("Posição inserida com sucesso", resposta.getBody());
    }
}