<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang=pt-br>
    
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro</title>

    <link rel="stylesheet" href="/emug/webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/emug/webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="/emug/assets/css/style.css"/>
</head>

<body>

    <jsp:include page="../include/headerMinimalista.jsp"/>

    <div class="container align-items-center justify-content-center w-50 p-4">
        <div class="card p-3">
        
            <h3 class="text-center mb-4">Cadastro</h3>

            <form action="" method="POST">

                <div class="row g-3 mb-3">
                    
                    <div class="col-md-6">
                        <label for="numCartao"><small>Número do cartão</small></label>
                        <input type="text" class="form-control" id="numCartao" name="numCartao" value="">
                    </div>

                    <div class="col-md-6">
                        <label for="nomeCartao"><small>Nome impresso no cartão</small></label>
                        <input type="text" class="form-control" id="nomeCartao" name="nomeCartao" value="">
                    </div>

                    <div class="col-md-5">
                        <label><small>Bandeira</small></label>    
                        <select class="form-select" id="bandeira" name="bandeira">
                            <option value="">Selecione</option>
                            <option>Visa</option>
                            <option>Mastercard</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label for="nomeCartao"><small>Código</small></label>
                        <input type="text" class="form-control" id="codigoCartao" name="codigoCartao" value="">
                    </div>

                    <div class="col-md-4">
                        <label for="nomeCartao"><small>Data de Validade</small></label>
                        <input type="text" class="form-control" id="dtValidade" name="dtValidade" value="">
                    </div>

                    <div class="col-md-4">
                        <input class="form-check-input" type="checkbox" value="" id="nomeCartao">
                        <label for="nomeCartao" class="form-check-label"><small>Cartão preferencial</small></label>
                    </div>

                </div>

            </form>

        </div>
    </div>

    <jsp:include page="../include/footer.jsp"/>

</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.0/jquery.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    aplicaMascaraTelefone()
    $('#cpf').mask("000.000.000-00")
    $('.money').mask('000.000.000.000.000,00', {reverse: true});
</script>

</html>