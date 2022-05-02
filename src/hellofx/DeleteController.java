package hellofx;

import java.util.List;

import dao.LivroDao;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DeleteController {
    @FXML
    private Button btDelete;

    @FXML
    private Button btPesquisa;

    @FXML
    private Label lbDigiteNome;

    @FXML
    private ListView<String> lvPesquisa;

    @FXML
    private TextField tfPesquisa;

    @FXML
    public void initialize(){
        carregarLivros();

    }

    @FXML
    void handlerDelete(ActionEvent event) {
        System.out.println(lvPesquisa.getSelectionModel().getSelectedItem());
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
            lvPesquisa.getItems().add(l.getTitulo());
            }

        }
    }

    @FXML
    void selectedRow(MouseEvent event){
        System.out.println(lvPesquisa.getSelectionModel().getSelectedItem());
    }


    private void carregarLivros(){
        LivroDao livroDao = new LivroDao();

        List<Livro> livros = livroDao.findAll();
        for(Livro l : livros){
            lvPesquisa.getItems().add(l.getTitulo());
        }
    }

}
