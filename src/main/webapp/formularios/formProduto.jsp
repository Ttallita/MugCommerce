<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Cadastro</title>

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css" />"/></head>
<body>

    <jsp:include page="../include/headerMinimalista.jsp"/>

    <div class="container align-items-center justify-content-center w-50 p-4">
        <div class="card p-3">
        
            <h3 class="text-center mb-4">Cadastro</h3>

            <form action="/emug/produtos/" enctype="multipart/form-data" method="POST">
                <div class="row g-3 p-4">

                    <div class="col-sm-12">
                        <label for="nomeProduto"><small>Nome</small></label>
                        <input type="text" class="form-control" id="nomeProduto" name="nomeProduto" value="">
                    </div>
                    
                    <div class="col-sm-3">
                        <label for="valorCompra"><small>Valor de compra</small></label>
                        <input type="text" class="form-control money" id="valorCompra" name="valorCompra" value="">
                    </div>

                    <div class="col-sm-5">
                        <label for="codBarras"><small>Cod. barras</small></label>
                        <input type="text" class="form-control" id="codBarras" name="codBarras" value="">
                    </div>

                    <div class="col-sm-4">
                        <label for="material"><small>Material</small></label>
                        <select class="form-select" id="material" name="grupoPrecificacao">
                            <option value="">Selecione</option>
                            <option>Porcelana</option>
                            <option>Plastico</option>
                        </select>
                    </div>

                    <div class="col-sm-4">
                        <label for="fabricante"><small>Fabricante</small></label>
                        <select class="form-select" id="fabricante" name="fabricante">
                            <option value="">Selecione</option>
                            <option>Emug</option>
                            <option>Tiazinha da esquina</option>
                        </select>
                    </div>

                    <div class="col-md-5">
                        <label for="grupoPrecificacao"><small>Grupo de precificação</small></label>
                        <select class="form-select" id="grupoPrecificacao" name="grupoPrecificacao">
                            <option value="">Selecione</option>
                            <option>Grupo A</option>
                            <option>Grupo B</option>
                        </select>
                    </div>

                    <div class="col-sm-3">
                        <label for="categorias"><small>Categorias</small></label>
                        <select class="form-select" id="categorias" multiple aria-label="Categorias">
                            <option selected>Selecione...</option>
                            <option>Animes</option>
                            <option>Filmes</option>
                            <option>Geek</option>
                        </select>
                    </div>

                    <div class="col-sm-12">
                        <label for="descricao"><small>Descrição</small></label>
                        <textarea class="form-control" id="descricao" name="descricao" rows="3"></textarea>
                    </div>

                    <div class="col-sm-12 h-25">
                        <label for="imgProduto" class="form-label">Imagem produto</label>

                        <div id="imagem-produto"></div>
                        <br/>
                        <input class="form-control form-control-sm" id="imgProduto" type="file">
                        <input type="hidden" name="imagemBase64" id="imagemBase64"/>
                    </div>

                    <div class="col-sm-3">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="isAtivo" name="isAtivo" checked>
                            <label class="form-check-label" for="isAtivo">Ativo</label>
                        </div>
                    </div>

                    <hr>
                    <input type="hidden" name="operacao" value="salvar">
                    <input type="submit" class="w-100 btn btn-primary btn-lg" name="botaoCadastro" value="Cadastrar">
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="../include/footer.jsp"/>
</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>
<script>
    $('.money').mask('000.000.000.000.000,00', {reverse: true});

    $('#imgProduto').on('change', async () => {
        let imagem = $('<img>')
        let imagemBase64 = await toBase64($('#imgProduto').prop('files')[0])

        imagem.prop("src", imagemBase64)
        imagem[0].classList.add('w-50')
        imagem[0].classList.add('h-50')

        $('#imagem-produto').html(imagem)
        $('#imagemBase64').val(imagemBase64)
    })
</script>

</html>