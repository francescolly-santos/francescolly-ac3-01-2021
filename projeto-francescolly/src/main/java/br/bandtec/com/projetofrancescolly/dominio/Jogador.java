package br.bandtec.com.projetofrancescolly.dominio;

import br.bandtec.com.projetofrancescolly.dominio.PosicaoJogador;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotNull
    @Positive
    private Integer numeroCamisa;

    @NotBlank
    private String diaDeEstreia;

    @ManyToOne
    private PosicaoJogador posicao;

    private Boolean aposentado;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getDiaDeEstreia() {
        return diaDeEstreia;
    }

    public void setDiaDeEstreia(String diaDeEstreia) {
        this.diaDeEstreia = diaDeEstreia;
    }

    public PosicaoJogador getPosicao() {
        return posicao;
    }

    public void setPosicao(PosicaoJogador posicao) {
        this.posicao = posicao;
    }

    public Boolean getAposentado() {
        return aposentado;
    }

    public void setAposentado(Boolean aposentado) {
        this.aposentado = aposentado;
    }
}
