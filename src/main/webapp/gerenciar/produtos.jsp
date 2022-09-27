<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Produtos</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>

<body>

<jsp:include page="../include/header.jsp" />

<main class="d-flex flex-nowrap mt-5">

    <jsp:include page="../include/sidebarAdm.jsp" />

    <div class="w-75 bg-white rounded p-5">
        <h5>Produtos</h5>

        <hr>

        <!-- Filtro de Estoque-->
        <div class="container">
            <form>
                <div class="row g-3">

                    <div class="col-sm-4">
                        <small>Valor compra</small>
                        <div class="input-group">
                            <input class="form-control" id="minCompra" placeholder="R$ min" type="text">
                            <span class="input-group-text">-</span>
                            <input type="text" class="form-control" id="maxCompra" placeholder="R$ max">
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <small>Valor venda</small>
                        <div class="input-group">
                            <input class="form-control" id="minVenda" placeholder="R$ min" type="text">
                            <span class="input-group-text">-</span>
                            <input type="text" class="form-control" id="maxVenda" placeholder="R$ max">
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                    </div>
                </div>
            </form>
        </div>

        <hr>

        <div class="table-responsive p-3 rounded mb-4">
            <table class="table table-hover w-100">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Valor compra</th>
                        <th>Valor venda</th>
                        <th>Material</th>
                        <th>Cod. Barras</th>
                        <th>Editar</th>
                        <th>Inativar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${produtos}" var="produto">
                        <tr ${!produto.ativo ? 'class="table-secondary"' : ''}>
                            <td>${produto.nome}</td>
                            <td>R$ ${produto.valorCompra}</td>
                            <td>R$ ${produto.valorVenda}</td>
                            <td>${produto.material}</td>
                            <td>${produto.codBarras}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/adm/produtos?operacao=listarUnico&id=${produto.id}" class="btn btn-primary btn-sm">
                                    <span class="material-icons">edit</span>
                                </a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${produto.ativo}">
                                        <button name="botaoModalDeletar" type="button" class="btn btn-danger btn-sm" onclick="abrirModal(${produto.id})">
                                            <span class="material-icons">delete</span>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        Produto inativado
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="<c:url value="/gerenciar/formularios/formProduto.jsp"/>">
                <button type="button" class="w-100 btn btn-primary btn-sm" >
                    Adicionar produto
                </button>
            </a>
        </div>
    </div>

    <!-- Modal Remover endereço -->
    <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="removeModalTitle">Inativar produto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/emug/adm/produtos" method="POST">
                    <div class="modal-body">
                        Deseja realmente inativar o produto?
                        <br/><br/>
                        <label for="categoriaInativacao">
                            <small>Categoria de Inativação</small>
                        </label>
                        <select class="form-select" id="categoriaInativacao" aria-label="Categorias de inativação">
                            <option selected>Selecione...</option>
                        </select>
                        <br/>

                        <label for="justificativa" class="form-label">
                            <small>Justificativa</small>
                        </label>
                        <textarea class="form-control" name="justificativa" id="justificativa" rows="3"></textarea>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" name="operacao" value="excluir">
                        <input type="hidden" id="idRemover" name="id" value="">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cancelar</button>
                        <input type="submit" class="btn btn-danger" value="Inativar" id="botaoRemoverEndereco">
                    </div>
                </form>
            </div>
        </div>
    </div>

</main>

<br/>

<jsp:include page="../include/footer.jsp"/>

<script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
<script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
<script src="<c:url value='/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js' />"></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>

<script>
    $('#minVenda').mask("#.##0,00", {reverse: true});
    $('#maxVenda').mask("#.##0,00", {reverse: true});
    $('#minCompra').mask("#.##0,00", {reverse: true});
    $('#maxCompra').mask("#.##0,00", {reverse: true});
</script>
</body>
</html>