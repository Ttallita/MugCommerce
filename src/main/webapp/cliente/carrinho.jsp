<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carrinho</title>
    <link rel="stylesheet" href="/emug/webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/emug/webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>
<body>

    <jsp:include page="../include/header.jsp" />

    <main class="wrapper container mt-3">
        <div class="row mt-3 mb-3 bg-white rounded p-4">

            <div class="col-lg-9">
                <h3 class="mb-3 fw-bold">
                    Carrinho<strong class="text-muted h4">(1)</strong>
                </h3>
                <div>
                    <div class="container">
                        <div class="row  border-bottom">
                            <div class="col-3">
                                <div class="card produto mb-3">
                                    <img alt="produto" src="\emug\assets\img\canecas\caneca_porco.jpeg" class="p-2">
                                </div>
                            </div>
                            <div class="col">
                                <h4 class="mb-3">
                                    <h6 class="text-muted">Categoria</h6>
                                    Caneca porco
                                </h4>

                                <div class="float-end col-4">
                                    <label>Quantidade:</label>
                                    <input type="number" class="form-control">
                                    <h6 class="text-muted small">10 disponíveis</h6>
                                </div>

                                <h4>R$ 100,00</h4>
                                <div class="d-flex col-12 justify-content-between">
                                    <a class="text-decoration-none">Excluir</a>
                                    <a class="text-decoration-none" href="/emug/cliente/confirmarPedido.jsp">Comprar agora</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 order-md-last">
                <div class="row vstack mt-5">
                    <div class="container border rounded mt-3 py-4">
                        <ul class="list-group">
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Carrinho:</strong>
                                <strong>$0.00</strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Frete:</strong>
                                <strong>$0.00</strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Desconto:</strong>
                                <strong>- R$ 0.00</strong>
                            </li>
                            <li class="d-flex justify-content-between py-3">
                                <strong class="text-muted">Total:</strong>
                                <h5 class="font-weight-bold">R$ 400,00</h5>
                            </li>
                            <a href="finalizarCompra.jsp" class="btn btn-primary rounded-pill py-2 ">
                                Finalizar compra
                            </a>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </main>

    <jsp:include page="../include/footer.jsp"/>
    
</body>

<script src="/emug/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>