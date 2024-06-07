package org.example.repositories;

import org.example.infrastructure.OracleDbConfiguration;
import org.example.entities.Doacao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoacaoRepository {

    public static final String TB_NAME = "DOACAO_GS2";

    public List<Doacao> getAll(){
        var doacaos = new ArrayList<Doacao>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME +" ORDER BY ID")){
            var rs = stmt.executeQuery();
            while(rs.next()){
                doacaos.add(new Doacao(
                        rs.getInt("ID_DOACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("HORA"),
                        rs.getString("MENSAGEM"),
                        rs.getString("ID_USUARIO_FK"),
                        rs.getString("ID_INSTITUICAO_FK")
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return doacaos;
    }

    /**
     * Método que retorna uma lista de doacaos de acordo com os filtros e ordenação
     * @param nome Nome do doacao
     * @param preco Preço do doacao
     * @param orderBy Coluna para ordenação
     * @param direction Direção da ordenação
     * @param limit Quantidade de registros
     * @param offset Quantidade de registros a serem pulados
     * @return Lista de doacaos
     */
    public List<Doacao> getAll(String nome, double preco, String orderBy, String direction, int limit, int offset) {
        var doacaos = new ArrayList<Doacao>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement(
                    // Query para buscar os doacaos
                    // O OFFSET é a quantidade de registros que serão pulados
                    // O FETCH NEXT é a quantidade de registros que serão retornados
                    // O ORDER BY é a coluna que será ordenada
                    // O ASC é a direção da ordenação
                    // O LIKE é um operador de comparação que busca registros que contenham o valor
                    // ex completo da query: SELECT * FROM DOACAO WHERE NOME LIKE ? AND PRECO <= ? ORDER BY ID ASC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
                    // esta query busca doacaos que contenham o nome passado no parâmetro nome e que tenham o preço menor ou igual ao preço passado no parâmetro preco
                    ("SELECT * FROM %s")
                            .formatted(TB_NAME)
            )){

            var rs = stmt.executeQuery();
            while (rs.next()){
                doacaos.add(new Doacao(
                        rs.getInt("ID_DOACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("HORA"),
                        rs.getString("MENSAGEM"),
                        rs.getString("ID_USUARIO_FK"),
                        rs.getString("ID_INSTITUICAO_FK")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return doacaos;
    }

    public List<Doacao> getAllByCategoria(int idCategoria){
        var doacaos = new ArrayList<Doacao>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE IDCATEGORIA = ?");){
            stmt.setInt(1, idCategoria);
            var rs = stmt.executeQuery();
            while (rs.next()){
                doacaos.add(new Doacao(
                        rs.getInt("ID_DOACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("HORA"),
                        rs.getString("MENSAGEM"),
                        rs.getString("ID_USUARIO_FK"),
                        rs.getString("ID_INSTITUICAO_FK")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return doacaos;
    }

    public Optional<Doacao> get(int id){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID_DOACAO = ?")
        ){
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                return Optional.of(new Doacao(
                        rs.getInt("ID_DOACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("HORA"),
                        rs.getString("MENSAGEM"),
                        rs.getString("ID_USUARIO_FK"),
                        rs.getString("ID_INSTITUICAO_FK")
                ));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void create(Doacao doacao){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("INSERT INTO " + TB_NAME + " (valor, hora, mensagem, id_usuario_fk, id_instituicao_fk) VALUES (?,?,?,?,?)")){
            stmt.setDouble(1, doacao.getValor());
            stmt.setString(2, doacao.getHora());
            stmt.setString(3, doacao.getMensagem());
            stmt.setString(4, doacao.getIdUsuario());
            stmt.setString(5, doacao.getIdInstituicao());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Doacao doacao){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("UPDATE "+ TB_NAME + " SET VALOR = ?, HORA = ?, MENSAGEM = ? WHERE ID_DOACAO = ?");)
        {
            stmt.setDouble(1, doacao.getValor());
            stmt.setString(2,doacao.getHora());
            stmt.setString(3,doacao.getMensagem());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int count(String nome, double preco){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT COUNT(*) FROM " +
                    TB_NAME)){
            var result = stmt.executeQuery();
            if(result.next()){
                return result.getInt(1);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(int id){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("DELETE FROM "+ TB_NAME + " WHERE ID_DOACAO = ?");)
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Doacao> getTop10() {
        var doacaos = new ArrayList<Doacao>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY VALOR DESC FETCH FIRST 10 ROWS ONLY");){

            var rs = stmt.executeQuery();
            while (rs.next()){
                doacaos.add(new Doacao(
                        rs.getInt("ID_DOACAO"),
                        rs.getDouble("VALOR"),
                        rs.getString("HORA"),
                        rs.getString("MENSAGEM"),
                        rs.getString("ID_USUARIO_FK"),
                        rs.getString("ID_INSTITUICAO_FK")
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return doacaos;
    }
}