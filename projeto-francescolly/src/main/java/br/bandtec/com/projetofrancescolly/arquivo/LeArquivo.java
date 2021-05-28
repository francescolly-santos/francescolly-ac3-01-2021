package br.bandtec.com.projetofrancescolly.arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeArquivo {

    public static void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nomeJogador, sobrenomeJogador, diaDeEstreia, nomePosicao;
        Integer numeroCamisa, idPosicao, contRegistro=0;
        Boolean aposentado;
        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 9));
                    int periodoLetivo= Integer.parseInt(registro.substring(9,14));
                    System.out.println("Período letivo: " + periodoLetivo);
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(14,33));
                    System.out.println("Versão do layout: " + registro.substring(33,35));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-12s %-12s %2s %10s %-2s %-10s %-5s\n","NOME","SOBRENOME","NUMEROCAMISA",
                                "DIADEESTREIA", "IDPOSICAO", "NOMEPOSICAO", "APOSENTADO");

                    }

                    nomeJogador = registro.substring(4, 16);
                    sobrenomeJogador= registro.substring(16, 28);
                    numeroCamisa = Integer.parseInt(registro.substring(28, 30));
                    diaDeEstreia = registro.substring(30, 40);
                    idPosicao = Integer.parseInt(registro.substring(40, 41));
                    nomePosicao = registro.substring(42, 52);
                    aposentado = Boolean.valueOf(registro.substring(52, 57));
                    System.out.printf("%-12s %-12s %-12d %-12s %-9d %-11s %-5b\n", nomeJogador, sobrenomeJogador,
                            numeroCamisa, diaDeEstreia, idPosicao, nomePosicao, aposentado);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
    }
}
