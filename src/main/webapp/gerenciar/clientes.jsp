<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Clientes</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table-filter-control.min.css' />"/>
</head>

<body>
    <jsp:include page="../include/header.jsp"/>
    <main class="d-flex flex-nowrap mt-5">
        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Clientes</h5>
            <hr>
            <div class="table-responsive p-3 rounded mb-4">
                <table id="tableClientes"
                       class="table table-hover w-100"
                       data-toggle="table"
                       data-pagination="true"
                       data-search="true"
                       data-page-size="25"
                       data-locale="pt-BR"
                       data-filter-control="true"
                       data-show-search-clear-button="true">
                    <thead>
                        <tr>
                            <th data-field="nome" data-filter-control="input">Nome</th>
                            <th data-field="cpf" data-filter-control="input">CPF</th>
                            <th data-field="genero" data-filter-control="select">Gênero</th>
                            <th data-field="dtNasc" data-filter-control="input">Data de Nascimento</th>
                            <th data-field="telefone" data-filter-control="input">Telefone</th>
                            <th data-field="rank" data-filter-control="input">Rank</th>
                            <!-- <th>Editar</th>
                            <th>Inativar</th> -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <td>${cliente.nome} ${cliente.sobrenome}</td>
                                <td>${cliente.cpf}</td>
                                <td>${cliente.genero}</td>
                                <td>
                                    <fmt:parseDate  value="${cliente.dataNascimento}"  type="date" pattern="yyyy-MM-dd" var="dataParseada" />
                                    <fmt:formatDate value="${dataParseada}" type="date" pattern="dd/MM/yyyy" var="dataFormatada" />
                                    ${dataFormatada}
                                </td>
                                <td>(${cliente.telefone.ddd}) ${cliente.telefone.numero}</td>
                                <td>${cliente.ranking}</td>
                                <!--
                                    <td><span class="material-icons text-primary">edit</span></td>
                                    <td><span class="material-icons text-danger">delete</span></td>
                                -->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- <a href='<c:url value="/gerenciar/formularios/formCliente.jsp"/>'>
                    <button type="button" class="w-100 btn btn-primary btn-sm">
                        Adicionar cliente
                    </button>
                </a> -->
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