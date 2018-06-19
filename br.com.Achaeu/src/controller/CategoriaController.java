/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CategoriaDAO;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.IEntidade;

/**
 *
 * @author igor
 */
public class CategoriaController implements IEntidadeController{

    @Override
    public IEntidade obterUm(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IEntidade> obterTodos() {
        return new CategoriaDAO().obterTodos();
    }

    @Override
    public IEntidade salvar(IEntidade obj) {
        if(!this.validarModel(obj)){
            return null;
        }
        Categoria categoria = (Categoria)obj;
        CategoriaDAO dao = new CategoriaDAO();        
        Categoria retorno = null;
        
        if(categoria.getId() == null || categoria.getId() == 0){
            retorno = (Categoria) dao.inserir(categoria);
        }
        else{
            retorno = (Categoria) dao.alterar(categoria);
        }
        
        return retorno;
    }

    @Override
    public Boolean validarModel(IEntidade obj) {
        String mensagem = null;
        Categoria categoria = (Categoria) obj;
        if (categoria.getNome().isEmpty() || categoria.getNome()== null) {
            mensagem += System.lineSeparator() + "Categoria vazia!";
        }
        
        if (categoria.getTags().isEmpty() || categoria.getTags()== null) {
            mensagem += System.lineSeparator() + "Tags vazias!";
        }
        
        if (mensagem != null) {
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        }
        return true;
    }

    @Override
    public IEntidade remover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
