package controller;

import dao.AutorDAO;
import dao.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Autor;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.ResourceBundle;



public class AutorFormularioController {

    @FXML private TableView<Autor> tableViewAutor = new TableView<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnID = new TableColumn<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnNOME = new TableColumn<>();
    @FXML private TableColumn<Autor, Integer> tablecolumnEMAIL = new TableColumn<>();
    @FXML private TextField txfNome;
    @FXML private TextField txfEmail;
    @FXML private Button btnSalvar;
    @FXML private Button btnPesquisar;
    @FXML private Button btnDeletar;
    @FXML private Button btnListarTodos;
    @FXML private Button btnAlterar;




    Autor autor = new Autor();
    AutorDAO autorDAO = new AutorDAO();

   public void Listar(){
        List<Autor> listAutor;
        ObservableList<Autor> autorObservableList;

        tablecolumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolumnNOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolumnEMAIL.setCellValueFactory(new PropertyValueFactory<>("email"));

        listAutor = autorDAO.listarTodos();

        autorObservableList = FXCollections.observableArrayList(listAutor);

        tableViewAutor.setItems(autorObservableList);


    }

    public void salvar(){

        //TODO: verificar se esta imprimindo no console
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


 /*   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarTableViewAutor();
    }
    public void carregarTableViewAutor(){

        tablecolumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecolumnNOME.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tablecolumnEMAIL.setCellValueFactory(new PropertyValueFactory<>("email"));

        listAutor = autorDAO.listarTodos();

        autorObservableList = FXCollections.observableArrayList(listAutor);

        tableViewAutor.setItems(autorObservableList);

    }*/
}