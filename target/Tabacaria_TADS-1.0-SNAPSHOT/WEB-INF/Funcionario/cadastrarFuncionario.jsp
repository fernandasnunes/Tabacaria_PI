
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

        <div class="form-horizontal">
           

                <legend> Cadastro de Funcionário </legend>

                <form method="post" action="${pageContext.request.contextPath}/IncluirFuncionario">
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="nome">Nome</label>
                        <div class="col-md-4">
                            <input id="nome" name="nome" type="text" placeholder="Fulano" class="form-control input-md">
                        </div>
                    </div>
                       <div class="form-group">
                        <label class="col-md-4 control-label" for="cargo">Cargo</label>
                        <div class="col-md-4">
                            <input id="cargo" name="cargo" type="text" placeholder="Cargo" class="form-control input-md">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-md-4 control-label" for="UF">Sexo</label>
                        <div class="col-md-4">
                                 <input id="sexo" name="sexo" type="text" placeholder="Sexo" class="form-control input-md">
                            
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-md-4 control-label" for="endereco">Endereço</label>
                        <div class="col-md-4">
                            <input id="endereco" name="endereco" type="text" placeholder="Avenida Paulista, 123" class="form-control input-md">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="telefone">Telefone</label>
                        <div class="col-md-4">
                            <input id="telefone" name="telefone" type="text" placeholder="00 00000000" class="form-control input-md">
                        </div>
                    </div>

                    
                            
              <div class="control-label col-md-4">
                <button type="submit" class="btn btn-success">Salvar</button>   
                                <button type="reset" class="btn btn-primary">Limpar</button>
                         </div>        
                            </form>

           



    </body>
</html>