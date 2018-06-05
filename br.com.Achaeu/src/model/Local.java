/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.Timestamp;

/**
 *
 * @author igor-bueno
 */
public class Local extends IEntidade{
    private String nome;
    private String descricao;
    private Timestamp dataCriacao;
    private Timestamp dataAlteracao;
    private Integer idUsuario;
    private Integer idEndereco;
    private String telefone1;
    private String telefone2;
    private Integer idCategoria;

    public Local() {
        super();
    }

    public Local(Integer id, String nome, String descricao, Timestamp dataCriacao, Timestamp dataAlteracao, Integer idUsuario, Integer idEndereco, String telefone1, String telefone2, Integer idCategoria) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
        this.idUsuario = idUsuario;
        this.idEndereco = idEndereco;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }            

    @Override
    public String toString() {
        return "Local{"+ "id=" + super.getId() + "nome=" + nome + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + ", idUsuario=" + idUsuario + ", idEndereco=" + idEndereco + ", telefone1=" + telefone1 + ", telefone2=" + telefone2 + ", idCategoria=" + idCategoria + '}';
    }
    
    
}
