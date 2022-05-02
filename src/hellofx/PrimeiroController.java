package hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimeiroController {
    

    @FXML
    private Button btAutores;

    @FXML
    private Button btLivros;

    @FXML
    void handlerEscolheAutor(ActionEvent event) throws Exception {
        Main.chamarAutor();
    }

    @FXML
    void handlerEscolheLivro(ActionEvent event) throws Exception {
        Main.chamarLivro();
    }
}
