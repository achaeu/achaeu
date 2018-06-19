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
import java.util.ArrayList;
import java.util.List;
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
            return local;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<IEntidade> obterTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Salva um local
     * @param obj
     * @return
     */
    @Override
    public IEntidade salvar(IEntidade obj) {
        Local local = (Local) obj;
        if(!this.validarModel(local)){
            return null;
        }
        Localizacao localizacao = local.getEndereco().getLocalizacao();
        Endereco endereco = local.getEndereco();
        IEntidade retorno = null;

        if (local.getId() == 0) {
            if (!(localizacao.getLatitude() == null) || !(localizacao.getLongitude() == null)) {
                if (localizacao.getId() == 0) {
                    localizacao = (Localizacao) new LocalizacaoDAO().inserir(localizacao);
                } else {
                    localizacao = (Localizacao) new LocalizacaoDAO().alterar(localizacao);
                }
                endereco.setIdLocalizacao(localizacao.getId());
                local.setEndereco(endereco);
            }
            endereco = (Endereco) new EnderecoDAO().inserir(local.getEndereco());
            //Preenchendo os ID's
            local.setIdEndereco(endereco.getId());
            local.setIdUsuario(1);
            local.setIdCategoria(1);
            retorno = new LocalDAO().inserir(local);
        } else {
            if (!(localizacao.getLatitude() == null) && !(localizacao.getLongitude() == null)) {
                if (localizacao.getId() == 0) {
                    localizacao = (Localizacao) new LocalizacaoDAO().inserir(localizacao);
                } else {
                    localizacao = (Localizacao) new LocalizacaoDAO().alterar(localizacao);
                }
                endereco.setIdLocalizacao(localizacao.getId());
                local.setEndereco(endereco);
            }
            endereco = (Endereco) new EnderecoDAO().alterar(local.getEndereco());
            //Preenchendo os ID's
            local.setIdEndereco(endereco.getId());
            local.setIdUsuario(1);
            local.setIdCategoria(1);
            retorno = new LocalDAO().alterar(local);
        }

        return retorno;
    }

    @Override
    public IEntidade remover() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Boolean validarModel(IEntidade obj) {
        Local local = (Local) obj;
        List<String> erros = new ArrayList<>();
        
        if(local.getNome().isEmpty() || local.getNome() == null){
            erros.add("Preencha o nome do local!");
        }
        
        if(local.getDescricao().isEmpty() || local.getDescricao() == null){
            erros.add("Preencha a descrição do local!");
        }
        
        if(local.getTelefone1().isEmpty() || local.getTelefone1()== null){
            erros.add("Preencha o telefone do local!");
        }
        //Endereço        
        if(local.getEndereco().getLogradouro().isEmpty() || local.getEndereco().getLogradouro() == null){
            erros.add("Preencha o logradouro do local!");
        }
                
        if(local.getEndereco().getNumero() == null){
            erros.add("Preencha o número do local!");
        }
                        
        if(local.getEndereco().getBairro().isEmpty() || local.getEndereco().getBairro() == null){
            erros.add("Preencha o bairro do local!");
        }
                        
        if(local.getEndereco().getCep().isEmpty() || local.getEndereco().getCep() == null){
            erros.add("Preencha o CEP do local!");
        }
                
        if(local.getEndereco().getLogradouro().isEmpty() || local.getEndereco().getLogradouro() == null){
            erros.add("Preencha o logradouro do local!");
        }
                
        if(local.getEndereco().getCidade().isEmpty() || local.getEndereco().getCidade() == null){
            erros.add("Preencha o a cidade do local!");
        }
                
        if(local.getEndereco().getUf().isEmpty() || local.getEndereco().getUf() == null){
            erros.add("Preencha o UF do local!");
        }
        
        if(erros.size() > 0){
            String mensagem = "Foram encontrados os seguintes erros:"+System.lineSeparator();
            for (String erro : erros) {
                mensagem += erro + System.lineSeparator();
            }
            JOptionPane.showMessageDialog(null, mensagem);
            return false;
        }
        
        return true;
    }

}
