<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <div class="form-horizontal">
            <fieldset>

                <legend>Cadastrar Produto</legend>

                <form method="post" action="${pageContext.request.contextPath}/IncluirProduto">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="nome">Nome</label>
                        <div class="col-sm-5">
                            <input style="width: 250px;" required="required" type="text" name="nome" id="nome" />
                        </div>
                        <label class="col-sm-2 col-form-label" for="marca">Marca</label>
                        <div>
                            <input style="width: 250px;" required="required" type="text" name="marca" id="marca" />
                        </div>
                    </div>

                    <div class="form-group row">
                        <fieldset>
                            <label class="col-sm-2 col-form-label" for="cat">Categoria</label>
                            <ul>
                                <c:forEach items="${categoria}" var="cat">
                                    <input type="radio" value="${cat.id}" id="${cat.id}" name="cat"/>${cat.nome}
                                </c:forEach>
                            </ul>
                        </fieldset>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="qtd">Quantidade</label>
                        <div class="col-sm-10">
                            <input onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                        return true;
                                    else
                                        return false;" style="width: 250px;" required="required" type="text" name="qtd" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="prcompra">Preço compra</label>
                        <div class="col-sm-5">
                            <input placeholder="0.00" style="width: 250px;" required="required" type="text" name="prcompra" />
                        </div>
                        <label class="col-sm-2 col-form-label" for="prvenda">Preço venda</label>
                        <div>
                            <input placeholder="0.00" style="width: 250px;" required="required" type="text" name="prvenda" />
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label" for="descricao">Descrição</label>
                        <div class="col-sm-10">
                            <textarea required="required" style="width: 900px;" name="descricao"></textarea>
                        </div>
                    </div>

                    <label class="col-md-4 control-label" for="button1id">


                    </label>
                    <button type="submit" class="btn btn-success">Salvar</button>   
                    <button type="reset" class="btn btn-primary">Limpar</button>
                    <button type="reset"  class="btn btn-success" onclick="window.location.href = 'menu.jsp';">Voltar</button>
                </form>

            </fieldset>


</body>
</html>