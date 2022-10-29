package selenium.dataHelpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoDataHelper {

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
}
