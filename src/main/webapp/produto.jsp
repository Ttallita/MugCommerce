<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${produto.nome}</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="include/header.jsp" />

    <main class="container mt-3">
        <div class="row mt-4 bg-white rounded p-4 mb-3">
            <div class="col-md-5">
                <div class="card  mb-3">
                    <img alt="produto" class="p-2" src="${produto.imagem}">
                </div>
            </div>
            <div class="col-md-4">
                <div>
                    <h2 class="title">${produto.nome}</h2>

                    <dl class="row">
                        <dt class="col-sm-12 pt-3">Fabricante</dt>
                        <dd class="col-sm-12 ">${produto.fabricante.nome}</dd>

                        <dt class="col-sm-12 pt-3">Material</dt>
                        <dd class="col-sm-12">${produto.material}</dd>
                      
                        <dt class="col-sm-12 pt-3">Categorias</dt>
                        <dd class="col-sm-12">
                            <c:forEach var="categoria" items="${produto.categorias}">
                                <span class="badge text-bg-secondary">${categoria.nome}</span> 
                            </c:forEach>
                        </dd>
                      
                        <dt class="col-sm-12 pt-3">Descrição</dt>
                        <dd class="col-sm-12">${produto.descricao}</dd>
                      
                    </dl>
                </div>
            </div>
            <div class="col-md-3 border p-3">
                <div class="container">
                    <p class="text-muted">10 vendidos</p>
                    <div class="mb-3">
                        <h2>R$ ${produto.valorVenda}</h2>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="row g-2 m-2 align-items-center">
                            <div class="col-auto">
                                <label class="col-form-label">Quantidade:</label>
                            </div>
                            <div class="col-4">
                                <input class="form-control" type="number">
                            </div>
                            <div class="col-auto">
                                <span class="form-text">(10 disponíveis)</span>
                            </div>
                        </div>
                    </div>
                    <div class="d-grid gap-1 col-10 mx-auto mt-3">
                        <a type="button" href="cliente/finalizarCompra.jsp" class="btn btn-primary rounded-pill">Comprar</a>
                        <a type="button" href="cliente/carrinho.jsp" class="btn btn-outline-primary rounded-pill">Adicionar ao carrinho</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="include/footer.jsp"/>

</body>

<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>