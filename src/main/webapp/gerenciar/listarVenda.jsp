<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang=pt-br>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Venda</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />

    <div class="container card w-50 mt-5 mb-5">

        <jsp:include page="../include/alert.jsp" />

        <h3 class="text-center mt-5">Detalhes venda</h3>

        <div class="container overflow-hidden">
            <div class="row">

                <div class="col-12">
                    <div class="p-3 border">
                        <h6>Dados cliente</h6>
                        <ul class="list-group">
                            <li class="list-group-item">Nome: Nome sobrenome cliente</li>
                            <li class="list-group-item">CPF: 000.000.000-00</li>
                            <li class="list-group-item">Telefone: (00) 00000-0000</li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Dados gerais</h6>
                        <ul class="list-group">
                            <li class="list-group-item">Data compra: 00/00/0000 </li>
                            <li class="list-group-item">Data entrega: 00/00/0000 </li>
                            <li class="list-group-item">Valor total: 00,00 </li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Endereços</h6>
                        <ul class="list-group">
                            <li class="list-group-item">
                                Endereço entrega:
                                Endereço Endereço Endereço Endereço Endereço Endereço
                            </li>
                            <li class="list-group-item">
                                Endereço cobrança:
                                Endereço Endereço Endereço Endereço Endereço Endereço
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Produtos</h6>

                        <ul class="list-group">
                            <li class="list-group-item">
                                <!-- Produto -->
                                <div class="row p-3">
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
                            </li>

                            <li class="list-group-item">
                                <!-- Produto -->
                                <div class="row p-3">
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
                            </li>
                        </ul>
                
                    </div>
                </div>

                <div class="col-12">
                    <div class="p-3 border bg-light">
                        <h6>Status</h6>
                       
                        <ul class="list-group">
                            <li class="list-group-item">
                                Status atual:
                                Em processamento
                            </li>

                            <li class="list-group-item">
                                Novo status:
                                <form>
                                    <select class="form-select" aria-label="Default select example">
                                        <option value="">Selecione</option>
                                        <option value="1">One</option>
                                        <option value="2">Two</option>
                                        <option value="3">Three</option>
                                    </select>

                                    <button type="button" class="w-100 btn btn-primary btn-sm">
                                        Atualizar status
                                    </button>
                                </form>
                            </li>
                        </ul>
                        
                    </div>
                </div>
                
            </div>
        </div>

    </div>

    <jsp:include page="../include/footer.jsp" />

</body>

<script src="<c:url value='webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js'/>"></script>
<script src="<c:url value='webjars/jquery/3.6.1/jquery.min.js'/>"></script>
<script src="<c:url value='webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js'/>"></script>
<script src="assets/js/geral.js"></script>

</html>