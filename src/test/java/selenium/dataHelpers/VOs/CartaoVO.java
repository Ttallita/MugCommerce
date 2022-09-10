package selenium.dataHelpers.VOs;

public class CartaoVO {

    private String numCartao;
    private String nomeCartao;
    private String bandeira;
    private String codigoCartao;
    private String dtValidade;

    private String botaoCadastro;

    public static CartaoVO createCartaoPadrao(){
        CartaoVO cartaoVO = new CartaoVO();
        cartaoVO.setNumCartao("1234 4567 8910 1234");
        cartaoVO.setNomeCartao("João Inácio");
        cartaoVO.setBandeira("Elo");
        cartaoVO.setCodigoCartao("123");
        cartaoVO.setDtValidade("00/0000");

        return cartaoVO;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getCodigoCartao() {
        return codigoCartao;
    }

    public void setCodigoCartao(String codigoCartao) {
        this.codigoCartao = codigoCartao;
    }

    public String getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(String dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getBotaoCadastro() {
        return botaoCadastro;
    }

    public void setBotaoCadastro(String botaoCadastro) {
        this.botaoCadastro = botaoCadastro;
    }
}
