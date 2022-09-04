package business.strategy.impl.cliente;

import business.strategy.IStrategy;
import dao.UsuarioDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.cliente.Cliente;
import utils.Utils;

public class VerificarSenhaStrategy implements IStrategy {

    @Override
    public String processa(EntidadeDominio entidade) {
        String nomeClasse = entidade.getClass().getSimpleName();

        String senha;
        String senhaConfirmacao;


        if(nomeClasse.equals("Cliente")) {
            Cliente cliente = (Cliente) entidade;
            senha = cliente.getUsuario().getSenha();
            senhaConfirmacao = cliente.getUsuario().getConfirmarSenha();
        } else  {
            Usuario usuario = (Usuario) entidade;
            senha = usuario.getSenha();
            senhaConfirmacao = usuario.getConfirmarSenha();


            if(!usuario.getSenhaAntiga().isEmpty()) {
                Usuario usuarioBanco = (Usuario) new UsuarioDAO().listar(entidade, "findById").get(0);

                String senhaSha512 = Utils.getSha512(usuario.getSenhaAntiga());

                if(!senhaSha512.equals(usuarioBanco.getSenha()))
                    return "Senha anterior incorreta";
            }
        }

        String caracteresEspeciais = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";

        boolean tamanho = false;
        boolean maiscula = false;
        boolean minuscula = false;
        boolean especial = false;

        if(!senha.equals(senhaConfirmacao)) {
            return "As senhas não coincidem";
        }

        if(senha.trim().length() > 7) {
            tamanho = true;
        }

        char[] letrasSenha = senha.toCharArray();


        for (char c : letrasSenha) {
            if (Character.isUpperCase(c))
                maiscula = true;

            if (Character.isLowerCase(c))
                minuscula = true;

            if (caracteresEspeciais.contains(Character.toString(c)))
                especial = true;
        }

        if(!maiscula || !minuscula || !especial || !tamanho) {
            return "Senha inválida";
        }

        return null;
    }
}
