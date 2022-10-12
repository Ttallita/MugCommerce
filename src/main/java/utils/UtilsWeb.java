package utils;

import com.google.gson.*;
import model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class UtilsWeb {

    public static final String URL_BASE = "/emug";

    public static Map<String, String> mapaRedirect = Map.of(
            "finalizarCompra", "/clientes/carrinho/finalizarCompra?operacao=listar"
    );

    public static void montaRespostaJson(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacao = request.getParameter("operacao");

        if(operacao.contains("listar")
                || operacao.equals("listarIndex")
                || operacao.equals("listarJson")) {

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

    public static void redirecionarParaOrigemChamada(String origemChamada, HttpServletRequest request, HttpServletResponse response, Map<String, String> novosValoresParametros) throws IOException {
        String urlRedirecionada = mapaRedirect.get(origemChamada);
        response.sendRedirect(URL_BASE + urlRedirecionada + getParametrosRequest(request, novosValoresParametros));
    }

    private static StringBuilder getParametrosRequest(HttpServletRequest request, Map<String, String> novosValoresParametros) {
        List<String> atributos = getNomesAtributosRequest(request);
        StringBuilder parametros = new StringBuilder();

        for (Map.Entry<String, String> entry : novosValoresParametros.entrySet()){
            atributos.remove(entry.getKey());
            parametros.append(String.format("&%s=%s", entry.getKey(), entry.getValue()));
        }

        atributos.forEach(atr -> parametros.append(String.format("&%s=%s", atr, request.getParameter(atr))));
        return parametros;
    }

    private static List<String> getNomesAtributosRequest(HttpServletRequest request) {
        List<String> atributos = new ArrayList<>();

        Iterator<String> iterator = request.getAttributeNames().asIterator();
        while (iterator.hasNext()) {
            String atributo = iterator.next();
            atributos.add(atributo);
        }

        return atributos;
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(Utils.formataLocalDateBR(localDate));
        }
    }

}
