package org.example.entities;

import java.util.ArrayList;
import java.util.Map;

public class Doacao {
    private int id;
    private double valor;
    private String hora;
    private String mensagem;
    private String idUsuario;
    private String idInstituicao;

    public Doacao () {}

    public Doacao(int id, double valor, String hora, String mensagem, String idUsuario, String idInstituicao) {
        this.id = id;
        this.valor = valor;
        this.hora = hora;
        this.mensagem = mensagem;
        this.idUsuario = idUsuario;
        this.idInstituicao = idInstituicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(String idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", valor='" + valor + '\'' +
                ", hora=" + hora +
                ", mensagem=" + mensagem+
                ", id_usuario=" + idUsuario+
                ", id_instituicao=" + idInstituicao+
                '}';
    }

    public Map<Boolean, ArrayList<String>> validate() {
        // trabalhar com uma lista de erros ao invés de fazer o throw de exceção direto no primeiro erro
        // isso permite que o usuário veja todos os erros de uma vez
        // e não apenas o primeiro erro que ocorreu
        // assim ele pode corrigir todos os erros de uma vez
        var errors = new ArrayList<String>();


        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
