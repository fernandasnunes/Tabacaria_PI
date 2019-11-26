/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.dao;

import br.senac.pg.tabacaria.model.Filial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilialDAO {
       
    private String jdbcURL = "jdbc:mysql://localhost:3307/tabacaria";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    
    private static final String INSERT_USERS_SQL = "INSERT INTO TABACARIA.FILIAL" + "  (LOCALIDADE, RESPONSAVEL, DT_CADASTRO) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT LOCALIDADE, RESPONSAVEL, DT_CADASTRO FROM TABACARIA.FILIAL WHERE ID =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM TABACARIA.FILIAL;";
    private static final String DELETE_USERS_SQL = "DELETE FROM TABACARIA.FILIAL WHERE ID = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE TABACARIA.FILIAL SET LOCALIDADE = ?,RESPONSAVEL= ? WHERE ID = ?;";
        
     
            
    
      
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
    
    public FilialDAO(){}
    
     public void inserirFilial(Filial filial) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, filial.getLocalidade());
            preparedStatement.setString(2, filial.getResponsavel());
            preparedStatement.setString(3, filial.getDataCadastro());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Filial selecionarFilial(long id) {
        Filial filial = null;
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
                
                String localidade = rs.getString("LOCALIDADE");
                String responsavel = rs.getString("RESPONSAVEL");
                String dataCadastro = rs.getString("DT_CADASTRO");
                filial = new Filial(id, localidade, responsavel, dataCadastro);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return filial;
    }

    public List <Filial> selecionarTodasFilial() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Filial> lista = new ArrayList <>();
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
                String localidade = rs.getString("LOCALIDADE");
                String responsavel = rs.getString("RESPONSAVEL");
                String dataCadastro = rs.getString("DT_CADASTRO");
                lista.add(new Filial(id, localidade, responsavel, dataCadastro));
               
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lista;
    }

    public boolean deletarFilial(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean editarFilial(Filial filial) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, filial.getLocalidade());
            statement.setString(2, filial.getResponsavel());
            statement.setLong(3, filial.getId());
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
















