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
import java.util.List;
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
            return local;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<IEntidade> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEntidade salvar(IEntidade obj) {
        Local local = (Local) obj;
        Localizacao localizacao = local.getEndereco().getLocalizacao();
        Endereco endereco = local.getEndereco();
        IEntidade retorno = null;
        
        if (local.getId() == 0) {
            if(!(localizacao.getLatitude() == null) || !(localizacao.getLongitude() == null)){
                localizacao = (Localizacao) new LocalizacaoDAO().inserir(localizacao);                
                endereco.setIdLocalizacao(localizacao.getId());
                local.setEndereco(endereco);
            }
            endereco = (Endereco) new EnderecoDAO().inserir(local.getEndereco());
            //Preenchendo os ID's
            local.setIdEndereco(endereco.getId());
            local.setIdUsuario(1);
            local.setIdCategoria(1);
            retorno = new LocalDAO().inserir(local);
        }
        
        return retorno;
    }

    @Override
    public IEntidade remover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean validarModel(IEntidade obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
