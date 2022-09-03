<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Cartões</title>

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
            <h5>Endereços</h5>

            <hr>

            <div class="container">

                <div class="table-responsive p-3 rounded mb-4">

                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Bandeira</th>
                                <th>Final cartão</th>
                                <th>Nome no cartão</th>
                                <th>Vencimento</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>Bandeira</td>
                                <td>0000</td>
                                <td>Nome no cartão</td>
                                <td>00/0000</td>
                                <td>
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
    
                            <tr>
                                <td>Bandeira</td>
                                <td>0000</td>
                                <td>Nome no cartão</td>
                                <td>00/0000</td>
                                <td>
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
    
                    <button type="button" class="w-100 btn btn-primary btn-sm">Adicionar cartão</button>
                </div>
            </div>

        </div>
    </main>

    <br/>
    <footer class="bg-dark text-center text-white">
        &copy;Todos direitos reservados André e Tallita
    </footer>
    
    <script src="../webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
</body>
</html>