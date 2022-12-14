<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header name="${sessionScope.usuarioLogado.tipoUsuario}" class="d-flex justify-content-md-between py-3 bg-dark navbar-dark">
    <div class="container-fluid">
        <div class="row align-items-center position-relative">
            <div class="col-lg-5">
                <ul class="nav me-lg-auto mb-2 mb-md-0">
                    <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">Produtos</a></li>
                </ul>
            </div>
            <div class="col-2 text-center">
                <a href="<c:url value='/index.jsp' />" class="d-flex align-items-center justify-content-center">
                    <img src="<c:url value='/assets/img/logo_preto_branco.png' />" alt="logo" class="logo">
                </a>
            </div>
            <div class="col-lg-5">
                <div class="container row text-center gap-3">
                    <div class="col-sm-6 col-md-7">
                        <form action="<c:url value='/produtos'/>" id="pesquisa" class="col-md-auto justify-content-center mb-md-0">
                            <div class="input-group">
                                <input class="form-control" type="text" name="pesquisa" placeholder="Pesquisar...">
                                <button class="btn btn-primary" type="submit">
                                    <span class="material-icons">search</span>
                                </button>
                            </div>

                            <input type="hidden" name="operacao" value="pesquisar">
                        </form>
                    </div>

                    <c:choose>
                        <c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'CLIENTE'}">
                            
                            <div class="col-md-1">
                                <a href="<c:url value='/clientes?operacao=listarUnico' />" class="btn btn-light pr-2" type="button">
                                    <span class="material-icons">account_circle</span>
                                </a>
                            </div>
                            <div class="col-md-1">
                                <a href="<c:url value='/cliente/carrinho.jsp' />" class="btn btn-light" type="button">
                                    <span class="material-icons">shopping_cart</span>
                                </a>
                            </div>
                            <div class="col-md-1">
                                <a href="<c:url value='/logout'/>" class="btn btn-light" type="button">
                                    <span class="material-icons inline-icon">logout</span>
                                </a>
                            </div>
                        </c:when>
                        <c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'ADMINISTRADOR'}">
                            <div class="col-md-1">
                                <a href="<c:url value='/gerenciar/dashboard.jsp' />" class="btn btn-light">
                                    <span class="material-icons">dashboard</span>
                                </a>
                            </div>

                            <div class="col-md-1">
                                <a href="<c:url value='/logout'/>" class="btn btn-light">
                                    <span class="material-icons inline-icon">logout</span>
                                </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-md-2 pt-1">
                                <a href="<c:url value='/login.jsp'/> " type="button" class="btn btn-light">
                                    Entrar
                                </a>
                            </div>
                            <div class="col-md-2 pt-1">
                                <a href="<c:url value='/cadastroCliente.jsp' />" type="button" class="btn btn-light">
                                    Registrar
                                </a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
        </div>
    </div>
</header>