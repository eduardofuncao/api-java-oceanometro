package org.example.entities;

public class DetalhesPix {
    private int id;
    private String codigo;
    private String idDoacao;

    public DetalhesPix(){}

    public DetalhesPix(int id, String codigo, String idDoacao) {
        this.id = id;
        this.codigo = codigo;
        this.idDoacao = idDoacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
