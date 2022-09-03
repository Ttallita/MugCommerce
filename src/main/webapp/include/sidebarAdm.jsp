<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="paginaCorrente" value="${pageContext.request.requestURL}"/>

<div class="l-navbar ms-5 me-5">

    <p>Administrador</p>
    
    <nav class="l-nav">

        <div class="nav_list">
            <div class="list-group btn-group-vertical">

                <a class="nav_link ${ fn:contains(paginaCorrente, 'dashboard') ? 'active-navbar' : ''}" id="paginaPrincipal" type="button" href="/emug/gerenciar/dashboard.html">
                    <span class="material-icons inline-icon">home</span> Dashboard
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'clientes') ? 'active-navbar' : ''} active-navbar" id="paginaClientes" type="button" href="/emug/gerenciar/clientes.html">
                    <span class="material-icons inline-icon">people</span> Clientes
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'vendas') ? 'active-navbar' : ''}" id="paginaVendas" type="button" href="/emug/gerenciar/vendas.html">
                    <span class="material-icons inline-icon">show_chart</span> Vendas
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'estoque') ? 'active-navbar' : ''}" id="paginaEstoque" type="button" href="/emug/gerenciar/estoque.html">
                    <span class="material-icons inline-icon">all_inbox</span> Estoque
                </a>

                <a href="#" class="nav_link">
                    <span class="material-icons inline-icon">logout</span>Sair
                </a>
                
            </div>
        </div>

    </nav>

</div>