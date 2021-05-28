package br.bandtec.com.projetofrancescolly.arquivo;

import br.bandtec.com.projetofrancescolly.controle.JogadorController;
import br.bandtec.com.projetofrancescolly.dominio.Jogador;
import br.bandtec.com.projetofrancescolly.obj.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GravaArquivo {

    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }


    public static void gravaArquivo(ListaObj<Jogador> lista) {

        String nomeArq = "ListaJogadores.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00JOGADOR20211";
        header += formatter.format(dataDeHoje);
        header += "01";

        // Grava o registro header
        gravaRegistro(nomeArq, header);


        for (int i = 0; i < lista.getTamanho(); i++) {
            corpo = "";
            corpo += "02";

            corpo += String.format("%-2d", lista.getElemento(i).getId());
            corpo += String.format("%-12s",lista.getElemento(i).getNome());
            corpo += String.format("%-12s",lista.getElemento(i).getSobrenome());
            corpo += String.format("%2d",lista.getElemento(i).getNumeroCamisa());
            corpo += String.format("%-10s",lista.getElemento(i).getDiaDeEstreia());
            corpo += String.format("%-2d",lista.getElemento(i).getPosicao().getId());
            corpo += String.format("%-10s",lista.getElemento(i).getPosicao().getNome());
            corpo += String.format("%-5b",lista.getElemento(i).getAposentado());

            contRegDados++;

            gravaRegistro(nomeArq, corpo);

        }

        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq, trailer);
    }

}
