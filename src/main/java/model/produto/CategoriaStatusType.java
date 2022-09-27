package model.produto;

public enum CategoriaStatusType {
    // Ativação
    NOVO_ITEM("Novo item", StatusType.ATIVO),
    QUEIMA_ESTOQUE("Queima de estoque", StatusType.ATIVO),

    // Inativação
    FORA_MERCADO("Fora de mercado", StatusType.INATIVO),
    PRODUTO_ANTIGO("Produto antigo", StatusType.INATIVO),
    PRODUTO_DEFEITUOSO("Produto defeituoso", StatusType.INATIVO),
    PRODUTO_SEM_DEMANDA("Produto sem demanda de compra", StatusType.INATIVO),

    // Aplicavel a ambos
    EPOCA_PRODUTO("Produto de epóca", StatusType.AMBOS),
    LIMITADO("Produto Limitado", StatusType.AMBOS),
    OUTRO("Outro...", StatusType.AMBOS);

    String nomeTela;
    StatusType type;

    CategoriaStatusType(String nomeTela, StatusType type) {
        this.nomeTela = nomeTela;
        this.type = type;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    public void setNomeTela(String nomeTela) {
        this.nomeTela = nomeTela;
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }
}
