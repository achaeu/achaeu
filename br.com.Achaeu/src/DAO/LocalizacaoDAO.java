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
import model.Localizacao;
import model.IEntidade;

/**
 *
 * @author Rodrigo
 */
public class LocalizacaoDAO implements IRepository{

    @Override
    public IEntidade inserir(IEntidade objeto) {
        try {
            Localizacao localizacao = (Localizacao) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO LOCALIZACAO"
                    + " (LATITUDE, LONGITUDE)"
                    + "VALUES (?,?);";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setDouble(1, localizacao.getLatitude());
            stmt.setDouble(2, localizacao.getLongitude());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                localizacao.setId(rs.getInt(1));
            }

            stmt.close();
            return localizacao;

        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
    try {
            Localizacao localizacao = (Localizacao) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE LOCALIZACAO SET LATITUDE = ?, LONGITUDE = ? "
                    + "WHERE ID = ?;";

            stmt = conexao.prepareStatement(sql);

            stmt.setDouble(1, localizacao.getLatitude());
            stmt.setDouble(2, localizacao.getLongitude());
            stmt.setInt(3, localizacao.getId());

            stmt.execute();

            stmt.close();
            return localizacao;

        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;    
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            Localizacao localizacao = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM LOCALIZACAO WHERE ID=" + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                localizacao = new Localizacao();
                localizacao.setId(rs.getInt(1));
                localizacao.setLatitude(rs.getDouble(2));
                localizacao.setLongitude(rs.getDouble(3));
  
            }
            return localizacao;

        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {
        try {
            Localizacao localizacao = null;
            List<IEntidade> localizacaos = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM LOCALIZACAO";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                localizacao = new Localizacao();
                localizacao.setId(rs.getInt(1));
                localizacao.setLatitude(rs.getDouble(2));
                localizacao.setLongitude(rs.getDouble(3));
  
                // Adicionar a lista
                localizacaos.add(localizacao);
            }

            return localizacaos;

        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public IEntidade remover(Integer id) {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Localizacao localizacao = new Localizacao();
            localizacao = (Localizacao) obterUm(id);

            String sql = "DELETE FROM LOCALIZACAO WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return localizacao;

        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
