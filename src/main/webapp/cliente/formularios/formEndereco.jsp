<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=pt-br>
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro endereço</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>

<body>

    <jsp:include page="../../include/headerMinimalista.jsp"/>

    <div class="container align-items-center justify-content-center w-50 p-4">
        <div class="card p-3">

            <jsp:include page="../../include/alert.jsp" />
        
            <h3 class="text-center mb-4">
                ${isEditar ? 'Editar' : 'Cadastro'}
            </h3>

            <form action="/emug/clientes/enderecos" method="POST">

                <div class="row g-3">

                    <c:if test="${!isEditar}">
                        <h6>Insira os dados do novo endereço</h6>
                    </c:if>


                    <c:if test="${isEditar}">
                        <h6>Atualize os dados do endereço</h6>
                    </c:if>

                    <div class="col-sm-4">
                        <label for="tpResidencia"><small>Tipo de residência</small></label>
                        <input type="text" class="form-control" id="tpResidencia" name="tpResidencia" value="${endereco.tipoResidencia}">
                    </div>

                    <div class="col-sm-4">
                        <label for="tpLogradouro"><small>Tipo de logradouro</small></label>
                        <input type="text" class="form-control" id="tpLogradouro" name="tpLogradouro" value="${endereco.tipoLogradouro}">
                    </div>

                    <div class="col-sm-4">
                        <label for="tpEndereco"><small>Tipo de Endereço</small></label>
                        <select class="form-select" id="tpEndereco" name="tpEndereco">
                            <option value="">Selecione</option>
                            <option ${endereco.tipoEndereco == 'ENTREGA' ? 'selected' : ''} value="ENTREGA">Entrega</option>
                            <option ${endereco.tipoEndereco == 'COBRANCA' ? 'selected' : ''} value="COBRANCA">Cobrança</option>
                            <option ${endereco.tipoEndereco == 'COBRANCA_ENTREGA' ? 'selected' : ''} value="COBRANCA_ENTREGA">Entrega e cobrança</option>
                        </select>
                    </div>

                    <div class="col-sm-12">
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

                    <div class="col-md-6">
                        <label for="estado"><small>Estado</small></label>
                        <input type="hidden" value="${endereco.estado}" id="estadoAtual">
                        <select class="form-select" id="estado" name="estado">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-md-6">
                        <label for="cidade"><small>Cidade</small></label>
                        <input type="hidden" value="${endereco.cidade}" id="cidadeAtual">
                        <select class="form-select" id="cidade" name="cidade">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-sm-12">
                        <label for="apelidoEndereco"><small>Apelido endereço</small></label>
                        <input type="text" class="form-control" id="apelidoEndereco" name="apelidoEndereco" value="${endereco.apelido}">
                    </div>

                    <div class="col-sm-12">
                        <label for="observacaoEndereco">
                            <small>Observação<small class="text-muted">(Opcional)</small></small>
                        </label>
                        <input type="text" class="form-control" id="observacaoEndereco" name="observacaoEndereco" value="${endereco.observacoes}">
                    </div>

                    <input type="hidden" name="id" value="${endereco.id}">
                    <input type="hidden" name="operacao" value="${isEditar ? 'atualizar' : 'salvar'}">
                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="botaoCadastro">
                        ${isEditar ? 'Atualizar' : 'Cadastrar'}
                    </button>
                </div>
            </form>

        </div>
    </div>

    <jsp:include page="../../include/footer.jsp"/>

</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    preencheSelectEstado()
    aplicaMascaraTelefone()
    $('#cpf').mask("000.000.000-00")
    $('#cep').mask("00000-000")
</script>

</html>