<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>${not empty produto ? 'Edição' : 'Cadastro'}</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css' />"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css' />"/>
</head>
<body>

    <jsp:include page="../../include/headerMinimalista.jsp"/>

    <div class="container align-items-center justify-content-center w-50 p-4">
        <jsp:include page="../../include/alert.jsp" />
        <div class="card p-3">
        
            <h3 class="text-center mb-4">
                ${not empty produto ? 'Atualizar' : 'Cadastrar'}
            </h3>

            <form action="/emug/adm/produtos" enctype="multipart/form-data" method="POST">
                <div class="row g-3 p-4">

                    <div class="col-sm-12">
                        <label for="nomeProduto"><small>Nome</small></label>
                        <input type="text" ${produto.ativo ? '' : 'disabled'} class="form-control" id="nomeProduto" name="nomeProduto" value="${produto.nome}">
                    </div>
                    
                    <div class="col-sm-3">
                        <label for="valorCompra"><small>Valor de compra</small></label>
                        <input type="text" ${produto.ativo ? '' : 'disabled'} class="form-control money" id="valorCompra" name="valorCompra" value="${produto.valorCompra}">
                    </div>

                    <div class="col-sm-5">
                        <label for="codBarras"><small>Cod. barras</small></label>
                        <input type="text" ${produto.ativo ? '' : 'disabled'} class="form-control" id="codBarras" name="codBarras" value="${produto.codBarras}">
                    </div>

                    <div class="col-sm-4">
                        <label for="material"><small>Material</small></label>
                        <select class="form-select" ${produto.ativo ? '' : 'disabled'} id="material" name="material">
                            <option value="">Selecione</option>
                            <option ${produto.material == 'Porcelana' ? 'selected' : ''}>Porcelana</option>
                            <option ${produto.material == 'Plastico' ? 'selected' : ''}>Plastico</option>
                            <option ${produto.material == 'Metal' ? 'selected' : ''}>Metal</option>
                        </select>
                    </div>

                    <div class="col-sm-4">
                        <label for="fabricante"><small>Fabricante</small></label>
                        <select class="form-select" ${produto.ativo ? '' : 'disabled'} id="fabricante" name="fabricante">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label for="grupoPrecificacao"><small>Grupo de precificação</small></label>
                        <select class="form-select" ${produto.ativo ? '' : 'disabled'} id="grupoPrecificacao" name="grupoPrecificacao">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-sm-3">
                        <label for="categorias"><small>Categorias</small></label>
                        <select class="form-select" ${produto.ativo ? '' : 'disabled'} name="categorias" id="categorias" multiple aria-label="Categorias">
                            <option>Selecione...</option>
                        </select>
                    </div>

                    <div class="col-sm-12">
                        <label for="descricao"><small>Descrição</small></label>
                        <textarea class="form-control" ${produto.ativo ? '' : 'disabled'} id="descricao" name="descricao" rows="3">${produto.descricao}</textarea>
                    </div>

                    <div class="col-sm-12 h-25">
                        <label for="imgProduto" class="form-label">Imagem produto</label>
                        <div id="imagem-produto">
                            <c:if test="${not empty produto}">
                                <img src="${produto.imagem}" class="w-50 h-50" alt="Imagem do Produto"/>
                            </c:if>
                        </div>
                        <br/>
                        <input class="form-control form-control-sm" ${produto.ativo ? '' : 'disabled'} id="imgProduto" type="file">
                        <input type="hidden" name="imagemBase64" id="imagemBase64" value="${produto.imagem}"/>
                    </div>

                    <c:if test="${empty produto}">
                        <div class="col-sm-3">
                            <div class="form-check form-switch">
                                <input class="form-check-input" type="checkbox" role="switch" id="isAtivo" name="isAtivo">
                                <label class="form-check-label" for="isAtivo">Ativo</label>
                            </div>
                        </div>
                    </c:if>

                    <hr>
                    <input type="hidden" name="id" value="${produto.id}">
                    <input type="hidden" name="operacao" value="${not empty produto ? 'atualizar' : 'salvar'}">
                    <c:choose>
                        <c:when test="${not empty produto}">
                            <c:if test="${produto.ativo}">
                                <input type="submit" class="w-100 btn btn-primary btn-lg" name="botaoCadastro" value="Atualizar">
                            </c:if>

                            <c:if test="${!produto.ativo}">
                                <button type="button" class="w-100 btn btn-primary btn-lg" onclick="abrirModal(${produto.id})">
                                    Ativar Produto
                                </button>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" class="w-100 btn btn-primary btn-lg" name="botaoCadastro" value="Cadastrar">
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </div>

        <!-- Modal Ativar Produto -->
        <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="removeModalTitle">Ativar produto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="/emug/adm/produtos" method="POST" id="formStatus">
                        <div class="modal-body">
                            <div class="alert alert-danger alert-dismissible fade show"
                                 role="alert"
                                 id="erroInativar"
                                 style="display: none">
                                <ul id="lista-erros">

                                </ul>
                            </div>

                            Deseja realmente ativar o produto?
                            <br/><br/>
                            <label for="categoriaStatus">
                                <small>Categoria de Inativação</small>
                            </label>
                            <select class="form-select" id="categoriaStatus" name="categoriaInativacao"
                                    aria-label="Categorias de inativação">
                                <option selected>Selecione...</option>
                                <c:forEach var="categoria" items="${categoriasAtivacao}">
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
                            <input type="hidden" name="status" value="true">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cancelar</button>
                            <input type="submit" class="btn btn-danger" value="Ativar produto">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../../include/footer.jsp"/>
</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    const categoriasJSON = JSON.parse('${not empty categorias ? categorias : '{}'}')

    $('.money').mask('000.000.000.000.000,00', {reverse: true});

    $('#imgProduto').on('change', async () => {
        let imagem = $('<img>')
        imagem.attr("alt", "Imagem do produto")

        let imagemBase64 = await toBase64($('#imgProduto').prop('files')[0])

        imagem.prop("src", imagemBase64)
        imagem[0].classList.add('w-50')
        imagem[0].classList.add('h-50')

        $('#imagem-produto').html(imagem)
        $('#imagemBase64').val(imagemBase64)
    })

    async function montaSelect(url, id, tipo) {
        let response = await fetch(url)
        const json = await response.json();

        json.forEach(valor => {
            let option = $('<option/>');
            option.val(valor.id)
            option.html(valor.nome)

            if(tipo === 'FABRICANTE') {
                if(valor.id == '${produto.fabricante.id}')
                    option.prop('selected', true)
            } else if(tipo === 'GRUPO') {
                if(valor.id == '${produto.grupoPrecificacao.id}')
                    option.prop('selected', true)
            } else {
                if(categoriasJSON.length > 0) {
                    let categoria = categoriasJSON.find(categoria => categoria.id === valor.id)

                    if(categoria)
                        option.prop('selected', true)
                }
            }

            $(id).append(option)
        })
    }

    $(document).ready(() => {
        const baseUrl = 'http://localhost:8080/emug/adm';
        let params = { operacao: 'listar' }

        let urlGrupos = montaUrl(baseUrl, 'grupos', params)
        let urlFabricantes = montaUrl(baseUrl, 'fabricantes', params)
        let urlCategorias = montaUrl(baseUrl, 'categorias', params)

        montaSelect(urlFabricantes, '#fabricante', 'FABRICANTE')
        montaSelect(urlGrupos, '#grupoPrecificacao', 'GRUPO')
        montaSelect(urlCategorias, '#categorias')
    })

</script>

</html>