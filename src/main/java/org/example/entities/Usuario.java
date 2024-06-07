package org.example.entities;

import java.util.ArrayList;
import java.util.Map;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String cpf;

    public Usuario(){}

    public Usuario(int id, String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email=" + email +
                ", cpf=" + cpf +
                '}';
    }

    public Map<Boolean, ArrayList<String>> validate() {
        // trabalhar com uma lista de erros ao invés de fazer o throw de exceção direto no primeiro erro
        // isso permite que o usuário veja todos os erros de uma vez
        // e não apenas o primeiro erro que ocorreu
        // assim ele pode corrigir todos os erros de uma vez
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("Nome do usuario não pode ser vazio");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
