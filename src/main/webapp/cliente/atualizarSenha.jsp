<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Atualizar senha</title>
    <link rel="stylesheet" href='<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />'/>
    <link rel="stylesheet" href='<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />'/>
    <link rel="stylesheet" href='<c:url value="/assets/css/style.css" />'/>
</head>

<body>
    <jsp:include page="../include/header.jsp"/>
    <main class="d-flex flex-nowrap mt-5">
        <jsp:include page="../include/sidebarCliente.jsp" />
        <div class="w-75 bg-white rounded p-5">
            <h5>Atualizar senha</h5>
            <hr>
            <div class="container">
                <jsp:include page="../include/alert.jsp" />
                <!-- Atualizar senha -->
                <form action="/emug/clientes/atualizarSenha" method="POST">
                    <div class="row g-3 p-4">

                        <div class="col-sm-12">
                            <label for="senhaAntiga"><small>Senha antiga</small></label>
                            <input type="password" class="form-control" id="senhaAntiga" name="senhaAntiga" value="">
                        </div>

                        <div class="col-sm-12">
                            <label for="senhaNova"><small>Senha</small></label>
                            <input type="password" class="form-control" id="senhaNova" name="senhaNova" value="">
                            <small>A senha deve conter pelo menos 8 caracteres, incluir caracteres maiúsculos, minúsculos e especiais.</small>
                        </div>
        
                        <div class="col-sm-12">
                            <label for="senhaConfirmacao"><small>Confirmação senha</small></label>
                            <input type="password" class="form-control" id="senhaConfirmacao" name="senhaConfirmacao" value="">
                        </div>
                        <input type="hidden" name="operacao" value="atualizar">
                        <button type="submit" class="w-100 btn btn-primary btn-sm">Atualizar</button>
                    </div>
                </form>
            </div>
            <hr>
        </div>
    </main>

    <br/>
    <footer class="bg-dark text-center text-white">
        &copy;Todos direitos reservados André e Tallita
    </footer>
    <script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js'/>"></script>
</body>
</html>