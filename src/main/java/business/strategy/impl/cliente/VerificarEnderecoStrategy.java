package business.strategy.impl.cliente;

import business.strategy.IStrategy;
import model.EntidadeDominio;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;

public class VerificarEnderecoStrategy implements IStrategy{

    @Override
    public String processa(EntidadeDominio entidade) {
        Endereco endereco;

        if(entidade instanceof Cliente) // Salvando endereço pela primeira vez...
            endereco = ((Cliente) entidade).getEnderecos().get(0);
        else {
            endereco = (Endereco) entidade;
            if(endereco.getTipoEndereco() == null)
                return "Selecione um tipo de endereço valido";
        }

        try {
            if(endereco.getTipoResidencia().trim().isEmpty())
                return "O campo tipo de residência é obrigatório";

            if(endereco.getTipoLogradouro().trim().isEmpty())
                return "O campo tipo de logradouro é obrigatório";

            if(endereco.getLogradouro().trim().isEmpty())
                return "O campo logradouro é obrigatório";

            if(endereco.getNumero() == null)
                return "O campo número é obrigatório";

            if(endereco.getBairro().trim().isEmpty())
                return "O campo bairro é obrigatório";

            if(endereco.getCep().trim().isEmpty())
                return "O campo CEP é obrigatório";

            if(endereco.getCidade().trim().isEmpty())
                return "O campo cidade é obrigatório";

            if(endereco.getEstado().trim().isEmpty())
                return "O campo estado é obrigatório";

            if(endereco.getApelido().trim().isEmpty())
                return "O campo apelido é obrigatório";

            return null;

        } catch (Exception e){
            return e.getMessage();
        }
    }

}
