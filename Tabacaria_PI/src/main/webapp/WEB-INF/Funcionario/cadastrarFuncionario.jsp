
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Cadastro de Funcionário</title>
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
        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        
        <center>
  <h1>Gerenciamento de Funcionarios</h1>
        <h2>
         <a href="new">Novo Funcionario</a>
         
         <a href="list">Listar Funcionarios</a>
         
        </h2>
 </center>
    <div align="center">
  <c:if test="${funcionario != null}">
   <form action="Edicao" method="post">
        </c:if>
        <c:if test="${funcionario == null}">
   <form action="Inserir" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
             <h2>
              <c:if test="${funcionario != null}">
               Editar Funcionario
              </c:if>
              <c:if test="${funcionario == null}">
               Novo Funcionario
              </c:if>
             </h2>
            </caption>
          <c:if test="${funcionario != null}">
           <input type="hidden" name="id" value="<c:out value='${funcionario.id}' />" />
          </c:if>            
            <tr>
                <th>Nome</th>
                <td>
                    <input type="text" name="nome" size="45"
                   value="<c:out value='${funcionario.nome}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Cargo</th>
                <td>
                 <input type="text" name="cargo" size="45"
                   value="<c:out value='${funcionario.cargo}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Endereço</th>
                <td>
                 <input type="text" name="endereco" size="45"
                   value="<c:out value='${funcionario.endereco}' />"
                 />
                </td>
            </tr>
             <tr>
                <th>Sexo</th>
                <td>
                 <input type="text" name="sexo" size="15"
                   value="<c:out value='${funcionario.sexo}' />"
                 />
                </td>
            </tr>
             <tr>
                <th>Telefone</th>
                <td>
                 <input type="text" name="telefone" size="15"
                   value="<c:out value='${funcionario.telefone}' />"
                 />
                </td>
            </tr>
           
            
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Salvar" />
             </td>
            </tr>
        </table>
        </form>
    </div>


    </body>
</html>