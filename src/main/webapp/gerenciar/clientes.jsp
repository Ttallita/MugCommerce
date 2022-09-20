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

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css" />"/>
</head>

<body>
    <jsp:include page="../include/header.jsp"/>
    <main class="d-flex flex-nowrap mt-5">
        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Clientes</h5>
            <hr>

            <!-- Filtro de cliente-->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-12">
                            <label for="pesquisa">
                                <small>Pesquisa</small>
                            </label>
                            <input type="text" class="form-control" id="pesquisa" name="pesquisa" value="">
                        </div>
                        <div class="col-sm-4">
                            <small>Gênero</small>
                            <select class="form-select" aria-label="Select gênero">
                                <option selected>Selecione</option>
                                <option value="Masculino">Masculino</option>
                                <option value="Feminino">Feminino</option>
                                <option value="Outro">Outro</option>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label for="pais"><small>País</small></label>
                            <select class="form-select" id="pais" name="pais">
                                <option value="">Selecione</option>
                                <option>Brasil</option>
                                <option>Uruguai</option>
                            </select>
                        </div>
        
                        <div class="col-md-4">
                            <label for="estado">
                                <small>Estado</small>
                            </label>
                            <select class="form-select" id="estado" name="estado">
                                <option value="">Selecione</option>
                                <option>São Paulo</option>
                                <option>Rio de Janeiro</option>
                                <option>Minas gerais</option>
                            </select>
                        </div>

                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>

                    </div>

                </form>
            </div>

            <hr>
            <div class="table-responsive p-3 rounded mb-4">
                <table class="table table-hover" width="100%">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Gênero</th>
                            <th>Data de Nascimento</th>
                            <th>Telefone</th>
                            <th>Rank</th>
                            <th>Editar</th>
                            <th>Inativar</th>
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
                                <td><span class="material-icons text-primary">edit</span></td>
                                <td><span class="material-icons text-danger">delete</span></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <button type="button" class="w-100 btn btn-primary btn-sm">
                    <a href='<c:url value="/cadastroCliente.jsp"/>'>Adicionar cliente</a>
                </button>

            </div>
            
        </div>
    </main>
    <br/>

    <jsp:include page="../include/footer.jsp"/>

</body>
<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>