<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Gerenciar - Solicitações</title>

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

            <!-- Filtro de solicitações -->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-4">
                            <small>Status</small>
                            <select class="form-select" aria-label="Default select example">
                                <option value="">Selecione</option>
                                <option value="">Troca solicitada</option>
                            </select>
                        </div>
                        
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>

                    </div>

                </form>
            </div>

            <hr>

            <div class="container">

                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="pills-gerenciar-solicitacoes-tab" data-bs-toggle="pill" data-bs-target="#pills-gerenciar-solicitacoes" type="button" role="tab" aria-controls="pills-gerenciar-solicitacoes" aria-selected="true">Gerenciar solicitações</button>
                    </li>

                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="pills-avancado-tab" data-bs-toggle="pill" data-bs-target="#pills-avancado" type="button" role="tab" aria-controls="pills-avancado" aria-selected="false">Avançado</button>
                    </li>

                </ul>


                <div class="tab-content" id="pills-tabContent">

                    <div class="tab-pane fade show active" id="pills-gerenciar-solicitacoes" role="tabpanel" aria-labelledby="pills-gerenciar-solicitacoes-tab" tabindex="0">
                        
                        <table class="table table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th>Nome produto</th>
                                    <th>Cliente</th>
                                    <th>Quant.</th>
                                    <th>Valor</th>
                                    <th>Data compra</th>
                                    <th>Data entrega</th>
                                    <th>Status</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
        
                                <tr>
                                    <td>nome produto</td>
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
                                        <button type="button" class="btn btn-primary btn-sm"><span class="material-icons">clear</span></button>
                                    </td>
                                </tr>
                
                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane fade" id="pills-avancado" role="tabpanel" aria-labelledby="pills-avancado-tab" tabindex="0">
                        <table class="table table-hover" width="100%">
                            <thead>
                                <tr>
                                    <th>Nome produto</th>
                                    <th>Cliente</th>
                                    <th>Quant.</th>
                                    <th>Valor</th>
                                    <th>Data compra</th>
                                    <th>Data entrega</th>
                                    <th>Status</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
        
                                <tr>
                                    <td>nome produto</td>
                                    <td>nome sobrenome</td>
                                    <td>00</td>
                                    <td>R$ 00,00</td>
                                    <td>00/00/0000</td>
                                    <td>00/00/0000</td>
                                    <td>
                                        Aprovado
                                    </td>
                                    <td>
                                        <a href="/emug/gerenciar/listarSolicitacao.jsp" name="botaoListarVenda" type="button" class="btn btn-primary btn-sm">
                                            <span class="material-icons">edit</span>
                                        </a>
                                    </td>
                                </tr>

                                <tr>
                                    <td>nome produto</td>
                                    <td>nome sobrenome</td>
                                    <td>00</td>
                                    <td>R$ 00,00</td>
                                    <td>00/00/0000</td>
                                    <td>00/00/0000</td>
                                    <td>
                                        Aprovado
                                    </td>
                                    <td>
                                        <a href="/emug/gerenciar/listarSolicitacao.jsp" name="botaoListarVenda" type="button" class="btn btn-primary btn-sm">
                                            <span class="material-icons">edit</span>
                                        </a>
                                    </td>
                                </tr>
                
                            </tbody>
                        </table>
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