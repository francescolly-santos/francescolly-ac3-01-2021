import java.util.Scanner;

public class Jogar {


    public static void main(String[] args) {
        Baralho baralho1 = new Baralho();

        PilhaObj<Carta> verificarJogo = new PilhaObj<Carta>(52);

        Scanner leitura = new Scanner(System.in);

        baralho1.exibirBaralho();

        System.out.println("- EMBARALHANDO -");

        baralho1.embaralhar();

        System.out.println("- EMBARALHANDO -");

        baralho1.exibirBaralho();

        System.out.println("- EMBARALHANDO -");

        baralho1.embaralhar();

        int valorCerto = 0;

        while(valorCerto < 4 || valorCerto > 8){

            System.out.println("Digite quantas cartas deseja que sejam viradas - Número entre 4 ou 8:");

            valorCerto = leitura.nextInt();

            if(valorCerto < 4 || valorCerto > 8){
                System.out.println(">>>Digite um número válido<<<\n\n");
            }
        }

        Integer vencerPreto = 0;
        Integer vencerVermelho = 0;

        for(int i = 1; i < valorCerto + 1; i++) {
            System.out.println("Virando a carta " + i);

            Carta alvo = baralho1.virarCarta();

            System.out.println("Carta virada = " + alvo + "\n");

            verificarJogo.push(alvo);

            Carta verificar = verificarJogo.pop();

            if (verificar.getNaipe().equals("Paus") || verificar.getNaipe().equals("Espadas")) {
                vencerPreto++;
            }

            if (verificar.getNaipe().equals("Copas") || verificar.getNaipe().equals("Ouros")) {
                vencerVermelho++;
            }
        }

        if(vencerPreto == valorCerto && vencerVermelho == 0){
            System.out.println("Você ganhou, todas as cartas possuiam naípe preto");
        }else if(vencerVermelho == valorCerto && vencerPreto == 0){
            System.out.println("Você ganhou, todas as cartas possuiam naípe vermelho");
        }else{
            System.out.println("Você perdeu, as cartas possuíam naipes diferentes.");
        }
    }
}
