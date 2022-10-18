<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Vendas</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table-filter-control.min.css' />"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Vendas</h5>

            <hr>

            <div class="table-responsive p-3 rounded mb-4">
                <table id="tableVenda" class="table table-hover"
                        data-toggle="table"
                        data-pagination="true"
                        data-search="true"
                        data-page-size="25"
                        data-locale="pt-BR"
                        data-filter-control="true"
                        data-show-search-clear-button="true">
                    <thead>
                        <tr>
                            <th data-field="nomeCliente" data-filter-control="input">Cliente</th>
                            <th data-field="valor" data-filter-control="input">Valor</th>
                            <th data-field="dataCompra" data-filter-control="datepicker">Data compra</th>
                            <th data-field="dataEntrega" data-filter-control="datepicker">Data entrega</th>
                            <th data-field="status" data-filter-control="select">Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>

                        <c:forEach items="${vendas}" var="venda">
                            <tr>
                                <td>${venda.cliente.nome} ${venda.cliente.sobrenome}</td>
                                <td>R$ ${venda.precoTotal}</td>

                                <fmt:parseDate  value="${venda.dataCompra}"  type="date" pattern="yyyy-MM-dd" var="dataCompraParseada" />
                                <fmt:formatDate value="${dataCompraParseada}" type="date" pattern="dd/MM/yyyy" var="dataCompraFormatada" />
                                <td>${dataCompraFormatada}</td>

                                <fmt:parseDate  value="${venda.dataEntrega}"  type="date" pattern="yyyy-MM-dd" var="dataEntregaParseada" />
                                <fmt:formatDate value="${dataEntregaParseada}" type="date" pattern="dd/MM/yyyy" var="dataEntregaFormatada" />
                                <td>${dataEntregaFormatada}</td>
                                <td>${venda.vendaStatus.nomeExibicao}</td>
                                <td>
                                    <a href="/emug/adm/vendas?operacao=listarUnico&id=${venda.id}" >
                                        <button name="botaoListarVenda" type="button" class="btn btn-primary btn-sm"><span class="material-icons">edit</span></button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
        
                    </tbody>
                </table>
            </div>

            
        </div>
    </main>

    <br/>

    <jsp:include page="../include/footer.jsp"/>

</body>

<script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
<script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
<script src='<c:url value="/assets/js/bootstrap-table.min.js"/>'></script>
<script src='<c:url value="/assets/js/bootstrap-table-filter-control.min.js"/>'></script>
<script src='<c:url value="/assets/js/bootstrap-table-pt-BR.min.js"/>'></script>

<script>

    $(function() {
        $('#tableVenda').bootstrapTable()
    })
</script>

</html>