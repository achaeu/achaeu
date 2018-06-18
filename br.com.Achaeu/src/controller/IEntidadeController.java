/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.IEntidade;

/**
 *
 * @author igor-bueno
 */
public interface IEntidadeController {

    /**
     * Retorna um objeto a partir do Id
     *
     * @param id
     * @return
     */
    public IEntidade obterUm(int id);
    public List<IEntidade> obterTodos();
    public IEntidade salvar(IEntidade obj);
    public IEntidade remover();
}
