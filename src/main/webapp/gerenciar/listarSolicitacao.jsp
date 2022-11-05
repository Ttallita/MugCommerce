<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang=pt-br>
<c:set var="paginaCorrente" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="isCancelamento" value="${ fn:contains(paginaCorrente, 'cancelamento') }"/>
<c:set var="nomePaginaCorrente" value="${ isCancelamento ? 'Cancelamento' : 'Troca' }"/>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${nomePaginaCorrente}</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="/include/header.jsp" />


    <div class="container card w-50 mt-5 mb-5">

        <jsp:include page="../include/alert.jsp" />

        <h3 class="text-center mt-5">${isCancelamento ? 'Detalhes do cancelamento' : 'Detalhes da troca'}</h3>

        <div class="container overflow-hidden">
            <div class="row">

                <div class="col-12">
                    <div class="p-3 border">
                        <h6>Dados cliente</h6>
                        <ul class="list-group">
                            <li class="list-group-item">Nome: ${solicitacao.cliente.nome} ${solicitacao.cliente.sobrenome}</li>
                            <li class="list-group-item">CPF: ${solicitacao.cliente.cpf}</li>
                            <li class="list-group-item">Telefone ${solicitacao.cliente.telefone.tipo.nomeExibicao}: (${solicitacao.cliente.telefone.ddd}) ${solicitacao.cliente.telefone.numero}</li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Dados solicitação</h6>
                        <ul class="list-group">

                            <fmt:parseDate  value="${solicitacao.venda.dataCompra}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="dataCompraParseada" />
                            <fmt:formatDate value="${dataCompraParseada}" type="both" pattern="dd/MM/yyyy HH:mm" var="dataCompraFormatada" />
                            <li class="list-group-item">Data compra: ${dataCompraFormatada} </li>

                            <fmt:parseDate  value="${solicitacao.venda.dataEnvio}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="dataEnvioParseada" />
                            <fmt:formatDate value="${dataEnvioParseada}" type="both" pattern="dd/MM/yyyy HH:mm" var="dataEnvioFormatada" />
                            <li class="list-group-item">Data entrega compra: ${dataEnvioFormatada != null ? dataEnvioFormatada : 'Não foi entregue'} </li>

                            <fmt:parseDate  value="${solicitacao.data}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="dataParseada" />
                            <fmt:formatDate value="${dataParseada}" type="both" pattern="dd/MM/yyyy HH:mm" var="dataFormatada" />
                            <li class="list-group-item">Data solicitação: ${dataFormatada} </li>

                            <li class="list-group-item">Valor total compra: <fmt:formatNumber value="${solicitacao.venda.precoTotal}" type="currency"/></li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Endereços</h6>
                        <ul class="list-group">

                            <c:choose>
                                <c:when test="${enderecoCobrancaEntrega != null}">
                                    <li class="list-group-item">
                                        <ul class="list-unstyled">
                                            <li><small class="text-muted">${enderecoCobrancaEntrega.tipoEndereco.nomeExibicao}</small></li>
                                            <li>${enderecoCobrancaEntrega.apelido} (${enderecoCobrancaEntrega.tipoResidencia})</li>
                                            <li>${enderecoCobrancaEntrega.tipoLogradouro} ${enderecoCobrancaEntrega.logradouro}, ${enderecoCobrancaEntrega.numero}</li>
                                            <li>${enderecoCobrancaEntrega.cidade}, ${enderecoCobrancaEntrega.estado} - Brasil</li>
                                            <li>CEP: ${enderecoCobrancaEntrega.cep}</li>
                                            <li>${enderecoCobrancaEntrega.observacoes}</li>
                                        </ul>
                                    </li>
                                </c:when>
                                <c:otherwise>

                                    <li class="list-group-item">
                                        <ul class="list-unstyled">
                                            <li><small class="text-muted">${enderecoEntrega.tipoEndereco.nomeExibicao}</small></li>
                                            <li>${enderecoEntrega.apelido} (${enderecoEntrega.tipoResidencia})</li>
                                            <li>${enderecoEntrega.tipoLogradouro} ${enderecoEntrega.logradouro}, ${enderecoEntrega.numero}</li>
                                            <li>${enderecoEntrega.cidade}, ${enderecoEntrega.estado} - Brasil</li>
                                            <li>CEP: ${enderecoEntrega.cep}</li>
                                            <li>${enderecoEntrega.observacoes}</li>
                                        </ul>
                                    </li>
                                    <hr>
                                    <li class="list-group-item">
                                        <ul class="list-unstyled">
                                            <li><small class="text-muted">${enderecoCobranca.tipoEndereco.nomeExibicao}</small></li>
                                            <li>${enderecoCobranca.apelido} (${enderecoCobranca.tipoResidencia})</li>
                                            <li>${enderecoCobranca.tipoLogradouro} ${enderecoCobranca.logradouro}, ${enderecoCobranca.numero}</li>
                                            <li>${enderecoCobranca.cidade}, ${enderecoCobranca.estado} - Brasil</li>
                                            <li>CEP: ${enderecoCobranca.cep}</li>
                                            <li>${enderecoCobranca.observacoes}</li>
                                        </ul>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>${isCancelamento ? 'Produtos cancelados' : 'Produto trocado'}</h6>
                        <ul class="list-group">
                            <c:choose>
                                <c:when test="${isCancelamento}">
                                    <!-- Produtos -->
                                    <c:forEach var="item" items="${solicitacao.venda.carrinho.itensCarrinho}">
                                        <li class="list-group-item">
                                            <div class="row p-3  ${ item.emTroca ? 'bg-secondary p-2 text-dark bg-opacity-25' : ''}">
                                                <div class="col-3">
                                                    <div class="card produto mb-3">
                                                        <img alt="produto" src="${item.produto.imagem}" class="p-2">
                                                    </div>
                                                </div>
                                                
                                                <div class="col">
                                                    <h5>${item.produto.nome}</h5>
                                                    <h6>Quantidade: ${item.quant}</h6>
                                                    <h6>Valor unitário: <fmt:formatNumber value="${item.produto.valorVenda}" type="currency"/></h6>
                                                </div>

                                                <c:if test="${item.emTroca}">
                                                    <div class="col-3">
                                                        <p class="text-center lead">Produto em Troca</p>
                                                    </div>
                                                </c:if>

                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <li class="list-group-item">
                                        <div class="row p-3">
                                            <div class="col-3">
                                                <div class="card produto mb-3">
                                                    <img alt="produto" src="${solicitacao.produto.imagem}" class="p-2">
                                                </div>
                                            </div>
                                            <div class="col">
                                                <h5>${solicitacao.produto.nome}</h5>
                                                <h6>Valor unitário: R$ ${solicitacao.produto.valorVenda}</h6>
                                                <h6>Quantidade: ${solicitacao.quantidade}</h6>
                                            </div>
                                            <div class="col-3">
                                                <p class="text-center lead">Produto em Troca</p>
                                            </div>

                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            
                        </ul>
                
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Status</h6>
                       
                        <ul class="list-group">
                            <li class="list-group-item">
                                Status atual: ${solicitacao.status.nomeExibicao}
                            </li>

                            <c:if test="${solicitacao.status != RECUSADA && solicitacao.status != 'REALIZADA'}">
                                <li class="list-group-item">
                                    Novo status:
    
                                    <form action="${isCancelamento ? '/emug/adm/cancelamentos' : '/emug/adm/trocas'}" method="POST">
                                        <select class="form-select" id="status" name="status">
                                            <option value="">Selecione</option>
                                            <c:forEach var="status" items="${listaStatus}">
                                                <option ${fn:contains(proximoStatus, status) ? '' : 'disabled' } value="${status}">${status.nomeExibicao}</option>
                                            </c:forEach>
                                        </select>
    
                                        <div class="form-check" id="divReentrada" style="display: none;">
                                            <br/>
                                            <input class="form-check-input" type="checkbox" id="isRetornoEstoque" name="reentradaEstoque">
                                            <label class="form-check-label" for="isRetornoEstoque">
                                                Reentrada de estoque
                                            </label>
                                        </div>
    
                                        <input type="hidden" name="id" value="${solicitacao.id}">
                                        <input type="hidden" name="operacao" value="atualizar">
                                        <button type="submit" class="w-100 btn btn-primary btn-sm">
                                            Atualizar status
                                        </button>
                                    </form>
                                </li>
                            </c:if>
                        </ul>
                        
                    </div>
                </div>                

            </div>
            <br/><br/>
        </div>
    </div>

    <jsp:include page="../include/footer.jsp" />

</body>

<script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>

<script>
    $('#status').change(function() {
        let status = $('#status option:selected').val();

        if(status === 'REALIZADA') 
            $("#divReentrada").show()
        else 
            $("#divReentrada").hide()
    })
</script>
</html>