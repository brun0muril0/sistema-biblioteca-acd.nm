package hellofx;

import java.io.IOException;
import java.util.List;

import javax.swing.Action;

import dao.LivroDao;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrincipalControllerLivro {
    
    @FXML
    private Button btAlterar;

    @FXML
    private Button btDelete;

    @FXML
    private Button btNovo;

    @FXML
    private Button btPesquisa;

    @FXML
    private Label lbDigiteNome;

    @FXML
    private ListView<Livro> lvPesquisa;

    @FXML
    private TextField tfPesquisa;

    
    @FXML
    public void initialize(){
        carregarLivros();

        lvPesquisa.setCellFactory(param -> new ListCell<Livro>() {
            @Override
            protected void updateItem(Livro item, boolean empty) {
                    super.updateItem(item, empty);
    
                    if (empty || item == null || item.getTitulo() == null) {
                            setText(null);
                    } else {
                            setText(item.getTitulo());
                    }
            }
        });

    }

    @FXML
    void handlerDelete(ActionEvent event) {
        Livro selectedBook = lvPesquisa.getSelectionModel().getSelectedItem();
        
        if(selectedBook != null){    
            LivroDao livroDao = new LivroDao();            
            livroDao.deleteById(selectedBook);            
            carregarLivros();
        }
        
    }

    @FXML
    void handlerPesquisar(ActionEvent event) {
        lvPesquisa.getItems().clear();

        String recebePesquisa = tfPesquisa.getText();

        if(recebePesquisa == ""){
            carregarLivros();
        } else{
            LivroDao livroDao = new LivroDao();
            List<Livro> livros = livroDao.findByTitle(recebePesquisa);

            for(Livro l : livros){
            lvPesquisa.getItems().add(l);
            }

        }
    }

    @FXML
    void selectedRow(MouseEvent event){
        System.out.println(lvPesquisa.getSelectionModel().getSelectedItem());
    }


    private void carregarLivros(){

        lvPesquisa.getItems().clear();
        LivroDao livroDao = new LivroDao();

        List<Livro> livros = livroDao.findAll();
        for(Livro l : livros){
            lvPesquisa.getItems().add(l);
        }
    }


    @FXML
    void handlerPrincipalAlterar() throws Exception{
        Livro selectedBook = lvPesquisa.getSelectionModel().getSelectedItem();

        if(selectedBook != null){
            Main.trocarCenaPrincipalAlterar(selectedBook.getId());
        }else{
            System.out.println("Deu ruim");
        }
    }

    @FXML
    void handlerCadastrarLivro(ActionEvent event) throws Exception{
        Main.trocarCenaPrincipalNovo();
    }

}
