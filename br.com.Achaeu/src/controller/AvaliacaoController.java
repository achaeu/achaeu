/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AvaliacaoDAO;
import autenticacao.UsuarioManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Avaliacao;
import model.IEntidade;

/**
 *
 * @author igor-bueno
 */
public class AvaliacaoController implements IEntidadeController {

    @Override
    public IEntidade obterUm(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IEntidade> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<IEntidade> obterTodos(int idLocal) {
        return new AvaliacaoDAO().obterTodos(idLocal);
    }

    @Override
    public IEntidade salvar(IEntidade obj) {
        Avaliacao avaliacao = (Avaliacao) obj;
        AvaliacaoDAO dao = new AvaliacaoDAO();
        
        if (!this.validarModel(obj)) {
            return null;
        }
        
        try {
            avaliacao.setIdUsuario(UsuarioManager.getUsuarioLogadoId());
            if (avaliacao.getId() == 0) {
                avaliacao = (Avaliacao) dao.inserir(avaliacao);
            } else {
                avaliacao = (Avaliacao) dao.alterar(avaliacao);
            }
        } catch (Exception ex) {
            Logger.getLogger(AvaliacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return avaliacao;
    }

    @Override
    public Boolean validarModel(IEntidade obj) {
        String mensagem = null;
        Avaliacao avalicao = (Avaliacao) obj;
        if (avalicao.getConteudo().isEmpty() || avalicao.getConteudo() == null) {
            mensagem += System.lineSeparator() + "Avaliação vazia!";
        }

        if (mensagem != null) {
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        }
        return true;
    }

    @Override
    public IEntidade remover(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
