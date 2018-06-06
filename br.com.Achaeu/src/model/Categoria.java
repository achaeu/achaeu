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
public class Categoria extends IEntidade{
    private String nome;
    private String tags;
    private Integer idCatPai;
    private Categoria categoriaPai;

    public Categoria() {
        super();
        this.categoriaPai = new Categoria();
    }

    public Categoria(String nome, String tags, Integer idCatPai, Categoria categoriaPai, Integer id) {
        super(id);
        this.nome = nome;
        this.tags = tags;
        this.idCatPai = idCatPai;
        this.categoriaPai = categoriaPai;
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

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    @Override
    public String toString() {
        return "Categoria{" + "nome=" + nome + ", tags=" + tags + ", idCatPai=" + idCatPai + ", categoriaPai=" + categoriaPai + '}';
    }
    

}
