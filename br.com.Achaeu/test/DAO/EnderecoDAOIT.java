/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import model.IEntidade;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class EnderecoDAOIT {
    
    public EnderecoDAOIT() {
    }
    
    @Test
    @Ignore
    public void inserir() {
        
        Endereco l1 = new Endereco();
        EnderecoDAO dao1 = new EnderecoDAO();
        
        l1.setLogradouro("Endereco Teste");
        l1.setNumero(11);
        l1.setCep("123");
        l1.setUf("PB");
        l1.setCidade("Vilhena");
        l1.setIdLocalizacao(2);
        
        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    @Ignore
    public void alterar(){
        
        Endereco l1 = new Endereco();
        EnderecoDAO da1 = new EnderecoDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (Endereco) da1.obterUm(4);
        // Altera o local
        l1.setLogradouro("Rua atualizada teste");
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        Endereco l1 = new Endereco();
        EnderecoDAO da1 = new EnderecoDAO();
        List<IEntidade> avaliacoes = new ArrayList<>();
        
        avaliacoes = da1.obterTodos();
        
        for(IEntidade avals : avaliacoes){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    //@Ignore
    public void remover(){
        EnderecoDAO dao1 = new EnderecoDAO();
        dao1.remover(3);
        
    }
    
    
}
