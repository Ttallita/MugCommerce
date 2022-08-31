package controller.viewHelper.impl;

import controller.viewHelper.IViewHelper;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.UsuarioType;
import model.cliente.Cliente;
import model.cliente.Telefone;
import model.cliente.endereco.TelefoneType;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class ClienteViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Cliente cliente = new Cliente();
        cliente.setEmail(request.getParameter("email"));
        cliente.setTipoUsuario(UsuarioType.CLIENTE);
        cliente.setSenha(request.getParameter("senha"));
        cliente.setConfirmarSenha(request.getParameter("confirmarSenha"));
        cliente.setAtivo(true);
        cliente.setNome(request.getParameter("nome"));
        cliente.setSobrenome(request.getParameter("sobrenome"));
        cliente.setCpf(request.getParameter("cpf"));

        cliente.setGenero(request.getParameter("genero"));

        LocalDate dataNascimento = Utils.converteStringLocalDate(request.getParameter("dataNascimento"));
        cliente.setDataNascimento(dataNascimento);

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    public Telefone criaTelefone(String numero, String ddd) {
        String[] s = numero.split(" ");

        boolean ehCelular = s[1].length() == 10;

        return new Telefone(ddd, telefoneType, numero);
    }
}
