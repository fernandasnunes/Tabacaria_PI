<%-- 
    Document   : resultadoConsulta
    Created on : 08/10/2019, 09:43:38
    Author     : fernanda
--%>

<%@page import="br.senac.sp.narguile.pi3.model.Cliente"%>
<%@page import="br.senac.sp.narguile.pi3.dao.ClienteDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Consulta de dados do cliente</title>
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
                        <br>
                        <div>
                            <form method="post" action="${pageContext.request.contextPath}/ExcluirCliente">
                                <div>                                    
                                    <table border= 1>
                                        <tr>
                                            <td align=middle width=100>Id</td>
                                            <td align=middle width=100>Nome</td>
                                            <td align=middle width=100>Endereco</td>
                                            <td align=middle width=120>Bairro</td>
                                            <td align=middle width=100>Cidade</td>
                                            <td align=middle width=10>Estado</td>
                                            <td align=middle width=100>Cep</td>
                                            <td align=middle width=10>Sexo</td>
                                            <td align=middle width=100>Telefone</td>
                                            <td align=middle width=100>Celular</td>
                                            <td align=middle width=150>Data de Cadastro</td>
                                        </tr>
                                        <tr>
                                            <td align=middle><c:out value="${cliente.id}"/></td>
                                            <td align=middle><c:out value="${cliente.nome}"/></td>
                                            <td align=middle><c:out value="${cliente.endereco}" /></td>
                                            <td align=middle><c:out value="${cliente.bairro}" /></td>
                                            <td align=middle><c:out value="${cliente.cidade}" /></td>
                                            <td align=middle><c:out value="${cliente.estado}" /></td>
                                            <td align=middle><c:out value="${cliente.cep}" /></td>
                                            <td align=middle><c:out value="${cliente.sexo}" /></td>
                                            <td align=middle><c:out value="${cliente.telefone}" /></td>
                                            <td align=middle><c:out value="${cliente.celular}" /></td>
                                            <td align=middle><fmt:formatDate value="${cliente.cadastroData}"/></td>
                                        </tr>
                                    </table>
                                </div>
                                <button type="submit" value="${cliente.id}" name="id">Excluir</button>
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ConsultarCliente">
                                <button type="button" onclick="window.location.href = 'menu.jsp';">Voltar</button>
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/AlterarCliente">
                                <button type="submit" value="${cliente.id}" name="id">Alterar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </nav>
    </body>
</html>
