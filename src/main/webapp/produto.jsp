<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                    <!-- <p class="text-muted">10 vendidos</p> -->
                    <div class="mb-3">
                        <h2><fmt:formatNumber value="${produto.valorVenda}" type="currency"/></h2>
                    </div>

                    <hr>
                    <div class="row">
                        <div class="row g-2 m-2 align-items-center">
                            <div class="col-auto">
                                <span class="form-text">
                                    ${quantidadeDisponivel} disponíveis
                                </span>
                            </div>
                        </div>
                    </div>

                    <form action="${sessionScope.usuarioLogado.tipoUsuario == 'CLIENTE' ? '/emug/clientes/carrinho' : 'login.jsp' }"
                          method="POST">
                        <div class="d-grid gap-1 col-10 mx-auto mt-3">
                            <!-- <c:choose>
                                <c:when test="${quantidadeDisponivel > 0}">
                                    <a type="button" href="cliente/finalizarCompra.jsp" class="btn btn-primary rounded-pill">Comprar</a>
                                    <input type="hidden" name="id" value="${produto.id}">
                                    <input type="hidden" name="operacao" value="salvar">
                                    <input type="submit" class="btn btn-outline-primary rounded-pill" value="Adicionar ao carrinho">
                                </c:when>
                                <c:otherwise>
                                    Sem produtos no estoque
                                </c:otherwise>
                            </c:choose> -->

                            <a type="button" href="cliente/finalizarCompra.jsp" class="btn btn-primary rounded-pill">Comprar</a>
                            <input type="hidden" name="id" value="${produto.id}">
                            <input type="hidden" name="operacao" value="salvar">
                            <input type="submit" class="btn btn-outline-primary rounded-pill" value="Adicionar ao carrinho">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </main>

    <jsp:include page="include/footer.jsp"/>

</body>

<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>