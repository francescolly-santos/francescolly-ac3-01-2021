//package br.bandtec.com.projetofrancescolly.controle;
//
//import br.bandtec.com.projetofrancescolly.Operacoes;
//import br.bandtec.com.projetofrancescolly.dominio.Jogador;
//import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
//import br.bandtec.com.projetofrancescolly.obj.FilaObj;
//import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
//import br.bandtec.com.projetofrancescolly.obj.PilhaObj;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.xml.ws.Response;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//@RestController
//@RequestMapping("/jogador")
//public class JogadorController {
//
//    Random random = new Random();
//
//    @Autowired
//    private JogadorRepository repository;
//
//
//    PilhaObj<Operacoes> operacoesRealizadas = new PilhaObj<Operacoes>(10);
//
//    @PostMapping("/inserir-jogador")
//    public ResponseEntity postJogador(@RequestBody @Valid Jogador novoJogador) {
//        Operacoes operacao = new Operacoes();
//
//        repository.save(novoJogador);
//
//        Optional<Jogador> lista = repository.findById(novoJogador.getId());
//
//        if (lista.isPresent()) {
//            operacao.setBody(novoJogador);
//            operacao.setProtocole(String.valueOf(random.nextInt(100000000)));
//            operacao.setTipo("POST");
//            operacao.setStatus(201);
//
//            operacoesOrdenadas.insert(operacao);
//
//            return ResponseEntity.status(201).body("Posição inserida com sucesso");
//        } else {
//            return ResponseEntity.status(400).body("Posição não inserida com sucesso");
//        }
//    }
//
//    @PutMapping("/alterar-jogador")
//    public ResponseEntity putJogador(@RequestBody @Valid Jogador novoJogador) {
//        Operacoes operacao = new Operacoes();
//
//        repository.save(novoJogador);
//
//        Optional<Jogador> lista = repository.findById(novoJogador.getId());
//
//        if (lista.isPresent()) {
//            operacao.setBody(novoJogador);
//            operacao.setProtocole(String.valueOf(random.nextInt(100000000)));
//            operacao.setTipo("POST");
//            operacao.setStatus(201);
//
//            operacoesOrdenadas.insert(operacao);
//
//            return ResponseEntity.status(201).body("Posição inserida com sucesso");
//        } else {
//            return ResponseEntity.status(400).body("Posição não inserida com sucesso");
//        }
//    }
//
//    @GetMapping("/desfazer/{protocolo}")
//    public ResponseEntity desfazerInsercao(@PathVariable String protocolo) {
//        if (!operacoes.isEmpty()) {
//            if (protocolo.equals(operacoes.peek().getProtocole())) {
//
//                Operacoes operacao = operacoes.pop();
//
//                repository.deleteById(operacao.getBody().getId());
//
//                return ResponseEntity.status(200).body("Inserção desfeita com sucesso"+operacao);
//
//            } else {
//                return ResponseEntity.status(400).body("Número de protocolo incorreto");
//            }
//        } else {
//            return ResponseEntity.status(400).body("Lista de operações está vazia");
//        }
//    }
//
//
//    @GetMapping("/listar-jogadores")
//    public ResponseEntity getJogadores() {
//        List<Jogador> lista = repository.findAll();
//        if (lista.isEmpty()) {
//            return ResponseEntity.status(400).body("A lista de jogadores está vazia");
//        }
//        return ResponseEntity.status(201).body(repository.findAll());
//    }
//}
