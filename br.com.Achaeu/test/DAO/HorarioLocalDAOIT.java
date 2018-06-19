/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.HorarioLocal;
import model.IEntidade;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rodrigo
 */
public class HorarioLocalDAOIT {
    
    public HorarioLocalDAOIT() {
    }

  @Test
    //@Ignore
    public void inserir() {
        
        HorarioLocal l1 = new HorarioLocal();
        HorarioLocalDAO dao1 = new HorarioLocalDAO();
        
        l1.setEntrada1(Time.valueOf("10:00:00"));
        l1.setSaida1(Time.valueOf("10:00:00"));
        l1.setDiaSemana(1);
        l1.setFuncionamento(1);
        l1.setIdLocal(4);
        
        if (dao1.inserir(l1).getId() != 0 ){
            System.out.println("Inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir"); 
        }
    }
    
    
    @Test
    @Ignore
    public void alterar(){
        
        HorarioLocal l1 = new HorarioLocal();
        HorarioLocalDAO da1 = new HorarioLocalDAO();
        System.out.println("Teste 2");
        
        // Obtem o que deseja alterar 
        l1 = (HorarioLocal) da1.obterUm(5);
        // Altera o local
        l1.setEntrada1(Time.valueOf(LocalTime.MIN));
        // Grava alteração
        da1.alterar(l1);
    }

    @Test
    @Ignore
    public void ObterTodos(){
      
        HorarioLocal l1 = new HorarioLocal();
        HorarioLocalDAO da1 = new HorarioLocalDAO();
        List<IEntidade> fotos = new ArrayList<>();
        
        fotos = da1.obterTodos();
        
        for(IEntidade avals : fotos){
            System.out.println(avals.getId()+" ");
        }
        
    }
    
    @Test
    @Ignore
    public void remover(){
        HorarioLocalDAO dao1 = new HorarioLocalDAO();
        dao1.remover(5);
        
    }
    
}
