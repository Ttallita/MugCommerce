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

                    <div class="col-12 align-self-center text-center">
                        <h5>Gráfico vendas</h5>
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
    <script src='<c:url value="/assets/js/lodash.js"/>'></script>

    <script>

        const mesesGrafico = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Aug', 'Set', 'Out', 'Nov', 'Dez']
        const baseUrl = 'http://localhost:8080/emug';


        $(document).ready(async () => {
            let url = new URL(baseUrl + "/adm/vendas")
            let params = { operacao: 'listarTodos', dashboard: true }

            url.search = new URLSearchParams(params).toString()

            let response = await fetch(url)
            let json = await response.json()

        
            let valores = getValoresDashboard(json)

            var options = {
                series: [{
                    name: "Vendas por mês",
                    data: valores
                }],
                chart: {
                    height: 350,
                    type: 'line',
                    zoom: {
                        enabled: false
                    }
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'straight'
                },
                title: {
                    text: 'Quantidade de vendas por mês',
                    align: 'left'
                },
                grid: {
                    row: {
                        colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                        opacity: 0.5
                    },
                },
                xaxis: {
                    categories: mesesGrafico,
                }
            };

            var chart = new ApexCharts(document.querySelector("#chart"), options);
            chart.render();
        }) 

        function getValoresDashboard(vendasAgrupadosPorMes) {
            let valoresGraficos = []
            mesesGrafico.forEach(function(valor, i) {
                let listaVendas = vendasAgrupadosPorMes[i + 1]

                if(listaVendas === undefined || listaVendas === null)
                    valoresGraficos.push(0)
                else
                    valoresGraficos.push(listaVendas.length)
            })

            return valoresGraficos
        }

    </script>
</body>

</html>