
<!DOCTYPE html>
<html>
    <head>

        <link href="menuformatacao.css" type="text/css" rel="stylesheet" />
        <title>Menu Principal</title>
        <meta charset="UTF-8">

    </head>
    <body>

        <!--        <nav >
                    <ul class="menu">
                        <li><a href="#">Cadastros</a>
                            <ul>
                                <li><a href="./CadastrarCliente">Clientes</a></li>                       
                                <li><a href="./IncluirFuncionario">Funcionários</a></li>
                                <li><a href="./IncluirProduto">Produtos</a></li>                  
                                <li><a href="./IncluirUsuario">Usuários</a></li> 
                            </ul>
                        </li>
                        <li><a href="#">Pesquisar</a>
                            <ul>
                                <li><a href="./ConsultarCliente">Clientes</a></li>
                                <li><a href="./ConsultaProduto">Produto</a></li>
                                <li><a href="./ConsultaFuncionario">Funcionário</a></li>
                                <li><a href="./ConsultaUsuario">Usuário</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Relatorios</a>
                            <ul>
                                <li><a href="#">Relatorio1</a></li>
                                <li><a href="#">Relatorio2</a></li>
                                <li><a href="#">Relatorio3</a></li>                    
                            </ul>
                        </li>
                        <li><a href="#">Sobre</a>
                        </li>
                        <li><a href="#">Usuário: ${nome}</a>
                            <ul>
                                <li><a href="index.html">Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
        <%@ include file="/rodape.jsp"%> -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">


        </script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <div class="wrapper">
            <!--Navigation Start-->
            <nav class="navigation">
                <ul>
                    <li class="active">
                        <a href="#"> Cadastros </a>
                        <ul class="children sub-menu">
                            <li>
                                <a href="./CadastrarCliente"">Clientes</a>
                            </li>
                            <li>
                                <a href="./IncluirFuncionario"> Funcionários </a>
                            </li>
                            <li>
                                <a href="./IncluirProduto"> Produtos</a>
                            </li>
                              <li>
                                <a href="./ConsultaUsuario"> Usuários</a>
                            </li>
                        </ul>
                    </li>
                  
                    <li>
                        <a href="">Pesquisa</a>
                        <ul class="children sub-menu">
                         
                                <li><a href="./ConsultarCliente">Clientes</a></li>
                                <li><a href="./ConsultaProduto">Produto</a></li>
                                <li><a href="./ConsultaFuncionario">Funcionário</a></li>
                                <li><a href="./ConsultaUsuario">Usuário</a></li>
                           
                        </ul>
                    </li>
                    <li>
                        <a href="">Relatórios</a>
                        <ul class="children sub-menu">
                           <li><a href="#">Relatorio1</a></li>
                                <li><a href="#">Relatorio2</a></li>
                                <li><a href="#">Relatorio3</a></li>        
                        </ul>
                    </li>
                    <li>
                        <a href="">Usuários</a>
                        <ul class="children sub-menu">
                            <li><a href="#">Usuário: ${nome}</a>
                         
                                <li>
                                    
                                    <a href="index.html">Sair</a>
                                
                                </li>
                            
                        </li>
                        </ul>
                    </li>
               
                </ul>
            </nav>
  
        </div>
    </body>
</html>
