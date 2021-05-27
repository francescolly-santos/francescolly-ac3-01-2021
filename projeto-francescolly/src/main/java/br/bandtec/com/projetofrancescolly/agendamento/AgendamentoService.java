package br.bandtec.com.projetofrancescolly.agendamento;

import br.bandtec.com.projetofrancescolly.Operacao;
import br.bandtec.com.projetofrancescolly.ResultadoProcessamento;
import br.bandtec.com.projetofrancescolly.controle.JogadorController;
import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import br.bandtec.com.projetofrancescolly.obj.FilaObj;
import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private JogadorController controller;

    //Método agendado está devolvendo nullPointerException, porém está funcionando de resto
    @Scheduled(fixedRate = 15000)
    public void verificarRequisições() {
        if (controller.operacoesFila.isEmpty()) {
            System.out.println("Fila vazia");
        } else {

            //Enquanto ela não estiver vazia, vai realizar suas operações e
            //salvar o resultado do processamento da requisição (status) junto com o
            //protocolo da operação.
            while (!controller.operacoesFila.isEmpty()) {
                Operacao operacao = controller.operacoesFila.poll();
                repository.save(operacao.getBody());

                ResultadoProcessamento resultado = new ResultadoProcessamento();
                resultado.setStatus(operacao.getStatus());
                resultado.setProtocolo(operacao.getProtocole());
                controller.resultadoProcessos.add(resultado);
            }

            System.out.println("Fila tratada");
        }
    }
}
