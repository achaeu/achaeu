/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.LocalDAO;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class LocalIT {
    
    public LocalIT() {
    }

@Test
    @Ignore
    public void inserir() {
        
        Local l1 = new Local();
        LocalDAO dao1 = new LocalDAO();
        
        l1.setNome("Local teste");
        l1.setDescricao("TEste de Criação de DAO");
        l1.setIdUsuario(2);
        l1.setIdEndereco(1);
        l1.setIdCategoria(1);
        
        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    //@Ignore
    public void alterar(){
        
        Local l1 = new Local();
        LocalDAO da1 = new LocalDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Local) da1.obterUm(5);
        // Altera o local
        l1.setDescricao("Descrição 223");
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Local l1 = new Local();
        LocalDAO da1 = new LocalDAO();
        List<IEntidade> fotos = new ArrayList<>();
        
        fotos = da1.obterTodos();
        
        for(IEntidade avals : fotos){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    @Ignore
    public void remover(){
        LocalDAO dao1 = new LocalDAO();
        dao1.remover(5);
        
    }
    
}
