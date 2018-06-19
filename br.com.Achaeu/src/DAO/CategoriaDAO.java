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
import model.Avaliacao;
import model.Categoria;
import model.IEntidade;

/**
 *
 * @author Rodrigo
 */
public class CategoriaDAO implements IRepository {

    @Override
    public IEntidade inserir(IEntidade objeto) {
       try {
            Categoria categoria = (Categoria) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO CATEGORIA"
                    + " (NOME, TAGS, ID_CAT_PAI)"
                    + "VALUES (?,?,?)";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTags());
            stmt.setInt(3, categoria.getIdCatPai());

            int numero = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                categoria.setId(rs.getInt(1));
            }

            stmt.close();
            return categoria;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
     try {
            Categoria categoria = (Categoria) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE CATEGORIA SET NOME = ?, TAGS = ?, ID_CAT_PAI = ?"
                    + " WHERE ID = ?;";

            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getTags());
            stmt.setInt(3, categoria.getIdCatPai());
            stmt.setInt(4, categoria.getId());

            stmt.execute();

            stmt.close();
            return categoria;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            Categoria categoria = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM CATEGORIA WHERE id = " + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("ID"));
                categoria.setNome(rs.getString("NOME"));
                categoria.setTags(rs.getString("TAGS"));
                categoria.setIdCatPai(rs.getInt("ID_CAT_PAI"));
                categoria.setCategoriaPai((Categoria) this.obterUm(categoria.getIdCatPai()));
            }
            return categoria;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {
        try {
            Categoria categoria = null;
            List<IEntidade> categorias = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM CATEGORIA";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("ID"));
                categoria.setNome(rs.getString("NOME"));
                categoria.setTags(rs.getString("TAGS"));
                categoria.setIdCatPai(rs.getInt("ID_CAT_PAI"));
                // Adicionar a lista
                categorias.add(categoria);
            }

            return categorias;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }}

    @Override
    public IEntidade remover(Integer id) {
       try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Categoria categoria = new Categoria();
            categoria = (Categoria) obterUm(id);

            String sql = "DELETE FROM CATEGORIA WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return categoria;

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
