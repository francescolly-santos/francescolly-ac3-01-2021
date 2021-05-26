package br.bandtec.com.projetofrancescolly.controle;

import br.bandtec.com.projetofrancescolly.Operacoes;
import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import br.bandtec.com.projetofrancescolly.obj.PilhaObj;
import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/jogadores")
public class JogadorControllerX {

    Random random = new Random();
    @Autowired
    private JogadorRepository repository;

    PilhaObj<Operacoes> operacoes = new PilhaObj<Operacoes>(10);

    @GetMapping("/listar-jogadores")
    public ResponseEntity getJogadores(){
        List<Jogador> lista = repository.findAll();
        return ResponseEntity.status(201).body(lista);
    }

    @PostMapping("/inserir-jogador")
    public ResponseEntity postJogadores(@RequestBody @Valid Jogador novoJogador){
        repository.save(novoJogador);

        Operacoes postJogador = new Operacoes();

        postJogador.setStatus(201);
        postJogador.setTipo("POST");
        postJogador.setProtocole(String.valueOf(random.nextInt(100000)));
        postJogador.setBody(novoJogador);
        operacoes.push(postJogador);

        return ResponseEntity.status(201).body("Jogador inserido com sucessp");
    }

    @PutMapping("/alterar-jogador")
    public ResponseEntity putJogador(@RequestBody @Valid Jogador novoJogador){
        repository.save(novoJogador);
        return ResponseEntity.status(201).body("Jogador alterado com sucesso");
    }

    @DeleteMapping("/deletar-jogador/{id}")
    public ResponseEntity deleteJogador(@PathVariable int id){
        repository.deleteById(id);
        return ResponseEntity.status(201).body("Jogador deletado com sucesso");
    }

}
