<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Cartões</title>

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css" />"/></head>

<body>

    <jsp:include page="../include/header.jsp" />
    <main class="d-flex flex-nowrap mt-5">
        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>Endereços</h5>

            <hr>

            <div class="container">

                <div class="table-responsive p-3 rounded mb-4">

                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Bandeira</th>
                                <th>Final cartão</th>
                                <th>Nome no cartão</th>
                                <th>Vencimento</th>
                                <th>Editar</th>
                                <th>Remover</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cartao" items="${cartoes}">
                                <tr
                                        class="${cartao.preferencial ? 'cartao-preferencial' : ''}"
                                        ${cartao.preferencial ? 'data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Cartão preferencial"' : '' }>
                                    <td>${cartao.bandeira}</td>
                                    <td>${cartao.numCartao}</td>
                                    <td>${cartao.nomeImpressoCartao}</td>
                                    <td>${cartao.mesValidade}/${cartao.anoValidade}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/clientes/cartoes?operacao=listarUnico&id=${cartao.id}">
                                            <span class="material-icons text-primary">edit</span>
                                        </a>
                                    </td>
                                    <td>
                                        <span class="material-icons text-danger" onclick="abrirModal(${cartao.id})">delete</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
    
                    <a type="button" class="w-100 btn btn-primary btn-sm" href="${pageContext.request.contextPath}/formularios/formCartaoCredito.jsp">Adicionar cartão</a>
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
                        <form action="/emug/clientes/cartoes" method="POST">
                            <input type="hidden" name="operacao" value="excluir">
                            <input type="hidden" id="idRemover" name="id" value="">
                            <input type="submit" class="btn btn-danger" value="Remover">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <br/>

    </main>
    <br/>

    <jsp:include page="../include/footer.jsp"/>
</body>
<script src="<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js" />"></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
</script>
</html>