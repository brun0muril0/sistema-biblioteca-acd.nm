package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import domain.LivroAutor;

public class LivroAutorDao {
    Connection connection;

    public LivroAutorDao(Connection connection){
        this.connection = connection;
    }
    public void insert(List<LivroAutor> livroAutores) throws SQLException{
        String sql = "INSERT INTO LIVRO_AUTOR (ID_LIVRO, ID_AUTOR) VALUES (?, ?)";

        try (
            PreparedStatement ps = connection.prepareStatement(sql);

        ){
            for (LivroAutor livroAutor : livroAutores) {
                ps.setLong(1, livroAutor.getIdLivro());
                ps.setLong(2, livroAutor.getIdAutor());

                ps.addBatch();
            }

            ps.executeBatch();
            
        } catch (Exception e) {
            //TODO: handle exception
            connection.rollback();
            throw e;
        }
    }
}
