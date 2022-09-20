<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                <!-- Cupom -->

                <div class="row row-cols-xl-2">

                    <div class="col">
                        <div class="border p-2 rounded m-1">
                            <div class="d-flex justify-content-between mt-2">
                                <small>Promocional</small>
                                <h6>Validade: 00/00/0000</h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6>Nome cupom</h6>
                                <h6>R$ 00,00</h6>
                            </div>
                            <p>Descrição cupom</p>
                        </div>
                    </div>

                    <div class="col">

                        <div class="border p-2 rounded m-1">
                            <div class="d-flex justify-content-between mt-2">
                                <small>Promocional</small>
                                <h6>Validade: 00/00/0000</h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6>Nome cupom</h6>
                                <h6>R$ 00,00</h6>
                            </div>
                            <p>Descrição cupom</p>
                        </div>
                    </div>


                    <div class="col">
                        <div class="border p-2 rounded m-1">
                            <div class="d-flex justify-content-between mt-2">
                                <small>Promocional</small>
                                <h6>Validade: 00/00/0000</h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6>Nome cupom</h6>
                                <h6>R$ 00,00</h6>
                            </div>
                            <p>Descrição cupom</p>
                        </div>
                    </div>

                    <div class="col">
                        <div class="border p-2 rounded m-1">
                            <div class="d-flex justify-content-between mt-2">
                                <small>Promocional</small>
                                <h6>Validade: 00/00/0000</h6>
                            </div>
                            <div class="d-flex justify-content-between">
                                <h6>Nome cupom</h6>
                                <h6>R$ 00,00</h6>
                            </div>
                            <p>Descrição cupom</p>
                        </div>
                    </div>


                </div>
            </div>

        </div>
    </main>

    <br/>

    <jsp:include page="../include/footer.jsp"/>
    
    <script src="../webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
    
</body>

</html>