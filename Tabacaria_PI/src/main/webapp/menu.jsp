
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
 -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">


        </script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Alterna navegação">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item ">
        <a class="nav-link" href="menu.jsp">Home<span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Cliente">Cliente</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Funcionario">Funcionário</a>
      </li>
      <li class="nav-item ">
        <a class="nav-link" href="Produto">Produto<span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Privilegiado</a>
      </li>
    </ul>
  </div>
</nav>
    </body>
</html>
