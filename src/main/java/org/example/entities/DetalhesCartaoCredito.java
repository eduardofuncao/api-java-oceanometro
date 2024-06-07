package org.example.entities;

public class DetalhesCartaoCredito {
    private int id;
    private String nome;
    private String numero;
    private String dataValidade;
    private String codigo;
    private String idDoacao;

    public DetalhesCartaoCredito() {}

    public DetalhesCartaoCredito(String nome, String numero, String dataValidade, String codigo, String idDoacao) {
        this.nome = nome;
        this.numero = numero;
        this.dataValidade = dataValidade;
        this.codigo = codigo;
        this.idDoacao = idDoacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(String idDoacao) {
        this.idDoacao = idDoacao;
    }
}
