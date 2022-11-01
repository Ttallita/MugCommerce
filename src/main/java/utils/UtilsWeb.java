package utils;

import com.google.gson.*;
import model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UtilsWeb {

    public static final String URL_BASE = "/emug";

    public static Map<String, String> mapaRedirect = Map.of(
            "finalizarCompra", "/clientes/carrinho/finalizarCompra?operacao=listarUnico"
    );

    public static void montaRespostaJson(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String operacao = request.getParameter("operacao");

        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

        Gson gson = gsonBuilder.setPrettyPrinting().create();

        PrintWriter writer = response.getWriter();

        String msg = result.getMsg();
        if(msg != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writer.write(gson.toJson(msg));
        } else {
            writer.write(gson.toJson(result.getEntidades()));
        }

        writer.flush();
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

        // TODO verificar casos onde tem endereço de cobrança
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

    public static List<String> converteParametrosParaLista(String parametros) {
        return Arrays.stream(parametros.replace("[","").replace("]","").split(",")).collect(Collectors.toList());
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(Utils.formataLocalDateBR(localDate));
        }
    }

    private static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(Utils.formataLocalDateBR(localDate));
        }
    }

}
