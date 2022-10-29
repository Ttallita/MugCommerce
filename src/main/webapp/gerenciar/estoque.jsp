<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Estoque</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Estoque</h5>
            <hr>

            <div class="container">
                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="pills-estoque-tab" data-bs-toggle="pill" data-bs-target="#pills-estoque" type="button" role="tab" aria-controls="pills-estoque" aria-selected="true">Estoque atual</button>
                    </li>

                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-estoque-historico-tab" data-bs-toggle="pill" data-bs-target="#pills-estoque-historico" type="button" role="tab" aria-controls="pills-estoque-historico" aria-selected="false">Historico Estoque</button>
                    </li>

                </ul>

                <div class="tab-content" id="pills-tabContent">

                    <div class="tab-pane fade show active" id="pills-estoque" role="tabpanel" aria-labelledby="pills-estoque-tab" tabindex="0">

                        <table class="table table-hover w-100">
                            <thead>
                            <tr>
                                <th>Produto</th>
                                <th>Quant.</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="estoque" items="${estoques}">
                                    <tr>
                                        <td>${estoque.produto.nome}</td>
                                        <td>${estoque.quantidade}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>


                        <a href="${pageContext.request.contextPath}/gerenciar/formularios/entradaEstoque.jsp">
                            <button type="button" class="w-100 btn btn-primary btn-sm" >
                                Entrada de estoque
                            </button>
                        </a>
                    </div>

                    <div class="tab-pane fade" id="pills-estoque-historico" role="tabpanel" aria-labelledby="pills-estoque-historico-tab" tabindex="0">
                        <table class="table table-hover w-100">
                            <thead>
                                <tr>
                                    <th>Produto</th>
                                    <th>Quant.</th>
                                    <th>Data de entrada</th>
                                    <th>Valor custo</th>
                                    <th>Fornecedor</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="entrada" items="${estoqueHistorico}">
                                    <tr>
                                        <td>${entrada.estoque.produto.nome}</td>
                                        <td>${entrada.quantidade}</td>
                                        <td>
                                            <fmt:parseDate  value="${entrada.dataEntrada}" type="both" pattern="yyyy-MM-dd'T'HH:mm" var="dataParseada" />
                                            <fmt:formatDate value="${dataParseada}" type="both" pattern="dd/MM/yyyy HH:mm" var="dataFormatada" />
                                            ${dataFormatada}
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${entrada.valorCusto}" type="currency"/>
                                        </td>
                                        <td>${entrada.fornecedor.nome}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </main>

    <br/>
    
    <jsp:include page="../include/footer.jsp"/>

    <script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
    <script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
    <script src="<c:url value='/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js' />"></script>

    <script>
        $('#minVenda').mask("#.##0,00", {reverse: true});
        $('#maxVenda').mask("#.##0,00", {reverse: true});
        $('#minCompra').mask("#.##0,00", {reverse: true});
        $('#maxCompra').mask("#.##0,00", {reverse: true});
    </script>
</body>
</html>