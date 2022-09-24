<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produtos</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>
<body>

    <jsp:include page="include/header.jsp" />

    <main class="wrapper container">

        <div class="row mt-4 bg-white rounded p-4 mb-3">
            <div class="col-md-12 p-3 border rounded mb-5">
                1-10 de 50 resultados para <strong class="text-muted">"palavra pesquisada"</strong>
            </div>
            <div class="col-xl-3 col-lg-4 col-md-5">
                <h5 class="fw-bolder">Categoria</h5>
                <ul class="list-group mt-4">
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">Anime</a>
                    </li>
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">Series</a>
                    </li>
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">Mundo Geek</a>
                    </li>
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">Jogos</a>
                    </li>
                </ul>

                <br/>
                <h5 class="fw-bolder">Preço</h5>
                <ul class="list-group mt-4">
                    <li class="d-flex  justify-content-between">
                        <a class="text-decoration-none" href="#">Até $50,00</a>
                        <h6 class="text-muted align-middle">(5)</h6>
                    </li>
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">R$ 50,00 a R$ 150,00 </a>
                        <h6 class="text-muted">(5)</h6>
                    </li>
                    <li class="d-flex justify-content-between">
                        <a class="text-decoration-none" href="#">Mais de R$ 150,00</a>
                        <h6 class="text-muted">(5)</h6>
                    </li>
                    <li class="d-flex justify-content-between ">
                        <div class="input-group">
                            <input class="form-control" placeholder="R$ min" type="text">
                            <span class="input-group-text">-</span>
                            <input type="text" class="form-control" placeholder="R$ max">
                        </div>
                    </li>
                </ul>
            </div>

            <div class="col-lg-9">
                <div class="row mb-4">
                    <div class="col-12">
                        <div class="btn-group float-end">
                            <button type="button" data-bs-toggle="dropdown" aria-expanded="false"
                                    class="btn btn-primary dropdown-toggle" id="ordenarResultados">Ordenar por</button>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="">
                                <li><a class="dropdown-item" href="#">Mais relevantes</a></li>
                                <li><a class="dropdown-item" href="#">Maior preço</a></li>
                                <li><a class="dropdown-item" href="#">Menor preço</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="row row-cols-xl-4">
                    <div class="col mb-3 ">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg"
                                 alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg" alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg" alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <a href="produto.jsp">
                                    <h5 class="fw-bolder">Caneca</h5>
                                </a>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg"
                                 alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg"
                                 alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg"
                                 alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg" alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg"
                                 alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-3">
                        <div class="card produto">
                            <img class="p-3" src="\emug\assets\img\canecas\caneca_porco.jpeg" alt="...">
                            <div class="card-body pt-0">
                                <h6>Canecas</h6>
                                <h5 class="fw-bolder">Caneca</h5>
                                <div class="row">
                                    <div class="col-8">R$40.00</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <div style="z-index: 11" class="position-fixed bottom-0 end-0 p-2">
            <div id="liveToast" role="alert" aria-live="assertive" aria-atomic="true" class="toast fade hide">
                <div class="toast-body">
                    <img src="\emug\assets\img\pelucias\caneca_porco.jpeg" alt="..." class="rounded me-2 w-25">
                </div>
                <div class="toast-header">
                    <strong class="me-auto">Hatsune</strong><small>adicionado ao carrinho</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="include/footer.jsp"/>

</body>

<script src="webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>

</html>