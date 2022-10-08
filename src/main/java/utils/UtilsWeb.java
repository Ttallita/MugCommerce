package utils;

import com.google.gson.*;
import model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class UtilsWeb {

    public static void montaRespostaJson(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacao = request.getParameter("operacao");

        if(operacao.contains("listar") || operacao.equals("pesquisar")) {

            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

            Gson gson = gsonBuilder.setPrettyPrinting().create();

            PrintWriter writer = response.getWriter();
            writer.write(gson.toJson(result.getEntidades()));
            writer.flush();
        }
    }

    public static void adicionaParametrosRequestOrigemChamada(String origemChamada, HttpServletRequest request){

        if ("finalizarCompra".equals(origemChamada)){
            request.setAttribute("origemChamada", origemChamada);
            request.setAttribute("idEnderecoEscolhido", request.getParameter("idEnderecoEscolhido"));
            request.setAttribute("idCartaoSelecionado", request.getParameter("idCartaoSelecionado"));
        }
    }

    public static void redirecionarParaOrigemChamada(String origemChamada, HttpServletRequest request, HttpServletResponse response, Result result) throws IOException {

        if("finalizarCompra".equals(origemChamada)) {

            // TODO Mudar id de acordo se a operação for salvar, isso deve variar se for cartão ou endereço...

            String idEnderecoEscolhido = request.getParameter("idEnderecoEscolhido");
            String idCartaoSelecionado = request.getParameter("idCartaoSelecionado");
            idEnderecoEscolhido = idEnderecoEscolhido != null ? idEnderecoEscolhido : "";
            idCartaoSelecionado = idCartaoSelecionado != null ? idCartaoSelecionado : "";

            response.sendRedirect(String.format("/emug/clientes/carrinho/finalizarCompra?operacao=listar&origemChamada=finalizarCompra" +
                    "&idEnderecoEscolhido=%s&idCartaoSelecionado=%s", idEnderecoEscolhido, idCartaoSelecionado));
        }
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(Utils.formataLocalDateBR(localDate));
        }
    }

}
