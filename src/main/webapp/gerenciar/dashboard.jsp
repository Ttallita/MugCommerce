<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Gerenciar - Dashboard</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Dashboard</h5>

            <hr>

            <!-- Gráficos-->
            <div class="container">
                <div class="row g-5">

                    <div class="col-12 align-self-center text-center">
                        <h5>Gráfico vendas</h5>
                        <img src="\emug\assets\img\grafico_exemplo.jpg" class="img-fluid" alt="img_grafico">
                    </div>

                    <div class="col-12 align-self-center text-center">
                        <h5>Taxa de trocas</h5>
                        <img src="\emug\assets\img\grafico_exemplo2.png" class="img-fluid" alt="img_grafico">
                    </div>

                </div>
            </div>
        </div>
    </main>
    <br/>

    <jsp:include page="../include/footer.jsp"/>
    
    <script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</body>

</html>