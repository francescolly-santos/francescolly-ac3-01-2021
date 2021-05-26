import java.util.Random;

public class Baralho {

    private PilhaObj<Carta> baralho;

    public Baralho() {
        String[] faces = {"Ás", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Rainha", "Rei"};
        String[] naipes = {"Copas", "Paus", "Ouros", "Espadas"};
        this.baralho = new PilhaObj<Carta>(52);

        int cont = 0;

        while (!baralho.isFull()){
            baralho.push(new Carta(faces[cont%13], // face da carta
                    cont%13 + 1, // valor da carta
                    naipes[cont/13])); // naipe
            cont++;
        }
    }

    public PilhaObj getBaralho() {
        return baralho;
    }

    public void setBaralho(PilhaObj baralho) {
        this.baralho = baralho;
    }

    public Carta removeCarta(int indice){
        PilhaObj<Carta> aux = new PilhaObj<Carta>(52);

        for (int i = 0; i < indice; i++) {
            aux.push(baralho.pop());
        }

        Carta alvo = baralho.pop();

        do {
            baralho.push(aux.pop());
        } while (!aux.isEmpty());

        return alvo;
    }

    public void embaralhar(){
        Random random = new Random();

        for(int i = 0; i < 52; i++){
            int indice = random.nextInt(52);
            if(indice != 0){
                Carta carta = removeCarta(indice);
                baralho.push(carta);
            }
        }
    }

    public Carta virarCarta(){

        Carta carta = baralho.pop();
        return carta;
    }

    public void exibirBaralho(){
        baralho.exibe();
    }

}
