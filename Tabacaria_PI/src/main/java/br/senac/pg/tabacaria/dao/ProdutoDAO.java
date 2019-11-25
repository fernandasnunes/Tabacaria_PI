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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ProdutoDAO {

     private String jdbcURL = "jdbc:mysql://localhost:3307/tabacaria";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    
    private static final String INSERT_USERS_SQL = "INSERT INTO TABACARIA.PRODUTO" + "(NOME, MARCA, DESCRICAO, CATEGORIA, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DT_CADASTRO) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT ID,NOME, MARCA, DESCRICAO, CATEGORIA, PRECO_COMPRA, PRECO_VENDA, QUANTIDADE, DT_CADASTRO FROM TABACARIA.PRODUTO WHERE ID =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM TABACARIA.PRODUTO;";
    private static final String DELETE_USERS_SQL = "DELETE FROM TABACARIA.PRODUTO WHERE ID = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE TABACARIA.PRODUTO SET NOME = ?,MARCA= ?, DESCRICAO = ?, CATEGORIA=?, PRECO_COMPRA= ?,PRECO_VENDA = ?, QUANTIDADE = ?, DT_CADASTRO = ? WHERE ID = ?;";
        
     
            
    
      
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    
    }
    
    public ProdutoDAO(){}
    
     public void inserirProduto(Produto produto) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getMarca());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setString(4, produto.getCategoria());
            preparedStatement.setDouble(5, produto.getPrecoCompra());
            preparedStatement.setDouble(6, produto.getPrecoVenda());
            preparedStatement.setInt(7, produto.getQuantidade());
            preparedStatement.setString(8, produto.getDatacadastro());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Produto selecionarProduto(long id) {
        Produto produto = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                
                String nome = rs.getString("NOME");
                String marca = rs.getString("MARCA");
                String descricao = rs.getString("DESCRICAO");
                String categoria = rs.getString("CATEGORIA");
                double precoCompra = rs.getDouble("PRECO_COMPRA");
                double precoVenda = rs.getDouble("PRECO_VENDA");
                int quantidade = rs.getInt("QUANTIDADE");
                String dataCadastro = rs.getString("DT_CADASTRO");
                
                produto = new Produto(nome, marca, descricao, categoria, precoCompra, precoVenda, quantidade, dataCadastro);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produto;
    }

    public List <Produto> selecionarTodosProduto() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Produto> lista = new ArrayList <>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String nome = rs.getString("NOME");
                String marca = rs.getString("MARCA");
                String descricao = rs.getString("DESCRICAO");
                String categoria = rs.getString("CATEGORIA");
                double precoCompra = rs.getDouble("PRECO_COMPRA");
                double precoVenda = rs.getDouble("PRECO_VENDA");
                int quantidade = rs.getInt("QUANTIDADE");
                String dataCadastro = rs.getString("DT_CADASTRO");
                lista.add(new Produto(id, nome, marca, descricao, categoria, precoCompra, precoVenda, quantidade, dataCadastro));
               
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lista;
    }

    public boolean deletarProduto(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean editarProduto(Produto produto) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getMarca());
            statement.setString(3, produto.getDescricao());
            statement.setString(4, produto.getCategoria());
            statement.setDouble(5, produto.getPrecoCompra());
            statement.setDouble(6, produto.getPrecoVenda());
            statement.setInt(7, produto.getQuantidade());
            statement.setString(8, produto.getDatacadastro());            
            statement.setLong(9, produto.getId());
            

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
