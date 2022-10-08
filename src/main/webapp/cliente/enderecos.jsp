<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Endereços</title>


    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>Endereços</h5>

            <hr>

            <div class="container">

                <div class="table-responsive p-3 rounded mb-4">

                    <table class="table table-hover w-100">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Logradouro</th>
                                <th>Bairro</th>
                                <th>Cidade</th>
                                <th>Número</th>
                                <th>Estado</th>
                                <th>CEP</th>
                                <th>Editar</th>
                                <th>Remover</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="endereco" items="${enderecos}">
                                <tr>
                                    <td>${endereco.apelido}</td>
                                    <td>${endereco.logradouro}</td>
                                    <td>${endereco.bairro}</td>
                                    <td>${endereco.cidade}</td>
                                    <td>${endereco.numero}</td>
                                    <td>${endereco.estado}</td>
                                    <td>${endereco.cep}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/clientes/enderecos?operacao=listarUnico&id=${endereco.id}" class="btn btn-primary btn-sm">
                                            <span class="material-icons" onclick="preencherCampos(${endereco.id})">edit</span>
                                        </a>
                                    </td>
                                    <td>
                                        <button name="botaoModalDeletar" type="button" class="btn btn-danger btn-sm" onclick="abrirModal(${endereco.id})">
                                            <span class="material-icons">delete</span>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
            
                        </tbody>
                    </table>

                    <a href="/emug/cliente/formularios/formEndereco.jsp">
                        <button type="button" class="w-100 btn btn-primary btn-sm">
                            Adicionar endereço
                        </button>
                    </a>
                </div>
            </div>
        </div>

        <!-- Modal Remover endereço -->
        <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="removeModalTitle">Remover registro</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Deseja realmente remover o registro?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cancelar</button>
                        <form action="/emug/clientes/enderecos" method="POST">
                            <input type="hidden" name="operacao" value="excluir">
                            <input type="hidden" id="idRemover" name="id" value="">
                            <input type="submit" class="btn btn-danger" value="Remover" id="botaoRemoverEndereco">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="../include/footer.jsp"/>
</body>
<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js" />'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js" />'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
</html>