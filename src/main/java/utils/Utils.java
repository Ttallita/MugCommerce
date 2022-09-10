package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
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
}
