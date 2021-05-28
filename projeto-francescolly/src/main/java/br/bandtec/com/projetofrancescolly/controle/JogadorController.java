package br.bandtec.com.projetofrancescolly.controle;

import br.bandtec.com.projetofrancescolly.dominio.Operacao;
import br.bandtec.com.projetofrancescolly.dominio.ResultadoProcessamento;
import br.bandtec.com.projetofrancescolly.agendamento.AgendamentoService;
import br.bandtec.com.projetofrancescolly.arquivo.GravaArquivo;
//import br.bandtec.com.projetofrancescolly.arquivo.LeArquivo;
import br.bandtec.com.projetofrancescolly.arquivo.LeArquivo;
import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import br.bandtec.com.projetofrancescolly.obj.FilaObj;
import br.bandtec.com.projetofrancescolly.obj.ListaObj;
import br.bandtec.com.projetofrancescolly.obj.PilhaObj;
import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/jogadores")
public class JogadorController {


    @Autowired
    private JogadorRepository repository;

    @Autowired
    private AgendamentoService agendamentoService;

    public static PilhaObj<Operacao> operacoesPilha = new PilhaObj<Operacao>(10);
    public static FilaObj<Operacao> operacoesFila = new FilaObj<Operacao>(10);
    public List<ResultadoProcessamento> resultadoProcessos = new ArrayList<ResultadoProcessamento>();
    public ListaObj<Jogador> listaJogadores = new ListaObj(11);


    // Endpoints para operações normais com o banco

    //Endpoint get que além de retornar no body a lista, também cria um arquivo .txt
    //que serve como um meio de verificar quais jogadores estavam registrados no banco em determinado momento
    @GetMapping("/listar-jogadores")
    public ResponseEntity<List<Jogador>> getJogadores() {
        List<Jogador> lista = repository.findAll();


        //Apaga a lista toda vez que for chamado o endpoint
        for(int i = 0; i < lista.size(); i++){
            listaJogadores.removePeloIndice(i);
        }

        //adiciona os jogadores
        for(int i = 0; i < lista.size(); i++){
            listaJogadores.adiciona(lista.get(i));
        }

        GravaArquivo.gravaArquivo(listaJogadores);
        LeArquivo.leArquivo("ListaJogadores.txt");

        return lista.isEmpty() ? ResponseEntity.status(204).build() :
                ResponseEntity.status(200).body(lista);
    }

    @PostMapping("/inserir-jogador")
    public ResponseEntity postJogadores(@RequestBody @Valid Jogador novoJogador) {
        repository.save(novoJogador);

        Optional<Jogador> busca = repository.findById(novoJogador.getId());

        if (busca.isPresent()) {

            Operacao postJogador = new Operacao();

            //salva o objeto tipo operacao numa pilha de operações POST
            postJogador.setStatus(201);
            postJogador.setTipo("POST");
            postJogador.setProtocole(UUID.randomUUID().toString());
            postJogador.setBody(novoJogador);
            operacoesPilha.push(postJogador);

            return ResponseEntity.status(201).body("Jogador inserido com sucessp");
        } else {
            return ResponseEntity.status(400).body("Jogador inserido sem sucesso");
        }
    }

    @PutMapping("/alterar-jogador/{id}")
    public ResponseEntity putJogador(@RequestBody @Valid Jogador novoJogador, @PathVariable int id) {
        if (repository.existsById(id)) {
            novoJogador.setId(id);
            repository.save(novoJogador);
            return ResponseEntity.status(200).body("Jogador alterado com sucesso");
        } else {
            return ResponseEntity.status(404).body("Jogador alterado sem sucesso");
        }
    }

    @GetMapping("/desfazer-insercao")
    public ResponseEntity desfazerGet() {
        if (operacoesPilha.isEmpty()) {
            return ResponseEntity.status(204).body("Pilha de operações está vazia");
        } else {
            Operacao operacao = operacoesPilha.pop();
            repository.deleteById(operacao.getBody().getId());
            return ResponseEntity.status(201).body("Última operação desfeita com sucesso");
        }
    }


    //Endpoint para operação assíncrona (essa operação ocorre apenas na controller
    // da entidade "jogador")
    //Primeiro guarda as operações do tipo "POST" e depois as executa por outro
    //endpoint
    @PostMapping("/inserir-jogador-async")
    public ResponseEntity postAsyncJogadores(@RequestBody @Valid Jogador novoJogador) {

        Operacao operacao = new Operacao();

        String protocolo = UUID.randomUUID().toString();

        //salva o objeto tipo operacao numa fila de operações POST
        operacao.setBody(novoJogador);
        operacao.setProtocole(protocolo);
        operacao.setStatus(201);
        operacao.setTipo("POST");
        operacoesFila.insert(operacao);

        return ResponseEntity.status(201).body(protocolo.toString());

    }

    //Endpoint para executar operações do tipo post da fila inteira
    //não é necessário ser utilizado pois temos o método agendado que faz a mesma
    //coisa
    @GetMapping("/executar")
    public ResponseEntity getAsyncJogadores() {

        //Verifica se a pilha de operações está vazia
        if (operacoesFila.isEmpty()) {
            return ResponseEntity.status(400).body("A fila de operações está vazia");
        } else {

            //Enquanto ela não estiver vazia, vai realizar suas operações e
            //salvar o resultado do processamento da requisição (status) junto com o
            //protocolo da operação.
            while (!operacoesFila.isEmpty()) {
                Operacao operacao = operacoesFila.poll();
                repository.save(operacao.getBody());

                ResultadoProcessamento resultado = new ResultadoProcessamento();
                resultado.setStatus(operacao.getStatus());
                resultado.setProtocolo(operacao.getProtocole());
                resultadoProcessos.add(resultado);

            }
            return ResponseEntity.status(201).body("Fila executada com sucesso");
        }
    }

    @GetMapping("/consultar/{protocolo}")
    public ResponseEntity getProtocolo(@PathVariable String protocolo) {

        if (resultadoProcessos.isEmpty()) {
            return ResponseEntity.status(404).body("Lista de resultados dos processos vazia");
        } else {
            for (int i = 0; i < resultadoProcessos.size(); i++) {
                if (resultadoProcessos.get(i).getProtocolo().equals(protocolo)) {
                    resultadoProcessos.remove(i);
                    return ResponseEntity.status(200)
                            .body("Processo assíncrono já executado");
                }
            } return ResponseEntity.status(404).body("Protocolo não corresponde a uma operação que já foi executada");
        }
    }

}

