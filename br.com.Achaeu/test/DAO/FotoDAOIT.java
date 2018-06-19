/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Foto;
import model.IEntidade;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class FotoDAOIT {
    
    public FotoDAOIT() {
    }
    
    @Test
    @Ignore
    public void inserir() {
        
        Foto l1 = new Foto();
        FotoDAO dao1 = new FotoDAO();
        
        l1.setDiretorioAbsoluto("aws.amazon.com/Fotos/IFRaoo/2");
        l1.setIdLocal(3);

        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    @Ignore
    public void alterar(){
        
        Foto l1 = new Foto();
        FotoDAO da1 = new FotoDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Foto) da1.obterUm(5);
        // Altera o local
        l1.setDiretorioAbsoluto("ws.amazon.com/Fotos/1111222/1");
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Foto l1 = new Foto();
        FotoDAO da1 = new FotoDAO();
        List<IEntidade> fotos = new ArrayList<>();
        
        fotos = da1.obterTodos();
        
        for(IEntidade avals : fotos){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    //@Ignore
    public void remover(){
        FotoDAO dao1 = new FotoDAO();
        dao1.remover(5);
        
    }
    
}
