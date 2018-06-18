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
public abstract class IEntidade {
    private Integer id;

    public IEntidade(Integer id) {
        this.id = id;
    }    

    public IEntidade() {
        this.id = 0;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
}
