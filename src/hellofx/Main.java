package hellofx;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stagePrincipal) throws Exception {

        primaryStage = stagePrincipal;
        Parent root =   FXMLLoader.load(getClass().getResource("../Javafx/cenaprimeira.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();

    }

    public static void trocarCenaPrincipalAlterar(Long id) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../Javafx/cenaalterar.fxml"));
        Parent root = loader.load();
        AlterarController alterarControler = loader.getController();
        alterarControler.trazerLivro(id);

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    }

    public static void trocarCenaAlterarPrincipal() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenaprincipal.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    }

    public static void trocarCenaPrincipalNovo() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenacadastro.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    }

    public static void chamarAutor() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenaprincipalautor.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    }    

    public static void chamarLivro() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenaprincipal.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    }

    public static void trocarPrincipalAutorParaNovoAutor() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenacadastraautor.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    } 

    public static void trocarPrincipalAutorParaAlterarAutor(Long id) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../Javafx/cenaalterarautor.fxml"));
        Parent root = loader.load();
        AlterarAutorController alterarAutorControler = loader.getController();
        alterarAutorControler.trazerAutor(id);

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    } 

    public static void trocarAlterarAutorParaPrincipalAutor() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenaprincipalautor.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    } 

    public static void trocarNovoAutorParaPrincipalAutor() throws Exception{
        Parent root =   FXMLLoader.load(Main.class.getResource("../Javafx/cenaprincipalautor.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Sistema de Biblioteca");

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.centerOnScreen();
    } 

    public static void main(String[] args) {
        launch(args);
    }
}

