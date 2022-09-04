<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <div class="container-fluid d-flex h-100 align-items-center justify-content-center">
            <div class="row">
                <div class="col-sm-12">
                    <jsp:include page="include/alert.jsp" />
                    <div class="card">
                        <div class="card-body">
                            <h3 class="text-center">Login</h3>
                            <form action="<c:url value="/login"/>" method="POST">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input id="email" type="email" class="form-control" name="email" aria-describedby="emailHelp">
                                </div>
                                <div class="mb-3">
                                    <label for="senha" class="form-label">Senha</label>
                                    <input id="senha" type="password" class="form-control" name="senha">
                                </div>
                                <input type="hidden" name="operacao" value="login">
                                <div class="d-grid gap-2 col-12 mx-auto align-middle">
                                    <input class="btn btn-primary" type="submit" value="Fazer Login" name="botaoLogin"/>
                                </div>
                            </form>
                            Não possui conta? <a href="cadastroCliente.jsp" name="linkCadastro">Cadastre-se</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="include/footer.jsp"/>

    </body>
    
    <script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
</html>