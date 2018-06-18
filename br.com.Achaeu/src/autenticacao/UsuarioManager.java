/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autenticacao;

import DAO.CategoriaDAO;
import DAO.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import model.Usuario;
import model.enums.UsuarioNivel;

/**
 *
 * @author jonathan
 */
public class UsuarioManager {

    private static final String LOGGED_USER = "logged_user";

    public static boolean isUserLogged() {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);

        int usuarioLogado = prefs.getInt(LOGGED_USER, 0);
        return usuarioLogado > 0;
    }

    public static Usuario logOn(String email, String senha) throws SQLException, Exception {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);

        prefs.remove(LOGGED_USER);

        Connection conexao = ConnectionManager.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT ID, NOME, EMAIL, NIVEL FROM usuario "
                + "WHERE EMAIL = ? AND SENHA = ?";

        stmt.setString(1, email);
        stmt.setString(2, senha);

        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        Usuario usuario = null;

        while (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("ID"));
            usuario.setNome(rs.getString("NOME"));
            usuario.setEmail(rs.getString("EMAIL"));
            usuario.setNivel(rs.getInt("NIVEL"));
        }
        if (usuario == null) {
            throw new Exception("E-mail ou senha incorretos!");
        }
        prefs.putInt(LOGGED_USER, usuario.getId());
        return usuario;
    }

    public static void logOff() {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);

        prefs.remove(LOGGED_USER);
    }

    private int getUsuarioLogadoId() throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);

        int usuarioLogado = prefs.getInt(LOGGED_USER, 0);
        if (usuarioLogado <= 0) {
            throw new Exception("Usuário não está logado");
        }
        return usuarioLogado;
    }

    private Usuario obterUm(int id) throws SQLException, Exception {
        Connection conexao = ConnectionManager.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT ID, NOME, EMAIL, NIVEL FROM usuario WHERE id = " + Integer.toString(id);

        stmt = conexao.prepareStatement(sql);
        rs = stmt.executeQuery();
        Usuario usuario = null;

        while (rs.next()) {
            usuario = new Usuario();
            usuario.setId(rs.getInt("ID"));
            usuario.setNome(rs.getString("NOME"));
            usuario.setEmail(rs.getString("EMAIL"));
            usuario.setNivel(rs.getInt("NIVEL"));
        }
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    public Usuario obterLogado() throws Exception {
        try {
            return this.obterUm(this.getUsuarioLogadoId());

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Usuario inserir(Usuario usuario) throws Exception {
        try {
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO usuario"
                    + " (nome, email, senha, nivel)"
                    + "VALUES (?,?,?, ?)";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getNivel().ordinal());

            int numero = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

            stmt.close();
            return usuario;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    // Senha deve ser passada como parâmetro, pois o ObterUm não busca a senha por motivo de segurança
    public Usuario alterar(Usuario usuarioAlterado, String senhaAtual) throws Exception {
        try {
            if (!isUserLogged()) {
                throw new Exception("Usuário precisa estar logado para esta operação");
            }
            Usuario usuarioAtual = this.obterLogado();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE usuario"
                    + " SET NOME = ?, SENHA = ? "
                    + "WHERE ID = ? AND SENHA = ?";

            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuarioAlterado.getNome());
            stmt.setString(2, usuarioAlterado.getEmail());
            stmt.setString(3, usuarioAlterado.getSenha() != null ? usuarioAlterado.getSenha() : senhaAtual);
            stmt.setInt(4, usuarioAtual.getId());
            stmt.setString(5, senhaAtual);

            int rowCount = stmt.executeUpdate();
            if (rowCount != 1) {
                throw new Exception("Senha atual incorreta!");
            }

            stmt.close();
            return this.obterLogado();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Usuario tornarAdmin(int idUsuario) throws Exception {
        if (!isUserLogged()) {
            throw new Exception("Usuário precisa estar logado para esta operação");
        }
        Usuario usuarioAtual = this.obterLogado();
        if (usuarioAtual.getNivel() != UsuarioNivel.USUARIO_ADMIN) {
            throw new Exception("Usuário precisa ser admin.");
        }
        Connection conexao = ConnectionManager.getConexao();
        PreparedStatement stmt = null;
        String sql = "UPDATE usuario"
                + " SET nivel = 1"
                + "WHERE ID = ?";

        stmt = conexao.prepareStatement(sql);

        stmt.setInt(1, idUsuario);

        int rowCount = stmt.executeUpdate();
        if (rowCount != 1) {
            throw new Exception("Usuário não encontrado!");
        }
        stmt.close();
        return this.obterUm(idUsuario);
    }

    public Usuario remover(int usuarioId) throws Exception {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Usuario usuario = this.obterUm(usuarioId);

            String sql = "DELETE FROM usuario WHERE ID = " + Integer.toString(usuarioId);
            stmt = conexao.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            return usuario;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
