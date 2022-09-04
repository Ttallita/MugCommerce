<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Clientes</title>

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
            <h5>Clientes</h5>

            <hr>

            <!-- Filtro de cliente-->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-12">
                            <small>Pesquisa</small>
                            <input type="text" class="form-control" id="pesquisa" name="pesquisa" value="">
                        </div>
                        <div class="col-sm-4">
                            <small>Gênero</small>
                            <select class="form-select" aria-label="Default select example">
                                <option selected>Selecione</option>
                                <option value="1">Masculino</option>
                                <option value="2">Feminino</option>
                                <option value="3">Outro</option>
                                <option value="4">Prefiro não informar</option>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <small>País</small>
                            <select class="form-select" id="pais" name="pais">
                                <option value="">Selecione</option>
                                <option>Brasil</option>
                                <option>Uruguai</option>
                            </select>
                        </div>
        
                        <div class="col-md-4">
                            <small>Estado</small>
                            <select class="form-select" id="estado" name="estado">
                                <option value="">Selecione</option>
                                <option>São Paulo</option>
                                <option>Rio de Janeiro</option>
                                <option>Minas gerais</option>
                            </select>
                        </div>

                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>

                    </div>

                </form>
            </div>

            <hr>

            <div class="table-responsive p-3 rounded mb-4">

                <table class="table table-hover" width="100%">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Gênero</th>
                            <th>Rank</th>
                            <th>Nascimento</th>
                            <th>Telefone</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
        
                        <tr>
                            <td>nome sobrenome</td>
                            <td>cpf</td>
                            <td>genero</td>
                            <td>ranking</td>
                            <td>dataNascimento</td>
                            <td>telefone.numero</td>
                            <td><span class="material-icons">edit</span></td>
                        </tr>

                        <tr>
                            <td>nome sobrenome</td>
                            <td>cpf</td>
                            <td>genero</td>
                            <td>ranking</td>
                            <td>dataNascimento</td>
                            <td>telefone.numero</td>
                            <td><span class="material-icons">edit</span></td>
                        </tr>
        
                    </tbody>
                </table>

                <a type="button" href="/emug/cadastroCliente.html" class="w-100 btn btn-primary btn-sm" >Adicionar cliente</a>

            </div>
            
        </div>
    </main>
    <br/>

    <jsp:include page="../include/footer.jsp"/>

    <script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</body>

</html>