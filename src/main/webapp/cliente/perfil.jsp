<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil</title>

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>"/>
</head>

<body>
    <jsp:include page="../include/header.jsp" />
    <main class="d-flex flex-nowrap mt-3">
        <!--Sidebar-->
        <div class="l-navbar ms-5 me-5">
            <p>${cliente.nome}  ${cliente.sobrenome}</p>
            <nav class="l-nav">
                <div class="nav_list">
                    <div class="list-group btn-group-vertical">
                        <a class="nav_link active-navbar" id="paginaAtualizar" type="button" href="/emug/cliente/perfil.html">
                            <span class="material-icons inline-icon">account_circle</span> Perfil
                        </a>

                        <a class="nav_link" id="paginaAtualizar" type="button" href="/emug/cliente/atualizarSenha.html">
                            <span class="material-icons inline-icon">lock</span> Atualizar senha
                        </a>

                        <a class="nav_link" id="paginaEstoque" type="button" href="/emug/cliente/compras.html">
                            <span class="material-icons inline-icon">shopping_bag</span> Compras
                        </a>

                        <a class="nav_link" id="paginaClientes" type="button" href="/emug/cliente/enderecos.html">
                            <span class="material-icons inline-icon">local_shipping</span> Endereços
                        </a>
                        
                        <a class="nav_link" id="paginaVendas" type="button" href="/emug/cliente/cartoes.html">
                            <span class="material-icons inline-icon">credit_card</span> Cartões
                        </a>

                        <a class="nav_link" id="paginaCupons" type="button" href="/emug/cliente/cupons.html">
                            <span class="material-icons inline-icon">local_play</span> Cupons
                        </a>

                        <a class="nav_link" id="" href="">
                            <span class="material-icons inline-icon">logout</span>Sair
                        </a>
                    </div>
                </div>
            </nav>
        </div>

        <div class="w-75 bg-white rounded p-5">
            <h5>Perfil</h5>
            <hr>
            <div class="container">
                <jsp:include page="../include/alert.jsp" />
                <!-- Editar cadastro cliente -->
                <form action="/emug/clientes" method="POST">
                    <div class="row g-3 p-4">

                        <div class="col-sm-3">
                            <label for="nome"><small>Nome</small></label>
                            <input type="text" class="form-control" id="nome" name="nome" value="${cliente.nome}">
                        </div>
        
                        <div class="col-sm-6">
                            <label for="sobrenome"><small>Sobrenome</small></label>
                            <input type="text" class="form-control" id="sobrenome" name="sobrenome" value="${cliente.sobrenome}">
                        </div>
        
                        <div class="col-sm-3">
                            <label for="cpf"><small>CPF</small></label>
                            <input type="text" class="form-control" id="cpf" name="cpf" value="${cliente.cpf}">
                        </div>
        
                        <div class="col-md-4">
                            <label for="genero"><small>Gênero</small></label>
                            <select class="form-select" id="genero" name="genero">
                                <option value="">Selecione</option>
                                <option ${cliente.genero == 'Masculino' ? 'selected' : ''}>Masculino</option>
                                <option ${cliente.genero == 'Feminino' ? 'selected' : ''}>Feminino</option>
                                <option ${cliente.genero == 'Outro' ? 'selected' : ''}>Outro</option>
                                <option ${cliente.genero == 'Prefiro não informar' ? 'selected' : ''}>Prefiro não informar</option>
                            </select>
                        </div>
        
                        <div class="col-sm-4">
                            <label for="dtNascimento"><small>Data de nascimento</small></label>
                            <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="${cliente.dataNascimento}">
                        </div>
        
                        <div class="col-sm-4">
                            <label for="telefone"><small>Telefone</small></label>
                            <input type="text" class="form-control" id="telefone" name="telefone" value="${cliente.telefone.ddd} ${cliente.telefone.numero}">
                        </div>
        
                        <hr>
                        <input type="hidden" name="operacao" value="atualizar">
                        <input type="submit" class="w-100 btn btn-primary btn-sm" value="Atualizar"/>
                    </div>
                </form>
            </div>
            <hr>
        </div>
    </main>
    <br/>
    <footer class="bg-dark text-center text-white">
        &copy;Todos direitos reservados André e Tallita
    </footer>
</body>
<script src="<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>"></script>
<script src="<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>"></script>
<script src="<c:url value="../assets/js/geral.js"/>"></script>
<script>
    aplicaMascaraTelefone()
    $('#cpf').mask("000.000.000-00")
</script>
</html>