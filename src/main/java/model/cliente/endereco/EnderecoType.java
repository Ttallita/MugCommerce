package model.cliente.endereco;

public enum EnderecoType {
    COBRANCA("Cobrança"),
    ENTREGA("Entrega"),
    COBRANCA_ENTREGA("Cobrança e entrega"),
    ;

    public String nomeExibicao;
    EnderecoType(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

}
