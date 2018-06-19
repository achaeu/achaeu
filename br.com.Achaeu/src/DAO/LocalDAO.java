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
import model.Local;
import model.IEntidade;

/**
 *
 * @author igor-bueno
 */
public class LocalDAO implements IRepository{
@Override
    public IEntidade inserir(IEntidade objeto) {
        try {
            Local local = (Local) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO LOCAL"
                    + " (NOME, DESCRICAO, ID_USUARIO, ID_ENDERECO, TELEFONE_1, TELEFONE_2, ID_CATEGORIA)"
                    + "VALUES (?,?,?,?,?,?,?);";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, local.getNome());
            stmt.setString(2, local.getDescricao());
            stmt.setInt(3, local.getIdUsuario());
            stmt.setInt(4, local.getIdEndereco());
            stmt.setString(5, local.getTelefone1());
            stmt.setString(6, local.getTelefone2());
            stmt.setInt(7,local.getIdCategoria());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                local.setId(rs.getInt(1));
            }

            stmt.close();
            return local;

        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
    try {
            Local local = (Local) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE LOCAL SET NOME = ?, DESCRICAO = ?, ID_USUARIO = ?, ID_ENDERECO = ?,"
                    + " TELEFONE_1 = ?, TELEFONE_2 = ?, ID_CATEGORIA = ? WHERE ID = ?";
            
            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, local.getNome());
            stmt.setString(2, local.getDescricao());
            stmt.setInt(3, local.getIdUsuario());
            stmt.setInt(4, local.getIdEndereco());
            stmt.setString(5, local.getTelefone1());
            stmt.setString(6, local.getTelefone2());
            stmt.setInt(7,local.getIdCategoria());
            stmt.setInt(8, local.getId());

            stmt.execute();

            stmt.close();
            return local;

        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;    
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            Local local = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM LOCAL WHERE id=" + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                local = new Local();
                local.setId(rs.getInt(1));
                local.setNome(rs.getString(2));
                local.setDescricao(rs.getString(3));
                local.setDataCriacao(rs.getTimestamp(4));
                local.setDataAlteracao(rs.getTimestamp(5));
                local.setIdUsuario(rs.getInt(6));
                local.setIdEndereco(rs.getInt(7));
                local.setTelefone1(rs.getString(8));
                local.setTelefone2(rs.getString(9));
                local.setIdCategoria(rs.getInt(10));
            }
            return local;

        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {
        try {
            Local local = null;
            List<IEntidade> locals = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM LOCAL";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                local = new Local();
                local.setId(rs.getInt(1));
                local.setNome(rs.getString(2));
                local.setDescricao(rs.getString(3));
                local.setDataCriacao(rs.getTimestamp(4));
                local.setDataAlteracao(rs.getTimestamp(5));
                local.setIdUsuario(rs.getInt(6));
                local.setIdEndereco(rs.getInt(7));
                local.setTelefone1(rs.getString(8));
                local.setTelefone2(rs.getString(9));
                local.setIdCategoria(rs.getInt(10));
                // Adicionar a lista
                locals.add(local);
            }

            return locals;

        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public IEntidade remover(Integer id) {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Local local = new Local();
            local = (Local) obterUm(id);

            String sql = "DELETE FROM LOCAL WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return local;

        } catch (SQLException ex) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
