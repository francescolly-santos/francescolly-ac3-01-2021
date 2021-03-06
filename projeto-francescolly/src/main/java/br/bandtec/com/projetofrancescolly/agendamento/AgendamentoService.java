package br.bandtec.com.projetofrancescolly.agendamento;

import br.bandtec.com.projetofrancescolly.dominio.Operacao;
import br.bandtec.com.projetofrancescolly.dominio.ResultadoProcessamento;
import br.bandtec.com.projetofrancescolly.controle.JogadorController;
import br.bandtec.com.projetofrancescolly.repositorio.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private JogadorRepository repository;

    @Autowired
    private JogadorController controller;

    //Método agendado está verificando se a fila de requisições está vazia e caso não faz as operações
    @Scheduled(fixedRate = 120000)
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
