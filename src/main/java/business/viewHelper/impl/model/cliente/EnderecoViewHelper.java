package business.viewHelper.impl.model.cliente;

import business.viewHelper.IViewHelper;
import dao.AuditoriaDAO;
import model.AuditoriaType;
import model.EntidadeDominio;
import model.Result;
import model.Usuario;
import model.cliente.Cliente;
import model.cliente.endereco.Endereco;
import model.cliente.endereco.EnderecoType;
import utils.Utils;
import utils.UtilsWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnderecoViewHelper implements IViewHelper {

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        String operacao = request.getParameter("operacao");

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        switch (operacao) {
            case "salvar", "atualizar", "excluir" -> {
                Endereco endereco = criaEndereco(request);

                String id = request.getParameter("id");
                if(id != null && !id.isBlank())
                    endereco.setId(Long.parseLong(id));

                endereco.setCliente(new Cliente(usuarioLogado));

                return endereco;
            }
            case "listar", "listarJson" -> {
                Endereco endereco = new Endereco();
                endereco.setCliente(new Cliente(usuarioLogado));

                return endereco;
            }
            case "listarUnico" -> {
                Endereco endereco = new Endereco();
                endereco.setId(Long.parseLong(request.getParameter("id")));
                endereco.setCliente(new Cliente(usuarioLogado));

                return endereco;
            }
        }

        return null;
    }

    @Override
    public void setView(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String operacao = request.getParameter("operacao");

        String origemChamada = request.getParameter("origemChamada");
        if(origemChamada != null)
            UtilsWeb.adicionaParametrosRequestOrigemChamada(origemChamada, request);

        String msgTela = result.getMsg();

        switch (operacao) {
            case "salvar", "atualizar", "excluir" -> {
                Endereco endereco = (Endereco) result.getEntidades().get(0);

                if (msgTela == null) {
                    AuditoriaType tipo = operacao.equals("salvar") ? AuditoriaType.INSERCAO : AuditoriaType.ALTERACAO;

                    new AuditoriaDAO().salvar(Utils.criaAuditoria(endereco, tipo, endereco.getCliente().getUsuario()));

                    if (origemChamada != null){
                        Map<String, String> novosValoresParametros = new HashMap<>();
                        novosValoresParametros.put("idEnderecoEscolhido", result.getEntidades().get(0).getId().toString());

                        UtilsWeb.redirecionarParaOrigemChamada(origemChamada, request, response, novosValoresParametros);
                    }
                    else
                        response.sendRedirect("/emug/clientes/enderecos?operacao=listar");

                } else {
                    String[] mensagens = msgTela.split("\n");

                    request.setAttribute("endereco", endereco);
                    request.setAttribute("isEditar", operacao.equals("atualizar"));
                    request.setAttribute("mensagens", mensagens);
                    request.setAttribute("erro", true);
                    request.getRequestDispatcher("/cliente/formularios/formEndereco.jsp").forward(request, response);
                }

            }
            case "listar" -> {
                request.setAttribute("enderecos", result.getEntidades());
                request.getRequestDispatcher("/cliente/enderecos.jsp").forward(request, response);
            }
            case "listarJson" ->
                UtilsWeb.montaRespostaJson(result, request, response);
            case "listarUnico" -> {
                request.setAttribute("isEditar", true);
                request.setAttribute("endereco", result.getEntidades().get(0));
                request.getRequestDispatcher("/cliente/formularios/formEndereco.jsp").forward(request, response);
            }
            case "adicionar" ->
                request.getRequestDispatcher("/cliente/formularios/formEndereco.jsp").forward(request, response);

        }
    }

    public Integer getNumeroEndereco(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Endereco criaEndereco(HttpServletRequest request) {
        Endereco endereco = new Endereco();
        endereco.setTipoResidencia(request.getParameter("tpResidencia"));
        endereco.setTipoLogradouro(request.getParameter("tpLogradouro"));
        endereco.setLogradouro(request.getParameter("logradouro"));
        endereco.setBairro(request.getParameter("bairro"));
        endereco.setNumero(getNumeroEndereco(request.getParameter("numeroEndereco")));
        endereco.setCep(request.getParameter("cep"));
        endereco.setEstado(request.getParameter("estado"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setApelido(request.getParameter("apelidoEndereco"));
        endereco.setObservacoes(request.getParameter("observacaoEndereco"));

        String tpEndereco = request.getParameter("tpEndereco");

        if(tpEndereco == null)
            endereco.setTipoEndereco(EnderecoType.COBRANCA_ENTREGA);
        else if (tpEndereco.isBlank())
            endereco.setTipoEndereco(null);
        else
            endereco.setTipoEndereco(EnderecoType.valueOf(tpEndereco));

        return endereco;
    }
}
