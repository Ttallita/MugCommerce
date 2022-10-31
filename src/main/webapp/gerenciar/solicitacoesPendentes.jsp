<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="paginaCorrente" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="isCancelamento" value="${ fn:contains(paginaCorrente, 'cancelamentos') }"/>

<c:set var="nomePaginaCorrente" value="${ isCancelamento ? 'Cancelamentos' : 'Trocas'}"/>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Gerenciar - ${nomePaginaCorrente}</title>

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
            <h5>${nomePaginaCorrente}</h5>

            <hr>

            <div class="container">

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
                                <th data-field="nomeCliente" data-filter-control="input">Nome cliente</th>
                                <th data-field="valor" data-filter-control="input">Valor venda</th>
                                <th data-field="dataCompra" data-filter-control="datepicker">Data solicitacao</th>
                                <th data-field="status" data-filter-control="select">Status</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${solicitacoes}" var="solicitacao">
                                <tr>
                                    <td>${solicitacao.cliente.nome} ${solicitacao.cliente.sobrenome}</td>
                                    <td>R$ ${solicitacao.venda.precoTotal}</td>

                                    <fmt:parseDate  value="${solicitacao.data}"  type="date" pattern="yyyy-MM-dd" var="dataParseada" />
                                    <fmt:formatDate value="${dataParseada}" type="date" pattern="dd/MM/yyyy" var="dataFormatada" />
                                    <td>${dataFormatada}</td>
                                    <td>${solicitacao.status.nomeExibicao}</td>
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

</html>