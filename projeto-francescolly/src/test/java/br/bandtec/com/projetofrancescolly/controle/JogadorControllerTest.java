package br.bandtec.com.projetofrancescolly.controle;

import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
import br.bandtec.com.projetofrancescolly.repositorio.PosicaoJogadorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JogadorControllerTest {

    @Autowired
    JogadorController jogadorController;

    @MockBean
    JogadorRepository jogadorRepository;

    @MockBean
    PosicaoJogadorRepository posicaoJogadorRepository;

    @Test
    void getJogadoresComRegistros() {

        List<Jogador> jogadorTeste = Arrays.asList(new Jogador(), new Jogador());

        Mockito.when(jogadorRepository.findAll()).thenReturn(jogadorTeste);

        ResponseEntity<List<Jogador>> resposta = jogadorController.getJogadores();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());

    }

    @Test
    void getJogadoresSemRegistros(){

        Mockito.when(jogadorRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Jogador>> resposta = jogadorController.getJogadores();

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    void postJogadores() {
        PosicaoJogador posicaoJogador = new PosicaoJogador();
        posicaoJogador.setId(1);
        posicaoJogador.setNome("Zagueiro");


        Jogador jogador = new Jogador();
        jogador.setId(1);
        jogador.setNome("Anderson");
        jogador.setAposentado(false);
        jogador.setPosicao(posicaoJogador);
        jogador.setDiaDeEstreia("11-21-2002");
        jogador.setNumeroCamisa(10);
        jogador.setSobrenome("Silva");

        ResponseEntity resposta = jogadorController.postJogadores(jogador);

        Optional<Jogador> busca = jogadorRepository.findById(jogador.getId());

        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    void putJogador() {
    }

    @Test
    void testGetJogadores() {
    }

    @Test
    void testPostJogadores() {
    }

    @Test
    void testPutJogador() {
    }
}