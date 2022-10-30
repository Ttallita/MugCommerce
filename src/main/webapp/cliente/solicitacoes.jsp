<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="paginaCorrente" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="isCancelamento" value="${ fn:contains(paginaCorrente, 'cancelamentos') }"/>

<c:set var="nomePaginaCorrente" value="${ isCancelamento ? 'Cancelamentos' : 'Trocas'}"/>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - ${nomePaginaCorrente}</title>

    <link rel="stylesheet" href="../webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>${nomePaginaCorrente}</h5>
            
            <hr>

            <div class="container">

                <form>
                    <div class="row g-3 p-4">

                        <table class="table table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th>Data de solicitação</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="solicitacao" items="${solicitacoes}">
                                    <tr>
                                        <fmt:parseDate  value="${solicitacao.data}"  type="date" pattern="yyyy-MM-dd" var="dataParseada" />
                                        <fmt:formatDate value="${dataParseada}" type="date" pattern="dd/MM/yyyy" var="dataFormatada" />
                                        <td>${dataFormatada}</td>
                                        <td>${solicitacao.status.nomeExibicao}</td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm" onclick="montarModal('${solicitacao.id}')" data-bs-toggle="modal" data-bs-target="#modal"><span class="material-icons">remove_red_eye</span></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </form>
            </div>

            <hr>

    </main>

    <br/>

    <jsp:include page="../include/modalBase.jsp" />
    <jsp:include page="../include/footer.jsp"/>
    
</body>

<script src="../webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
<script src='<c:url value="../webjars/jquery/3.6.1/jquery.min.js"/>'></script>
<script src='<c:url value="../assets/js/geral.js"/>'></script>
<script src='<c:url value="../assets/js/construir-modal.js"/>'></script>

<script>
    async function listaItensModal(url, idSolicitacao) {
        let response = await fetch(url)
        let json = await response.json();

        limpaModal();
        
        json.forEach(solicitacao => {

            if (${isCancelamento}) {
                
                let detalhesVenda =
                    $(`<h5>Detalhes da venda cancelada:</h5>
                    <ul class="list-unstyled">
                        <li><h6 class="fw-bold">\${solicitacao.venda.dataCompra}</h6></li>
                        <li><h6>\${solicitacao.venda.vendaStatus}</h6></li>
                        <li>Valor total da venda: R$ \${solicitacao.venda.precoTotal}</li>
                        <li>Frete: R$ \${solicitacao.venda.frete}</li>
                    </ul>`);

                if (solicitacao.venda.dataEntrega != null)
                    $(`<li>Chegou no dia \${solicitacao.venda.dataEntrega}</li>`).appendTo(detalhesVenda);

                if (solicitacao.venda.dataEnvio != null)
                    $(`<li>Data de envio: \${solicitacao.venda.dataEnvio}</li>`).appendTo(detalhesVenda);

                adicionaItemBodyModal(detalhesVenda);
            }

            let itens;

            if (${isCancelamento}){
                itens = solicitacao.venda.carrinho.itensCarrinho
            } else {
                itens = solicitacao.venda.carrinho.itensCarrinho.filter(i => i.produto.id == solicitacao.produto.id)
            }

            itens.forEach(i => {

                // --- Imagem Item
                let colImagem = document.createElement("div")
                colImagem.classList.add("col-2")

                let divImagem = document.createElement("div")
                divImagem.classList.add("card")
                divImagem.classList.add("produto")
                divImagem.classList.add("mb-3")

                let imagem = $('<img>')
                imagem.attr("alt", i.produto.nome)
                imagem.prop("src", i.produto.imagem)
                imagem.prop("class", "p-2")

                divImagem.appendChild(imagem[0])
                colImagem.appendChild(divImagem)


                // --- Detalhes
                let colDetalhesItem = document.createElement("div")
                colDetalhesItem.classList.add("col")

                let nomeItem = document.createElement("h6")
                let quantItem = document.createElement("h6")
                let valorItem = document.createElement("h6")
                nomeItem.innerHTML = i.produto.nome
                quantItem.innerHTML = "Quantidade: " + i.quant
                valorItem.innerHTML = "Valor: R$ " + i.produto.valorVenda

                colDetalhesItem.appendChild(nomeItem)
                colDetalhesItem.appendChild(quantItem)
                colDetalhesItem.appendChild(valorItem)

                let divItem = document.createElement("div")
                divItem.appendChild(colImagem)
                divItem.appendChild(colDetalhesItem)

                divItem.classList.add("row")
                divItem.classList.add("border")
                divItem.classList.add("rounded")
                divItem.classList.add("p-3")

                document.getElementById("modalBody").appendChild(divItem);
            });

            let botaoFechar = $(`<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Fechar</button>`);
            adicionaBotaoFooter(botaoFechar);
            setTituloModal("Detalhes ${nomePaginaCorrente}");

        });
        
    }

    const path = "clientes/" + '${isCancelamento ? "cancelamentos" : "trocas"}' ;

    function montarModal(idSolicitacao){
        let params = { operacao: 'listarJson',
                        id: idSolicitacao};

        let urlItensModal = montaUrl(baseUrl, path, params);

        listaItensModal(urlItensModal, idSolicitacao);
    }
</script>

</html>