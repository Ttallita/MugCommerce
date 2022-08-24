package dao;

import model.EntidadeDominio;
import model.IEntidade;

import java.util.List;

public interface IDAO {

    public EntidadeDominio salvar(EntidadeDominio entidade);
    public abstract EntidadeDominio atualizar(EntidadeDominio entidade);
    public abstract EntidadeDominio deletar(EntidadeDominio entidade);
    public abstract List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao);

}
