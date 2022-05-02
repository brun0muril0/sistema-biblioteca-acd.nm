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

public class AutorDao {

    public void insert(Autor autor){

        String sql = """
            INSERT INTO Autor(NOME, NACIONALIDADE, ANO_NASCIMENTO) 
            VALUES (?, ?, ?)""";
    
        try(
            Connection connection = ConfigDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            
            ){
                
                statement.setString(1, autor.getNome());
                statement.setString(2, autor.getNacionalidade());
                statement.setInt(3, autor.getAnoNascimento());

                statement.executeUpdate();

                System.out.println(statement);

                PreparedStatement ps = connection.prepareStatement("SELECT AUTOR_SEQUENCE.CURRVAL FROM DUAL");
                
                ResultSet resultSet = ps.executeQuery();

                if(resultSet.next()){
                    autor.setId(resultSet.getLong(1));
                }
                
                

            }               
                //close...
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Autor> findAll(){

        String sql = """
            SELECT ID_AUTOR, NOME, NACIONALIDADE, ANO_NASCIMENTO
                FROM AUTOR
            """;
            List<Autor> autores = null;
        try (
            // 1- Abrir conexao
            Connection connection = ConfigDB.getConnection();            // 2- criar a sesão
            Statement statement = connection.createStatement();
        ){
            
            // 3- fazer...
            ResultSet resultSet = statement.executeQuery(sql);
            autores = new ArrayList<Autor>();
            Autor autor;
            
            while (resultSet.next()) {
                autor = obterAutorPorResultSet(resultSet);
                autores.add(autor);
            }

        } catch (Exception e) {
            //TODO: handle exception
        }
        return autores;
    }

    private Autor obterAutorPorResultSet(ResultSet resultSet) throws SQLException {
        Autor autor = new Autor();    
        autor.setId(resultSet.getLong("ID_AUTOR"));
        autor.setNome(resultSet.getString("NOME"));
        autor.setNacionalidade(resultSet.getString("NACIONALIDADE"));
        autor.setAnoNascimento(resultSet.getInt("ANO_NASCIMENTO"));

        return autor;
    }

    public Autor findById(Long ID_AUTOR){
        String sql = """
            SELECT ID_AUTOR, NOME, NACIONALIDADE, ANO_NASCIMENTO
            FROM AUTOR
            WHERE  ID_AUTOR = ?
                """;
            Autor autor = null;
                try (
                    // 1- Abrir conexao
                    Connection connection = ConfigDB.getConnection();                    // 2- criar a sesão
                    PreparedStatement statement = connection.prepareStatement(sql);

                ){

                    
                    statement.setLong(1, ID_AUTOR);
            ResultSet resultSet = statement.executeQuery();
                    // 3- fazer...
            
                    if(resultSet.next()){
                        autor = new Autor();    
                        autor.setId(resultSet.getLong("ID_AUTOR"));
                        autor.setNome(resultSet.getString("NOME"));
                        autor.setNacionalidade(resultSet.getString("NACIONALIDADE"));
                        autor.setAnoNascimento(resultSet.getInt("ANO_NASCIMENTO"));
                        
                    }

                } catch (Exception e) {
                    //TODO: handle exception
                }
                return autor;
    }

    public List<Autor> findByTitle(String NOME){
        String sql = """
            SELECT ID_AUTOR, NOME, NACIONALIDADE, ANO_NASCIMENTO
            FROM AUTOR
            WHERE  NOME LIKE ?
                """;
            List<Autor> autores = null;
                try (
                    // 1- Abrir conexao
                    Connection connection = ConfigDB.getConnection();                    // 2- criar a sesão
                    PreparedStatement statement = connection.prepareStatement(sql);

                ){

                    
                    statement.setString(1, "%" + NOME + "%" );
            ResultSet resultSet = statement.executeQuery();
                    // 3- fazer...
            
                    autores = new ArrayList<Autor>();
                    Autor autor;
                    
                    while (resultSet.next()) {
                        autor = obterAutorPorResultSet(resultSet);
                        autores.add(autor);
                    }

                } catch (Exception e) {
                    //TODO: handle exception
                }
                return autores;
    }

    public void deleteById(Autor autor){
        String sql = """
        DELETE 
        FROM AUTOR
        WHERE ID_AUTOR = ?
            """;
        try (
            // 1- Abrir conexao
            Connection connection = ConfigDB.getConnection();             // 2- criar a sesão
            PreparedStatement statement = connection.prepareStatement(sql);
        ){

            statement.setLong(1, autor.getId());
            statement.executeUpdate();


        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void update(Autor autor){
        String sql = """
            UPDATE AUTOR 
            SET NOME = ?, NACIONALIDADE = ?, ANO_NASCIMENTO = ?
            WHERE  ID_AUTOR = ?
                """;

            try(
                // 1- Abrir conexao
                Connection connection = ConfigDB.getConnection();             // 2- criar a sesão
            PreparedStatement statement = connection.prepareStatement(sql);
            ){

                statement.setString(1, autor.getNome());
                statement.setString(2, autor.getNacionalidade());
                statement.setInt(3, autor.getAnoNascimento());
                statement.setLong(4, autor.getId());

                statement.executeUpdate();

            } catch (Exception e) {
                //TODO: handle exception
            }
    }
}
