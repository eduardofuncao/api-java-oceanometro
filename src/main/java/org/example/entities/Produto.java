package org.example.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private transient Categoria categoria;

    public Produto(){}

    public Produto(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(int id, String nome, double preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }

    public Map<Boolean, ArrayList<String>> validate() {
        // trabalhar com uma lista de erros ao invés de fazer o throw de exceção direto no primeiro erro
        // isso permite que o usuário veja todos os erros de uma vez
        // e não apenas o primeiro erro que ocorreu
        // assim ele pode corrigir todos os erros de uma vez
        var errors = new ArrayList<String>();
        if (nome == null || nome.isBlank())
            errors.add("Nome do produto não pode ser vazio");
        if (preco < 0)
            errors.add("Preço do produto não pode ser menor que zero");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
