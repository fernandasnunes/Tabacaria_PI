<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Gerenciamento de Funcionarios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

     
    </head>
    <body><c:choose>
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
    <center>
  <h1>Gerenciamento de Funcionarios</h1>
        <h2>
         <a href="${pageContext.request.contextPath}/pgProtect/Funcionario/Novo">Novo Funcionario</a>
</h2>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
           
        <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Cargo</th>
                <th>Ações</th>
            </tr>
            <c:forEach var="funcionario" items="${listaFuncionarios}">
           
                <tr>
                    <td><c:out value="${funcionario.id}" /></td>
                    <td><c:out value="${funcionario.nome}" /></td>
                    <td><c:out value="${funcionario.idCargo}" /></td>
                    <td>
                     <a href="${pageContext.request.contextPath}/pgProtect/Funcionario/Editar?id=<c:out value='${funcionario.id}' />">Editar</a>
                     
                     <a href="${pageContext.request.contextPath}/pgProtect/Funcionario/Deletar?id=<c:out value='${funcionario.id}' />">Deletar</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div> 
  
    </body>
</html>


