package br.bandtec.com.projetofrancescolly.dominio;

import br.bandtec.com.projetofrancescolly.dominio.Jogador;

import javax.persistence.Id;


public class Operacao {

    private String protocole;

    private String tipo;

    private Jogador body;

    private Integer status;


    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Jogador getBody() {
        return body;
    }

    public void setBody(Jogador body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
