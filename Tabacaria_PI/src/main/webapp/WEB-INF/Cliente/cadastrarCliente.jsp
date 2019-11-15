
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="cadastrarClienteFormatacao.css"/>
        <title>Cadastro de Cliente</title>
        <meta charset="UTF-8">       
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        


    </head>
    <body>

    <div align="center">
  <c:if test="${cliente != null}">
   <form action="EdicaoCliente" method="post">
        </c:if>
        <c:if test="${cliente == null}">
   <form action="InserirCliente" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            
             <h2>
              <c:if test="${cliente != null}">
               Edição de Cliente
              </c:if>
              <c:if test="${cliente == null}">
               Novo Cliente
              </c:if>
             </h2>
            
          <c:if test="${cliente != null}">
           <input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
          </c:if>            
            <tr>
                <th>Nome</th>
                <td>
                    <input type="text" name="nome" size="45" id="nome"
                   value="<c:out value='${cliente.nome}' />"
                  />
                </td>
            </tr>
            <tr>
                <th>Sexo</th>
                <td>
                 <input type="text" name="sexo" size="45"
                   value="<c:out value='${cliente.sexo}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Data Nascimento</th>
                <td>
                 <input type="text" name="datanascimento" size="15"
                   value="<c:out value='${cliente.dataNascimento}' />"
                 />
                </td>
            </tr>
             <tr>
                <th>CPF</th>
                <td>
                 <input type="text" name="cpf" size="14"
                   value="<c:out value='${cliente.cpf}' />"
                 />
                </td>
            </tr>
             <tr>
                <th>Endereço</th>
                <td>
                 <input type="text" name="endereco" size="25"
                   value="<c:out value='${cliente.endereco}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Telefone</th>
                <td>
                 <input type="text" name="telefone" size="15"
                   value="<c:out value='${cliente.telefone}' />"
                 />
                </td>
            </tr>
            <tr>
                <th>Email</th>
                <td>
                 <input type="text" name="email" size="25"
                   value="<c:out value='${cliente.email}' />"
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
