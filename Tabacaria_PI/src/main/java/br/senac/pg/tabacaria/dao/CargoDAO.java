/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.dao;

import br.senac.pg.tabacaria.model.Cargo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO {
       
    private String jdbcURL = "jdbc:mysql://localhost:3307/tabacaria";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";
    
    
    private static final String SELECT_USER_BY_ID = "SELECT NOME WHERE ID = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM TABACARIA.CARGO;";
    
   
        
     
            
    
      
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
    
    public CargoDAO(){}
   
    public Cargo selecionarCargo(long id) {
        Cargo cargo = null;
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
                
                cargo = new Cargo(id, nome);
                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cargo;
    }

    public List <Cargo> selecionarTodosCargo() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Cargo> lista = new ArrayList <>();
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
                
                lista.add(new Cargo(id, nome));
               
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lista;
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
















