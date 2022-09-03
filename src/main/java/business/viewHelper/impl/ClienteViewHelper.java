package business.viewHelper.impl;

import business.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.UsuarioType;
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

        Usuario usuario = new Usuario();

        usuario.setEmail(request.getParameter("email"));
        usuario.setTipoUsuario(UsuarioType.CLIENTE);
        usuario.setSenha(request.getParameter("senha"));
        usuario.setConfirmarSenha(request.getParameter("senhaConfirmacao"));
        usuario.setAtivo(true);

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setNome(request.getParameter("nome"));
        cliente.setSobrenome(request.getParameter("sobrenome"));
        cliente.setCpf(request.getParameter("cpf"));

        cliente.setGenero(request.getParameter("genero"));

        LocalDate dataNascimento = Utils.converteStringLocalDate(request.getParameter("dtNascimento"));
        cliente.setDataNascimento(dataNascimento);
        cliente.setTelefone(criaTelefone(request.getParameter("telefone")));

        Endereco endereco = new Endereco();
        endereco.setTipoResidencia(request.getParameter("tpResidencia"));
        endereco.setTipoLogradouro(request.getParameter("tpLogradouro"));
        endereco.setLogradouro(request.getParameter("logradouro"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setNumero(getNumeroEndereco(request.getParameter("numeroEndereco")));
        endereco.setCep(request.getParameter("cep"));
        endereco.setPais(request.getParameter("pais"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setApelido(request.getParameter("apelidoEndereco"));
        endereco.setObservacoes(request.getParameter("observacaoEndereco"));
        endereco.setTipoEndereco(EnderecoType.COBRANCA_ENTREGA);

        cliente.setEnderecos(List.of(endereco));

        return cliente;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        String[] mensagens = null;
        String msgTela = result.getMsg();
        if(operacao.equals("salvar")) {
            if(msgTela != null) {
                mensagens = msgTela.split("\n");

                Cliente cliente = (Cliente) result.getEntidades().get(0);
                request.setAttribute("cliente", cliente);
            }
            else
                mensagens = new String[] { "Cadastrado com sucesso" };

        }
;
        request.setAttribute("mensagens", mensagens);
        request.setAttribute("erro", msgTela != null);
        request.getRequestDispatcher("cadastroCliente.jsp").forward(request, response);
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

    public Integer getNumeroEndereco(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
