<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Endereços</title>


    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css" />"/>
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
                                <th>País</th>
                                <th>CEP</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>Nome</td>
                                <td>Logradouro</td>
                                <td>Bairro</td>
                                <td>Cidade</td>
                                <td>Número</td>
                                <td>Estado</td>
                                <td>País</td>
                                <td>CEP</td>
                                <td>
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
    
                            <tr>
                                <td>Nome</td>
                                <td>Logradouro</td>
                                <td>Bairro</td>
                                <td>Cidade</td>
                                <td>Número</td>
                                <td>Estado</td>
                                <td>País</td>
                                <td>CEP</td>
                                <td>
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
    
                    <button type="button" class="w-100 btn btn-primary btn-sm">Adicionar endereço</button>
                </div>
            </div>

        </div>
    </main>

    <br/>
    <footer class="bg-dark text-center text-white">
        &copy;Todos direitos reservados André e Tallita
    </footer>
</body>
<script src="<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js" />"></script>
</html>