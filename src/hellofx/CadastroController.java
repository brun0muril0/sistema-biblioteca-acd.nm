package hellofx;

import java.util.ArrayList;
import java.util.List;

import dao.AutorDao;
import dao.LivroDao;
import domain.Autor;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class CadastroController {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    private Label lbAutor;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbEdicao;

    @FXML
    private Label lbIsbn;

    @FXML
    private Label lbTitulo;

    @FXML
    private ListView<Autor> lvAutores;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfEdicao;

    @FXML
    private TextField tfIsbn;

    @FXML
    private TextField tfTitulo;

    
    
    private List<Autor> autoresSelecionados = new ArrayList<Autor>();

    @FXML
    public void initialize(){
        carregarAutores();

        lvAutores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvAutores.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            autoresSelecionados.clear();
            autoresSelecionados.addAll(lvAutores.getSelectionModel().getSelectedItems());

        });
        lvAutores.setCellFactory(param -> new ListCell<Autor>() {
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

       @FXML
    void handlerCadastrarLivro(ActionEvent event) throws Exception {
        Livro livro = new Livro();
        
        livro.setTitulo(tfTitulo.getText());
        livro.setIsbn(tfIsbn.getText());
        livro.setEdicao(Integer.parseInt(tfEdicao.getText()));
        livro.setDescricao(tfDescricao.getText());
        livro.setAutores(autoresSelecionados);
        
        LivroDao livroDao = new LivroDao();

        livroDao.insert(livro);

    }

    private void carregarAutores(){

        lvAutores.getItems().clear();
        AutorDao autorDao = new AutorDao();

        List<Autor> autores = autorDao.findAll();
        for(Autor l : autores){
            lvAutores.getItems().add(l);
        }
    }

    @FXML
    void handlerCancelarNovoLivro(ActionEvent event) throws Exception {
        Main.trocarCenaAlterarPrincipal();
    }
}
