<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="include/header.jsp" />

    <main class="container mt-3">
        <div class="row mt-4 bg-white rounded p-4 mb-3">
            <div class="col-md-4">
                <div class="card  mb-3">
                    <img alt="produto" class="p-2" src="\emug\assets\img\canecas\caneca_exemplo.jpeg">
                </div>
            </div>
            <div class="col-md-5 p-3">
                <div>
                    <p class="text-muted">Caneca Personalizada</p>
                    <h2 class="title">Caneca</h2>
                    <p>Descrição</p>
                    <ul class="list-group mt-4">
                        <li class="d-flex  justify-content-between">
                            <h6 class="fw-bolder">Material</h6>

                            <h6 class="text-muted align-middle">material</h6>
                        </li>
                        <li class="d-flex  justify-content-between">
                            <h6 class="fw-bolder">Fabricante</h6>
                            <h6 class="text-muted align-middle">fabricante</h6>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-3 border p-3">
                <div>
                    <p class="text-muted">10 vendidos</p>
                    <div class="mb-3">
                        <h2>R$ 100,00</h2>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="dropdown mb-3">
                            <a type="button" id="alterarEndereco" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#alterarEnderecoModal">
                                Envio para casa de Nattan Silva
                                <span class="material-icons inline-icon">keyboard_arrow_down</span>
                            </a>
                            <ul aria-labelledby="navbarDropdown" class="dropdown-menu">
                                <li>
                                    <a href="" class="dropdown-item">
                                        Envio para casa de Nathan Silva
                                    </a>
                                </li>
                                <li>
                                    <a href="#" class="dropdown-item">Adicionar endereço</a>
                                </li>
                            </ul>
                        </div>
                        <p>Frete: R$ 00,00</p>
                        <div class="row g-2 m-2 align-items-center">
                            <div class="col-auto">
                                <label class="col-form-label">Quantidade:</label>
                            </div>
                            <div class="col-4">
                                <input class="form-control" type="number">
                            </div>
                            <div class="col-auto">
                                <span class="form-text">(10 disponíveis)</span>
                            </div>
                        </div>
                    </div>
                    <div class="d-grid gap-1 col-10 mx-auto mt-3">
                        <a type="button" href="cliente/finalizarCompra.jsp" class="btn btn-primary rounded-pill">Comprar</a>
                        <a type="button" href="cliente/carrinho.jsp" class="btn btn-outline-primary rounded-pill">Adicionar ao carrinho</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="include/footer.jsp"/>

</body>

<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>