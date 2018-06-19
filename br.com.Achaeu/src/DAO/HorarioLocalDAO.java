/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HorarioLocal;
import model.IEntidade;

/**
 *
 * @author igor-bueno
 */
public class HorarioLocalDAO implements IRepository{
@Override
    public IEntidade inserir(IEntidade objeto) {
        try {
            HorarioLocal horarioLocal = (HorarioLocal) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO HORARIO_LOCAL"
                    + " (ENTRADA_1, SAIDA_1, ENTRADA_2, SAIDA_2, FUNCIONAMENTO, DIA_SEMANA, ID_LOCAL)"
                    + "VALUES (?,?,?,?,?,?,?);";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setTime(1, horarioLocal.getEntrada1());
            stmt.setTime(2, horarioLocal.getSaida1());
            stmt.setTime(3, horarioLocal.getEntrada2());
            stmt.setTime(4, horarioLocal.getSaida2());
            stmt.setInt(5, horarioLocal.getFuncionamento());
            stmt.setInt(6, horarioLocal.getDiaSemana());
            stmt.setInt(7, horarioLocal.getIdLocal());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                horarioLocal.setId(rs.getInt(1));
            }

            stmt.close();
            return horarioLocal;

        } catch (SQLException ex) {
            Logger.getLogger(HorarioLocalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
    try {
            HorarioLocal horarioLocal = (HorarioLocal) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE HORARIO_LOCAL SET ENTRADA1 = ?, SAIDA1 = ?, ENTRADA2 = ?, SAIDA2 = ?,"
                    + " FUNCIONAMENTO = ?, DIA_SEMANA = ?, ID_LOCAL = ? WHERE ID = ?";
            
            stmt = conexao.prepareStatement(sql);

            stmt.setTime(1, horarioLocal.getEntrada1());
            stmt.setTime(2, horarioLocal.getSaida1());
            stmt.setTime(3, horarioLocal.getEntrada2());
            stmt.setTime(4, horarioLocal.getSaida2());
            stmt.setInt(5, horarioLocal.getFuncionamento());
            stmt.setInt(6, horarioLocal.getDiaSemana());
            stmt.setInt(7, horarioLocal.getIdLocal());
            stmt.setInt(8, horarioLocal.getId());

            stmt.execute();

            stmt.close();
            return horarioLocal;

        } catch (SQLException ex) {
            Logger.getLogger(HorarioLocalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;    
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            HorarioLocal horarioLocal = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM HORARIO_LOCAL WHERE id=" + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                horarioLocal = new HorarioLocal();
                horarioLocal.setId(rs.getInt(1));
                horarioLocal.setEntrada1(rs.getTime(2));
                horarioLocal.setSaida1(rs.getTime(3));
                horarioLocal.setEntrada2(rs.getTime(4));
                horarioLocal.setSaida2(rs.getTime(5));
                horarioLocal.setFuncionamento(rs.getInt(6));
                horarioLocal.setDiaSemana(rs.getInt(7));
                horarioLocal.setIdLocal(rs.getInt(8));
  
            }
            return horarioLocal;

        } catch (SQLException ex) {
            Logger.getLogger(HorarioLocalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {
        try {
            HorarioLocal horarioLocal = null;
            List<IEntidade> horarioLocals = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM HORARIO_LOCAL";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                horarioLocal = new HorarioLocal();
                horarioLocal.setId(rs.getInt(1));
                horarioLocal.setEntrada1(rs.getTime(2));
                horarioLocal.setSaida1(rs.getTime(3));
                horarioLocal.setEntrada2(rs.getTime(4));
                horarioLocal.setSaida2(rs.getTime(5));
                horarioLocal.setFuncionamento(rs.getInt(6));
                horarioLocal.setDiaSemana(rs.getInt(7));
                horarioLocal.setIdLocal(rs.getInt(8));
                // Adicionar a lista
                horarioLocals.add(horarioLocal);
            }

            return horarioLocals;

        } catch (SQLException ex) {
            Logger.getLogger(HorarioLocalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public IEntidade remover(Integer id) {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            HorarioLocal horarioLocal = new HorarioLocal();
            horarioLocal = (HorarioLocal) obterUm(id);

            String sql = "DELETE FROM HORARIO_LOCAL WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return horarioLocal;

        } catch (SQLException ex) {
            Logger.getLogger(HorarioLocalDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
