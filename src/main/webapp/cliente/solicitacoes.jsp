<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="paginaCorrente" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="nomePaginaCorrente" value="${ fn:contains(paginaCorrente, 'cancelamentos') ? 'Cancelamentos' : 'Trocas'}"/>

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
                                    <th>Quant. Produtos</th>
                                    <th>Valor</th>
                                    <th>Tipo</th>
                                    <th>Status</th>
                                    <th>Data de entrega</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="solicitacao" items="${solicitacoes}">
                                    <tr>
                                        <td>00/00/0000</td>
                                        <td>${solicitacao}</td>
                                        <td>R$ 00,00</td>
                                        <td>Troca</td>
                                        <td>${solicitacao.status.nomeExibicao}</td>
                                        <td>00/00/0000</td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#detalhesSolicitacao"><span class="material-icons">remove_red_eye</span></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </form>
            </div>

            <hr>

            <!-- Modal Detalhes Solicitação-->
            <div class="modal fade" id="detalhesSolicitacao" tabindex="-1" aria-labelledby="detalhesSolicitacaoLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title fw-bold" id="detalhesSolicitacaoLabel">Detalhes ${nomePaginaCorrente}</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body m-5">

                            <h6>Status</h6>
                            <h6>Data solicitação: 00/00/0000</h6>

                            <!-- Produto -->
                            <div class="row border rounded p-3">
                                <div class="col-2">
                                    <div class="card produto mb-3">
                                        <img alt="produto" src="\emug\assets\img\canecas\caneca_porco.jpeg" class="p-2">
                                    </div>
                                </div>

                                <div class="col">
                                    <h6 class="text-muted">Categoria</h6>
                                    <h5>Nome produto</h5>
                                    <h6>Quantidade: 00</h6>
                                    <h6>Valor: R$ 00,00</h6>
                                </div>
                            </div>

                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Fechar</button>
                        </div>

                    </div>
                </div>
            </div>
  
        </div>
    </main>

    <br/>

    <jsp:include page="../include/footer.jsp"/>
    
    <script src="../webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
</body>

</html>