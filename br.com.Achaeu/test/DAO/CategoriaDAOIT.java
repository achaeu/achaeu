/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.IEntidade;
import model.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class CategoriaDAOIT {
    
    public CategoriaDAOIT() {
    }

    @Test
    // @Ignore
    public void inserir() {
        
        Categoria l1 = new Categoria();
        CategoriaDAO dao1 = new CategoriaDAO();
        
        l1.setId(3);
        l1.setNome("Categoria Teste");
        l1.setTags("testes");
        l1.setIdCatPai(1);

        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    @Ignore
    public void alterar(){
        
        Categoria l1 = new Categoria();
        CategoriaDAO da1 = new CategoriaDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Categoria) da1.obterUm(2);
        // Altera o local
        l1.setNome("TESTE ALTERADO");
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Categoria l1 = new Categoria();
        CategoriaDAO da1 = new CategoriaDAO();
        List<IEntidade> avaliacoes = new ArrayList<>();
        
        avaliacoes = da1.obterTodos();
        
        for(IEntidade avals : avaliacoes){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    @Ignore
    public void remover(){
        CategoriaDAO dao1 = new CategoriaDAO();
        dao1.remover(4);
        
    }
    
}

    

