package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class PrincipalController implements Initializable{

    @FXML private MenuItem menuitemAutorAutores;
    @FXML private MenuItem menuitemAutorListar;
    @FXML private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void menuitemAutores() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/autor_formulario.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    public void cadastrar(ActionEvent actionEvent) {
    }

    public void Pesquisar(ActionEvent actionEvent) {
    }

    public void Listar(ActionEvent actionEvent) {
    }

    public void Deletar(ActionEvent actionEvent) {
    }

    public void Alterar(ActionEvent actionEvent) {
    }
}

