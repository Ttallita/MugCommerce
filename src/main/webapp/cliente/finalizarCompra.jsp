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
                        <a type="button" id="alterarEndereco" onclick="montarModalCadastro('clientes/enderecos', true)" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modal">
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

                    <div class="col-6 p-3 ${showEnderecoCobranca ? 'd-none' : ''}" id="divEnderecoCobranca">
                        <h6>Endereço de Cobrança</h6>
                        <a type="button" id="alterarEnderecoCobranca" onclick="montarModalCadastro('clientes/enderecos', false)" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modal">
                            Alterar
                        </a>
                        <c:if test="${enderecoCobranca.id != null}">
                            <ul class="list-unstyled">
                                <li>${enderecoCobranca.apelido} (${enderecoCobranca.tipoResidencia})</li>
                                <li>${enderecoCobranca.tipoLogradouro} ${enderecoCobranca.logradouro}, ${enderecoCobranca.numero}</li>
                                <li>${enderecoCobranca.cidade}, ${enderecoCobranca.estado} - Brasil</li>
                                <li>CEP: ${enderecoCobranca.cep}</li>
                                <li>${enderecoCobranca.observacoes}</li>
                            </ul>
                        </c:if>
                    </div>

                    <div class="col-6">
                        <div class="col p-3">
                            <h6>Forma de pagamento</h6>
                            <a type="button" id="alterarPagamento" onclick="montarModalCadastro('clientes/cartoes', false)" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modal">
                                Alterar
                            </a>
                            <ul class="list-unstyled">
                                <c:forEach var="cartao" items="${cartoesSelecionados}">
                                    <li>(Crédito) cartão com final ${cartao.finalCartao}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <div class="col-6 p-3" id="cuponsAplicados">
                        <h6>Cupons aplicados:</h6>
                        <!-- Lista cupons aplicados -->
                    </div>

                    <div class="col-6 p-3">
                        <h6>Adicionar cupom</h6>
                        <div class="input-group mb-3">
                            <select class="form-select" id="cupons" name="cupons">
                                <option value="">Selecione</option>
                                <c:forEach var="cupom" items="${cupons}">
                                    <option value="${cupom.id}">${cupom.nome}</option>
                                </c:forEach>
                            </select>
                            <button type="button" class="btn btn-outline-primary" id="botaoAplicarCupom" onclick="aplicarCupom()">Aplicar</button>
                        </div>
                    </div>

                </div>

                <div class="row border rounded p-3 m-2">
                    
                    <h4 class="mb-3 fw-bold">Itens</h4>
                    <strong class="mb-3 text-primary fw-bold">Previsão de entrega: ${dataPrevisaoEntrega} </strong>

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
                        <form action="<c:url value='/clientes/carrinho/finalizarCompra'/>" id="formFinalizarCompra">
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
                                <strong id="valorDesconto" class="text-danger"> - R$ 00,00</strong>
                            </li>
                            <li class="d-flex justify-content-between py-3">
                                <strong class="text-muted">Total do pedido:</strong>
                                <h5 class="font-weight-bold" id="vlrTotalPedido"><fmt:formatNumber value="${carrinho.totalCarrinho}" type="currency"/></h5>
                            </li>
                        </ul>

                        <input type="hidden" name="idEnderecoEscolhido" value="${enderecoEntrega.id}">
                        <!-- IDs cupons -->
                        <c:forEach var="cartao" items="${cartoesSelecionados}">
                            <input type="hidden" name="idsCartoesSelecionados[]" value="${cartao.id}">
                        </c:forEach>
                        
                        <!-- todos os produtos da venda são resgatados do carrinho salvo na session -->
                        <input type="hidden" name="operacao" value="salvar">

                        <input type="submit" class="btn btn-primary rounded-pill py-2" value="Confirmar pedido">
                    </form>
                    </div>
                </div>
            </div>
            
        </div>

        <jsp:include page="../include/modalBase.jsp" />

    </main>

    <jsp:include page="../include/footer.jsp"/>

</body>

<script src='<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="/assets/js/construir-modal.js"/>'></script>
<script src='<c:url value="/assets/js/geral.js"/>'></script>

<script>

    const idEnderecoEscolhido = '${enderecoEntrega.id}';
    const idEnderecoCobrancaEscolhido = '${enderecoCobranca.id}'
    const idsCartoesSelecionados = '${idsCartoesSelecionados}';

    const parametroOrigemChamada = "&origemChamada=finalizarCompra";
    const parametrosVendaHref = `&idEnderecoEscolhido=\${idEnderecoEscolhido}&idsCartoesSelecionados=\${idsCartoesSelecionados}`;

    function atualizarCartoesUtilizados(){
        var ids = [];
        Array.from(document.getElementById("formModal").getElementsByTagName("input"))
                            .filter(elem => elem.checked)
                            .forEach(elem => ids.push(elem.id.match(/\d+/g)));

        window.location.href = construirURLFinalizarCompra(idEnderecoEscolhido, ids)
    }

    async function atualizarEnderecoEntrega(event){
        let idAlterar = Array.from(document.getElementById("formModal").getElementsByTagName("input")).find(elem => elem.checked).id;
        
        let id = idAlterar.match(/\d+/g);

        let params = { operacao: 'listarJson'}
        let url = montaUrl(baseUrl, "clientes/enderecos", params)

        let response = await fetch(url)
        let json = await response.json();

        let endereco = json.find(endereco => endereco.id == id[0]);

        if(endereco.tipoEndereco === 'COBRANCA_ENTREGA')  {
            window.location.href = construirURLFinalizarCompra(id, idsCartoesSelecionados, '')
            return
        }

        window.location.href = event.data.isEnderecoEntrega ?
            construirURLFinalizarCompra(id, idsCartoesSelecionados, idEnderecoCobrancaEscolhido)
            :
            construirURLFinalizarCompra(idEnderecoEscolhido, idsCartoesSelecionados, id);
    }

    function construirURLFinalizarCompra(idEnderecoEscolhido, idsCartoesSelecionados, idEnderecoCobrancaEscolhido){
        let urlBase = `<c:url value="/clientes/carrinho/finalizarCompra?operacao=listarUnico"/>`;
        let parametrosVendaHref = `&idEnderecoEscolhido=\${idEnderecoEscolhido}&idEnderecoCobrancaEscolhido=\${idEnderecoCobrancaEscolhido}&idsCartoesSelecionados=\${idsCartoesSelecionados}`;
        
        return urlBase + parametrosVendaHref;
    }

    function montarModalCadastro(path, isEnderecoEntrega){
        let params = { operacao: 'listarJson'}

        if (path.includes("enderecos"))
            montaModalSelecionarEnderecos(montaUrl(baseUrl, path, params), isEnderecoEntrega);

        else if (path.includes("cartoes")) 
            montaModalSelecionarCartoes(montaUrl(baseUrl, path, params));
    }

    async function montaModalSelecionarEnderecos(url, isEnderecoEntrega){
        let response = await fetch(url)
        let json = await response.json();

        limpaModal();

        json.filter(endereco =>{
                if(isEnderecoEntrega)
                    return endereco.tipoEndereco !== 'COBRANCA'
                else
                    return endereco.tipoEndereco === 'COBRANCA'
            })
            .forEach(endereco => {
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

                adicionaFormModal(checkEndereco);
        });

        setTituloModal("Escolha o endereço");

        let botaoAlterar =
            $(`<button type="button" class="btn btn-primary" id="botaoAlterarModal">Alterar</button>`);

        botaoAlterar.click({ isEnderecoEntrega }, atualizarEnderecoEntrega)

        let urlAdicionar = "<c:url value='/clientes/enderecos?operacao=adicionar'/>";
        let botaoAdicionar =
                $(`<a href="\${urlAdicionar}\${parametroOrigemChamada}\${parametrosVendaHref}">
                    <button type="button" class="btn btn-primary" id="botaoAdicionarModal">Adicionar</button>
                </a>`);

        adicionaBotaoFooter(botaoAdicionar);
        adicionaBotaoFooter(botaoAlterar);
    }


    async function montaModalSelecionarCartoes(url){
        let response = await fetch(url)
        let json = await response.json();

        limpaModal();

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

            adicionaFormModal(checkCartao);
        });

        setTituloModal("Escolha o cartão");
        
        let botaoAlterar =
            $(`<button type="button" class="btn btn-primary" onclick="atualizarCartoesUtilizados()" id="botaoAlterarModal">Alterar</button>`);

        let urlAdicionar = "<c:url value='/clientes/cartoes?operacao=adicionar'/>";
        let botaoAdicionar =
                $(`<a href="\${urlAdicionar}\${parametroOrigemChamada}\${parametrosVendaHref}">
                    <button type="button" class="btn btn-primary" id="botaoAdicionarModal">Adicionar</button>
                </a>`);

        adicionaBotaoFooter(botaoAdicionar);
        adicionaBotaoFooter(botaoAlterar);
    }

    const cuponsAplicados = new Map();

    async function aplicarCupom(){
        let idCupomSelecionado = $('#cupons option:selected')[0].value;

        if (idCupomSelecionado == "" || Array.from(document.getElementsByName('idsCupons[]')).find(elem => elem.value == idCupomSelecionado))
            return;

        fetch('http://localhost:8080/emug/clientes/cupons?operacao=listarUnico&id=' + idCupomSelecionado)
        .then(result => result.json())
        .then(cupom => {

            cupom = cupom[0];

            let cupomAplicado =
            $(`<div class="toast align-items-center p-2 m-2 fade show" aria-live="assertive" aria-atomic="true" role="alert">
                <div class="d-flex justify-content-between">
                    <strong class="fw-bold">\${cupom.tipo}</strong>
                    <small>Validade: \${cupom.dataValidade}</small>
                    <button type="button" class="btn-close" onclick="removerCupomAplicado(\${cupom.id})" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>

                <div class="d-flex justify-content-between">
                    <p class="lead">\${cupom.nome}</p>
                    <strong class="lead fw-bold">R$ \${cupom.valor}</strong>
                </div>
            </div>`);
            
            let inputCupom = $(`<input type="hidden" name="idsCupons[]" value="\${cupom.id}">`);

            inputCupom.appendTo(document.getElementById("formFinalizarCompra"));
            cupomAplicado.appendTo(document.getElementById("cuponsAplicados"));

            cuponsAplicados.set(cupom.id, cupom.valor);

            atualizarValoresVenda();
        });
    }

    function removerCupomAplicado(idCupom){
        let inputCupomAplicado = Array.from(document.getElementsByName('idsCupons[]')).find(elem => elem.value == idCupom);
        inputCupomAplicado.remove();

        cuponsAplicados.delete(idCupom);
        atualizarValoresVenda();
    }

    const valorVenda = parseFloat('${carrinho.totalCarrinho}') + parseFloat('${valorFrete}');

    function atualizarValoresVenda(){
        let somaCupons = 0;
        cuponsAplicados.forEach((v) => {somaCupons += v;});

        document.getElementById("valorDesconto").innerText = "-R$ " + somaCupons;
        document.getElementById("vlrTotalPedido").innerText = "R$ " + (parseFloat(valorVenda) - parseFloat(somaCupons)) * 100.0 / 100.0 ;
    }
    
</script>

</html>