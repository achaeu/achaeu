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
import model.IEntidade;

/**
 *
 * @author igor-bueno
 */
public class AvaliacaoDAO implements IRepository {

    @Override
    public IEntidade inserir(IEntidade objeto) {
        try {
            Avaliacao avaliacao = (Avaliacao) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO AVALIACAO"
                    + " (CONTEUDO, ID_USUARIO, NOTA, ID_LOCAL, DATA_CRIACAO, DATA_ALTERACAO)"
                    + "VALUES (?,?,?,?,?,?);";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, avaliacao.getConteudo());
            stmt.setInt(2, avaliacao.getIdUsuario());
            stmt.setFloat(3, avaliacao.getNota());
            stmt.setInt(4, avaliacao.getIdLocal());
            stmt.setTimestamp(5, avaliacao.getDataCriacao());
            stmt.setTimestamp(6, avaliacao.getDataAlteracao());

            int numero = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                avaliacao.setId(rs.getInt(1));
            }

            stmt.close();
            return avaliacao;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade alterar(IEntidade objeto) {
        try {
            Avaliacao avaliacao = (Avaliacao) objeto;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE AVALIACAO SET CONTEUDO = ?, ID_USUARIO = ?, NOTA = ?, ID_LOCAL = ?,"
                    + "DATA_CRIACAO = ?, DATA_ALTERACAO = ? WHERE ID = ?;";

            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, avaliacao.getConteudo());
            stmt.setInt(2, avaliacao.getIdUsuario());
            stmt.setFloat(3, avaliacao.getNota());
            stmt.setInt(4, avaliacao.getIdLocal());
            stmt.setTimestamp(5, avaliacao.getDataCriacao());
            stmt.setTimestamp(6, avaliacao.getDataAlteracao());
            stmt.setInt(7, avaliacao.getId());

            stmt.execute();

            stmt.close();
            return avaliacao;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public IEntidade obterUm(Integer id) {
        try {
            Avaliacao avaliacao = null;
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM AVALIACAO WHERE ID=" + Integer.toString(id);

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("ID"));
                avaliacao.setConteudo(rs.getString("CONTEUDO"));
                avaliacao.setIdUsuario(rs.getInt("ID_USUARIO"));
                avaliacao.setIdLocal(rs.getInt("ID_LOCAL"));
                avaliacao.setNota(rs.getFloat("NOTA"));
                avaliacao.setDataCriacao(rs.getTimestamp("DATA_CRIACAO"));
                avaliacao.setDataAlteracao(rs.getTimestamp("DATA_ALTERACAO"));
            }
            return avaliacao;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<IEntidade> obterTodos() {

        try {
            Avaliacao avaliacao = null;
            List<IEntidade> avaliacoes = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM AVALIACAO";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("ID"));
                avaliacao.setConteudo(rs.getString("CONTEUDO"));
                avaliacao.setIdUsuario(rs.getInt("ID_USUARIO"));
                avaliacao.setIdLocal(rs.getInt("ID_LOCAL"));
                avaliacao.setNota(rs.getFloat("NOTA"));
                avaliacao.setDataCriacao(rs.getTimestamp("DATA_CRIACAO"));
                avaliacao.setDataAlteracao(rs.getTimestamp("DATA_ALTERACAO"));
                // Adicionar a lista
                avaliacoes.add(avaliacao);
            }

            return avaliacoes;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<IEntidade> obterTodos(int id) {

        try {
            Avaliacao avaliacao = null;
            List<IEntidade> avaliacoes = new ArrayList<>();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM AVALIACAO WHERE ID_LOCAL = '" + Integer.toString(id) + "' ";

            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("ID"));
                avaliacao.setConteudo(rs.getString("CONTEUDO"));
                avaliacao.setIdUsuario(rs.getInt("ID_USUARIO"));
                avaliacao.setIdLocal(rs.getInt("ID_LOCAL"));
                avaliacao.setNota(rs.getFloat("NOTA"));
                avaliacao.setDataCriacao(rs.getTimestamp("DATA_CRIACAO"));
                avaliacao.setDataAlteracao(rs.getTimestamp("DATA_ALTERACAO"));
                // Adicionar a lista
                avaliacoes.add(avaliacao);
            }

            return avaliacoes;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public IEntidade remover(Integer id) {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Avaliacao avaliacao = new Avaliacao();
            avaliacao = (Avaliacao) obterUm(id);

            String sql = "DELETE FROM AVALIACAO WHERE ID =" + Integer.toString(id);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return avaliacao;

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
