<%-- 
    Document   : exibirProduto
    Created on : 10/10/2019, 20:28:35
    Author     : fernanda
--%>

<%@page import="br.senac.sp.narguile.pi3.model.Produto"%>
<%@page import="java.sql.Connection"%>
<%@page import="br.senac.sp.narguile.pi3.dao.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Exibir Produto</title>
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
        <nav>
            <h2 style="color: white">Resultado..</h2>
            <main class="container">
                <div class="row">
                    <div class="col-12">                       
                        <div>
                            <form method="post" action="${pageContext.request.contextPath}/ExcluirProduto">
                                <div>                                    
                                    <table border= 1>
                                        <tr>
                                            <td align=middle width=100>Id</td>
                                            <td align=middle width=100>Nome</td>
                                            <td align=middle width=100>Marca</td>
                                            <td align=middle width=100>Descrição</td>
                                            <td align=middle width=120>Preço Compra</td>
                                            <td align=middle width=100>Preço Venda</td>
                                            <td align=middle width=100>Quantidade</td>
                                            <td align=middle width=150>Data de Cadastro</td>
                                        </tr>
                                        <tr>
                                            <td align=middle><c:out value="${prod.id}"/></td>
                                            <td align=middle><c:out value="${prod.nome}"/></td>
                                            <td align=middle><c:out value="${prod.marca}" /></td>
                                            <td align=middle><c:out value="${prod.descricao}" /></td>
                                            <td align=middle><fmt:formatNumber value="${prod.precoCompra}" type="currency" /></td>
                                            <td align=middle><fmt:formatNumber value="${prod.precoVenda}" type="currency" /></td>
                                            <td align=middle><c:out value="${prod.quantidade}" /></td>
                                            <td align=middle><fmt:formatDate value="${prod.dataCadastro}"/></td>
                                        </tr>
                                    </table>
                                </div>
                                <br>
                                <div>
                                    <button type="submit" value="${prod.id}" name="id">Excluir</button>

                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ConsultaProduto">
                                <button type="button" onclick="window.location.href = 'menu.jsp';">Voltar</button>
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/AlterarProduto">
                                <button type="submit" value="${prod.id}" name="id">Alterar</button>
                            </form>
                        </div>
                    </div>
                </div>
                </div>
            </main>
        </nav>
    </body>
</html>
