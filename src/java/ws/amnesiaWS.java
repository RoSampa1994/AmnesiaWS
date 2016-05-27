package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ws.DAO.LoginDAO;  
import ws.DAO.QuadroDAO;
import ws.DAO.TarefaDAO;
import ws.modelo.Login;
import ws.modelo.Quadro;
import ws.modelo.Tarefa;

@Path("generics")
public class amnesiaWS {

    @Context
    private UriInfo context;

    public amnesiaWS() {
    }
    
    /*LOGIN*/
    @POST
    @Consumes({"application/json"})
    @Path("login/inserir")
    public void setUsuario(String content) throws SQLException, ClassNotFoundException{
        Gson g = new Gson();
        LoginDAO objlogindao = new LoginDAO();
        Login objlogin = (Login) g.fromJson(content, Login.class);
        objlogindao.inserir(objlogin);    
    }
    /*QUADRO*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("quadro/get/{usuarioID}")
    public String getQuadros(@PathParam("usuarioID") String usuarioID) throws SQLException, ClassNotFoundException{
        List<Quadro> lista;
        QuadroDAO quadros = new QuadroDAO();
        lista = quadros.lista(usuarioID);
        Gson g = new Gson();
        return g.toJson(lista);
    } 
    
    @POST
    @Consumes({"application/json"})
    @Path("quadro/inserir")
    public void setQuadro(String content) throws SQLException, ClassNotFoundException{
        Gson g = new Gson();
        QuadroDAO objquadrodao = new QuadroDAO();
        Quadro objquadro = (Quadro) g.fromJson(content, Quadro.class);
        objquadrodao.inserir(objquadro);    
    }
    @PUT
    @Consumes({"application/json"})
    public void alterarQuadro(String content) {
        
    }     
    /*TAREFA*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("tarefa/get/{quadroID}")
    public String getTarefas(@PathParam("quadroID") String quadroID) throws SQLException, ClassNotFoundException{
        List<Tarefa> lista;
        TarefaDAO tarefa = new TarefaDAO();
        lista = tarefa.lista(quadroID);
        Gson g = new Gson();
        return g.toJson(lista);
    }
    
    @POST
    @Consumes({"application/json"})
    @Path("tarefa/inserir")
    public void setTarefa(String content) throws SQLException, ClassNotFoundException{
        Gson g = new Gson();
        TarefaDAO objtarefadao = new TarefaDAO();
        Tarefa objtarefa = (Tarefa) g.fromJson(content, Tarefa.class);
        objtarefadao.inserir(objtarefa);    
    } 
    @PUT
    @Consumes({"application/json"})
    @Path("tarefa/alterar")
    public void alterarTarefa(String content) throws SQLException, ClassNotFoundException {
        TarefaDAO objtarefadao = new TarefaDAO();
        Gson g = new Gson();
        Tarefa objtarefa = (Tarefa) g.fromJson(content, Tarefa.class);        
        objtarefadao.descricaoalterar(objtarefa);
    }     
    @DELETE
    @Path("tarefa/excluir/{tarefaID}")
    public void excluirTarefa(@PathParam("tarefaID") String tarefaID) throws SQLException, ClassNotFoundException{
       TarefaDAO tarefa = new TarefaDAO(); 
       tarefa.excluir(tarefaID);
    }    
}
