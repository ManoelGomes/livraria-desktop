package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import dao.AutorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Autor;


public class PrincipalController implements Initializable{

    @FXML private TableView<Autor> tableViewAutor = new TableView<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnID = new TableColumn<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnNOME = new TableColumn<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnEMAIL = new TableColumn<>();
    @FXML private MenuItem menuitemAutorAutores;
    @FXML private MenuItem menuitemAutorListar;
    @FXML private MenuItem menuitemAutores;
    @FXML private AnchorPane anchorPane;
    @FXML private Button Salvar;
    @FXML private TextField txfNome, txfEmail;

    private Autor del;


    Autor autor = new Autor();
    AutorDAO autorDAO = new AutorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void menuitemAutores() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/autor_formulario.fxml"));
        anchorPane.getChildren().setAll(a);
    }


    public void salvar(ActionEvent salvar) {
        System.out.println("Nome: " + txfNome.getText());
        System.out.println("Email: " + txfEmail.getText());



        autor.setNome(txfNome.getText());
        autor.setEmail(txfEmail.getText());

        autorDAO.inserir(autor);



        limparcampos();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de autores");
        alert.setHeaderText("Cadastro de autores");
        alert.setContentText("Autor cadastrado com sucesso");
        alert.showAndWait();

    }

    private void limparcampos() {
        txfNome.setText("");
        txfEmail.setText("");
        txfNome.requestFocus();
    }

    public void Pesquisar(ActionEvent actionEvent) {
    }

    public void Listar(ActionEvent actionEvent) {
        del = null;

        List<Autor> listAutor;
        ObservableList<Autor> autorObservableList;

        tablecolumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolumnNOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolumnEMAIL.setCellValueFactory(new PropertyValueFactory<>("email"));

        listAutor = autorDAO.listarTodos();

        autorObservableList = FXCollections.observableArrayList(listAutor);

        tableViewAutor.setItems(autorObservableList);

        tableViewAutor.setOnMouseClicked(TableClick);
    }

    public void Deletar() {
        try {
            autorDAO.deletar(del);
        }catch (Exception e){
            System.out.println("Erro ao deletar!" + e);
        }
    }

    public void Alterar(ActionEvent actionEvent) {
    }

    private EventHandler<MouseEvent> TableClick = evt ->{
        del = tableViewAutor.getSelectionModel().getSelectedItem();

    };
}

