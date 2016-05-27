/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.DAO;

import ws.modelo.Login;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cliente
 */
public class LoginDAO {
    private Connection conexao; 

    public LoginDAO() throws SQLException, ClassNotFoundException{
        this.conexao = new ws.conexao.Conexao().getConnection();
    }    
    
    public Login GetSingleLogin(String login) throws SQLException {
        PreparedStatement stmt = this.conexao.prepareStatement(
            "select * from logins where login = ?");    
        Login usuario = new Login();
        stmt.setString(1, login);  
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            usuario.setId(rs.getInt("id"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setLogin(rs.getString("login"));
        }
        rs.close();
        stmt.close();
        return usuario;
    } 
    
    public void inserir(Login login) throws SQLException {
        String sql = "insert into logins(login, senha, email) values (?,?,?)";
    
        // prepared statement para inserção
        PreparedStatement stmt = conexao.prepareStatement(sql);

        // seta os valores
        stmt.setString(1,login.getLogin());
        stmt.setString(2,login.getSenha());
        stmt.setString(3,login.getEmail());
        // executa
        stmt.execute();
        stmt.close();        
    }    
    
}
