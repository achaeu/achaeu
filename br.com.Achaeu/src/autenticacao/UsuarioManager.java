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
        ResultSet rs = null;

        String sql = "SELECT ID, NOME, EMAIL, NIVEL FROM USUARIO "
                + "WHERE EMAIL = ? AND SENHA = ?";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);

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

    public static int getUsuarioLogadoId() throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(br.com.achaeu.BrComAchaeu.class);

        int usuarioLogado = prefs.getInt(LOGGED_USER, 0);
        if (usuarioLogado <= 0) {
            throw new Exception("Usuário não está logado");
        }
        return usuarioLogado;
    }

    private static Usuario obterUm(int id) throws SQLException, Exception {
        Connection conexao = ConnectionManager.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT ID, NOME, EMAIL, NIVEL FROM USUARIO WHERE id = " + Integer.toString(id);

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

    public static Usuario obterLogado() throws Exception {
        try {
            return obterUm(getUsuarioLogadoId());

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Usuario inserir(Usuario usuario) throws Exception {
        try {
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "INSERT INTO USUARIO"
                    + " (NOME, EMAIL, SENHA, NIVEL)"
                    + "VALUES (?,?,?, ?)";

            stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, UsuarioNivel.USUARIO_COMUM.ordinal());

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
    public static Usuario alterar(Usuario usuarioAlterado, String senhaAtual) throws Exception {
        try {
            if (!isUserLogged()) {
                throw new Exception("Usuário precisa estar logado para esta operação");
            }
            Usuario usuarioAtual = obterLogado();

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            String sql = "UPDATE USUARIO"
                    + " SET NOME = ?, SENHA = ? "
                    + "WHERE ID = ? AND SENHA = ?";

            stmt = conexao.prepareStatement(sql);

            stmt.setString(1, usuarioAlterado.getNome());
            stmt.setString(2, usuarioAlterado.getSenha().isEmpty() ? senhaAtual : usuarioAlterado.getSenha());
            stmt.setInt(3, usuarioAtual.getId());
            stmt.setString(4, senhaAtual);

            int rowCount = stmt.executeUpdate();
            if (rowCount != 1) {
                throw new Exception("Senha atual incorreta!");
            }

            stmt.close();
            return obterLogado();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Usuario tornarAdmin(int idUsuario) throws Exception {
        if (!isUserLogged()) {
            throw new Exception("Usuário precisa estar logado para esta operação");
        }
        Usuario usuarioAtual = obterLogado();
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
        return obterUm(idUsuario);
    }

    public Usuario remover(int usuarioId) throws Exception {
        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement stmt = null;
            Usuario usuario = this.obterUm(usuarioId);

            String sql = "DELETE FROM USUARIO WHERE ID = " + Integer.toString(usuarioId);
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
