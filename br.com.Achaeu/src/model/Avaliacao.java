/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author igor-bueno
 */
public class Avaliacao extends IEntidade{
    private String conteudo;
    private int idUsuario;
    private float nota;
    private int idLocal;
    private Timestamp dataCriacao;
    private Timestamp dataAlteracao;

    public Avaliacao() {
        super();
    }

    public Avaliacao(Integer id, String conteudo, int idUsuario, float nota, int idLocal, Timestamp dataCriacao, Timestamp dataAlteracao) {
        super(id);
        this.conteudo = conteudo;
        this.idUsuario = idUsuario;
        this.nota = nota;
        this.idLocal = idLocal;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Timestamp getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Timestamp dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + super.getId() + ", conteudo=" + conteudo + ", idUsuario=" + idUsuario + ", nota=" + nota + ", idLocal=" + idLocal + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + '}';
    } 
}
