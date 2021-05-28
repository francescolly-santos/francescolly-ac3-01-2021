package br.bandtec.com.projetofrancescolly.dominio;

public class ResultadoProcessamento {

    private String protocolo;
    private Integer status;

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
