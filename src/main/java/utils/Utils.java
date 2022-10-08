package utils;

import com.google.gson.*;
import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final DateTimeFormatter formatterBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formataLocalDateBR(LocalDate data){
        return data != null ? formatterBR.format(data) : null;
    }

    public static LocalDate converteStringLocalDate(String data) {
       if(data == null || data.isBlank())
           return null;

        return LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String getSha512(String input) {
        String criptografada = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes(StandardCharsets.UTF_8));
            criptografada = String.format("%064x", new BigInteger(1, digest.digest()));

        }catch (Exception e) {
            System.err.println("Erro ao criptografar" + e.getMessage());
            e.printStackTrace();
        }

        return criptografada;
    }

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

    public static Auditoria criaAuditoria(EntidadeDominio entidade, AuditoriaType tipo, Usuario usuario) {
        Auditoria auditoria = new Auditoria();
        auditoria.setData(LocalDateTime.now());
        auditoria.setTipo(tipo);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

        auditoria.setJson(gsonBuilder.create().toJson(entidade));
        auditoria.setUsuario(usuario);

        return auditoria;
    }

    public static class LocalDateSerializer implements JsonSerializer <LocalDate> {
        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatterBR.format(localDate));
        }
    }



}
