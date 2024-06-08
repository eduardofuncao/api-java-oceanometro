package org.example.entities;
import org.example.services.ViaCepService;

import java.io.IOException;

public class Instituicao {
    private int id;
    private String nome;
    private String cnpj;
    private Endereco endereco;

    Instituicao() {}

    Instituicao(int id, String nome, String cnpj, String cep) throws IOException {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = ViaCepService.getEndereco(cep);
    }

    Instituicao(int id, String nome, String cnpj, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
