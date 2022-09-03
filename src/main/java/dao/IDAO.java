package dao;

import model.EntidadeDominio;

import java.util.List;

public interface IDAO {

    EntidadeDominio salvar(EntidadeDominio entidade);
    EntidadeDominio atualizar(EntidadeDominio entidade);
    EntidadeDominio deletar(EntidadeDominio entidade);
    List<EntidadeDominio> listar(EntidadeDominio entidade, String operacao);

}
