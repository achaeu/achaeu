/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Avaliacao;
import model.IEntidade;
import model.Localizacao;
import model.Usuario;
import org.junit.Test;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class AvaliacaoDAOIT {
    
    public AvaliacaoDAOIT() {
    }

    @Test
    @Ignore
    public void inserir() {
        
        Avaliacao l1 = new Avaliacao();
        UsuarioDAO daoUser = new UsuarioDAO();
        AvaliacaoDAO dao1 = new AvaliacaoDAO();
        
        l1.setConteudo("Teste 1");
        l1.setIdUsuario(1);
        l1.setUsuario((Usuario) daoUser.obterUm(l1.getIdUsuario()));
        l1.setNota(10);
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
        
        Avaliacao l1 = new Avaliacao();
        AvaliacaoDAO da1 = new AvaliacaoDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Avaliacao) da1.obterUm(12);
        // Altera o local
        l1.setConteudo("TESTE ALTERADO");
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Avaliacao l1 = new Avaliacao();
        AvaliacaoDAO da1 = new AvaliacaoDAO();
        List<IEntidade> avaliacoes = new ArrayList<>();
        
        avaliacoes = da1.obterTodos();
        
        for(IEntidade avals : avaliacoes){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    //@Ignore
    public void remover(){
        AvaliacaoDAO dao1 = new AvaliacaoDAO();
        dao1.remover(4);
        
    }
    
}
