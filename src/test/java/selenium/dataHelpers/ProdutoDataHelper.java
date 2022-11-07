package selenium.dataHelpers;

import org.junit.jupiter.params.provider.Arguments;
import selenium.dataHelpers.VOs.ProdutoVO;

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

    private static Object[][] produtosInvalidos() {
        ProdutoVO produtoSemNome = ProdutoVO.criaProdutoTeste();
        produtoSemNome.setNome("");

        ProdutoVO produtoSemValor = ProdutoVO.criaProdutoTeste();
        produtoSemValor.setValorCompra(null);

        ProdutoVO produtoSemCodBarras = ProdutoVO.criaProdutoTeste();
        produtoSemCodBarras.setCodBarras("");

        ProdutoVO produtoSemMaterial = ProdutoVO.criaProdutoTeste();
        produtoSemMaterial.setMaterial("");

        ProdutoVO produtoSemFabricante = ProdutoVO.criaProdutoTeste();
        produtoSemFabricante.setFabricante("");

        ProdutoVO produtoSemGrupo = ProdutoVO.criaProdutoTeste();
        produtoSemGrupo.setGrupoPrecificao("");

        ProdutoVO produtoSemCategorias = ProdutoVO.criaProdutoTeste();
        produtoSemCategorias.setCategorias("");

        ProdutoVO produtoSemDescricao = ProdutoVO.criaProdutoTeste();
        produtoSemDescricao.setDescricao("");


        return new Object[][]{
                {produtoSemNome, "Insira um nome para o produto"},
                {produtoSemValor, "Insira o valor de compra do produto"},
                {produtoSemMaterial, "Insira o material do produto"},
                {produtoSemFabricante, "Escolha um fabricante"},
                {produtoSemGrupo, "Escolha um grupo de precificação"},
                {produtoSemCategorias, "Escolha uma ou mais categorias para o produto"},
                {produtoSemDescricao, "Insira uma descrição para o produto"}
        };
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
