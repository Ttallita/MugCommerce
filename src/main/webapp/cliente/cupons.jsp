<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Cupons</title>

    <link rel="stylesheet" href="../webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>Cupons</h5>

            <hr>

            <div class="container">
                <div class="row row-cols-xl-2">

                    <c:forEach var="cupom" items="${cupons}">
                        <div class="col">
                            <div class="border p-2 rounded m-1">
                                <div class="d-flex justify-content-between mt-2">
                                    <small>${cupom.tipo}</small>
                                    <fmt:parseDate  value="${cupom.dataValidade}"  type="date" pattern="yyyy-MM-dd" var="dataParseada" />
                                    <fmt:formatDate value="${dataParseada}" type="date" pattern="dd/MM/yyyy" var="dataFormatada" />
                                    <h6>Validade: ${dataFormatada}</h6>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h6>${cupom.nome}</h6>
                                    <h6>R$ ${cupom.valor}</h6>
                                </div>
                                <p>${cupom.descricao}</p>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

        </div>
    </main>

    <br/>

    <jsp:include page="../include/footer.jsp"/>
    
</body>

<script src="../webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>