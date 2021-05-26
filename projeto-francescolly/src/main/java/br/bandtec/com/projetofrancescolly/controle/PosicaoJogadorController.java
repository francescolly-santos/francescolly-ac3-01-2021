package br.bandtec.com.projetofrancescolly.controle;

import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
import br.bandtec.com.projetofrancescolly.repositorio.PosicaoJogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posicao")
public class PosicaoJogadorController {

    @Autowired
    private PosicaoJogadorRepository repository;

    @GetMapping("/lista")
    public ResponseEntity getPosicao() {
        List<PosicaoJogador> lista = repository.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.status(400).body("lista de posições vazia");
        } else {
            return ResponseEntity.status(201).body(lista);
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity postPosicao(@RequestBody @Valid PosicaoJogador novaPosicao) {
        repository.save(novaPosicao);
        Optional<PosicaoJogador> lista = repository.findById(novaPosicao.getId());
        if (lista.isPresent()) {
            return ResponseEntity.status(201).body("Posição inserida com sucesso");
        } else {
            return ResponseEntity.status(400).body("Posição não inserida com sucesso");
        }
    }
}
