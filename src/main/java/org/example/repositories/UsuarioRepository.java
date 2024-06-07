package org.example.repositories;

import org.example.infrastructure.OracleDbConfiguration;
import org.example.entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    public static final String TB_NAME = "USUARIO_GS2";

    public List<Usuario> getAll(){
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME +" ORDER BY ID")){
            var rs = stmt.executeQuery();
            while(rs.next()){
                usuarios.add(new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("CPF")
                        ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return usuarios;
    }

    /**
     * Método que retorna uma lista de usuarios de acordo com os filtros e ordenação
     * @param nome Nome do usuario
     * @param preco Preço do usuario
     * @param orderBy Coluna para ordenação
     * @param direction Direção da ordenação
     * @param limit Quantidade de registros
     * @param offset Quantidade de registros a serem pulados
     * @return Lista de usuarios
     */
    public List<Usuario> getAll(String nome, double preco, String orderBy, String direction, int limit, int offset) {
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement(
                    // Query para buscar os usuarios
                    // O OFFSET é a quantidade de registros que serão pulados
                    // O FETCH NEXT é a quantidade de registros que serão retornados
                    // O ORDER BY é a coluna que será ordenada
                    // O ASC é a direção da ordenação
                    // O LIKE é um operador de comparação que busca registros que contenham o valor
                    // ex completo da query: SELECT * FROM USUARIO WHERE NOME LIKE ? AND PRECO <= ? ORDER BY ID ASC OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY
                    // esta query busca usuarios que contenham o nome passado no parâmetro nome e que tenham o preço menor ou igual ao preço passado no parâmetro preco
                    ("SELECT * FROM %s")
                            .formatted(TB_NAME)
            )){

            var rs = stmt.executeQuery();
            while (rs.next()){
                usuarios.add(new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("CPF")
                        ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public List<Usuario> getAllByCategoria(int idCategoria){
        var usuarios = new ArrayList<Usuario>();
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE IDCATEGORIA = ?");){
            stmt.setInt(1, idCategoria);
            var rs = stmt.executeQuery();
            while (rs.next()){
                usuarios.add(new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("CPF")
                        ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Optional<Usuario> get(int id){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID_USUARIO = ?")
        ){
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                return Optional.of(new Usuario(
                        rs.getInt("ID_USUARIO"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("CPF")));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void create(Usuario usuario){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("INSERT INTO " + TB_NAME + " (NOME, EMAIL, CPF) VALUES (?,?,?)")){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Usuario usuario){
        try(var conn = new OracleDbConfiguration().getConnection();
            var stmt = conn.prepareStatement("UPDATE "+ TB_NAME + " SET NOME = ?, EMAIL = ?, CPF = ? WHERE ID_USUARIO = ?");)
        {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2,usuario.getEmail());
            stmt.setString(3,usuario.getCpf());
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
                    TB_NAME + " WHERE NOME LIKE ?")){
            stmt.setString(1, "%"+nome+"%");
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
            var stmt = conn.prepareStatement("DELETE FROM "+ TB_NAME + " WHERE ID_USUARIO = ?");)
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}