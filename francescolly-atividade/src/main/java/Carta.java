public class Carta {
    private String face;
    private Integer valor;
    private String naipe;

    public Carta(String face, Integer valor, String naipe) {
        this.face = face;
        this.valor = valor;
        this.naipe = naipe;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    @Override
    public String toString() {
        return face+" de "+naipe;
    }
}
