/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CategoriaDAO;
import DAO.ConnectionManager;
import DAO.EnderecoDAO;
import DAO.LocalDAO;
import DAO.LocalizacaoDAO;
import autenticacao.UsuarioManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Endereco;
import model.IEntidade;
import model.Local;
import model.Localizacao;

/**
 *
 * @author igor-bueno
 */
public class LocalController implements IEntidadeController {

    @Override
    public IEntidade obterUm(int id) {
        try {
            LocalDAO dao = new LocalDAO();
            Local local = (Local) dao.obterUm(id);
            local.setCategoria((Categoria) new CategoriaDAO().obterUm(local.getIdCategoria()));
            local.setEndereco((Endereco) new EnderecoDAO().obterUm(local.getIdEndereco()));
            local.setIdEndereco(local.getEndereco().getId());
            local.setIdCategoria(local.getCategoria().getId());
            return local;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<IEntidade> obterTodos() {
        return new LocalDAO().obterTodos();
    }

    /**
     * Salva um local
     *
     * @param obj
     * @return
     */
    @Override
    public IEntidade salvar(IEntidade obj) {
        IEntidade retorno = null;
        try {
            Local local = (Local) obj;
            if (!this.validarModel(local)) {
                return null;
            }
            Localizacao localizacao = local.getEndereco().getLocalizacao();
            Endereco endereco = local.getEndereco();

            if (local.getId() == 0 || local.getId() == null) {
                endereco = (Endereco) new EnderecoDAO().inserir(local.getEndereco());
                //Preenchendo os ID's
                local.setIdEndereco(endereco.getId());
                local.setIdUsuario(UsuarioManager.getUsuarioLogadoId());
                local.setIdCategoria(1);
                retorno = new LocalDAO().inserir(local);
            } else {
                endereco = (Endereco) new EnderecoDAO().alterar(local.getEndereco());
                //Preenchendo os ID's
                local.setIdEndereco(endereco.getId());
                local.setIdUsuario(UsuarioManager.getUsuarioLogadoId());
                local.setIdCategoria(1);
                retorno = new LocalDAO().alterar(local);
            }
        } catch (Exception e) {
            Logger.getLogger(LocalController.class.getName()).log(Level.SEVERE, null, e);
        }

        return retorno;
    }

    public Boolean validarModel(IEntidade obj) {
        Local local = (Local) obj;
        List<String> erros = new ArrayList<>();

        if (local.getNome().isEmpty() || local.getNome() == null) {
            erros.add("Preencha o nome do local!");
        }

        if (local.getDescricao().isEmpty() || local.getDescricao() == null) {
            erros.add("Preencha a descrição do local!");
        }

        if (local.getTelefone1().isEmpty() || local.getTelefone1() == null) {
            erros.add("Preencha o telefone do local!");
        }
        //Endereço        
        if (local.getEndereco().getLogradouro().isEmpty() || local.getEndereco().getLogradouro() == null) {
            erros.add("Preencha o logradouro do local!");
        }

        if (local.getEndereco().getNumero() == null) {
            erros.add("Preencha o número do local!");
        }

        if (local.getEndereco().getBairro().isEmpty() || local.getEndereco().getBairro() == null) {
            erros.add("Preencha o bairro do local!");
        }

        if (local.getEndereco().getCep().isEmpty() || local.getEndereco().getCep() == null) {
            erros.add("Preencha o CEP do local!");
        }

        if (local.getEndereco().getLogradouro().isEmpty() || local.getEndereco().getLogradouro() == null) {
            erros.add("Preencha o logradouro do local!");
        }

        if (local.getEndereco().getCidade().isEmpty() || local.getEndereco().getCidade() == null) {
            erros.add("Preencha o a cidade do local!");
        }

        if (local.getEndereco().getUf().isEmpty() || local.getEndereco().getUf() == null) {
            erros.add("Preencha o UF do local!");
        }

        if (erros.size() > 0) {
            String mensagem = "Foram encontrados os seguintes erros:" + System.lineSeparator();
            for (String erro : erros) {
                mensagem += erro + System.lineSeparator();
            }
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        }

        return true;
    }

    public List<IEntidade> consultarLocal(String textoConsulta) {
        return new LocalDAO().consultarLocal(textoConsulta);
    }

    @Override
    public IEntidade remover(int id) {
        return new LocalDAO().remover(id);
    }

}
