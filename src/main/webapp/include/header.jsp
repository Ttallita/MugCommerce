<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <div class="navbar bg-dark navbar-dark">
        <div class="container-fluid">
            <a href="<c:url value="/index.jsp" />">
                <img src="<c:url value="/assets/img/logo_branco.png" />" alt="logo" class="logo w-50">
            </a>

            <form class="d-flex" action="<c:url value="/pesquisa.html"/>">
                <input class="form-control me-2" type="search" aria-label="Search">
                <button class="btn btn-primary" type="submit">
                    <span class="material-icons">search</span>
                </button>
            </form>

            <c:choose>
                <c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'CLIENTE'}">
                    <div class="d-flex align-items-center gap-3">
                        <button class="btn btn-light" type="submit">
                            <a href="<c:url value="/cliente/perfil.html" />">
                                <span class="material-icons">account_circle</span>
                            </a>
                        </button>
                        <button class="btn btn-light" type="submit">
                            <a href="<c:url value="/cliente/carrinho.html"/> ">
                                <span class="material-icons">shopping_cart</span>
                            </a>
                        </button>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="d-flex align-items-center gap-3">
                        <button class="btn btn-light" type="submit">
                            <a href="<c:url value="/cadastroCliente.jsp" />">
                                Cadastre-se
                            </a>
                        </button>
                        <button class="btn btn-light" type="submit">
                            <a href="<c:url value="/login.jsp"/> ">
                                Fazer Login
                            </a>
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>

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
