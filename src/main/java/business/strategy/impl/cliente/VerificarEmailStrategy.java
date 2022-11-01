package business.strategy.impl.cliente;

import business.strategy.IStrategy;
import dao.UsuarioDAO;
import model.EntidadeDominio;
import model.Usuario;
import model.cliente.Cliente;

public class VerificarEmailStrategy implements IStrategy {

    @Override
    public String processa(EntidadeDominio entidade) {
        String name = entidade.getClass().getSimpleName();

        String email;
        if(name.equals("Cliente")) {
            Cliente cliente = (Cliente) entidade;
            email = cliente.getUsuario().getEmail();
        } else {
            Usuario usuario = (Usuario) entidade;
            email = usuario.getEmail();
        }

        if(email.trim().isBlank())
            return "Insira um email";

        if(!email.contains("@") || !email.endsWith(".com"))
            return "Insira um email valido";

        return null;
    }
}
