<%-- 
    Document   : exibirFuncionario
    Created on : 10/10/2019, 19:39:46
    Author     : fernanda
--%>
<%@page import="br.senac.sp.narguile.pi3.model.Funcionario"%>
<%@page import="br.senac.sp.narguile.pi3.dao.FuncionarioDAO"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Exibir</title>
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
  
          <div class="form-horizontal">
            <fieldset>

                <legend> Resultado Funcionario </legend>
                            <form method="post" action="${pageContext.request.contextPath}/ExcluirFuncionario">
                                <div>                                    
                                    <table border= 1>
                                        <tr>
                                            <td align=middle width=100>Id</td>
                                            <td align=middle width=100>Nome</td>
                                            <td align=middle width=100>Cargo</td>
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
                                            <td align=middle><c:out value="${func.id}"/></td>
                                            <td align=middle><c:out value="${func.nome}"/></td>
                                            <td align=middle><c:out value="${func.cargo}" /></td>
                                            <td align=middle><c:out value="${func.endereco}" /></td>
                                            <td align=middle><c:out value="${func.bairro}" /></td>
                                            <td align=middle><c:out value="${func.cidade}" /></td>
                                            <td align=middle><c:out value="${func.estado}" /></td>
                                            <td align=middle><c:out value="${func.cep}" /></td>
                                            <td align=middle><c:out value="${func.sexo}" /></td>
                                            <td align=middle><c:out value="${func.telefone}" /></td>
                                            <td align=middle><c:out value="${func.celular}" /></td>
                                            <td align=middle><fmt:formatDate value="${func.dataCadastro}"/></td>
                                        </tr>
                                    </table>
                                </div>
                                <button type="submit" value="${func.id}" name="id">Excluir</button>
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/ConsultaFuncionario">
                                <button type="button" onclick="window.location.href = 'menu.jsp';">Voltar</button>
                            </form>
                            <form method="get" action="${pageContext.request.contextPath}/AlterarFuncionario">
                                <button type="submit" value="${func.id}" name="id">Alterar</button>
                            </form>
                        </div>
                    </div>
                </div>
         
    </body>
</html>