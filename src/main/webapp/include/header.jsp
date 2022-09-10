<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header name="${sessionScope.usuarioLogado.tipoUsuario}">
    <div class="navbar bg-dark navbar-dark">
        <div class="container">
            <a href="<c:url value="/index.jsp" />">
                <img src="<c:url value="/assets/img/logo_branco.png" />" alt="logo" class="logo w-50">
            </a>

            <div class="col-6">
                <form action="<c:url value="/pesquisa.html"/>">
                    <div class="input-group">
                        <input class="form-control" type="search" aria-label="Search">
                        <button class="btn btn-primary" type="submit">
                            <span class="material-icons">search</span>
                        </button>
                    </div>
                </form>
            </div>


            <c:choose>
                <c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'CLIENTE'}">
                    <div class="d-flex align-items-center gap-3">
                        <a href="<c:url value="/clientes?operacao=listar" />">
                            <button class="btn btn-light pr-2" type="button">
                                 <span class="material-icons">account_circle</span>
                            </button>
                        </a>
                        <a href="<c:url value="/cliente/carrinho.html"/> ">
                            <button class="btn btn-light" type="button">
                                <span class="material-icons">shopping_cart</span>
                            </button>
                        </a>
                    </div>
                </c:when>
                <c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'ADMINISTRADOR'}">
                    <!-- TODO criar menus de administrador -->
                </c:when>
                <c:otherwise>
                    <div class="d-flex align-items-center gap-3">

                        <a href="<c:url value="/cadastroCliente.jsp" />">
                            <button class="btn btn-light" type="submit">
                                Cadastre-se
                            </button>
                        </a>
                        <a href="<c:url value="/login.jsp"/> ">
                            <button class="btn btn-light" type="submit">
                                Fazer Login
                            </button>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg bg-dark navbar-dark ">
        <div class="container">

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
