<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="paginaCorrente" value="${pageContext.request.requestURL}"/>

<div class="ms-5 me-5">

    <p>${cliente.nome} ${cliente.sobrenome}</p>

    <nav class="l-nav">
        <div class="nav_list">
            <div class="list-group btn-group-vertical">
                <a class="nav_link ${ fn:contains(paginaCorrente, 'perfil') ? 'active-navbar' : ''}" id="paginaPerfil" type="button" href="/emug/clientes?operacao=listarUnico">
                    <span class="material-icons inline-icon">account_circle</span> Perfil
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'atualizarSenha') ? 'active-navbar' : ''}" id="paginaAtualizarSenha" type="button" href="/emug/cliente/atualizarSenha.jsp">
                    <span class="material-icons inline-icon">lock</span> Atualizar senha
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'compras') ? 'active-navbar' : ''}" id="paginaCompras" type="button" href="/emug/clientes/compras?operacao=listar">
                    <span class="material-icons inline-icon">shopping_bag</span> Compras
                </a>

                <small class="nav_link text-muted">
                    <span class="material-icons inline-icon">dynamic_feed</span> Solicitações
                </small>
                <ul class="ms-4 list-unstyled">
                    <li>
                        <a class="nav_link ${ fn:contains(paginaCorrente, 'trocas') ? 'active-navbar' : ''}" id="paginaTrocas" type="button" href="/emug/clientes/trocas?operacao=listar">
                            <span class="material-icons inline-icon">compare_arrows</span> Trocas
                        </a>
                    </li>
                    <li>
                        <a class="nav_link ${ fn:contains(paginaCorrente, 'cancelamentos') ? 'active-navbar' : ''}" id="paginaCancelamentos" type="button" href="/emug/clientes/cancelamentos?operacao=listar">
                            <span class="material-icons inline-icon">cancel_presentation</span> Cancelamentos
                        </a>
                    </li>
                </ul>


                <a class="nav_link ${ fn:contains(paginaCorrente, 'enderecos') ? 'active-navbar' : ''}" id="paginaEnderecos" type="button" href="/emug/clientes/enderecos?operacao=listar">
                    <span class="material-icons inline-icon">local_shipping</span> Endereços
                </a>
                
                <a class="nav_link ${ fn:contains(paginaCorrente, 'cartoes') ? 'active-navbar' : ''}" id="paginaCartoes" type="button" href="/emug/clientes/cartoes?operacao=listar">
                    <span class="material-icons inline-icon">credit_card</span> Cartões
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'cupons') ? 'active-navbar' : ''}" id="paginaCupons" type="button" href="/emug/clientes/cupons?operacao=listarTodos">
                    <span class="material-icons inline-icon">local_play</span> Cupons
                </a>

                <a class="nav_link ${ fn:contains(paginaCorrente, 'desativarConta') ? 'active-navbar' : ''}" id="paginaDesativar" type="button" href="/emug/cliente/desativarConta.jsp">
                    <span class="material-icons inline-icon">highlight_off</span> Desativar Conta
                </a>

                <a class="nav_link" id="deslogar" href="/emug/logout">
                    <span class="material-icons inline-icon">logout</span>Sair
                </a>

            </div>
        </div>
    </nav>
</div>