/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.dao;


import br.senac.pg.tabacaria.model.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fernanda
 */
public class FuncionarioDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3307/tabacaria";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    
    private static final String INSERT_USERS_SQL = "INSERT INTO TABACARIA.FUNCIONARIO" + "  (NOME, CARGO, ENDERECO, SEXO, TELEFONE) VALUES " +
        " (?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT ID,NOME, CARGO, ENDERECO, SEXO, TELEFONE FROM TABACARIA.FUNCIONARIO WHERE ID =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM TABACARIA.FUNCIONARIO;";
    private static final String DELETE_USERS_SQL = "DELETE FROM TABACARIA.FUNCIONARIO WHERE ID = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE TABACARIA.FUNCIONARIO SET NOME = ?,CARGO= ?, ENDERECO = ?, SEXO= ?,TELEFONE = ? WHERE ID = ?;";
        
     
            
    
      
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
    
    public FuncionarioDAO(){}
    
     public void inserirFuncionario(Funcionario funcionario) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(4, funcionario.getSexo());
            preparedStatement.setString(5, funcionario.getTelefone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Funcionario selecionarFuncionario(long id) {
        Funcionario funcionario = null;
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
                String cargo = rs.getString("CARGO");
                String endereco = rs.getString("ENDERECO");
                String sexo = rs.getString("SEXO");
                String telefone = rs.getString("TELEFONE");
                
                funcionario = new Funcionario(nome, cargo, endereco, sexo, telefone);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return funcionario;
    }

    public List <Funcionario> selecionarTodosFuncionarios() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Funcionario> lista = new ArrayList <>();
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
                String cargo = rs.getString("CARGO");
                String endereco = rs.getString("ENDERECO");
                String sexo = rs.getString("SEXO");
                String telefone = rs.getString("TELEFONE");
                lista.add(new Funcionario(id, nome, cargo, endereco, sexo, telefone));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lista;
    }

    public boolean deletarFuncionario(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean editarFuncionario(Funcionario funcionario) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCargo());
            statement.setString(3, funcionario.getEndereco());
            statement.setString(4, funcionario.getSexo());
            statement.setString(5, funcionario.getTelefone());
            statement.setLong(6, funcionario.getId());

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