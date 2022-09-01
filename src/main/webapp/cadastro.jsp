<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro</title>

    <link rel="stylesheet" href="webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="assets/css/style.css"/>
</head>

<body>
    <div class="container card w-50 mt-5 mb-5">
        <h3 class="text-center mt-5">Cadastro</h3>
        <form action="/emug/cadastro" method="POST">
            <div class="row g-3 p-4">
                <h5>Conta</h5>
                <div class="col-sm-6">
                    <label for="email"><small>Email</small></label>
                    <input type="email" class="form-control" id="email" name="email" value="">
                </div>

                <div class="col-sm-3">
                    <label for="senha"><small>Senha</small></label>
                    <input type="password" class="form-control" id="senha" name="senha" value="">
                </div>

                <div class="col-sm-3">
                    <label for="senhaConfirmacao"><small>Confirmação senha</small></label>
                    <input type="password" class="form-control" id="senhaConfirmacao" name="senhaConfirmacao" value="">
                </div>

                <hr>
                <h5>Dados pessoais</h5>

                <div class="col-sm-3">
                    <label for="nome"><small>Nome</small></label>
                    <input type="text" class="form-control" id="nome" name="nome" value="">
                </div>

                <div class="col-sm-6">
                    <label for="sobrenome"><small>Sobrenome</small></label>
                    <input type="text" class="form-control" id="sobrenome" name="sobrenome" value="">
                </div>

                <div class="col-sm-3">
                    <label for="cpf"><small>CPF</small></label>
                    <input type="text" class="form-control" id="cpf" name="cpf" value="">
                </div>

                <div class="col-md-4">
                    <label for="genero"><small>Gênero</small></label>
                    <select class="form-select" id="genero" name="genero">
                        <option value="">Selecione</option>
                        <option>Masculino</option>
                        <option>Feminino</option>
                        <option>Outro</option>
                        <option>Prefiro não informar</option>
                    </select>
                </div>

                <div class="col-sm-4">
                    <label for="dtNascimento"><small>Data de nascimento</small></label>
                    <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="">
                </div>

                <div class="col-sm-4">
                    <label for="telefone"><small>Telefone</small></label>
                    <input type="text" class="form-control" id="telefone" name="telefone" value="">
                </div>

                <hr>
                <h5>Endereço</h5>
                <div class="col-sm-4">
                    <label for="tpResidencia"><small>Tipo de residência</small></label>
                    <input type="text" class="form-control" id="tpResidencia" name="tpResidencia" value="">
                </div>

                <div class="col-sm-4">
                    <label for="tpLogradouro"><small>Tipo de logradouro</small></label>
                    <input type="text" class="form-control" id="tpLogradouro" name="tpLogradouro" value="">
                </div>

                <div class="col-sm-4">
                    <label for="logradouro"><small>Logradouro</small></label>
                    <input type="text" class="form-control" id="logradouro" name="logradouro" value="">
                </div>

                <div class="col-sm-6">
                    <label for="bairro"><small>Bairro</small></label>
                    <input type="text" class="form-control" id="bairro" name="bairro" value="">
                </div>

                <div class="col-sm-2">
                    <label for="numeroEndereco"><small>Número</small></label>
                    <input type="text" class="form-control" id="numeroEndereco" name="numeroEndereco" value="">
                </div>

                <div class="col-sm-4">
                    <label for="cep"><small>CEP</small></label>
                    <input type="text" class="form-control" id="cep" name="cep" value="">
                </div>

                <div class="col-md-4">
                    <label for="pais"><small>País</small></label>
                    <select class="form-select" id="pais" name="pais">
                        <option value="">Selecione</option>
                        <option>Brasil</option>
                        <option>Uruguai</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="estado"><small>Estado</small></label>
                    <select class="form-select" id="estado" name="estado">
                        <option value="">Selecione</option>
                        <option>São Paulo</option>
                        <option>Rio de Janeiro</option>
                        <option>Minas gerais</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="cidade"><small>Cidade</small></label>
                    <select class="form-select" id="cidade" name="cidade">
                        <option value="">Selecione</option>
                        <option>Cidade</option>
                        <option>Cidade</option>
                    </select>
                </div>

                <div class="col-sm-12">
                    <label for="apelidoEndereco"><small>Apelido endereço</small></label>
                    <input type="text" class="form-control" id="apelidoEndereco" name="apelidoEndereco" value="">
                </div>

                <div class="col-sm-12">
                    <label for="observacaoEndereco">
                        <small>Observação<small class="text-muted">(Opcional)</small></small>
                    </label>
                    <input type="text" class="form-control" id="observacaoEndereco" name="observacaoEndereco" value="">
                </div>
                <hr>
                <input type="hidden" name="operacao" value="salvar">
                <button class="w-100 btn btn-primary btn-lg" type="submit">Cadastrar</button>
            </div>
        </form>
    </div>
</body>
<script src="assets/js/geral.js"></script>
<script src='<c:url value="webjars/jquery/3.6.0/jquery.min.js"/>'></script>
<script src='<c:url value="webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script>
    aplicaMascaraTelefone()
</script>
</html>