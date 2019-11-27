<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <link href="indexformatacao.css" type="text/css" rel="stylesheet" />
        <title>Controle de Acessoo</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&display=swap" rel="stylesheet">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    </head>
    <body>
      
<div class="wrapper fadeInDown">
  <div id="formContent">

    <div class="fadeIn first">
      <img src="https://webstockreview.net/images/conversation-clipart-grouping-2.png" id="icon" alt="User Icon" />
      <h1>Controle de Acesso</h1>
    </div>
<c:if test="${msgErro != null}">
            <div style="background-color: salmon">
                <h3><c:out value="${msgErro}" /></h3>
            </div>
        </c:if>
    <form  method="post" id="loginform" name="loginform" action="${pageContext.request.contextPath}/login">
      <input type="text" id="login" class="fadeIn second" name="username" placeholder="username">
      <input type="text" id="password" class="fadeIn third" name="senha" placeholder="senha">
      <input type="submit" class="fadeIn fourth" value="Log In">
    </form>

    <div id="formFooter">
    </div>
  </div>

  </div>
    </body>


</html>

