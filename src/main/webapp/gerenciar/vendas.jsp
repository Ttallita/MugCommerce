<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Vendas</title>

    <link rel="stylesheet" href="../webjars/bootstrap/5.2.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../webjars/material-design-icons/4.0.0/material-icons.css"/>
    <link rel="stylesheet" href="../assets/css/style.css"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Vendas</h5>

            <hr>

            <!-- Filtro de vendas-->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-4">
                            <small>Status</small>
                            <select class="form-select" aria-label="Default select example">
                                <option value="">Selecione</option>
                                <option value="">Aprovado</option>
                                <option value="">Entregue</option>
                                <option value="">Em processamento</option>
                                <option value="">Troca solicitada</option>
                            </select>
                        </div>

                        <div class="col-sm-4">
                            <small>Data de compra</small>
                            <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="">
                        </div>

                        <div class="col-sm-4">
                            <small>Data de entrega</small>
                            <input type="date" class="form-control" id="dtNascimento" name="dtNascimento" value="">
                        </div>
                        
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>

                    </div>

                </form>
            </div>



            <div class="tab-content" id="pills-tabContent">

                <div class="tab-pane fade show active" id="pills-gerenciar-vendas" role="tabpanel" aria-labelledby="pills-gerenciar-vendas-tab" tabindex="0">
                    
                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Quant. Produtos</th>
                                <th>Valor</th>
                                <th>Data compra</th>
                                <th>Data entrega</th>
                                <th>Status</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Aprovado
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary btn-sm"><span class="material-icons">done</span></button>
                                    <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane fade" id="pills-avancado" role="tabpanel" aria-labelledby="pills-avancado-tab" tabindex="0">
                    <table class="table table-hover" width="100%">
                        <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>Quant. Produtos</th>
                                <th>Valor</th>
                                <th>Data compra</th>
                                <th>Data entrega</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Aprovado
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
    
                            <tr>
                                <td>nome sobrenome</td>
                                <td>00</td>
                                <td>R$ 00,00</td>
                                <td>00/00/0000</td>
                                <td>00/00/0000</td>
                                <td>
                                    Entregue
                                    <span class="material-icons">edit</span>
                                </td>
                            </tr>
            
                        </tbody>
                    </table>
                </div>

            </div>
              
            <!-- Modal Detalhes venda-->
            <div class="modal fade" id="detalhesCompra" tabindex="-1" aria-labelledby="detalhesCompraLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                    <h4 class="modal-title fw-bold" id="detalhesCompraLabel">Detalhes compra</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body m-5">

                        <h6 class="fw-bold">00/00/0000</h6>
                        <h6>Status</h6>
                        <h6>Chegou no dia 00/00/0000</h6>

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

                            <div class="col-2">
                                <button type="button" class="btn btn-primary btn-sm">Trocar</button>
                            </div>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Cancelar compra</button>
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Fechar</button>
                    </div>

                </div>
                </div>
            </div>
            
        </div>
    </main>

    <br/>

    <jsp:include page="../include/footer.jsp"/>

    <script src="/emug/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</body>

</html>