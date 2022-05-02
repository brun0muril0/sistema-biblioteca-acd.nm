package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConfigDB;
import domain.Autor;
import domain.Livro;
import domain.LivroAutor;
import oracle.net.aso.l;

public class LivroDao {
    
    public void insert(Livro livro){

        String sql = """
            INSERT INTO Livro(TITULO_LIVRO, ISBN_LIVRO, EDICAO_LIVRO, DESCRICAO_LIVRO) 
            VALUES (?, ?, ?, ?)""";
    
        try(
            Connection connection = ConfigDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            ){
                connection.setAutoCommit(false);
                statement.setString(1, livro.getTitulo());
                statement.setString(2, livro.getIsbn());
                statement.setInt(3, livro.getEdicao());
                statement.setString(4, livro.getDescricao());

                statement.executeUpdate();

                PreparedStatement ps = connection.prepareStatement("SELECT LIVRO_SEQUENCE.CURRVAL FROM DUAL");
                
                ResultSet resultSet = ps.executeQuery();

                if(resultSet.next()){
                    livro.setId(resultSet.getLong(1));
                }

                LivroAutorDao livroAutorDao = new LivroAutorDao(connection);

                List<LivroAutor> livroAutores = new ArrayList<LivroAutor>();

                LivroAutor livroAutor;

                for (Autor autor : livro.getAutores()) {
                    livroAutor = new LivroAutor(livro.getId(), autor.getId());
                    livroAutores.add(livroAutor);
                }

                    livroAutorDao.insert(livroAutores);
                    connection.commit();
            
            }               
                //close...
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    public List<Livro> findAll(){

        String sql = """
            SELECT ID_LIVRO, TITULO_LIVRO,ISBN_LIVRO,EDICAO_LIVRO,DESCRICAO_LIVRO
                FROM LIVRO
            """;
            List<Livro> livros = null;
        try (
            // 1- Abrir conexao
            Connection connection = ConfigDB.getConnection();            // 2- criar a sesão
            Statement statement = connection.createStatement();
        ){
            
            // 3- fazer...
            ResultSet resultSet = statement.executeQuery(sql);
            livros = new ArrayList<Livro>();
            Livro livro;
            
            while (resultSet.next()) {
                livro = obterLivroPorResultSet(resultSet);
                livros.add(livro);
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        return livros;
    }

    public Livro findById(Long ID_LIVRO){
        String sql = """
            SELECT ID_LIVRO, TITULO_LIVRO,ISBN_LIVRO,EDICAO_LIVRO,DESCRICAO_LIVRO
            FROM LIVRO
            WHERE  ID_LIVRO = ?
                """;
            Livro livro = null;
                try (
                    // 1- Abrir conexao
                    Connection connection = ConfigDB.getConnection();                    // 2- criar a sesão
                    PreparedStatement statement = connection.prepareStatement(sql);

                ){

                    
                    statement.setLong(1, ID_LIVRO);
            ResultSet resultSet = statement.executeQuery();
                    // 3- fazer...
            
                    if(resultSet.next()){
                        livro = new Livro();    
                        livro.setId(resultSet.getLong("ID_LIVRO"));
                        livro.setTitulo(resultSet.getString("TITULO_LIVRO"));
                        livro.setIsbn(resultSet.getString("ISBN_LIVRO"));
                        livro.setEdicao(resultSet.getInt("EDICAO_LIVRO"));
                        livro.setDescricao(resultSet.getString("DESCRICAO_LIVRO"));
                    }

                } catch (Exception e) {
                    //TODO: handle exception
                }
                return livro;
    }

    public List<Livro> findByTitle(String TITULO_LIVRO){
        String sql = """
            SELECT ID_LIVRO, TITULO_LIVRO,ISBN_LIVRO,EDICAO_LIVRO,DESCRICAO_LIVRO
            FROM LIVRO
            WHERE  TITULO_LIVRO LIKE ?
                """;
            List<Livro> livros = null;
                try (
                    // 1- Abrir conexao
                    Connection connection = ConfigDB.getConnection();                    // 2- criar a sesão
                    PreparedStatement statement = connection.prepareStatement(sql);

                ){

                    
                    statement.setString(1, "%" + TITULO_LIVRO + "%" );
            ResultSet resultSet = statement.executeQuery();
                    // 3- fazer...
            
                    livros = new ArrayList<Livro>();
                    Livro livro;
                    
                    while (resultSet.next()) {
                        livro = obterLivroPorResultSet(resultSet);
                        livros.add(livro);
                    }

                } catch (Exception e) {
                    //TODO: handle exception
                }
                return livros;
    }

    public void deleteById(Livro livro){
        String sql = """
        DELETE 
        FROM LIVRO
        WHERE ID_LIVRO = ?
            """;
        try (
            // 1- Abrir conexao
            Connection connection = ConfigDB.getConnection();             // 2- criar a sesão
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setLong(1, livro.getId());
            statement.executeUpdate();


        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void update(Livro livro){
        String sql = """
            UPDATE LIVRO 
            SET TITULO_LIVRO = ?, ISBN_LIVRO = ?, EDICAO_LIVRO = ?, DESCRICAO_LIVRO = ?
            WHERE  ID_LIVRO = ?
                """;

            try(
                // 1- Abrir conexao
                Connection connection = ConfigDB.getConnection();             // 2- criar a sesão
            PreparedStatement statement = connection.prepareStatement(sql);
            ){

                statement.setString(1, livro.getTitulo());
                statement.setString(2, livro.getIsbn());
                statement.setInt(3, livro.getEdicao());
                statement.setString(4, livro.getDescricao());
                statement.setLong(5, livro.getId());

                statement.executeUpdate();

            } catch (Exception e) {
                //TODO: handle exception
            }
    }

    private void prepararParametros(PreparedStatement statement, Livro livro) throws SQLException{
        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getIsbn());
        statement.setInt(3, livro.getEdicao());
        statement.setString(4, livro.getDescricao());
    }

    private Livro obterLivroPorResultSet(ResultSet resultSet) throws SQLException {
        Livro livro = new Livro();    
        livro.setId(resultSet.getLong("ID_LIVRO"));
        livro.setTitulo(resultSet.getString("TITULO_LIVRO"));
        livro.setIsbn(resultSet.getString("ISBN_LIVRO"));
        livro.setEdicao(resultSet.getInt("EDICAO_LIVRO"));
        livro.setDescricao(resultSet.getString("DESCRICAO_LIVRO"));

        return livro;
    }
}
