package ws.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ws.conexao.Conexao;
import ws.modelo.Quadro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuadroDAO{ 
    private Connection conexao; 
 
    public QuadroDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new Conexao().getConnection();
    }
        
    
    public void inserir(Quadro quadro) throws SQLException {
        String sql = "insert into quadros(titulo, descricao) values (?,?)";
    
        // prepared statement para inserção
        PreparedStatement stmt = conexao.prepareStatement(sql);

        // seta os valores
        stmt.setString(1,quadro.getTitulo());
        stmt.setString(2,quadro.getDescricao());
        // executa
        stmt.execute();
        stmt.close();        
    }

    public void excluir(String quadro) throws SQLException {
        String sql = "delete from quadros WHERE id = ?";
        // prepared statement para inserção
        PreparedStatement stmt = conexao.prepareStatement(sql);
        // seta os valores
        stmt.setString(1,quadro);
        // executa
        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public void tituloalterar(Quadro quadro) throws SQLException{
        String sql = "update quadros set titulo = ? where id = ?";
        // prepared statement para inserção
        PreparedStatement stmt = conexao.prepareStatement(sql);
        // seta os valores
        stmt.setString(1,quadro.getTitulo());
        stmt.setInt(1,quadro.getId());
        // executa
        stmt.execute();
        stmt.close();
        conexao.close();
    }

    public void descricaoalterar(Quadro quadro) throws SQLException{
        String sql = "update quadros set descricao = ? where id = ?";
        // prepared statement para inserção
        PreparedStatement stmt = conexao.prepareStatement(sql);
        // seta os valores
        stmt.setString(1,quadro.getDescricao());
        stmt.setInt(1,quadro.getId());
        // executa
        stmt.execute();
        stmt.close();
        conexao.close();
    }    
    
        // empresas: array armazena a lista de registros
    public List<Quadro> lista(String login_id) throws SQLException{
       List<Quadro> quadros = new ArrayList<Quadro>();

       PreparedStatement stmt = this.conexao.prepareStatement("select * from quadros where login_id = ?");
       stmt.setString(1, login_id); 
       ResultSet rs = stmt.executeQuery();

       while (rs.next()) {      
           // criando o objeto Empresa
           Quadro quadro = new Quadro();
           quadro.setId(rs.getInt("id"));
           quadro.setTitulo(rs.getString("descricao"));
           quadro.setDescricao(rs.getString("titulo"));
           // adiciona o empresa à lista de empresas
           quadros.add(quadro);
       }

       rs.close();
       stmt.close();
       return quadros;
        
    }    
}
