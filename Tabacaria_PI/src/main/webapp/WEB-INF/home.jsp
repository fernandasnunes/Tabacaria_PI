<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <link href="menuformatacao.css" type="text/css" rel="stylesheet" />
        <title>Home Page</title>
        <meta charset="UTF-8">

    </head>
    <body>


        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">


        </script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <c:choose>
            <c:when test="${sessionScope.usuario != null}">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a class="navbar-brand" href="">Navbar</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Alterna navegação">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.request.contextPath}/pgProtect/Home">Home<span class="sr-only"></span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/pgProtect/Cliente">Cliente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/pgProtect/Funcionario">Funcionário</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.request.contextPath}/pgProtect/Produto">Produto<span class="sr-only"></span></a>
                            </li>
                            <c:if test="${sessionScope.usuario.verificarPapel('GERENTE')}">
                                <li class="nav-item ">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/pgProtect/Filial">Filial<span class="sr-only"></span></a>
                                </li>
                            </c:if>
                            <li class="nav-item ">
                                <a class="nav-link" href="${pageContext.request.contextPath}/logout">SAIR DO SISTEMA<span class="sr-only"></span></a>
                            </li>
                            Seja bem vindo <c:out value="${sessionScope.usuario.nomeCompleto}" />
                            <c:forEach items="${sessionScope.usuario.papeis}" var="pap">
                                <c:out value="CARGO:${pap.nome}" />
                            </c:forEach>
                    </div>
                </ul>
            </div>
        </nav>
    </c:when>
</c:choose>
</body>

</html>
