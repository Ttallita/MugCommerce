<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                            
                            <c:forEach var="cartao" items="${cartoesSelecionados}">
                                <p>
                                    (Crédito) cartão com final ${cartao.finalCartao}
                                </p>
                            </c:forEach>

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
                                <strong><fmt:formatNumber value="${carrinho.totalCarrinho}" type="currency"/></strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Frete:</strong>
                                <strong><fmt:formatNumber value="${valorFrete}" type="currency"/></strong>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Desconto:</strong>
                                <strong>- <fmt:formatNumber value="${valorDesconto}" type="currency"/></strong>
                            </li>
                            <li class="d-flex justify-content-between py-3">
                                <strong class="text-muted">Total do pedido:</strong>
                                <h5 class="font-weight-bold"><fmt:formatNumber value="${carrinho.totalCarrinho}" type="currency"/></h5>
                            </li>

                            <input type="hidden" name="idEnderecoEscolhido" value="${enderecoEntrega.id}">
                            <input type="hidden" name="idsCartoesSelecionados[]" value="${idsCartoesSelecionados}">
                            <input type="hidden" name="idsCupons[]" value="">
                            <!-- todos os produtos da venda são resgatados do carrinho salvo na session -->
                            <input type="hidden" name="operacao" value="salvar">

                            <input type="submit" class="btn btn-primary rounded-pill py-2" value="Confirmar pedido">

                        </ul>
                    </form>
                    </div>
                </div>
            </div>
            
        </div>

        <!-- Modal endereço-->
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
                    <div class="modal-footer" id="modalFooter">
                        <!-- Botões -->
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
    const idsCartoesSelecionados = '${idsCartoesSelecionados}';

    const parametroOrigemChamada = "&origemChamada=finalizarCompra";
    const parametrosVendaHref = `&idEnderecoEscolhido=\${idEnderecoEscolhido}&idsCartoesSelecionados=\${idsCartoesSelecionados}`;

    async function listaItensModal(url, path) {
        let response = await fetch(url)
        let json = await response.json();

        let formModal = document.getElementById("formModal");
        let tituloModal = document.getElementById("modalAlterarLabel");

        formModal.innerHTML = '';

        let urlAdicionar, botaoAlterar;

        if (path.includes("enderecos")) {

            listaEnderecosModal(json);

            tituloModal.innerText = "Escolha o endereço";
            urlAdicionar = "<c:url value='/clientes/enderecos?operacao=adicionar'/>";

            botaoAlterar =
                $(`<button type="button" class="btn btn-primary" onclick="atualizarEnderecoEntrega()" id="botaoAlterarModal">Alterar</button>`);

        } else if (path.includes("cartoes")) {

            listaCartoesModal(json);

            tituloModal.innerText = "Escolha o cartão";
            urlAdicionar = "<c:url value='/clientes/cartoes?operacao=adicionar'/>";

            botaoAlterar =
                $(`<button type="button" class="btn btn-primary" onclick="atualizarCartoesUtilizados()" id="botaoAlterarModal">Alterar</button>`);

        }

        let botaoAdicionar =
                $(`<a href="\${urlAdicionar}\${parametroOrigemChamada}\${parametrosVendaHref}">
                    <button type="button" class="btn btn-primary" id="botaoAdicionarModal">Adicionar</button>
                </a>`);

        let modalFooter = document.getElementById("modalFooter");
        modalFooter.innerHTML = '';

        botaoAdicionar.appendTo(modalFooter);
        botaoAlterar.appendTo(modalFooter);
        
    }

    function montarModalCadastro(path){
        let params = { operacao: 'listarJson'}

        let urlItensModal = montaUrl(baseUrl, path, params);

        listaItensModal(urlItensModal, path);
    }

    function listaEnderecosModal(json){

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
        });
    }

    function listaCartoesModal(json){

        json.forEach(cartao => {

            let isCartaoSelecionado = idsCartoesSelecionados.includes(cartao.id);

            let hrefEditar = `<c:url value="/clientes/cartoes?operacao=listarUnico&id=\${cartao.id}\${parametroOrigemChamada}\${parametrosVendaHref}"/>`;

            let checkCartao =
                $(`<div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="cartao\${cartao.id}" \${isCartaoSelecionado ? 'checked' : ''}>
                    <label class="form-check-label" for="cartao\${cartao.id}">
                        <small class="float-end"><a href='\${hrefEditar}'>Editar</a> </small>
                        \${cartao.nomeImpressoCartao} \${cartao.bandeira}, final \${cartao.finalCartao}
                    </label>
                </div>`);

            checkCartao.appendTo(formModal);
        });
    }

    function atualizarCartoesUtilizados(){
        // Encontra endereço/cartão selecionado
        var ids = [];
        Array.from(document.getElementById("formModal").getElementsByTagName("input"))
                            .filter(elem => elem.checked)
                            .forEach(elem => ids.push(elem.id.match(/\d+/g)));

        window.location.href = construirURL(idEnderecoEscolhido, ids)

    }

    function atualizarEnderecoEntrega(){
        let idAlterar = Array.from(document.getElementById("formModal").getElementsByTagName("input")).find(elem => elem.checked).id;
        
        var id = idAlterar.match(/\d+/g);
        var tipoInfo =  idAlterar.match(/[a-zA-Z]+/g);

        window.location.href = construirURL(id, idsCartoesSelecionados);
    }

    function construirURL(idEnderecoEscolhido, idsCartoesSelecionados){
        let urlBase = `<c:url value="/clientes/carrinho/finalizarCompra?operacao=listar"/>`;
        let parametrosVendaHref = `&idEnderecoEscolhido=\${idEnderecoEscolhido}&idsCartoesSelecionados=\${idsCartoesSelecionados}`;
        
        return urlBase + parametrosVendaHref;
    }

</script>

</html>