package dao;

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

    public List<Autor> listarTodos(){

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


        return autores;
    }


    public void alterar(Autor autor){

    }

    public  void deletar(Autor autor){

    }

    public void listarPorid(Autor autor){

    }
}
