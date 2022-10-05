<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carrinho</title>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>
<body>

<jsp:include page="../include/header.jsp"/>

<main class="wrapper container mt-3">
    <div class="row mt-3 mb-3 bg-white rounded p-4">

        <div class="col-lg-9">
            <h3 class="mb-3 fw-bold">
                Carrinho de compras
            </h3>
            <div class="container">
                <c:choose>
                    <c:when test="${empty sessionScope.carrinho || sessionScope.carrinho.itensCarrinho.size() == 0}">
                        <div class="row border-bottom">
                            <div class="col-12">
                                Sem itens no carrinho
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-sm">
                            <thead>
                            <tr>
                                <th scope="col">Produto</th>
                                <th scope="col"></th>
                                <th scope="col">Quantidade</th>
                                <th scope="col">Preço</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${sessionScope.carrinho.itensCarrinho}">
                                <tr>
                                    <td style="height: 10%; width: 10%">
                                        <img alt="${item.produto.nome}" src="${item.produto.imagem}" class="img-fluid">
                                    </td>
                                    <td class="p-3">
                                        <a href="<c:url value='/produtos?operacao=listarUnico&id=${item.produto.id}'/>">
                                            <h6>${item.produto.nome}</h6>
                                        </a>
                                    </td>
                                    <td style="width: 20%">
                                       <form action="/emug/clientes/carrinho" method="POST" id="formQuantidade${item.produto.id}">
                                           <div class="input-group input-group-sm mb-3">
                                               <button class="btn btn-outline-secondary btn-sm" type="button" id="removeQuant" onclick="mudarQuantidade('remover', '${item.produto.id}')">
                                                   <span class="material-icons">remove</span>
                                               </button>
                                               <input type="hidden" value="atualizar" name="operacao">
                                               <input type="hidden" value="" name="tipo" id="tipo${item.produto.id}">
                                               <input type="hidden" value="${item.produto.id}" name="id">
                                               <input type="hidden" value="${item.quant}" name="quantidade" id="quantidadeAtual${item.produto.id}">
                                               <input type="text" class="form-control" readonly value="${item.quant}">
                                               <button class="btn btn-outline-secondary btn-sm" type="button" id="addQuant" onclick="mudarQuantidade('adicionar', '${item.produto.id}')">
                                                   <span class="material-icons">add</span>
                                               </button>
                                           </div>
                                       </form>
                                        <form action="/emug/clientes/carrinho" method="POST">
                                            <input type="hidden" name="id" value="${item.produto.id}">
                                            <input type="hidden" name="operacao" value="excluir">
                                            <input type="submit" class="input-link" value="Excluir" />
                                        </form>
                                    </td>
                                    <td class="p-3">
                                        <fmt:formatNumber value="${item.produto.valorVenda * item.quant}" type="currency"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <c:if test="${not empty sessionScope.carrinho && sessionScope.carrinho.itensCarrinho.size() > 0}">
            <div class="col-lg-3 order-md-last">
                <div class="row vstack mt-5">
                    <div class="container border rounded mt-3 py-4">
                        <ul class="list-group">
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Carrinho:</strong>
                                <strong><fmt:formatNumber value="${sessionScope.carrinho.totalCarrinho}" type="currency"/></strong>
                            </li>
                            <!--
                                <li class="d-flex justify-content-between">
                                    <strong class="text-muted">Frete:</strong>
                                    <strong>$0.00</strong>
                                </li>

                                <li class="d-flex justify-content-between">
                                    <strong class="text-muted">Desconto:</strong>
                                    <strong>- R$ 0.00</strong>
                                </li>
                            -->
                            <li class="d-flex justify-content-between py-3">
                                <strong class="text-muted">Total:</strong>
                                <h5 class="font-weight-bold">
                                    <fmt:formatNumber value="${sessionScope.carrinho.totalCarrinho}" type="currency"/>
                                </h5>
                            </li>
                            <a href="<c:url value='/clientes/carrinho/finalizarCompra?operacao=listar'/>" class="btn btn-primary rounded-pill py-2 ">
                                Finalizar compra
                            </a>
                        </ul>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</main>

<jsp:include page="../include/footer.jsp"/>

</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script>

    function mudarQuantidade(tipo, id) {
        $('#tipo' + id).val(tipo)
        $('#formQuantidade' + id)[0].submit()
    }
</script>
</html>