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
        <h5>Desativar Conta</h5>

        <hr>

        <div class="container">
            <p>
                <b>Aviso:</b> A conta será desativada e não será possivel acessa-la novamente. Caso deseje recuperar o acesso
                    novamente um administrador do sistema deve ser notificado.
            </p>

            <a type="button" class="w-100 btn btn-danger btn-sm" onclick="abrirModal(${sessionScope.usuario.id})">
                Desativar Conta
            </a>

        </div>
    </div>

    <!-- Modal Remover endereço -->
    <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="removeModalTitle">Desativar conta</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Deseja realmente desativar a conta?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cancelar</button>
                    <form action="/emug/clientes/desativarConta" method="POST">
                        <input type="hidden" name="operacao" value="excluir">
                        <input type="hidden" id="idRemover" name="id" value="">
                        <input type="submit" class="btn btn-danger" value="Remover">
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
<br/>
<jsp:include page="../include/footer.jsp"/>
</body>
<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js" />'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
</html>