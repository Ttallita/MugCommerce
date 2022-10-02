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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-table-filter-control.min.css' />"/>

</head>

<body>

<jsp:include page="../include/header.jsp" />

<main class="d-flex flex-nowrap mt-5">

    <jsp:include page="../include/sidebarAdm.jsp" />

    <div class="w-75 bg-white rounded p-5">
        <h5>Produtos</h5>

        <hr>
        <div class="table-responsive p-3 rounded mb-4">
            <table id="tableProduto"
                   class="table table-hover w-100"
                   data-toggle="table"
                   data-pagination="true"
                   data-search="true"
                   data-page-size="25"
                   data-locale="pt-BR"
                   data-filter-control="true"
                   data-show-search-clear-button="true">
                <thead>
                    <tr>
                        <th data-field="nome" data-filter-control="input">Nome</th>
                        <th data-field="valorCompra" data-filter-control="input">Valor compra</th>
                        <th data-field="valorVenda" data-filter-control="input">Valor venda</th>
                        <th data-field="material" data-filter-control="select">Material</th>
                        <th data-field="codBarras" data-filter-control="input">Cod. Barras</th>
                        <th>${!produto.ativo ? 'Ativar Produto' : ''}</th>
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

    <!-- Modal Inativar Produto -->
    <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="removeModalTitle">Inativar produto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="/emug/adm/produtos" method="POST" id="formInativar">
                    <div class="modal-body">
                        <div class="alert alert-danger alert-dismissible fade show"
                             role="alert"
                             id="erroInativar"
                             style="display: none">
                            <ul id="lista-erros">

                            </ul>
                        </div>

                        Deseja realmente inativar o produto?
                        <br/><br/>
                        <label for="categoriaInativacao">
                            <small>Categoria de Inativação</small>
                        </label>
                        <select class="form-select" id="categoriaInativacao" name="categoriaInativacao" aria-label="Categorias de inativação">
                            <option selected>Selecione...</option>
                            <c:forEach var="categoria" items="${categoriasInativacao}">
                                <option value="${categoria}">${categoria.nomeTela}</option>
                            </c:forEach>
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

<script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>
<script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
<script src='<c:url value="/assets/js/bootstrap-table.min.js"/>'></script>
<script src='<c:url value="/assets/js/bootstrap-table-filter-control.min.js"/>'></script>
<script src='<c:url value="/assets/js/bootstrap-table-pt-BR.min.js"/>'></script>
<script src="<c:url value='/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js' />"></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    $('#minVenda').mask("#.##0,00", {reverse: true});
    $('#maxVenda').mask("#.##0,00", {reverse: true});
    $('#minCompra').mask("#.##0,00", {reverse: true});
    $('#maxCompra').mask("#.##0,00", {reverse: true});


    $(function() {
        $('#tableProduto').bootstrapTable()
    })

    $( "#formStatus" ).submit(function( event ) {
        event.preventDefault();

        let categorias = $('#categoriaStatus').val();
        let justificativa = $('#justificativa').val();

        let erros = []

        if(categorias === '' || categorias === 'Selecione...')
            erros.push('Selecione uma categoria')

        if(justificativa === '')
            erros.push('Digite a justificativa')

        if(erros.length > 0) {
            erros.forEach(erro => {
                let li = document.createElement('li')

                li.innerHTML = erro

                document.getElementById('lista-erros').appendChild(li)
            })

            document.getElementById('erroInativar').style.display = 'block'
            return;
        }

        $('#formInativar')[0].submit()
    });

</script>
</body>
</html>