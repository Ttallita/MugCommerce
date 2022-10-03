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
                        <a type="button" id="alterarEndereco" onclick="listarEnderecosEntrega()" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#alterarEnderecoModal">
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
                            <a type="button" id="alterarPagamento" class="text-decoration-none" data-bs-toggle="modal"
                               data-bs-target="#alterarFormaPagamentoModal">
                                Alterar
                            </a>
                            <p>(Crédito) com final ${cartaoCredito.numCartao}
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

                    <c:forEach var="produto" items="${produtos}">
                        <div class="row border-top p-3">
                            <div class="col-3">
                                <div class="card produto mb-3">
                                    <img alt="imagem_produto" src="${produto.imagem}" class="p-2">
                                </div>
                            </div>
                            <div class="col">
                                <h4>R$ 100,00</h4>Quantidade: 1
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>

            <div class="col-lg-4 order-md-last">
                <div class="row vstack mt-5">
                    <div class="container border rounded py-4">
                        <ul class="list-group">
                            <li class="d-flex mb-4 justify-content-between">
                                <h5 class="fw-bold">Resumo do pedido</h5>
                            </li>
                            <li class="d-flex justify-content-between">
                                <strong class="text-muted">Itens:</strong>
                                <strong>R$ ${valorItens}</strong>
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
                                <h5 class="font-weight-bold">R$ ${valorTotal}</h5>
                            </li>
                            <a href="/emug/cliente/pedidoConfirmado.jsp" class="btn btn-primary  rounded-pill py-2">Confirmar pedido</a>
                        </ul>
                    </div>
                </div>
            </div>
            
        </div>

        <!-- Modal endereco-->
        <div class="modal fade" id="alterarEnderecoModal" tabindex="-1" aria-labelledby="alterarEnderecoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="alterarEnderecoModalLabel">Escolha o endereço</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="checksEnderecosEntrega">
                            <!-- Lista de endereços -->
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Alterar</button>
                        <button type="button" class="btn btn-secondary">Adicionar outro endereço</button>
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

async function listaProdutos(url) {
        let response = await fetch(url)
        let json = await response.json();

        json.forEach(endereco => {

            let checkEndereco =
            $(`<div class="form-check">
                <input class="form-check-input" type="radio" name="endereco" id="endereco1">
                <label class="form-check-label" for="endereco1">
                    "${endereco.apelido}"
                </label>
                <small class="float-end"><a href="">Editar</a> </small>
            </div>`);


            checkEndereco.appendTo(document.getElementById("checksEnderecosEntrega"));
        })
    }

    function listarEnderecosEntrega(){
        const baseUrl = 'http://localhost:8080/emug';
        let params = { operacao: 'listarJson'}

        let urlProdutos = montaUrl(baseUrl, 'clientes/enderecos', params)

        listaProdutos(urlProdutos)
    }
</script>

</html>