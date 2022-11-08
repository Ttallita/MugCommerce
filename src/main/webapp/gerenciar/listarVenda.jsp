<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>

<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Venda</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />

    <div class="container card w-50 mt-5 mb-5">

        <jsp:include page="../include/alert.jsp" />

        <h3 class="text-center mt-5">Detalhes venda</h3>

        <div class="container overflow-hidden">
            <div class="row">

                <div class="col-12">
                    <div class="p-3">
                        <h5>Dados cliente</h5>
                        <ul class="list-group">
                            <li class="list-group-item">Nome: ${venda.cliente.nome} ${venda.cliente.sobrenome}</li>
                            <li class="list-group-item">CPF: ${venda.cliente.cpf}</li>
                            <li class="list-group-item">Telefone: (${venda.cliente.telefone.ddd}) ${venda.cliente.telefone.numero}</li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3">
                        <h5>Dados gerais</h5>
                        <ul class="list-group">
                            <fmt:parseDate  value="${venda.dataCompra}"  type="date" pattern="yyyy-MM-dd" var="dataCompraParseada" />
                            <fmt:formatDate value="${dataCompraParseada}" type="date" pattern="dd/MM/yyyy" var="dataCompraFormatada" />
                            <li class="list-group-item">Data compra: ${dataCompraFormatada} </li>

                            <fmt:parseDate  value="${venda.dataEntrega}"  type="date" pattern="yyyy-MM-dd" var="dataEntregaParseada" />
                            <fmt:formatDate value="${dataEntregaParseada}" type="date" pattern="dd/MM/yyyy" var="dataEntregaFormatada" />
                            <li class="list-group-item">Data entrega: ${dataEntregaFormatada} </li>

                            <li class="list-group-item">Valor total: ${venda.precoTotal} </li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3">
                        <h5>Endereços</h5>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <h6>Endereço entrega:</h6>
                                <ul class="list-unstyled">
                                    <li>${venda.enderecoEntrega.apelido} (${venda.enderecoEntrega.tipoResidencia}), ${venda.enderecoEntrega.tipoLogradouro} ${venda.enderecoEntrega.logradouro}, ${venda.enderecoEntrega.numero}</li>
                                    <li>${venda.enderecoEntrega.cidade}, ${venda.enderecoEntrega.estado} - Brasil</li>
                                    <li>CEP: ${venda.enderecoEntrega.cep}</li>
                                    <li>${venda.enderecoEntrega.observacoes}</li>
                                </ul>
                            </li>
                            <li class="list-group-item">
                                Endereço cobrança:
                                <ul class="list-unstyled">
                                    <li>${venda.enderecoCobranca.apelido} (${venda.enderecoCobranca.tipoResidencia})</li>
                                    <li>${venda.enderecoCobranca.tipoLogradouro} ${venda.enderecoCobranca.logradouro}, ${venda.enderecoCobranca.numero}</li>
                                    <li>${venda.enderecoCobranca.cidade}, ${venda.enderecoCobranca.estado} - Brasil</li>
                                    <li>CEP: ${venda.enderecoCobranca.cep}</li>
                                    <li>${venda.enderecoCobranca.observacoes}</li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3">
                        <h5>Produtos</h5>

                        <ul class="list-group">
                            <!-- Produtos -->
                            <c:forEach var="item" items="${venda.carrinho.itensCarrinho}">
                                <li class="list-group-item">
                                    <div class="row p-3">
                                        <div class="col-2">
                                            <div class="card produto mb-3">
                                                <img alt="produto" src="${item.produto.imagem}" class="p-2">
                                            </div>
                                        </div>
                                        
                                        <div class="col">
                                            <h5>${item.produto.nome}</h5>
                                            <h6>Quantidade: ${item.quant}</h6>
                                            <h6>Valor unitário: R$ ${item.produto.valorVenda}</h6>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3">
                        <h5>Status</h5>
                       
                        <ul class="list-group">
                            <li class="list-group-item">
                                Status atual: ${venda.vendaStatus.nomeExibicao}
                            </li>

                            <c:if test="${venda.vendaStatus != 'FINALIZADO' && venda.vendaStatus != 'CANCELADA'}">
                                <li class="list-group-item">
                                    Novo status:
                                    <form action="/emug/adm/vendas" method="POST">
                                        <select class="form-select" id="status" name="status">
                                            <option value="">Selecione</option>
                                            <c:forEach var="status" items="${listaStatus}">
                                                <option ${fn:contains(proximoStatus, status) ? '' : 'disabled' } value="${status}">${status.nomeExibicao}</option>
                                            </c:forEach>
                                        </select>
    
                                        <input type="hidden" name="id" value="${venda.id}">
                                        <input type="hidden" name="operacao" value="atualizar">
                                        <button type="submit" id="botaoAtualizarStatus" class="w-100 btn btn-primary btn-sm">
                                            Atualizar status
                                        </button>
                                    </form>
                                </li>
                            </c:if>
                        </ul>
                        
                    </div>
                </div>
                
            </div>
        </div>

    </div>

    <jsp:include page="../include/footer.jsp" />

</body>

<script src="<c:url value='webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='webjars/jquery/3.6.1/jquery.min.js'/>"></script>
<script src="<c:url value='webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js'/>"></script>
<script src="assets/js/geral.js"></script>

</html>