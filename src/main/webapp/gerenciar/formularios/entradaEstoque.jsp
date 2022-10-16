<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Entrada de estoque</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>
<body>

<jsp:include page="../../include/headerMinimalista.jsp"/>

<div class="container align-items-center justify-content-center w-50 p-4">
    <jsp:include page="../../include/alert.jsp" />
    <div class="card p-3">

        <h3 class="text-center mb-4">Entrada de Estoque</h3>

        <form action="/emug/adm/estoqueEntrada" enctype="multipart/form-data" method="POST">
            <div class="row g-3 p-4">

                <div class="col-sm-12">
                    <label for="produto" class="form-label">
                        <small>Produto</small>
                    </label>
                    <select class="form-select" id="produto" name="produto">
                        <option value="">Selecione</option>
                    </select>
                </div>

                <div class="col-sm-2">
                    <label for="quantidade">
                        <small>Quantidade</small>
                    </label>
                    <input type="number" min="1" class="form-control" id="quantidade" name="quantidade" value="${entrada.quantidade}">
                </div>

                <div class="col-sm-2">
                    <label for="valorCusto"><small>Valor de custo</small></label>
                    <input type="text" class="form-control money" id="valorCusto" name="valorCusto" value="${entrada.valorCusto}">
                </div>

                <div class="col-sm-4">
                    <label for="fornecedor"><small>Fornecedor</small></label>
                    <select class="form-select" id="fornecedor" name="fornecedor">
                        <option value="">Selecione</option>
                        <option ${entrada.fornecedor.nome == 'Amazon' ? 'selected' : ''} value="Amazon">Amazon</option>
                        <option ${entrada.fornecedor.nome == 'Submarino' ? 'selected' : ''} value="Submarino">Submarino</option>
                        <option ${entrada.fornecedor.nome == 'Mercado Livre' ? 'selected' : ''} value="Mercado Livre">Mercado Livre</option>
                    </select>
                </div>

                <hr>
                <input type="hidden" name="operacao" value="salvar">
                <input type="submit" class="w-100 btn btn-primary" name="botaoEntrada" value="Realizar entrada de estoque">
            </div>
        </form>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script>
    $('#valorCusto').mask('000.000.000.000.000,00', {reverse: true});

    $(document).ready(async () => {
        const baseUrl = 'http://localhost:8080/emug/adm';
        let params = { operacao: 'listarJson' }

        let url = new URL(baseUrl + "/produtos")
        url.search = new URLSearchParams(params).toString()

        const response = await fetch(url)
        const json = await response.json()

        json.forEach(produto => montaOptionsProduto(produto))
    })

    function montaOptionsProduto(produto) {
        let option = document.createElement("option")
        option.value = produto.id
        option.text = produto.nome

        if(option.value == '${entrada.estoque.produto.id}')
            option.selected = true

        $('#produto').append(option)
    }
</script>
</html>