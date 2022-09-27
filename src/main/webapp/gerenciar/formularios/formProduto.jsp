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
        <div class="card p-3">
        
            <h3 class="text-center mb-4">
                ${not empty produto ? 'Atualizar' : 'Cadastrar'}
            </h3>

            <form action="/emug/adm/produtos" enctype="multipart/form-data" method="POST">
                <div class="row g-3 p-4">

                    <div class="col-sm-12">
                        <label for="nomeProduto"><small>Nome</small></label>
                        <input type="text" class="form-control" id="nomeProduto" name="nomeProduto" value="${produto.nome}">
                    </div>
                    
                    <div class="col-sm-3">
                        <label for="valorCompra"><small>Valor de compra</small></label>
                        <input type="text" class="form-control money" id="valorCompra" name="valorCompra" value="${produto.valorCompra}">
                    </div>

                    <div class="col-sm-5">
                        <label for="codBarras"><small>Cod. barras</small></label>
                        <input type="text" class="form-control" id="codBarras" name="codBarras" value="${produto.codBarras}">
                    </div>

                    <div class="col-sm-4">
                        <label for="material"><small>Material</small></label>
                        <select class="form-select" id="material" name="material">
                            <option value="">Selecione</option>
                            <option ${produto.material == 'Porcelana' ? 'selected' : ''}>Porcelana</option>
                            <option ${produto.material == 'Plastico' ? 'selected' : ''}>Plastico</option>
                            <option ${produto.material == 'Metal' ? 'selected' : ''}>Metal</option>
                        </select>
                    </div>

                    <div class="col-sm-4">
                        <label for="fabricante"><small>Fabricante</small></label>
                        <select class="form-select" id="fabricante" name="fabricante">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label for="grupoPrecificacao"><small>Grupo de precificação</small></label>
                        <select class="form-select" id="grupoPrecificacao" name="grupoPrecificacao">
                            <option value="">Selecione</option>
                        </select>
                    </div>

                    <div class="col-sm-3">
                        <label for="categorias"><small>Categorias</small></label>
                        <select class="form-select" name="categorias" id="categorias" multiple aria-label="Categorias">
                            <option>Selecione...</option>
                        </select>
                    </div>

                    <div class="col-sm-12">
                        <label for="descricao"><small>Descrição</small></label>
                        <textarea class="form-control" id="descricao" name="descricao" rows="3">${produto.descricao}</textarea>
                    </div>

                    <div class="col-sm-12 h-25">
                        <label for="imgProduto" class="form-label">Imagem produto</label>
                        <div id="imagem-produto">
                            <c:if test="${not empty produto}">
                                <img src="${produto.imagem}" class="w-50 h-50" alt="Imagem do Produto"/>
                            </c:if>
                        </div>
                        <br/>
                        <input class="form-control form-control-sm" id="imgProduto" type="file">
                        <input type="hidden" name="imagemBase64" id="imagemBase64" value="${produto.imagem}"/>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="isAtivo" name="isAtivo" ${produto.ativo ? 'checked' : ''}>
                            <label class="form-check-label" for="isAtivo">Ativo</label>
                        </div>
                    </div>


                    <hr>
                    <input type="hidden" name="operacao" value="${not empty produto ? 'atualizar' : 'salvar'}">
                    <input type="submit" class="w-100 btn btn-primary btn-lg" name="botaoCadastro" value="${not empty produto ? 'Atualizar' : 'Cadastrar'}">
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

    function montaUrl(baseUrl, path, params) {
        let url = new URL(baseUrl + "/" +path)
        url.search = new URLSearchParams(params).toString()

        return url;
    }

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