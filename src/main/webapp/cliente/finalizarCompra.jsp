<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Finalizar compra</title>
    <link rel="stylesheet" href="/emug/webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/emug/webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>
<body>
    <header>
        <div class="navbar bg-dark navbar-dark">
            <div class="container-fluid">

                <a href="/emug/index.html">
                    <img src="\emug\assets\img\logo_branco.png" alt="logo" class="logo w-50">
                </a>

                <form class="d-flex" action="pesquisa.html">
                    <input class="form-control me-2" type="search" aria-label="Search">
                    <button class="btn btn-primary" type="submit">
                        <span class="material-icons">search</span>
                    </button>
                </form>

                <div class="d-flex align-items-center gap-3">
                    <button class="btn btn-light" type="submit">
                        <a href="cliente/perfil.html">
                            <span class="material-icons">account_circle</span>
                        </a>
                    </button>
                    <button class="btn btn-light" type="submit">
                        <a href="cliente/carrinho.html">
                            <span class="material-icons">shopping_cart</span>
                        </a>
                    </button>
                </div>

            </div>
        </div>

        <nav class="navbar navbar-expand-lg bg-dark navbar-dark ">
            <div class="container-fluid">

                <button class="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Produtos</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>

    </header>

    <main class="wrapper container mt-3 mb-3">
        <div class="row bg-white rounded p-4">
            <div class="col-lg-8">
                <h3 class="mb-3 fw-bold">Confira e confirme sua compra</h3>
                <div class="row border rounded p-3 m-2">
                    <div class="col-6 p-3">
                        <strong>Endereço de entrega</strong>
                        <!--Aciona modal alterar endereço entrega-->
                        <a type="button" id="alterarEndereco" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#exampleModal">
                            Alterar
                        </a>
                        <h6>Casa</h6>
                        <h6>Rua Carlos Barattino, 908</h6>
                        <h6>SP - Brasil</h6>
                        <h6>CEP: 08773-600</h6>
                        <h6>Observação</h6>
                    </div>
                    <div class="col-6">
                        <div class="col p-3">
                            <strong>Forma de pagamento</strong>
                            <!--Aciona modal alterar forma de pagamento-->
                            <a type="button" id="alterarPagamento" class="text-decoration-none" data-bs-toggle="modal"
                               data-bs-target="#alterarFormaPagamentoModal">
                                Alterar
                            </a>
                            <h6>(Crédito) com final 0000</h6>
                        </div>
                    </div>
                    <div class="col-6 p-3">
                        <strong>Cupons aplicados:</strong>
                        <div class="toast align-items-center text-white bg-primary border-0 fade show" aria-live="assertive" aria-atomic="true" role="alert">
                            <div class="p-2">
                                <button type="button" data-bs-dismiss="toast" aria-label="Close" class="btn-close btn-close-white me-2"></button>
                                <div class="d-flex justify-content-between">
                                    <h6 class="fw-bold">PROMOCIONAL</h6>
                                    <h7>Validade: 26/2023</h7>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h5>BLACK FRIDAY</h5>
                                    <h5>R$ 50,00</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 p-3"><strong>Adicionar cupom</strong>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" aria-label="Recipient's username"
                                   aria-describedby="button-addon2" placeholder="Nome cupom">
                            <button type="button" id="button-addon2" class="btn btn-outline-primary">Aplicar</button>
                        </div>
                    </div>
                </div>
                <div class="row border rounded p-3 m-2">
                    <h4 class="mb-3 fw-bold">Itens</h4>
                    <div class="row border-top p-3">
                        <h6 class="mb-3 text-primary fw-bold">Data de entrega: 00 mês 0000 </h6>
                        <div class="col-3">
                            <div class="card produto mb-3">
                                <img alt="..." src="\emug\assets\img\canecas\caneca_exemplo.jpeg" class="p-2">
                            </div>
                        </div>
                        <div class="col">
                            <h4 class="mb-3">
                                <h6 class="text-muted">Categoria</h6>Nome
                            </h4>
                            <h4>R$ 100,00</h4>Quantidade: 1
                            <a class="text-decoration-none">Alterar</a>
                        </div>
                    </div>
                    <div class="row border-top p-3">
                        <h6 class="mb-3 text-primary fw-bold">Data de entrega: 00 mês 0000 </h6>
                        <div class="col-3">
                            <div class="card produto mb-3">
                                <img alt="produto" src="\emug\assets\img\canecas\caneca_porco.jpeg" class="p-2">
                            </div>
                        </div>
                        <div class="col">
                            <h4 class="mb-3">
                                <h6 class="text-muted">Categoria</h6>Nome
                            </h4>
                            <h4>R$ 100,00</h4>Quantidade: 1
                            <a class="text-decoration-none">Alterar</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 order-md-last">
                <div class="row vstack mt-5">
                    <div class="container border rounded py-4">
                        <ul class="list-group">
                            <li class="d-flex mb-4 justify-content-between">
                                <h5 class="fw-bold ">Resumo do pedido</h5>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Itens:</strong>
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
                                <strong class="text-muted">Total do pedido:</strong>
                                <h5 class="font-weight-bold">R$ 400,00</h5>
                            </li>
                            <a href="/LootCommerce/cliente/pedidoConfirmado.jsp" class="btn btn-primary  rounded-pill py-2 ">Confirmar pedido</a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Escolha o endereço</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                <label class="form-check-label" for="flexRadioDefault1">
                                    Rua Carlos Barattino, 908, Vila Nova - Vila Mogilar, Mogi das Cruzes - SP - CEP 08773-600
                                </label>
                                <small class="float-end"><a href="">Editar</a> </small>
                            </div>
                            <br/>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                                <label class="form-check-label" for="flexRadioDefault2">
                                    Rua Carlos de Carvalho, 200, Jardim Sao Joao, Ferraz de Vasconcelos - SP - CEP 08545-130
                                </label>
                                <small class="float-end"><a href="">Editar</a> </small>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Alterar</button>
                        <button type="button" class="btn btn-secondary">Adicionar outro endereço</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>
<script src="/emug/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
</html>