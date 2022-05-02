package hellofx;

import java.util.List;

import dao.AutorDao;
import domain.Autor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class PrincipalControllerAutor {
    

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btNovo;

    @FXML
    private Button btPesquisarAutor;

    @FXML
    private Label lbPesquisaAutor;

    @FXML
    private ListView<Autor> lwAutores;

    @FXML
    private TextField tfPesquisaAutor;

    @FXML
    public void initialize(){
        carregarAutores();

        lwAutores.setCellFactory(param -> new ListCell<Autor>() {
            @Override
            protected void updateItem(Autor item, boolean empty) {
                    super.updateItem(item, empty);
    
                    if (empty || item == null || item.getNome() == null) {
                            setText(null);
                    } else {
                            setText(item.getNome());
                    }
            }
        });

    }

    private void carregarAutores(){

        lwAutores.getItems().clear();
        AutorDao autorDao = new AutorDao();

        List<Autor> autores = autorDao.findAll();
        for(Autor l : autores){
            lwAutores.getItems().add(l);
        }
    }

    @FXML
    void handlerAlterarAutor(ActionEvent event) throws Exception {
        Autor selectedBook = lwAutores.getSelectionModel().getSelectedItem();

        if(selectedBook != null){
            Main.trocarPrincipalAutorParaAlterarAutor(selectedBook.getId());
        }else{
            System.out.println("Deu ruim");
        }
    }

    @FXML
    void handlerExcluirAutor(ActionEvent event) {
        Autor selectedBook = lwAutores.getSelectionModel().getSelectedItem();
        
        // System.out.println("Entrei aqui!");
        if(selectedBook != null){    
            AutorDao autorDao = new AutorDao();            
            autorDao.deleteById(selectedBook);            
            carregarAutores();
        }
        
    }

    @FXML
    void handlerNovoAutor(ActionEvent event) throws Exception {
        Main.trocarPrincipalAutorParaNovoAutor();
    }

    @FXML
    void handlerPesquisar(ActionEvent event) {
        lwAutores.getItems().clear();

        String recebePesquisa = tfPesquisaAutor.getText();

        if(recebePesquisa == ""){
            carregarAutores();
        } else{
            AutorDao autorDao = new AutorDao();
            List<Autor> autores = autorDao.findByTitle(recebePesquisa);

            for(Autor l : autores){
                lwAutores.getItems().add(l);
            }

        }
    }
}
