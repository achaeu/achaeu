package DAO;

import java.util.List;
import model.IEntidade;

public interface IRepository {
    public IEntidade inserir(IEntidade objeto);
    public IEntidade alterar(IEntidade objeto);
    public IEntidade obterUm(Integer id);
    public List<IEntidade> obterTodos();
    public IEntidade remover(Integer id);    
}
