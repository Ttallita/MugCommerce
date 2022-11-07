package selenium.dataHelpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoDataHelper {

    /**
     * Essa caneca deve ser utilizada apenas para este teste!!!!
     * @return
     */
    private static Stream<Arguments> quantProdutosCarrinho() {
        return Stream.of(
                Arguments.of(1, "Caneca Urso Polar"),
                Arguments.of(9, "Caneca Urso Polar"),
                Arguments.of(-1, "Caneca Urso Polar"),
                Arguments.of(-9, "Caneca Urso Polar")
        );
    }

    public static List<String> nomesProdutos() {
        return List.of(
                "Caneca Urso Polar",
                "Caneca Urso Polar"
        );
    }

    /**
     * @return nomes de todos os produtos salvos no banco reservados para os teste de venda(script_implantacao.sql)
     */
    public static List<String> nomesTodosProdutos(){
        return List.of(
                "Caneca Polvinho",
                "Caneca Gato Preto",
                "Caneca Yn Yang",
                "Caneca Sapinho",
                "Caneca Snoppy",
                "Caneca Peixe Assombrado",
                "Caneca Arte Abstrata",
                "Caneca Passaros",
                "Caneca de Porcelana",
                "Caneca Raposinha",
                "Caneca Garota Anime",
                "Caneca Vaquinha",
                "Caneca Espaço Sideral",
                "Caneca Personalizada",
                "Caneca Beymax",
                "Caneca Gatinho",
                "Caneca do Jack",
                "Caneca Yoda",
                "Canecas Casal Estrela e Lua",
                "Caneca Cacto",
                "Caneca Múmia",
                "Caneca Abóbora Hallowen",
                "Canecas Diversas Gatinhos",
                "Caneca Sherlock Holmes"
        );
    }

    public static String getNomeProdutoAleatorio(){
        List<String> nomesProdutos = new ArrayList<>(ProdutoDataHelper.nomesTodosProdutos());
        Collections.shuffle(nomesProdutos);

        return nomesProdutos.get(0);
    }
}
