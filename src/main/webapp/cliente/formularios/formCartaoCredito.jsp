<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=pt-br>
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro cartão</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>

<body>

    <jsp:include page="../../include/headerMinimalista.jsp"/>

    <div class="container align-items-center justify-content-center w-50 p-4">
        <div class="card p-3">

            <jsp:include page="../../include/alert.jsp"/>
            <h3 class="text-center mb-4">${isEditar ? 'Atualizar Dados' : 'Cadastro'}</h3>

            <form action="/emug/clientes/cartoes" method="POST">

                <div class="row g-3 mb-3">
                    
                    <div class="col-md-6">
                        <label for="numCartao"><small>Número do cartão</small></label>
                        <input type="text" class="form-control" id="numCartao" name="numCartao" value="${cartao.numCartao}">
                    </div>

                    <div class="col-md-6">
                        <label for="nomeCartao"><small>Nome impresso no cartão</small></label>
                        <input type="text" class="form-control" id="nomeCartao" name="nomeCartao" value="${cartao.nomeImpressoCartao}">
                    </div>

                    <div class="col-md-5">
                        <label for="bandeira"><small>Bandeira</small></label>
                        <select class="form-select" id="bandeira" name="bandeira">
                            <option value="">Selecione</option>
                            <option ${cartao.bandeira == 'Visa' ? 'selected' : ''}>Visa</option>
                            <option ${cartao.bandeira == 'Mastercard' ? 'selected' : ''}>Mastercard</option>
                            <option ${cartao.bandeira == 'Elo' ? 'selected' : ''}>Elo</option>
                            <option ${cartao.bandeira == 'American Express' ? 'selected' : ''}>American Express</option>
                            <option ${cartao.bandeira == 'Hipercard' ? 'selected' : ''}>Hipercard</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label for="codigoCartao"><small>Código</small></label>
                        <input type="text" class="form-control" id="codigoCartao" name="codigoCartao" value="${cartao.codigo}">
                    </div>

                    <div class="col-md-4">
                        <label for="dtValidade"><small>Data de Validade</small></label>
                        <input type="text" class="form-control" id="dtValidade" name="dtValidade" value="${cartao.mesValidade}${cartao.anoValidade}">
                    </div>

                    <div class="col-md-12">
                        <input class="form-check-input" type="${empty isMostrar || isMostrar ? 'checkbox' : 'hidden'}" name="preferencial" id="preferencial">
                        <c:if test="${empty isMostrar || isMostrar}">
                            <label for="preferencial" class="form-check-label"><small>Cartão preferencial(Se for o primeiro cartão cadastrado será automaticamente marcado como preferencial)</small></label>
                        </c:if>
                    </div>

                    <input type="hidden" name="id" value="${cartao.id}">
                    <input type="hidden" name="operacao" value="${isEditar ? 'atualizar' : 'salvar'}">
                    
                    <c:if test="${origemChamada == 'finalizarCompra'}">
                        <input type="hidden" name="origemChamada" value="${origemChamada}">
                        <input type="hidden" name="idEndereco" value="${idEndereco}">
                        <input type="hidden" name="idCartaoDeCredito" value="${idCartaoDeCredito}">
                    </c:if>

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
    aplicaMascaraTelefone()
    $('#cpf').mask("000.000.000-00")
    $('#numCartao').mask("0000 0000 0000 0000")
    $('#dtValidade').mask('00/0000')
</script>

</html>