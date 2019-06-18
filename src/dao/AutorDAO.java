package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conexao;

    public  AutorDAO() {

        conexao = new ConnectionFactory().getConnection();
    }
    public void conextar2(){
        conexao = new ConnectionFactory().getConnection();

    }

    public void inserir(Autor autor){

        String sql = "insert into autores (nome, email) values (?, ?)";


        try {
            // preparar a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1 , autor.getNome());
            stmt.setString(2, autor.getEmail());


            // executar o comando
            stmt.execute();

            conexao.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Autor> listarTodos(){
        conextar2();
        String sql = "select * from autores";
        List<Autor> autores = new ArrayList<>();


        try{
            // preparar conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // executar

            ResultSet resultados = stmt.executeQuery();

            //percorrer os resultados

            while (resultados.next()){
                Autor autor = new Autor();
                autor.setId(resultados.getInt("id"));
                autor.setNome(resultados.getString("nome"));
                autor.setEmail(resultados.getString("email"));


                autores.add(autor);
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        ObservableList autores2 = FXCollections.observableList(autores);
        return autores2;
    }
    public void alterar(Autor autor){
        conextar2();
        String sql = "update autores set nome = ?, email = ? where id = ?";
        try{
            //prepara conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.setInt(3, autor.getId());
            //executa
            stmt.execute();
            //fecha conexao
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public  void deletar(Autor autor){
        conextar2();
        String sql = "delete *from autores where id = ?";
        try{
            //prepara conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getEmail());
            stmt.setInt(3, autor.getId());
            //executa
            stmt.execute();
            //fecha conexao
            stmt.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }




    public Autor listarPorid (int id){
        conextar2();
        String sql = "select * from autores where id = ?";
       Autor autor = new Autor();
        try {
            //prepara a conexao
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            //executa
            ResultSet resultado = stmt.executeQuery();
            resultado.next();
            autor.setId(resultado.getInt("id"));
            autor.setNome(resultado.getString("nome"));
            autor.setEmail(resultado.getString("email"));
            // fechar conexao
            conexao.close();
        }catch ( SQLException e){
            throw new RuntimeException(e);
        }
        return autor;
    }



}






