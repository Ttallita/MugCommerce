package business.viewHelper.impl.model.cliente;

import business.facade.Facade;
import business.viewHelper.IViewHelper;
import dao.AuditoriaDAO;
import model.*;
import model.cliente.CartaoDeCredito;
import model.cliente.Cliente;
import model.cliente.Telefone;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import model.cliente.endereco.TelefoneType;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author andre
 */
public class ClienteViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        switch (operacao) {
            case "salvar" -> {
                Usuario usuario = new Usuario();

                usuario.setEmail(request.getParameter("email"));
                usuario.setTipoUsuario(UsuarioType.CLIENTE);
                usuario.setSenha(request.getParameter("senha"));
                usuario.setConfirmarSenha(request.getParameter("senhaConfirmacao"));
                usuario.setAtivo(true);

                Cliente cliente = criaClienteBasico(request, usuario);

                Endereco endereco = (Endereco) new EnderecoViewHelper().getEntidade(request);

                cliente.setEnderecos(List.of(endereco));
                return cliente;
            }
            case "listar" -> {
                Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

                Cliente cliente = new Cliente();
                cliente.setUsuario(usuarioLogado);

                return cliente;
            }
            case "atualizar" -> {
                Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

                return criaClienteBasico(request, usuarioLogado);
            }
        }


        return null;
    }

    private Cliente criaClienteBasico(HttpServletRequest request, Usuario usuario) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setNome(request.getParameter("nome"));
        cliente.setSobrenome(request.getParameter("sobrenome"));
        cliente.setCpf(request.getParameter("cpf"));

        cliente.setGenero(request.getParameter("genero"));

        LocalDate dataNascimento = Utils.converteStringLocalDate(request.getParameter("dtNascimento"));
        cliente.setDataNascimento(dataNascimento);
        cliente.setTelefone(criaTelefone(request.getParameter("telefone")));

        return cliente;
    }

    public Telefone criaTelefone(String numero) {
        String[] telefone = numero.split(" ");

        if(telefone.length != 2 || (telefone[1].length() != 9 && telefone[1].length() != 10))
            return new Telefone();

        boolean ehCelular = telefone[1].length() == 10;

        TelefoneType telefoneType = ehCelular ? TelefoneType.CELULAR : TelefoneType.RESIDENCIAL;

        String dddSemParenteses = telefone[0].replace("(", "")
                .replace(")", "");

        return new Telefone(telefoneType, dddSemParenteses, telefone[1]);
    }


    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        String[] mensagens;
        String msgTela = result.getMsg();


        switch (operacao) {
            case "salvar" -> {
                Cliente cliente = (Cliente) result.getEntidades().get(0);

                if (msgTela != null) {
                    mensagens = msgTela.split("\n");


                    request.setAttribute("endereco", cliente.getEnderecos().get(0));
                    request.setAttribute("cliente", cliente);
                } else {
                    mensagens = new String[]{"Cadastrado com sucesso. <a href=\"login.jsp\">Clique aqui para logar</a>"};

                    new AuditoriaDAO().salvar(Utils.criaAuditoria(cliente, AuditoriaType.INSERCAO, cliente.getUsuario()));
                }
                request.setAttribute("mensagens", mensagens);
                request.setAttribute("erro", msgTela != null);
                request.getRequestDispatcher("cadastroCliente.jsp").forward(request, response);
            }
            case "listar" -> {
                Cliente cliente = (Cliente) result.getEntidades().get(0);
                request.setAttribute("cliente", cliente);

                request.getRequestDispatcher("/cliente/perfil.jsp").forward(request, response);
            }
            case "atualizar" -> {
                Cliente cliente = (Cliente) result.getEntidades().get(0);
                request.setAttribute("cliente", cliente);

                if (msgTela != null)
                    mensagens = msgTela.split("\n");
                else {
                    mensagens = new String[]{ "Atualizado com Sucesso" };
                    new AuditoriaDAO().salvar(Utils.criaAuditoria(cliente, AuditoriaType.ALTERACAO, cliente.getUsuario()));
                }

                request.setAttribute("mensagens", mensagens);
                request.setAttribute("erro", msgTela != null);
                request.getRequestDispatcher("/cliente/perfil.jsp").forward(request, response);
            }
        }
    }
}
