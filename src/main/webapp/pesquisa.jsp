<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>
<body>

    <jsp:include page="include/header.jsp" />

    <main class="wrapper bg-white rounded container mt-3 mb-3" style="min-height: 70vh">

        <c:if test="${pesquisa != ''}">
            <div class="row mt-3 ms-3 me-3">
                <div class="col-md-12 p-3 border rounded mt-3">
                    ${produtos.size()} resultados para <strong class="text-muted">"${pesquisa}"</strong>
                </div>
            </div>
        </c:if>

        <div class="row p-4 mb-3">
            <div class="col-xl-2 col-lg-4 col-md-5">
                <form id="formFilters">
                    <h5 class="fw-bolder">Categoria</h5>
                    <ul class="list-group mt-4">
                        <c:forEach var="categoria" items="${categorias}">
                            <li class="d-flex justify-content-between">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="${categoria.id}" name="categorias">
                                    <label class="form-check-label">
                                        ${categoria.nome}
                                    </label>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <br/>

                    <h5 class="fw-bolder">Fabricantes</h5>
                    <ul class="list-group mt-4">
                        <c:forEach var="fabricante" items="${fabricantes}">
                            <li class="d-flex justify-content-between">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="${fabricante.id}" name="fabricantes">
                                    <label class="form-check-label">
                                            ${fabricante.nome}
                                    </label>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <br/>

                    <h5 class="fw-bolder">Material</h5>
                    <ul class="list-group mt-4">
                        <li class="d-flex justify-content-between">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Porcelana"  name="material">
                                <label class="form-check-label">
                                    Porcelana
                                </label>
                            </div>
                        </li>
                        <li class="d-flex justify-content-between">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Porcelana"  name="material">
                                <label class="form-check-label">
                                    Porcelana
                                </label>
                            </div>
                        </li>
                        <li class="d-flex justify-content-between">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Plástico"  name="material">
                                <label class="form-check-label">
                                    Plástico
                                </label>
                            </div>
                        </li>
                        <li class="d-flex justify-content-between">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="Metal"  name="material">
                                <label class="form-check-label">
                                    Metal
                                </label>
                            </div>
                        </li>
                    </ul>
                    <br/>

                    <h5 class="fw-bolder">Preço</h5>
                    <ul class="list-group mt-4">
                        <li class="d-flex justify-content-between ">
                            <div class="input-group">
                                <input class="form-control" placeholder="R$ min" type="text" id="minPrice" name="minPrice">
                                <span class="input-group-text">-</span>
                                <input type="text" class="form-control" placeholder="R$ max" id="maxPrice" name="maxPrice">
                            </div>
                        </li>
                    </ul>
                    <br/>

                    <input type="submit" class="btn btn-primary block" value="Aplicar filtros">
                </form>
            </div>

            <div class="col-lg-10">
                <div class="row mb-4">
                    <div class="col-12">
                        <div class="btn-group float-end">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Ordenar por
                                </button>
                                <ul class="dropdown-menu">
                                    <li onclick="ordena(true)"><a class="dropdown-item" href="#">Maior preço</a></li>
                                    <li onclick="ordena(false)"><a class="dropdown-item" href="#">Menor preço</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>

                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div id="loading" style="display: none">
                                <div class="d-flex justify-content-center">
                                    <div class="spinner-border" role="status">
                                        <span class="visually-hidden">Loading...</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-cols-xl-4 g-4" id="divProdutos">
                        <!-- Lista produtos -->
                        <c:forEach var="produto" items="${produtos}">
                            <div class="col mb-3 ">
                                <div class="card produto">
                                    <img class="p-3" src="${produto.imagem}" alt="${produto.nome}">
                                    <div class="card-body pt-0">
                                        <a href="<c:url value='/produtos?operacao=listarUnico&id=${produto.id}'/>" class="fw-bolder nome-produto">${produto.nome}</a>
                                        <fmt:formatNumber value="${produto.valorVenda}" type="currency"/>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>

    </main>

    <jsp:include page="include/footer.jsp"/>

</body>
<script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>

<script>
    $('#minPrice').mask('000.000.000.000.000,00', {reverse: true});
    $('#maxPrice').mask('000.000.000.000.000,00', {reverse: true});

    let formatter = new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    });

    $( "#formFilters" ).submit(async function( event ) {
        event.preventDefault()

        $('#divProdutos').html("")
        $("#loading").show();

        let produtos = await getProdutos()

        let filters = getFilters();

        montaProdutos(produtos, filters)

        $("#loading").hide();
    })

    async function getProdutos() {
        const baseUrl = 'http://localhost:8080/emug/adm';
        let url = new URL(baseUrl + "/produtos")
        let params = { operacao: 'listarJson' }

        url.search = new URLSearchParams(params).toString()

        let response = await fetch(url)

        return await response.json()
    }

    function isFilter(produto, filters) {
        return (filters.categorias.length === 0 || produto.categorias.some(v => filters.categorias.includes(v)))
            && (filters.fabricantes.length === 0 || filters.fabricantes.includes(produto.fabricante.id))
            && (filters.materiais.length === 0 || filters.materiais.includes(produto.material))
            && produto.valorVenda > filters.minPrice
            && produto.valorVenda < filters.maxPrice
    }

    function montaProduto(produto) {
        let divInterna = document.createElement("div")
        divInterna.classList.add("col")
        divInterna.classList.add("mb-3")

        let card = document.createElement("div")
        card.classList.add("card")
        card.classList.add("produto")

        divInterna.appendChild(card)

        let imagem = $('<img>')
        imagem.attr("alt", produto.nome)
        imagem.prop("src", produto.imagem)

        card.appendChild(imagem[0])

        let cardBody = document.createElement("div")
        card.classList.add("card-body")
        card.classList.add("pt-0")

        card.appendChild(cardBody)

        let a = document.createElement("a")
        a.href = "/emug/produtos?operacao=listarUnico&id=" + produto.id
        a.classList.add("fw-bolder")
        a.classList.add("nome-produto")
        a.innerHTML = produto.nome

        cardBody.appendChild(a)

        cardBody.innerHTML += formatter.format(produto.valorVenda)

        document.getElementById('divProdutos').appendChild(divInterna)
    }

    function getFilters() {
        let categorias = []
        let fabricantes = []
        let materiais = []

        $("input:checkbox[name=categorias]:checked").each(function() { categorias.push($(this).val()) })
        $("input:checkbox[name=fabricantes]:checked").each(function () { fabricantes.push($(this).val()) })
        $("input:checkbox[name=material]:checked").each(function() { materiais.push($(this).val()) })

        let minPrice = $('#minPrice').val()
        let maxPrice = $('#maxPrice').val()

        return {
            categorias,
            fabricantes,
            materiais,
            minPrice: minPrice === '' ? 0 : parseFloat(minPrice.replaceAll(".", "").replace(",", ".")),
            maxPrice: maxPrice === '' ? 1000000000 : parseFloat(maxPrice.replaceAll(".", "").replace(",", "."))
        }
    }

    function montaProdutos(produtos, filters) {
        produtos.forEach(produto => {
            if(isFilter(produto, filters)) {
                montaProduto(produto)
            }
        })
    }

    async function ordena(isMaior) {
        $('#divProdutos').html("")
        $("#loading").show();

        let produtos = await getProdutos()

        let filters = getFilters();

        produtos.sort((a, b) => isMaior ? b.valorVenda - a.valorVenda : a.valorVenda - b.valorVenda)

        montaProdutos(produtos, filters)

        $("#loading").hide();
    }


</script>
</html>