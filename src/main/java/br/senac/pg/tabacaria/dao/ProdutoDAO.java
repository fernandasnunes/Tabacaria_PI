/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.senac.pg.tabacaria.model.Produto;
import br.senac.pg.tabacaria.model.Categoria;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matheus
 */
public class ProdutoDAO {

    Connection conexao;

    public ProdutoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //
        Connection conn = null;
        // Passo 1: Registar Driver JBDC
        Class.forName("com.mysql.jdbc.Driver");
        // Passo 2: Obter a conexão
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/tabacaria",
                "root",
                "");

        return conn;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            connection = obterConexao();
            String sql = "SELECT * FROM PRODUTO";
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto produto = new Produto();

                long codigo = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String marca = rs.getString("MARCA");
                String descricao = rs.getString("DESCRICAO");
                double precoCompra = rs.getDouble("PRECO_COMPRA");
                double precoVenda = rs.getDouble("PRECO_VENDA");
                int qtd = rs.getInt("QUANTIDADE");
                Date dataCad = rs.getDate("DT_CADASTRO");

                produto.setId(codigo);
                produto.setNome(nome);
                produto.setMarca(marca);
                produto.setDescricao(descricao);
                produto.setPrecoCompra(precoCompra);
                produto.setPrecoVenda(precoVenda);
                produto.setQuantidade(qtd);
                produto.setDataCadastro(dataCad);

                produtos.add(produto);
            }

        } catch (Exception e) {
            
            e.printStackTrace();
        
        }
        return produtos;
    }
    //Insere um produto na tabela "produto" do banco de dados

    public static void inserir(Produto produto)
            throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produto passados como parâmetro
        String sql = "INSERT INTO PRODUTO (NOME, MARCA, DESCRICAO, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DT_CADASTRO) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getMarca());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setDouble(4, produto.getPrecoCompra());
            preparedStatement.setDouble(5, produto.getPrecoVenda());
            preparedStatement.setInt(6, produto.getQuantidade());
            //Executa o comando no banco de dados
            preparedStatement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        connection = null;
        preparedStatement = null;
        ResultSet result = null;
        try {
            String sqlid = "select max(id) as id from PRODUTO";
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sqlid);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                long id = result.getLong("id");
                inserirCategoriaProduto(produto.getCategorias(), id);
            }

        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    private static void inserirCategoriaProduto(String[] categorias, long id)
            throws SQLException, Exception {
        String sql = "INSERT INTO PRODUTO_CATEGORIA (ID_PRODUTO, ID_CATEGORIA) "
                + "VALUES (?, ?)";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;

        for (String numero : categorias) {
            try {
                //Abre uma conexão com o banco de dados
                connection = obterConexao();
                //Cria um statement para execução de instruções SQL
                preparedStatement = connection.prepareStatement(sql);
                //Configura os parâmetros do "PreparedStatement"
                //preparedStatement.setDate(1, produto.getDatahora());
                preparedStatement.setLong(1, id);
                preparedStatement.setInt(2, Integer.parseInt(numero));

                //Executa o comando no banco de dados
                preparedStatement.execute();
            } finally {
                //Se o statement ainda estiver aberto, realiza seu fechamento
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                //Se a conexão ainda estiver aberta, realiza seu fechamento
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }
    }

    //Obtém uma instância da classe "Produto" através de dados do
    //banco de dados, de acordo com o ID fornecido como parâmetro
    public static Produto obter(Long id)
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o produto
        //com o ID informado e que esteja ativo ("habilitado" com "true")
        String sql = "SELECT * FROM produto WHERE id=?";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setLong(1, id);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD

                Produto produto = new Produto();                
                produto.setId(result.getLong("ID"));
                produto.setNome(result.getString("NOME"));
                produto.setMarca(result.getString("MARCA"));
                produto.setDescricao(result.getString("DESCRICAO"));
                produto.setPrecoCompra(result.getDouble("PRECO_COMPRA"));
                produto.setPrecoVenda(result.getDouble("PRECO_VENDA"));
                produto.setQuantidade(result.getInt("QUANTIDADE"));
                produto.setDataCadastro(result.getDate("DT_CADASTRO"));
                
                //Retorna o resultado
                return produto;
            }
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
        return null;
    }

    //Obtém uma instância da classe "Produto" através de dados do
    //banco de dados, de acordo com o ID fornecido como parâmetro
    public static List<Categoria> obterCategoria()
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o produto        
        String sql = "SELECT * FROM CATEGORIA";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenará os resultados do banco de dados
        ResultSet result = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            List<Categoria> categorias = new ArrayList<Categoria>();

            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Produto e popula com os valores do BD

                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("ID"));
                categoria.setNome(result.getString("NOME"));
                categorias.add(categoria);
                //Retorna o resultado
            }

            return categorias;
        } finally {
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        //Se chegamos aqui, o "return" anterior não foi executado porque
        //a pesquisa não teve resultados
        //Neste caso, não há um elemento a retornar, então retornamos "null"
    }

    public static void alterar(Produto produto) throws SQLException, Exception{
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            String sql = "UPDATE produto "
                + " SET nome = ?, marca = ?, descricao = ?, preco_compra = ?, preco_venda = ?,"
                + " quantidade = ?"
                + " WHERE id = ?";
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            //Comando do banco            

             
            //Setando valores
            
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getMarca());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setDouble(4, produto.getPrecoCompra());
            preparedStatement.setDouble(5, produto.getPrecoVenda());
            preparedStatement.setInt(6, produto.getQuantidade());
            preparedStatement.setLong(7, produto.getId());                      
            preparedStatement.executeUpdate();
            
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        connection = null;
        preparedStatement = null;
        ResultSet result = null;
        try {
            String sqlid = "select max(id) as id from PRODUTO";
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sqlid);

            result = preparedStatement.executeQuery();

            if (result.next()) {
                long id = result.getLong("id");
                alterarCategoriaProduto(produto.getCategorias(), id);
            }

        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }   
    public static void alterarCategoriaProduto(String[] categorias, long id)
            throws SQLException, Exception {
        String sql = "UPDATE PRODUTO_CATEGORIA SET ID_PRODUTO=?, ID_CATEGORIA=? "
                + "WHERE ID_PRODUTO=?";

        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;

        for (String numero : categorias) {
            try {
                //Abre uma conexão com o banco de dados
                connection = obterConexao();
                //Cria um statement para execução de instruções SQL
                preparedStatement = connection.prepareStatement(sql);
                //Configura os parâmetros do "PreparedStatement"
                //preparedStatement.setDate(1, produto.getDatahora());
                preparedStatement.setLong(1, id);
                preparedStatement.setInt(2, Integer.parseInt(numero));
                preparedStatement.setLong(3, id);
                //Executa o comando no banco de dados
                preparedStatement.execute();
            } finally {
                //Se o statement ainda estiver aberto, realiza seu fechamento
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
                //Se a conexão ainda estiver aberta, realiza seu fechamento
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }
    }
    public static void remove(long codigo) throws SQLException, Exception {
        
        String sql = "DELETE FROM PRODUTO_CATEGORIA WHERE ID_PRODUTO = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try 
        {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, codigo);
            preparedStatement.execute();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) 
            {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) 
            {
                connection.close();
            }
            remove1(codigo);
        }
    }
    public static  void remove1(long codigo) throws SQLException, Exception 
    {
        String sql = "DELETE FROM PRODUTO WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        
        try 
        {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, codigo);
            preparedStatement.execute();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        finally 
        {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) 
            {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) 
            {
                connection.close();
            }
        }
    }
}
