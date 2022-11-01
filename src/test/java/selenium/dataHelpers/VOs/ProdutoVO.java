package selenium.dataHelpers.VOs;

import model.produto.Categoria;
import model.produto.Produto;

import java.util.List;
import java.util.Random;

public class ProdutoVO {
    private String nome;
    private Double valorCompra;
    private String codBarras;
    private String material;
    private String fabricante;
    private String grupoPrecificao;
    private String categorias;
    private String descricao;
    private String imagem;
    private String ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getGrupoPrecificao() {
        return grupoPrecificao;
    }

    public void setGrupoPrecificao(String grupoPrecificao) {
        this.grupoPrecificao = grupoPrecificao;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public static ProdutoVO criaProdutoTeste() {
        ProdutoVO vo = new ProdutoVO();

        Random random = new Random();

        vo.setNome("Teste");
        vo.setValorCompra(new Random().nextDouble(1000));
        vo.setCodBarras(new Random().nextInt(100000) + "");
        vo.setMaterial("Porcelana");
        vo.setFabricante("Amazon");
        vo.setGrupoPrecificao("Margem %4");
        vo.setCategorias(ProdutoVO.getListCategorias()[random.nextInt(ProdutoVO.getListCategorias().length)]);
        vo.setDescricao("Teste Descrição");

        return vo;
    }

    public static String[] getListCategorias (){
        return new String[] {
                "Anime",
                "Geek",
                "Filmes",
                "Series",
                "Esportes",
                "Celebridades",
                "Games",
                "Literatura",
                "Lugares",
                "Pet"
        };
    }
}
