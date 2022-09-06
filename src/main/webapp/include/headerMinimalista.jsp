<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>

    <nav class="navbar navbar-expand-lg bg-dark navbar-dark ">

        <div class="container">

            <a href='<c:url value="/index.jsp"/>'>
                <img src='<c:url value="/assets/img/logo_branco.png"/>' alt="logo" class="logo w-50">
            </a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Produtos</a>
                    </li>
                </ul>
            </div>

        </div>
        
    </nav>
    
</header>
