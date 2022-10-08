<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Finalizar compra</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>
    <jsp:include page="../include/header.jsp" />

    <main class="wrapper container mt-3 mb-3">
        <div class="row bg-white rounded p-4">
            <div class="col-lg-8">
                <h3 class="mb-3 fw-bold">Confira e confirme sua compra</h3>
                <div class="row border rounded p-3 m-2">

                    <div class="col-6 p-3">
                        <h6>Endereço de entrega</h6>
                        <!--Aciona modal alterar endereço entrega-->
                        <a type="button" id="alterarEndereco" onclick="montarModalCadastro('clientes/enderecos')" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modalAlterar">
                            Alterar
                        </a>
                        <ul class="list-unstyled">
                            <li>${enderecoEntrega.apelido} (${enderecoEntrega.tipoResidencia})</li>
                            <li>${enderecoEntrega.tipoLogradouro} ${enderecoEntrega.logradouro}, ${enderecoEntrega.numero}</li>
                            <li>${enderecoEntrega.cidade}, ${enderecoEntrega.estado} - Brasil</li>
                            <li>CEP: ${enderecoEntrega.cep}</li>
                            <li>${enderecoEntrega.observacoes}</li>
                        </ul>
                    </div>

                    <div class="col-6">
                        <div class="col p-3">
                            <h6>Forma de pagamento</h6>
                            <!--Aciona modal alterar forma de pagamento-->
                            <a type="button" id="alterarPagamento" onclick="montarModalCadastro('clientes/cartoes')" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modalAlterar">
                                Alterar
                            </a>
                            
                            <p>
                                (Crédito) ${cartaoSelecionado.nomeImpressoCartao}, cartão com final ${cartaoSelecionado.finalCartao}
                            </p>
                        </div>
                    </div>

                    <div class="col-6 p-3">
                        <h6>Cupons aplicados:</h6>

                        <c:forEach var="cupom" items="${cuponsAplicados}">
                            <div class="toast align-items-center p-2 fade show" aria-live="assertive" aria-atomic="true" role="alert">

                                <div class="d-flex justify-content-between">
                                    <strong class="fw-bold">${cupom.tipo}</strong>
                                    <small>Validade: ${cupom.dataValidade}</small>
                                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                                </div>
                                
                                <div class="d-flex justify-content-between">
                                    <p class="lead">${cupom.nome}</p>
                                    <strong class="lead fw-bold">R$ ${cupom.valor}</strong>
                                </div>
                            </div>
                        </c:forEach>

                    </div>

                    <div class="col-6 p-3">
                        <h6>Adicionar cupom</h6>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" aria-label="Recipient's username"
                                   aria-describedby="button-addon2" placeholder="Nome cupom">
                            <button type="button" id="button-addon2" class="btn btn-outline-primary">Aplicar</button>
                        </div>
                    </div>

                </div>

                <div class="row border rounded p-3 m-2">

                    <h4 class="mb-3 fw-bold">Itens</h4>
                    <strong class="mb-3 text-primary fw-bold">Previsão de entrega: 00 mês 0000 </strong>

                    <c:forEach var="itemCarrinho" items="${carrinho.itensCarrinho}">
                        <div class="row border-top p-3">
                            <div class="col-3">
                                <div class="card produto mb-3">
                                    <img alt="imagem_produto" src="${itemCarrinho.produto.imagem}" class="p-2">
                                </div>
                            </div>
                            <div class="col">
                                <h4>R$ ${itemCarrinho.produto.valorVenda * itemCarrinho.quant}</h4>Quantidade: ${itemCarrinho.quant}
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

            <div class="col-lg-4 order-md-last">
                <div class="row vstack mt-5">
                    <div class="container border rounded py-4">
                        <form action="/emug/clientes/finalizarCompra" >
                        <ul class="list-group">
                            <li class="d-flex mb-4 justify-content-between">
                                <h5 class="fw-bold">Resumo do pedido</h5>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Itens:</strong>
                                <strong>R$ ${carrinho.totalCarrinho}</strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Frete:</strong>
                                <strong>RS ${valorFrete}</strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Desconto:</strong>
                                <strong>- R$ ${valorDesconto}</strong>
                            </li>
                            <li class="d-flex justify-content-between py-3">
                                <strong class="text-muted">Total do pedido:</strong>
                                <h5 class="font-weight-bold">R$ ${carrinho.totalCarrinho}</h5>
                            </li>

                            <input type="hidden" name="id" value="${enderecoEntrega.id}">
                            <input type="hidden" name="id" value="${cartaoSelecionado.id}">
                            <!-- <input type="hidden" name="id" value="${cupons.id}"> -->

                            <!-- todos os produtos da venda são resgatados do carrinho salvo na session -->
                            <input type="hidden" name="operacao" value="salvar">
                            <input type="submit" class="btn btn-primary rounded-pill py-2" value="Confirmar pedido">

                        </ul>
                    </form>
                    </div>
                </div>
            </div>
            
        </div>

        <!-- Modal-->
        <div class="modal fade" id="modalAlterar" tabindex="-1" aria-labelledby="modalAlterarLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalAlterarLabel"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formModal">
                            <!-- Lista de itens-->
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="atualizarInfoVenda()" id="botaoAlterarModal">Alterar</button>
                        <!-- Botão alterar item selecionado -->
                        <!-- Botão adicionar -->


                    </div>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="../include/footer.jsp"/>

</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>

<script>

    const baseUrl = 'http://localhost:8080/emug';

    const idEnderecoEscolhido = '${enderecoEntrega.id}';
    const idCartaoSelecionado = '${cartaoSelecionado.id}';

    const parametroOrigemChamada = "&origemChamada=finalizarCompra";
    const parametrosVendaHref = `&idEnderecoEscolhido=\${idEnderecoEscolhido}&idCartaoSelecionado=\${idCartaoSelecionado}`;

    async function listaItensModal(url, path) {
        let response = await fetch(url)
        let json = await response.json();

        let formModal = document.getElementById("formModal");

        // Limpa todos os campos de check
        formModal.innerHTML = '';

        let tituloModal = document.getElementById("modalAlterarLabel");
        let botaoAlterar = document.getElementById("botaoAlterarModal");

        if (path.includes("enderecos")) {
            json.forEach(endereco => {

                let isEnderecoSelecionado = idEnderecoEscolhido == endereco.id;

                let hrefEditar = `<c:url value="/clientes/enderecos?operacao=listarUnico&id=\${endereco.id}\${parametroOrigemChamada}\${parametrosVendaHref}"/>`
                
                let checkEndereco =
                    $(`<div class="form-check">
                        <input class="form-check-input" type="radio" name="endereco" id="endereco\${endereco.id}" \${isEnderecoSelecionado ? 'checked' : ''}>
                        <label class="form-check-label" for="endereco\${endereco.id}">
                            <small class="float-end"><a href='\${hrefEditar}'>Editar</a> </small>
                            \${endereco.tipoLogradouro} \${endereco.logradouro}, \${endereco.numero}, \${endereco.bairro}, \${endereco.estado}, CEP \${endereco.cep}
                        </label>
                    </div>`);

                checkEndereco.appendTo(formModal);
            })

            tituloModal.innerText = "Escolha o endereço";

        } else if (path.includes("cartoes")) {
            json.forEach(cartao => {

                let isCartaoSelecionado = idCartaoSelecionado == cartao.id;

                let hrefEditar = `<c:url value="/clientes/cartoes?operacao=listarUnico&id=\${cartao.id}\${parametroOrigemChamada}\${parametrosVendaHref}"/>`;

                let checkCartao =
                    $(`<div class="form-check">
                        <input class="form-check-input" type="radio" name="cartao" id="cartao\${cartao.id}" \${isCartaoSelecionado ? 'checked' : ''}>
                        <label class="form-check-label" for="cartao\${cartao.id}">
                            <small class="float-end"><a href='\${hrefEditar}'>Editar</a> </small>
                            \${cartao.nomeImpressoCartao} \${cartao.bandeira}, final \${cartao.finalCartao}
                        </label>
                    </div>`);

                checkCartao.appendTo(formModal);
            })

            tituloModal.innerText = "Escolha o cartão";
        }
    }

    function montarModalCadastro(path){
        let params = { operacao: 'listarJson'}

        let urlItensModal = montaUrl(baseUrl, path, params);

        listaItensModal(urlItensModal, path);
    }

    function atualizarInfoVenda(){
        // Encontra endereço/cartão selecionado
        let idAlterar = Array.from(document.getElementById("formModal").getElementsByTagName("input")).find(elem => elem.checked).id;

        var id = idAlterar.match(/\d+/g);
        var tipoInfo =  idAlterar.match(/[a-zA-Z]+/g);

        let urlAtualizar;
        if (tipoInfo == 'endereco')
            urlAtualizar = construirURL(id, idCartaoSelecionado)
        else
            urlAtualizar = construirURL(idEnderecoEscolhido, id)

        window.location.href = urlAtualizar;
    }

    function construirURL(idEndereco, idCartao){
        let urlBase = `<c:url value="/clientes/carrinho/finalizarCompra?operacao=listar"/>`;
        let parametrosVendaHref = `&idEnderecoEscolhido=\${idEndereco}&idCartaoSelecionado=\${idCartao}`;
        
        return urlBase + parametrosVendaHref;
    }

</script>

</html>