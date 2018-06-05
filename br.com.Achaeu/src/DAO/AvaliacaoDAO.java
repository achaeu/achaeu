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
        return null;
    }

    @Override
    public List<IEntidade> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEntidade remover(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
