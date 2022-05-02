package hellofx;

import dao.LivroDao;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlterarController {
    @FXML
    private Button btAlterar;

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
    private TextField tfAutor;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfEdicao;

    @FXML
    private TextField tfIsbn;

    @FXML
    private TextField tfTitulo;

    @FXML
    private TextField tfId;

    @FXML
    void handlerCancelarAlteracao(ActionEvent event) throws Exception {
        Main.trocarCenaAlterarPrincipal();
    }

    @FXML
    void handlerConfirmarAlteracao(ActionEvent event) throws Exception {

        Livro livro = new Livro();

        livro.setId(Long.parseLong(tfId.getText()));
        livro.setTitulo(tfTitulo.getText());
        livro.setIsbn(tfIsbn.getText());
        livro.setEdicao(Integer.parseInt(tfEdicao.getText()));
        livro.setDescricao(tfDescricao.getText());

        LivroDao livroDao = new LivroDao();

        livroDao.update(livro);

        
    }

    public void trazerLivro(Long id){
        LivroDao livroDao = new LivroDao();

        Livro livro = livroDao.findById(id);

        tfId.setText(livro.getId().toString());
        tfTitulo.setText(livro.getTitulo());
        tfIsbn.setText(livro.getIsbn());
        tfEdicao.setText(livro.getEdicao().toString());
        tfDescricao.setText(livro.getDescricao());

    }

    public void trazerAutor(Long id) {
    }

}
