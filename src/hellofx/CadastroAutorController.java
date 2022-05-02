package hellofx;

import dao.AutorDao;
import domain.Autor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastroAutorController {
    @FXML
    private Label lbNacioAutor;

    @FXML
    private Label lbNascAutor;

    @FXML
    private Label lbNomeAutor;

    @FXML
    private TextField tfNacioAutor;

    @FXML
    private TextField tfNascAutor;

    @FXML
    private TextField tfNomeAutor;

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;

    @FXML
    void handlerCadastrarAutor(ActionEvent event) throws Exception {
        Autor autor = new Autor();

        autor.setNome(tfNomeAutor.getText());
        autor.setNacionalidade(tfNacioAutor.getText());
        autor.setAnoNascimento(Integer.parseInt(tfNascAutor.getText()));

        AutorDao autorDao = new AutorDao();

        autorDao.insert(autor);

        Main.trocarNovoAutorParaPrincipalAutor();
    }

    @FXML
    void handlerCancelarCadastro(ActionEvent event) throws Exception {
        Main.trocarNovoAutorParaPrincipalAutor();
    }
}
