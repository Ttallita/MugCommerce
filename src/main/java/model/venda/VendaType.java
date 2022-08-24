package model.venda;

public enum VendaType {
    EM_PROCESSAMENTO,
    PAGAMENTO_REALIZADO,
    ENVIADO_PARA_TRANSPORTADORA,
    EM_TRANSPORTE,
    ENTREGUE,

    TROCA_SOLICITADA,
    CANCELAMENTO_SOLICITADO,

    TROCA_RECUSADA,
    CANCELAMENTO_RECUSADO,

    TROCA_ACEITA,
    CANCELAMENTO_ACEITO,

    PRODUTO_ENVIADO_PARA_TROCA,
    PRODUTO_ENVIADO_PARA_CANCELAMENTO,

    PRODUTO_RECEBIDO_PARA_TROCA,
    PRODUTO_RECEBIDO_PARA_CANCELAMENTO,

    TROCA_REALIZADA,
    CANCELAMENTO_REALIZADO,
}