<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar - Estoque</title>

    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" href="<c:url value="/webjars/material-design-icons/4.0.0/material-icons.css" />"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/style.css" />"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Estoque</h5>

            <hr>

            <!-- Filtro de Estoque-->
            <div class="container">
                <form>
                    <div class="row g-3">

                        <div class="col-sm-4">
                            <small>Valor compra</small>
                            <div class="input-group">
                                <input class="form-control" id="minCompra" placeholder="R$ min" type="text">
                                <span class="input-group-text">-</span>
                                <input type="text" class="form-control" id="maxCompra" placeholder="R$ max">
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <small>Valor venda</small>
                            <div class="input-group">
                                <input class="form-control" id="minVenda" placeholder="R$ min" type="text">
                                <span class="input-group-text">-</span>
                                <input type="text" class="form-control" id="maxVenda" placeholder="R$ max">
                            </div>
                        </div>
                        
                        <div class="col-sm-12">
                            <button type="button" class="btn btn-primary btn-sm">Pesquisar</button>
                        </div>
                    </div>
                </form>
            </div>

            <hr>

            <div class="table-responsive p-3 rounded mb-4">
                <table class="table table-hover w-100">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Quant. Produtos</th>
                            <th>Valor compra</th>
                            <th>Valor venda</th>
                            <th>Quant. estoque</th>
                            <th>Limite venda</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                        <tr>
                            <td>nome</td>
                            <td>00</td>
                            <td>R$ 00,00</td>
                            <td>R$ 00,00</td>
                            <td>00</td>
                            <td>00</td>
                            <td>
                                <span class="material-icons">edit</span>
                            </td>
                        </tr>

                        <tr>
                            <td>nome</td>
                            <td>00</td>
                            <td>R$ 00,00</td>
                            <td>R$ 00,00</td>
                            <td>00</td>
                            <td>00</td>
                            <td>
                                <span class="material-icons">edit</span>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <a href="formularios/formProduto.jsp">
                    <button type="button" class="w-100 btn btn-primary btn-sm" >
                        Adicionar produto
                    </button>
                </a>
            </div>
        </div>
    </main>

    <br/>
    
    <jsp:include page="../include/footer.jsp"/>

    <script src="<c:url value="/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js" />"></script>
    <script src="<c:url value="/webjars/jquery/3.6.1/jquery.min.js" />"></script>
    <script src="<c:url value="/webjars/jquery-mask-plugin/1.14.16/dist/jquery.mask.min.js" />"></script>

    <script>
        $('#minVenda').mask("#.##0,00", {reverse: true});
        $('#maxVenda').mask("#.##0,00", {reverse: true});
        $('#minCompra').mask("#.##0,00", {reverse: true});
        $('#maxCompra').mask("#.##0,00", {reverse: true});
    </script>
</body>
</html>