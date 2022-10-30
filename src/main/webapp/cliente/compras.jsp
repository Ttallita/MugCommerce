<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil - Compras</title>

    <link rel="stylesheet" href="../webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarCliente.jsp" />

        <div class="w-75 bg-white rounded p-5">
            <h5>Compras</h5>

            <hr>

            <div class="container">

                <form>
                    <div class="row g-3 p-4">

                        <table class="table table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th>Data de compra</th>
                                    <th>Valor</th>
                                    <th>Frete</th>
                                    <th>Status</th>
                                    <th>Data de envio</th>
                                    <th>Data de entrega</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <c:forEach var="compra" items="${compras}">
                                    <tr>
                                        <fmt:parseDate  value="${compra.dataCompra}"  type="date" pattern="yyyy-MM-dd" var="dataCompraParseada" />
                                        <fmt:formatDate value="${dataCompraParseada}" type="date" pattern="dd/MM/yyyy" var="dataCompraFormatada" />
                                        <td>${dataCompraFormatada}</td>

                                        <td>R$ ${compra.precoTotal}</td>
                                        <td>R$ ${compra.frete}</td>
                                        <td>${compra.vendaStatus.nomeExibicao}</td>

                                        <fmt:parseDate  value="${compra.dataEnvio}"  type="date" pattern="yyyy-MM-dd" var="dataEnvioParseada" />
                                        <fmt:formatDate value="${dataEnvioParseada}" type="date" pattern="dd/MM/yyyy" var="dataEnvioFormatada" />
                                        <td>${dataEnvioFormatada}</td>

                                        <fmt:parseDate  value="${compra.dataEntrega}"  type="date" pattern="yyyy-MM-dd" var="dataEntregaParseada" />
                                        <fmt:formatDate value="${dataEntregaParseada}" type="date" pattern="dd/MM/yyyy" var="dataEntregaFormatada" />
                                        <td>${dataEntregaFormatada}</td>

                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm" onclick="montarModal('clientes/compras', '${compra.id}')" data-bs-toggle="modal" data-bs-target="#modal"><span class="material-icons">remove_red_eye</span></button>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </form>
            </div>

            <hr>
  
        </div>
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

    async function listaItensModal(url, idVenda) {
        let response = await fetch(url)
        let json = await response.json();

        limpaModal();
        
        json.forEach(venda => {
            let detalhesVenda =
                $(`<ul class="list-unstyled">
                    <li><h6 class="fw-bold">\${venda.dataCompra}</h6></li>
                    <li><h6>\${venda.vendaStatus}</h6></li>
                    <li>Valor total da venda: R$ \${venda.precoTotal}</li>
                    <li>Frete: R$ \${venda.frete}</li>
                </ul>`);

            if (venda.dataEntrega != null)
                $(`<li>Chegou no dia \${venda.dataEntrega}</li>`).appendTo(detalhesVenda);

            if (venda.dataEnvio != null)
                $(`<li>Data de envio: \${venda.dataEnvio}</li>`).appendTo(detalhesVenda);

            adicionaItemBodyModal(detalhesVenda);
            
            venda.carrinho.itensCarrinho.forEach(i => {

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


                // --- Ação troca
                let colAcaoTrocaItem = document.createElement("div")
                colAcaoTrocaItem.classList.add("col-2")

                let linkTrocar = document.createElement("a")
                linkTrocar.href = "<c:url value='/clientes/trocas?operacao=salvar'/>" + "&idProduto=" + i.produto.id + "&idVenda=" + venda.id
                

                if (venda.vendaStatus != 'Cancelada'){
                    if (i.emTroca) {
                        let texto = document.createElement("small")
                        texto.classList.add("text-muted")
                        texto.innerHTML = "Em troca"

                        colAcaoTrocaItem.appendChild(texto)
                        
                    } else {
                        let botaoTrocar = document.createElement("button")
                        botaoTrocar.type = "button"
                        botaoTrocar.classList.add("btn")
                        botaoTrocar.classList.add("btn-primary")
                        botaoTrocar.classList.add("btn-sm")
                        botaoTrocar.innerHTML = "Solicitar troca"
                        
                        linkTrocar.appendChild(botaoTrocar)
                        colAcaoTrocaItem.appendChild(linkTrocar)
                    }
                }
                

                let divItem = document.createElement("div")
                divItem.appendChild(colImagem)
                divItem.appendChild(colDetalhesItem)
                divItem.appendChild(colAcaoTrocaItem)

                divItem.classList.add("row")
                divItem.classList.add("border")
                divItem.classList.add("rounded")
                divItem.classList.add("p-3")

                document.getElementById("modalBody").appendChild(divItem);
            }); 

            setTituloModal("Detalhes compra");

            let botaoFecharModal = $('<button type="button" class="btn btn-primary" data-bs-dismiss="modal">Fechar</button>');

            let botaoCancelar;

            if (venda.vendaStatus != 'Cancelada'){
                let urlCancelarVenda = "<c:url value='/clientes/cancelamentos?operacao=salvar&idVenda='/>" + venda.id;
                botaoCancelar =
                        $(`<a href="\${urlCancelarVenda}">
                            <button type="button" class="btn btn-primary" id="botaoCancelarCompra">Cancelar compra</button>
                        </a>`);
            } else {
                botaoCancelar =
                        $(`<button type="button" class="btn btn-primary" id="botaoCancelarCompra" disabled>Cancelar compra</button>`);
                
            }

            adicionaBotaoFooter(botaoCancelar);
            adicionaBotaoFooter(botaoFecharModal);

        });
        
    }

    function montarModal(path, idVenda){
        let params = { operacao: 'listarJson',
                        id: idVenda};

        let urlItensModal = montaUrl(baseUrl, path, params);

        listaItensModal(urlItensModal, idVenda);
    }


</script>

</html>