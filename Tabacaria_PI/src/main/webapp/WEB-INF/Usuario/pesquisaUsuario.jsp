<%-- 
    Document   : pesquisaUsuario
        Created on : 06/10/2019, 20:56:07
    Author     : fernanda
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Pesquisar Usuario</title>
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

        <script language="javascript"></script>
    </head>
    <body>
        <div class="form-horizontal">
            <fieldset>


                <legend>Pesquisar Usuário</legend>

                <form method="post" action="${pageContext.request.contextPath}/ConsultarUsuario"> 




                    <div class="form-group">


                        <label class="col-md-4 control-label" for="id">ID</label>




                        <input required="required" onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                    return true;
                                else
                                    return false;"  type="text" name="id" id="id"/></label>
                    </div>


                    <label class="col-md-4 control-label" for="button1id">

                        <br>
                    </label>
                    <button  href="./ConsultaUsuario" class="btn btn-success">Salvar</button>   
                    <button type="reset" class="btn btn-primary">Limpar</button>
                    <button type="reset"  class="btn btn-success" onclick="window.location.href = 'menu.jsp';">Voltar</button>
                </form>
        </div>


    </fieldset>
</body>
</html>
