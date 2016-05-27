/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.modelo;

/**
 *
 * @author Cliente
 */
public class Tarefa {
    private int id;

    private Quadro quadro;

    private String descricao;

    private String status;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quadro getQuadro() {
        return quadro;
    }

    public void setQuadro(Quadro quadro) {
        this.quadro = quadro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
