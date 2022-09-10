<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="paginaCorrente" value="${pageContext.request.requestURL}"/>

<div class="l-navbar ms-5 me-5">

    <p>${cliente.nome} ${cliente.sobrenome}</p>

    <nav class="l-nav">
        <div class="nav_list">
            <div class="list-group btn-group-vertical">
                <a class="nav_link ${ fn:contains(paginaCorrente, 'perfil') ? 'active-navbar' : ''}" id="paginaPerfil" type="button" href="/emug/clientes?operacao=listar">
                    <span class="material-icons inline-icon">account_circle</span> Perfil
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'atualizarSenha') ? 'active-navbar' : ''}" id="paginaAtualizarSenha" type="button" href="/emug/cliente/atualizarSenha.jsp">
                    <span class="material-icons inline-icon">lock</span> Atualizar senha
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'compras') ? 'active-navbar' : ''}" id="paginaCompras" type="button" href="/emug/cliente/compras.jsp">
                    <span class="material-icons inline-icon">shopping_bag</span> Compras
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'enderecos') ? 'active-navbar' : ''}" id="paginaEnderecos" type="button" href="/emug/clientes/enderecos?operacao=listar">
                    <span class="material-icons inline-icon">local_shipping</span> Endereços
                </a>
                
                <a class="nav_link ${ fn:contains(paginaCorrente, 'cartoes') ? 'active-navbar' : ''}" id="paginaCartoes" type="button" href="/emug/clientes/cartoes?operacao=listar">
                    <span class="material-icons inline-icon">credit_card</span> Cartões
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'cupons') ? 'active-navbar' : ''}" id="paginaCupons" type="button" href="/emug/cliente/cupons.jsp">
                    <span class="material-icons inline-icon">local_play</span> Cupons
                </a>

                <a class="nav_link" id="" href="">
                    <span class="material-icons inline-icon">logout</span>Sair
                </a>

            </div>
        </div>
    </nav>
</div>