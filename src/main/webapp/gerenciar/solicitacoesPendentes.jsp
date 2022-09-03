<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Vendas</title>

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

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Vendas</h5>

            <hr>

            <!-- Filtro de vendas-->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-4">
                            <small>Status</small>
                            <select class="form-select" aria-label="Default select example">
                                <option value="">Selecione</option>
                                <option value="">Aprovado</option>
                                <option value="">Entregue</option>
                                <option value="">Em processamento</option>
                                <option value="">Troca solicitada</option>
                            </select>
                        </div>

                        <div class="col-sm-4">
                            <small>Data de compra</small>
                            <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="">
                        </div>

                        <div class="col-sm-4">
                            <small>Data de entrega</small>
                            <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="">
                        </div>
                        
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>

                    </div>

                </form>
            </div>


            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="pills-gerenciar-vendas-tab" data-bs-toggle="pill" data-bs-target="#pills-gerenciar-vendas" type="button" role="tab" aria-controls="pills-gerenciar-vendas" aria-selected="true">Gerenciar vendas</button>
                </li>

                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="pills-avancado-tab" data-bs-toggle="pill" data-bs-target="#pills-avancado" type="button" role="tab" aria-controls="pills-avancado" aria-selected="false">Avançado</button>
                </li>

            </ul>


            <div class="tab-content" id="pills-tabContent">

                <div class="tab-pane fade show active" id="pills-gerenciar-vendas" role="tabpanel" aria-labelledby="pills-gerenciar-vendas-tab" tabindex="0">
                    
                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Quant. Produtos</th>
                                <th>Valor</th>
                                <th>Data compra</th>
                                <th>Data entrega</th>
                                <th>Status</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Aprovado
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary btn-sm"><span class="material-icons">done</span></button>
                                    <button type="button" class="btn btn-primary btn-sm"><span class="material-icons">clear</span></button>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane fade" id="pills-avancado" role="tabpanel" aria-labelledby="pills-avancado-tab" tabindex="0">
                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Quant. Produtos</th>
                                <th>Valor</th>
                                <th>Data compra</th>
                                <th>Data entrega</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Aprovado
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Entregue
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
                </div>

            </div>
              
            

            <hr>

            
        </div>
    </main>

    <br/>

    <footer class="bg-dark text-center text-white">
        &copy;Todos direitos reservados André e Tallita
    </footer>

    <script src="/emug/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</body>

</html>