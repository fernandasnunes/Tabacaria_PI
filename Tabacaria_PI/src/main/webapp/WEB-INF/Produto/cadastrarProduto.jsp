<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Cadastrar Produto</title>
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
    <body>
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
        <div align="center">
            <c:if test="${produto != null}">
                <form action="Edicao" method="post">
                </c:if>
                <c:if test="${produto == null}">
                    <form action="Inserir" method="post">
                    </c:if>
                    <table border="1" cellpadding="5">
                        <caption>
                            <h2>
                                <c:if test="${produto != null}">
                                    Editar Produto
                                </c:if>
                                <c:if test="${produto == null}">
                                    Novo Produto
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${produto != null}">
                            <input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
                        </c:if>            
                        <tr>
                            <th>Nome</th>
                            <td>
                                <input type="text" name="nome" size="45" id="nome"
                                       value="<c:out value='${produto.nome}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Marca</th>
                            <td>
                                <input type="text" name="marca" size="45"
                                       value="<c:out value='${produto.marca}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Descrição</th>
                            <td>
                                <input type="text" name="descricao" size="45"
                                       value="<c:out value='${produto.descricao}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                        <tr>
                            <th>Preço Compra</th>
                            <td>
                                <input type="number" name="preco_compra" step="any" size="15"
                                       value="<c:out value='${produto.precoCompra}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Preço Venda</th>
                            <td>
                                <input type="number" name="preco_venda" step="any" size="15"
                                       value="<c:out value='${produto.precoVenda}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Quantidade</th>
                            <td>
                                <input type="number" name="quantidade" size="15"
                                       value="<c:out value='${produto.quantidade}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Data de cadastro</th>
                            <td>
                                <c:out value='${produto.dataCadastro}'></c:out>

                                </td>
                            </tr>

                            <tr>
                                <td colspan="2" align="center">
                                    <input type="submit" value="Salvar" />
                                    <input type="button" value="Cancel" onclick="javascript:window.location = '${pageContext.request.contextPath}/pgProtect/Produto';" />

                        </tr>
                    </table>
                </form>
        </div>

    </body>
</html>