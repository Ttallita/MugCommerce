<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Gerenciar - Dashboard</title>

    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/5.2.0/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/webjars/material-design-icons/4.0.0/material-icons.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/apexcharts.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"/>
</head>

<body>

    <jsp:include page="../include/header.jsp" />
    
    <main class="d-flex flex-nowrap mt-5">

        <jsp:include page="../include/sidebarAdm.jsp" />
    
        <div class="w-75 bg-white rounded p-5">
            <h5>Dashboard</h5>

            <hr>

            <!-- Gráficos-->
            <div class="container">
                <div class="row g-5">
                    <label><h6>Periodo:</h6></label>

                    <div class="d-flex justify-content-between w-50">
                        <div class="input-group">
                            <span class="input-group-text" id="basic-addon3">De:</span>
                            <input type="date" class="form-control" id="dataInicio">
                            <span class="input-group-text">a</span>
                            <input type="date" class="form-control" id="dataFim">
                        </div>
                    </div>

                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-primary btn-sm" onclick="montaGraficoPeriodo()">Filtrar</button>
                    </div>
                    
                    <div class="col-12 align-self-center text-center">
                        <h5>Gráfico vendas</h5>
                        <br/><br/>
                        <div id="loading">
                            <div class="d-flex justify-content-center">
                                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                                  <span class="visually-hidden">Loading...</span>
                                </div>
                            </div>
                        </div>
                      
                        <div id="chart"></div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <br/>

    <jsp:include page="../include/footer.jsp"/>
    
    <script src="<c:url value='/webjars/bootstrap/5.2.0/js/bootstrap.bundle.min.js' />"></script>
    <script src="<c:url value='/webjars/jquery/3.6.1/jquery.min.js' />"></script>   
    <script src='<c:url value="/assets/js/apexcharts.min.js"/>'></script>

    <script>
        const baseUrl = 'http://localhost:8080/emug';

        $(document).ready(async () => {
            $('#loading').show()

            let url = new URL(baseUrl + "/adm/dashboard")
            let params = { operacao: 'listar' }

            url.search = new URLSearchParams(params).toString()

            let response = await fetch(url)
            let json = await response.json()

            montaGraficoVolumeVendasProduto(json)

            $('#loading').hide()

        }) 

        async function montaGraficoPeriodo() {

            $('#loading').show()

            let dataInicio = $('#dataInicio').val()
            let dataFim = $('#dataFim').val()
            
            let url = new URL(baseUrl + "/adm/dashboard")
            let params = { operacao: 'listar', dataInicio: dataInicio, dataFim: dataFim }

            url.search = new URLSearchParams(params).toString()

            let response = await fetch(url)
            let json = await response.json()

            montaGraficoVolumeVendasProduto(json)

            $('#loading').hide()
        }

        function montaGraficoVolumeVendasProduto(json) {
            var options = {
                series: json.options,
                chart: {
                    height: 650,
                    type: 'line',
                    zoom: {
                        enabled: false
                    },
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'straight',
                },
                title: {
                    text: 'Volume de venda de produtos por data',
                    align: 'left'
                },
                markers: {
                    size: 0,
                    hover: {
                        sizeOffset: 6
                    }
                },
                xaxis: {
                    categories: json.categories
                },
                grid: {
                    borderColor: '#f1f1f1',
                }
            };

            var chart = new ApexCharts(document.querySelector("#chart"), options);
            chart.render();

            options.series.forEach(data => chart.hideSeries(data.name));

            chart.showSeries(options.series[0].name)
        }

    </script>
</body>

</html>