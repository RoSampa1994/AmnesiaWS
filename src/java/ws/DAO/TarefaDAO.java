package ws.DAO;

import ws.conexao.Conexao;
import ws.modelo.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TarefaDAO {

    private Connection conexao;

    public TarefaDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new Conexao().getConnection();
    }
	public void inserir(Tarefa tarefa)  throws SQLException {
            String sql = "insert into tarefas(quadro_id, descricao, status) values (?,?,?)";
            // prepared statement para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // seta os valores
            stmt.setInt(1,tarefa.getQuadro().getId());
            stmt.setString(2,tarefa.getDescricao());
            stmt.setString(3,tarefa.getStatus());
            // executa
            stmt.execute();
            stmt.close();

	}

	public void excluir(String tarefaID)  throws SQLException {
            String sql = "delete from tarefas WHERE id = ?";
            // prepared statement para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // seta os valores
            stmt.setString(1, tarefaID);
            // executa
            stmt.execute();
            stmt.close();
            conexao.close();
	}

    public List<Tarefa> lista(String quadro_id) throws SQLException{
       List<Tarefa> tarefas = new ArrayList<Tarefa>();

       PreparedStatement stmt = this.conexao.prepareStatement("select * from tarefas where quadro_id = ?");
       stmt.setString(1, quadro_id); 
       ResultSet rs = stmt.executeQuery();

       while (rs.next()) {
           // criando o objeto Tarefa
           Tarefa tarefa = new Tarefa();
           tarefa.setId(rs.getInt("id"));
           tarefa.setDescricao(rs.getString("descricao"));
           // adiciona a tarefa à lista de tarefa
           tarefas.add(tarefa);
       }
       rs.close();
       stmt.close();
       return tarefas;
    }

	public void descricaoalterar(Tarefa tarefa)  throws SQLException {
            String sql = "update tarefas set descricao = ? where id = ?";
            // prepared statement para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);
            // seta os valores
            stmt.setString(1,tarefa.getDescricao());
            stmt.setInt(2,tarefa.getId());
            // executa
            stmt.execute();
            stmt.close();
            conexao.close();
	}
}
