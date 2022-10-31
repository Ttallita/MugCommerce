<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="paginaCorrente" value="${pageContext.request.requestURL}"/>

<div class="l-navbar ms-5 me-5">

    <p>Administrador</p>
    
    <nav class="l-nav">

        <div class="nav_list">
            <div class="list-group btn-group-vertical">

                <a class="nav_link ${ fn:contains(paginaCorrente, 'dashboard') ? 'active-navbar' : ''}" id="paginaPrincipal" type="button" href="/emug/gerenciar/dashboard.jsp">
                    <span class="material-icons inline-icon">home</span> Dashboard
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'clientes') ? 'active-navbar' : ''}" id="paginaClientes" type="button" href="/emug/adm/clientes?operacao=listarTodos">
                    <span class="material-icons inline-icon">people</span> Clientes
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'produtos') ? 'active-navbar' : ''}" id="paginaProdutos" type="button" href="/emug/adm/produtos?operacao=listar">
                    <span class="material-icons inline-icon">free_breakfast</span> Produtos
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'vendas') ? 'active-navbar' : ''}" id="paginaVendas" type="button" href="/emug/adm/vendas?operacao=listarTodos">
                    <span class="material-icons inline-icon">show_chart</span> Vendas
                </a>
                
                <small class="nav_link">
                    <span class="material-icons inline-icon">fact_check</span> Solicitações pendentes
                </small>
                <ul class="ms-4 list-unstyled">
                    <li>
                        <a class="nav_link ${ fn:contains(paginaCorrente, 'trocas') ? 'active-navbar' : ''}" id="paginaTrocas" type="button" href="/emug/adm/trocas?operacao=listarTodos">
                            <span class="material-icons inline-icon">compare_arrows</span> Trocas
                        </a>
                    </li>
                    <li>
                        <a class="nav_link ${ fn:contains(paginaCorrente, 'cancelamentos') ? 'active-navbar' : ''}" id="paginaCancelamentos" type="button" href="/emug/adm/cancelamentos?operacao=listarTodos">
                            <span class="material-icons inline-icon">cancel_presentation</span> Cancelamentos
                        </a>
                    </li>
                </ul>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'estoque') ? 'active-navbar' : ''}" id="paginaEstoque" type="button" href="/emug/adm/estoque?operacao=listar">
                    <span class="material-icons inline-icon">all_inbox</span> Estoque
                </a>

                <a class="nav_link" id="deslogar" href="/emug/logout">
                    <span class="material-icons inline-icon">logout</span>Sair
                </a>
                
            </div>
        </div>

    </nav>

</div>