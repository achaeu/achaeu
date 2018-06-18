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
import model.Foto;
import model.IEntidade;

/**
 *
 * @author Rodrigo
 */
public class FotoDAO implements IRepository{

    @Override
    public IEntidade inserir(IEntidade objeto) {
        try {
            Foto foto = (Foto) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO foto"
                    + " (DIRETORIO_ABSOLUTO, ID_LOCAL)"
                    + "VALUES (?,?);";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, foto.getDiretorioAbsoluto());
            stmt.setInt(2, foto.getIdLocal());

            int numero = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                foto.setId(rs.getInt(1));
            }

            stmt.close();
            return foto;

        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
    try {
            Foto foto = (Foto) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE foto SET DIRETORIO_ABSOLUTO = ?, ID_LOCAL = ? "
                    + "WHERE ID = ?;";

            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, foto.getDiretorioAbsoluto());
            stmt.setInt(2, foto.getIdLocal());
            stmt.setInt(3, foto.getId());

            stmt.execute();

            stmt.close();
            return foto;

        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;    
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            Foto foto = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM foto WHERE id=" + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                foto = new Foto();
                foto.setId(rs.getInt("ID"));
                foto.setDiretorioAbsoluto(rs.getString("DIRETORIO_ABSOLUTO"));
                foto.setIdLocal(rs.getInt("ID_LOCAL"));
  
            }
            return foto;

        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {
        try {
            Foto foto = null;
            List<IEntidade> fotos = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM foto";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                foto = new Foto();
                foto.setId(rs.getInt("ID"));
                foto.setDiretorioAbsoluto(rs.getString("DIRETORIO_ABSOLUTO"));
                foto.setIdLocal(rs.getInt("ID_LOCAL"));
                // Adicionar a lista
                fotos.add(foto);
            }

            return fotos;

        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public IEntidade remover(Integer id) {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Foto foto = new Foto();
            foto = (Foto) obterUm(id);

            String sql = "DELETE foto WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return foto;

        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
