<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro - Produto</title>
    <link rel="stylesheet" href='/emug/webjars/bootstrap/5.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href='/emug/webjars/material-design-icons/4.0.0/material-icons.css"/>'>
    <link rel="stylesheet" href='/emug/assets/css/style.css"/>'>
</head>

<body>
    <div class="container card w-75 mt-5 mb-5">

        <h3 class="text-center mt-5">Cadastro</h3>
        <form action="/emug/cadastroProduto" method="POST">
            <div class="row g-3 p-4">

                <div class="col-sm-12">
                    <label for="nomeProduto"><small>Nome</small></label>
                    <input type="text" class="form-control" id="nomeProduto" name="nomeProduto" value="">
                </div>
                
                <div class="col-sm-2">
                    <label for="valorCompra"><small>Valor de compra</small></label>
                    <input type="number" class="form-control" id="valorCompra" name="valorCompra" value="">
                </div>

                <div class="col-sm-2">
                    <label for="valorVenda"><small>Valor de venda</small></label>
                    <input type="number" class="form-control" id="valorVenda" name="valorVenda" value="">
                </div>

                <div class="col-sm-2">
                    <label for="limiteVenda"><small>Limite de venda</small></label>
                    <input type="number" class="form-control" id="limiteVenda" name="limiteVenda" value="">
                </div>

                <div class="col-sm-2">
                    <label for="quantEstoque"><small>Quant. estoque</small></label>
                    <input type="number" class="form-control" id="quantEstoque" name="quantEstoque" value="">
                </div>

                <div class="col-sm-4">
                    <label for="codBarras"><small>Cod. barras</small></label>
                    <input type="number" class="form-control" id="codBarras" name="codBarras" value="">
                </div>

                <div class="col-sm-6">
                    <label for="material"><small>Material</small></label>
                    <input type="text" class="form-control" id="material" name="material" value="">
                </div>

                <div class="col-sm-6">
                    <label for="fabricante"><small>Fabricante</small></label>
                    <input type="text" class="form-control" id="fabricante" name="fabricante" value="">
                </div>

                <div class="col-md-4">
                    <label for="grupoPrecificacao"><small>Grupo de precificação</small></label>
                    <select class="form-select" id="grupoPrecificacao" name="grupoPrecificacao">
                        <option value="">Selecione</option>
                        <option>Grupo A</option>
                        <option>Grupo B</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="status"><small>Status</small></label>
                    <select class="form-select" id="status" name="status">
                        <option value="">Selecione</option>
                        <option>Status A</option>
                        <option>Status B</option>
                    </select>
                </div>

                <div class="col-sm-12">
                    <label for="descricao"><small>Descrição</small></label>
                    <textarea class="form-control" id="descricao" name="descricao" rows="3"></textarea>
                </div>

                <div class="form-check">
                    <label class="form-check-label" for="isLimitado"><small>Limitado</small></label>
                    <input class="form-check-input" type="checkbox" id="isLimitado" name="isLimitado" value="">
                </div>

                <hr>
                <input type="hidden" name="operacao" value="salvar">
                <button class="w-100 btn btn-primary btn-lg" type="submit" name="botaoCadastro">Cadastrar</button>
            </div>
        </form>
    </div>
</body>

<script src="assets/js/geral.js"></script>
<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>'></script>

</html>