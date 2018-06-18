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
    private Usuario usuario;
    private float nota;
    private int idLocal;
    private Local local;
    private Timestamp dataCriacao;
    private Timestamp dataAlteracao;

    public Avaliacao() {
        super();
        this.usuario = new Usuario();
        this.local = new Local();
    }

    public Avaliacao(String conteudo, int idUsuario, Usuario usuario, float nota, int idLocal, Local local, Timestamp dataCriacao, Timestamp dataAlteracao, Integer id) {
        super(id);
        this.conteudo = conteudo;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nota = nota;
        this.idLocal = idLocal;
        this.local = local;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
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
        return "Avaliacao{" + "conteudo=" + conteudo + ", idUsuario=" + idUsuario + ", usuario=" + usuario + ", nota=" + nota + ", idLocal=" + idLocal + ", local=" + local + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + '}';
    } 

}
