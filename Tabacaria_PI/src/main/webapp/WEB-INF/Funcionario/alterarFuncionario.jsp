<%-- 
    Document   : alterarFuncionario
    Created on : 06/10/2019, 23:05:10
    Author     : fernanda
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="formatacao.css"/>
        <title>Alterar Funcionario</title>
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
            <h2 style="color: white">Alterar Funcionario</h2>
            <main class="container">
                <div class="row">
                    <div class="col-12">                       
                        <div>
                            <form method="post" action="${pageContext.request.contextPath}/AlterarFuncionario">
                                <input type="hidden" value="${id}" name="codFunc" id="codFunc"/>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="nome">Nome</label>
                                    <div class="col-sm-5">
                                        <input style="width: 300px;" required="required" value="${func.nome}" type="text" name="nome" />
                                    </div>
                                    <label class="col-sm-2 col-form-label" for="sexo">Sexo</label>

                                    <select name="sexo" value="${func.sexo}">                                        
                                        <option value="M">Masculino</option>
                                        <option value="F">Feminino</option>                                        
                                    </select>                                
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="cargo">Cargo/ Função</label>
                                    <div class="col-sm-10">
                                        <input style="width: 300px;" required="required" value="${func.cargo}" type="text" name="cargo" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="endereco">Endereço</label>
                                    <div class="col-sm-5">
                                        <input style="width: 300px;" required="required" value="${func.endereco}" type="text" name="endereco" />
                                    </div>
                                    <label class="col-sm-2 col-form-label" for="bairro">Bairro</label>
                                    <div >
                                        <input required="required" type="text" name="bairro" value="${func.bairro}" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="cidade">Cidade</label>
                                    <div class="col-sm-5">
                                        <input style="width: 300px;" required="required" value="${func.cidade}" type="text" name="cidade" />
                                    </div>
                                    <label class="col-sm-2 col-form-label" for="estado">Estado</label>
                                    <div>
                                        <input onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                                    return false; else return true;" value="${func.estado}" maxlength="2" style="width: 50px;" placeholder="SP" required="required" type="text" name="estado" />
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="cep">Cep</label>
                                    <div class="col-sm-10">
                                        <input onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                                    return true; else return false;" value="${func.cep}" maxlength="9" placeholder="000-00000" required="required" type="text" name="cep"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label" for="telefone">Telefone</label>
                                    <div class="col-sm-5">
                                        <input onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                                    return true; else return false;" value="${func.telefone}" maxlength="14" placeholder="(00)0000-0000" required="required" type="text" name="telefone"/>
                                    </div>
                                    <label class="col-sm-2 col-form-label" for="celular">Celular</label>
                                    <div>
                                        <input onkeypress="if (!isNaN(String.fromCharCode(window.event.keyCode)))
                                                    return true; else return false;" value="${func.celular}" maxlength="14" placeholder="(00)00000-0000" required="required" type="text" name="celular"/>
                                    </div>
                                </div> 
                                <button type="submit">Salvar</button>
                                <button type="reset">Limpar</button>
                                <button type="reset" onClick="history.go(-1)">Voltar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </nav>
        <%@ include file="/rodape.jsp"%> 
    </body>
</html>
