package selenium.dataHelpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ProdutoDataHelper {

    private static Stream<Arguments> quantProdutosCarrinho() {
        return Stream.of(
                Arguments.of(1, "Caneca ursinho"),
                Arguments.of(10, "Caneca ursinho"),
                Arguments.of(-1, "Caneca ursinho"),
                Arguments.of(-10, "Caneca ursinho")
        );
    }
}
