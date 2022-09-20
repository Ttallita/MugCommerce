<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro - Cliente</title>

    <link rel="stylesheet" href="webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="assets/css/style.css"/>
</head>

<body>

    <jsp:include page="/include/header.jsp" />

    <div class="container card w-50 mt-5 mb-5">
        <jsp:include page="include/alert.jsp" />
        <h3 class="text-center mt-5">Cadastro</h3>
        <form action="/emug/cadastro" method="POST">
            <div class="row g-3 p-4">
                <h5>Conta</h5>
                <div class="col-sm-12">
                    <label for="email"><small>Email</small></label>
                    <input type="email" class="form-control" id="email" name="email" value="${cliente.usuario.email}">
                </div>

                <div class="col-sm-6">
                    <label for="senha"><small>Senha</small></label>
                    <input type="password" class="form-control" id="senha" name="senha" value="${cliente.usuario.senha}">
                    <div id="senhaHelp" class="form-text">A senha deve conter pelo menos 8 caracteres, incluir caracteres maiúsculos, minúsculos e especiais.</div>
                </div>

                <div class="col-sm-6">
                    <label for="senhaConfirmacao"><small>Confirmação senha</small></label>
                    <input type="password" class="form-control" id="senhaConfirmacao" name="senhaConfirmacao" value="${cliente.usuario.confirmarSenha}">
                </div>

                <hr>
                <h5>Dados pessoais</h5>

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
                    <input type="text" class="form-control" id="telefone" name="telefone" value="${cliente.telefone.ddd}${cliente.telefone.numero}">
                </div>

                <hr>
                <h5>Endereço</h5>
                <div class="col-sm-4">
                    <label for="tpResidencia"><small>Tipo de residência</small></label>
                    <input type="text" class="form-control" id="tpResidencia" name="tpResidencia" value="${endereco.tipoResidencia}">
                </div>

                <div class="col-sm-4">
                    <label for="tpLogradouro"><small>Tipo de logradouro</small></label>
                    <input type="text" class="form-control" id="tpLogradouro" name="tpLogradouro" value="${endereco.tipoLogradouro}">
                </div>

                <div class="col-sm-4">
                    <label for="logradouro"><small>Logradouro</small></label>
                    <input type="text" class="form-control" id="logradouro" name="logradouro" value="${endereco.logradouro}">
                </div>

                <div class="col-sm-6">
                    <label for="bairro"><small>Bairro</small></label>
                    <input type="text" class="form-control" id="bairro" name="bairro" value="${endereco.bairro}">
                </div>

                <div class="col-sm-2">
                    <label for="numeroEndereco"><small>Número</small></label>
                    <input type="text" class="form-control" id="numeroEndereco" name="numeroEndereco" value="${endereco.numero}">
                </div>

                <div class="col-sm-4">
                    <label for="cep"><small>CEP</small></label>
                    <input type="text" class="form-control" id="cep" name="cep" value="${endereco.cep}">
                </div>

                <div class="col-md-4">
                    <label for="estado"><small>Estado</small></label>
                    <input type="hidden" value="${endereco.estado}" id="estadoAtual">
                    <select class="form-select" id="estado" name="estado">
                        <option value="">Selecione</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="cidade"><small>Cidade</small></label>
                    <input type="hidden" value="${endereco.cidade}" id="cidadeAtual">
                    <select class="form-select" id="cidade" name="cidade" disabled >
                        <option value="">Selecione</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label for="apelidoEndereco"><small>Apelido endereço</small></label>
                    <input type="text" class="form-control" id="apelidoEndereco" name="apelidoEndereco" value="${endereco.apelido}">
                </div>

                <div class="col-sm-12">
                    <label for="observacaoEndereco">
                        <small>Observação<small class="text-muted">(Opcional)</small></small>
                    </label>
                    <input type="text" class="form-control" id="observacaoEndereco" name="observacaoEndereco" value="${endereco.observacoes}">
                </div>

                <hr>
                <input type="hidden" name="operacao" value="salvar">
                <button class="w-100 btn btn-primary btn-lg" type="submit" name="botaoCadastro">Cadastrar</button>
            </div>
        </form>
        <div class="d-flex justify-content-center">
            <small>
                Já possui uma conta? <a href="login.jsp">Entre</a>
            </small>
        </div>
        <br/>
    </div>

    <jsp:include page="include/footer.jsp"/>
    
</body>

<script src='<c:url value="webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="webjars/jquery/3.6.0/jquery.min.js"/>'></script>
<script src='<c:url value="webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src="assets/js/geral.js"></script>
<script>
    preencheSelectEstado()
    aplicaMascaraTelefone()
    $('#cpf').mask("000.000.000-00")
    $('#cep').mask("00000-000")
</script>

</html>