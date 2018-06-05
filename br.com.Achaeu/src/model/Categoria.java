/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author igor-bueno
 */
public class Categoria {
    private Integer id;
    private String nome;
    private String tags;
    private Integer idCatPai;

    public Categoria() {
    }

    public Categoria(Integer id, String nome, String tags, Integer idCatPai) {
        this.id = id;
        this.nome = nome;
        this.tags = tags;
        this.idCatPai = idCatPai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIdCatPai() {
        return idCatPai;
    }

    public void setIdCatPai(Integer idCatPai) {
        this.idCatPai = idCatPai;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nome=" + nome + ", tags=" + tags + ", idCatPai=" + idCatPai + '}';
    }       
}
