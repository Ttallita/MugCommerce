<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Compras</title>

    <link rel="stylesheet" href="../webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../webjars/material-design-icons/4.0.0/material-icons.css"/>
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
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>Compras</h5>

            <hr>

            <div class="container">

                <form>
                    <div class="row g-3 p-4">

                        <table class="table table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th>Data de compra</th>
                                    <th>Quant. Produtos</th>
                                    <th>Valor</th>
                                    <th>Status</th>
                                    <th>Data de entrega</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <tr>
                                    <td>00/00/0000</td>
                                    <td>00</td>
                                    <td>R$ 00,00</td>
                                    <td>Aprovado</td>
                                    <td>00/00/0000</td>
                                    <td>
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#detalhesCompra"><span class="material-icons">remove_red_eye</span></button>
                                    </td>
                                </tr>

                                <tr>
                                    <td>00/00/0000</td>
                                    <td>00</td>
                                    <td>R$ 00,00</td>
                                    <td>Aprovado</td>
                                    <td>00/00/0000</td>
                                    <td>
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#detalhesCompra"><span class="material-icons">remove_red_eye</span></button>
                                    </td>
                                </tr>

                                <tr>
                                    <td>00/00/0000</td>
                                    <td>00</td>
                                    <td>R$ 00,00</td>
                                    <td>Aprovado</td>
                                    <td>00/00/0000</td>
                                    <td>
                                        <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#detalhesCompra"><span class="material-icons">remove_red_eye</span></button>
                                    </td>
                                </tr>
                
                            </tbody>
                        </table>
                    </div>

                </form>
            </div>

            <hr>

            <!-- Modal Detalhes compra-->
            <div class="modal fade" id="detalhesCompra" tabindex="-1" aria-labelledby="detalhesCompraLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title fw-bold" id="detalhesCompraLabel">Detalhes compra</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body m-5">

                            <h6 class="fw-bold">00/00/0000</h6>
                            <h6>Status</h6>
                            <h6>Chegou no dia 00/00/0000</h6>

                            <!-- Produto -->
                            <div class="row border rounded p-3">
                                <div class="col-2">
                                    <div class="card produto mb-3">
                                        <img alt="produto" src="\emug\assets\img\canecas\caneca_porco.jpeg" class="p-2">
                                    </div>
                                </div>

                                <div class="col">
                                    <h6 class="text-muted">Categoria</h6>
                                    <h5>Nome produto</h5>
                                    <h6>Quantidade: 00</h6>
                                    <h6>Valor: R$ 00,00</h6>
                                </div>

                                <div class="col-2">
                                    <button type="button" class="btn btn-primary btn-sm">Solicitar troca</button>
                                </div>
                            </div>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Cancelar compra</button>
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Fechar</button>
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