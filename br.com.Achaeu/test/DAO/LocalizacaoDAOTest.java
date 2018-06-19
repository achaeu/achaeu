/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.IEntidade;
import model.Localizacao;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class LocalizacaoDAOTest {
    
    public LocalizacaoDAOTest() {
    }


    @Test
    @Ignore
    public void inserir() {
        
        Localizacao l1 = new Localizacao(1,1112.1,111.1);
        LocalizacaoDAO dao1 = new LocalizacaoDAO();
        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    @Ignore
    public void alterar(){
        
        Localizacao l1 = new Localizacao();
        LocalizacaoDAO da1 = new LocalizacaoDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Localizacao) da1.obterUm(4);
        // Altera o local
        l1.setLatitude(2222.1);
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Localizacao l1 = new Localizacao();
        LocalizacaoDAO da1 = new LocalizacaoDAO();
        List<IEntidade> localizacaos = new ArrayList<>();
        
        localizacaos = da1.obterTodos();
        
        for(IEntidade locais : localizacaos){
            System.out.println(locais.getId()+" ");
        }
        
    }
    
    @Test
    public void remover(){
        LocalizacaoDAO dao1 = new LocalizacaoDAO();
        dao1.remover(5);
        
        
    }
    
}
