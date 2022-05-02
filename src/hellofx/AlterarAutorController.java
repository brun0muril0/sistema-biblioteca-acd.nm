package hellofx;

import dao.AutorDao;
import domain.Autor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlterarAutorController {
    @FXML
    private Button btCancelar;

    @FXML
    private Button btSalvar;

    @FXML
    private Label lbNacionalidade;

    @FXML
    private Label lbNascimento;

    @FXML
    private Label lbNome;

    @FXML
    private TextField tfNacionalidade;

    @FXML
    private TextField tfNascimento;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfId;

    @FXML
    void handlerCancelarAlterar(ActionEvent event) throws Exception {
        Main.trocarAlterarAutorParaPrincipalAutor();
    }

    @FXML
    void handlerSalvarAlterar(ActionEvent event) throws Exception {
        Autor autor = new Autor();

        autor.setId(Long.parseLong(tfId.getText()));
        autor.setNome(tfNome.getText());
        autor.setNacionalidade(tfNacionalidade.getText());
        autor.setAnoNascimento(Integer.parseInt(tfNascimento.getText()));

        AutorDao autorDao = new AutorDao();

        autorDao.update(autor);

        Main.trocarAlterarAutorParaPrincipalAutor();

        
    }

    public void trazerAutor(Long id){
        AutorDao autorDao = new AutorDao();

        Autor autor = autorDao.findById(id);

        tfId.setText(autor.getId().toString());
        tfNome.setText(autor.getNome());
        tfNacionalidade.setText(autor.getNacionalidade());
        tfNascimento.setText(autor.getAnoNascimento().toString());
    }

}
