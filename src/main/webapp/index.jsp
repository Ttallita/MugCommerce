<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>
<body>
    <jsp:include page="/include/header.jsp" />

    <!-- banner-->
    <div class="container-fluid">
        <div class="banner-home row justify-content-center">

            <div class="col-5 align-self-center">
                <h1 class="mb-5">Canecas personalizadas</h1>
                <p>
                    Escolha um dos nossos modelos prontos de canecas personalizadas com estampas especiais para diversos gostos.
                </p>
            </div>

            <div class="col-3 align-self-center text-center">
                <img src="\emug\assets\img\canecas\caneca_banner.png" class="img-fluid" alt="img_banner">
            </div>

        </div>
    </div>

    <!--Lançamentos-->
    <div class="container-sm p-5">
        <h4>Lançamentos</h4>
        <div class="row row-cols-xl-5 gap-5" id="lancamentos">
            <!-- Lista produtos -->
        </div>

    </div>

    <jsp:include page="include/footer.jsp"/>

</body>

<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>

<script>

    async function listaProdutos(url, id) {
        let response = await fetch(url)
        const json = await response.json();

        json.forEach(produto => {

            var nome = $('<em />', { 
                text: produto.nome
            });

            var valorCompra = $('<p />', {
                text: produto.valorCompra
            });

            var image = $('<img />', { 
                class: 'img-fluid p-4' ,
                src: produto.imagem
            });

            var divProduto = $( '<produto>', {
                class: 'col card text-center'
            });

            image.appendTo(divProduto);
            nome.appendTo(divProduto);
            valorCompra.appendTo(divProduto);

            divProduto.appendTo(document.getElementById("lancamentos"));
        })
    }

    $(document).ready(() => {
        const baseUrl = 'http://localhost:8080/emug/index';
        let params = { operacao: 'listarIndex'}

        let urlProdutos = montaUrl(baseUrl, 'produtos', params)

        listaProdutos(urlProdutos, '#produtos')
    })

</script>

</html>