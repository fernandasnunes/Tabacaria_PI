/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.dao;

import br.senac.pg.tabacaria.model.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3307/tabacaria";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO TABACARIA.CLIENTE" + "  (NOME, SEXO, DATANASCIMENTO, CPF, ENDERECO, TELEFONE, ID_FILIAL, EMAIL, DATACADASTRO) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT ID,NOME, SEXO, DATANASCIMENTO, CPF, ENDERECO, TELEFONE, ID_FILIAL, EMAIL, DATACADASTRO FROM TABACARIA.CLIENTE WHERE ID =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM TABACARIA.CLIENTE;";
    private static final String DELETE_USERS_SQL = "DELETE FROM TABACARIA.CLIENTE WHERE ID = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE TABACARIA.CLIENTE SET NOME = ?,SEXO= ?, DATANASCIMENTO = ?, CPF= ?,ENDERECO = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?;";

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

    public ClienteDAO() {
    }

    public void inserirCliente(Cliente cliente) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSexo());
            preparedStatement.setString(3, cliente.getDataNascimento());
            preparedStatement.setString(4, cliente.getCpf());
            preparedStatement.setString(5, cliente.getEndereco());
            preparedStatement.setString(6, cliente.getTelefone());
            preparedStatement.setInt(7, cliente.getIdFilial());
            preparedStatement.setString(8, cliente.getEmail());
            preparedStatement.setString(9, cliente.getDatacadastro());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Cliente selecionarCliente(long id) {
        Cliente cliente = null;
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
                String sexo = rs.getString("SEXO");
                String datanascimento = rs.getString("DATANASCIMENTO");
                String cpf = rs.getString("CPF");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");
                String email = rs.getString("EMAIL");
                String datacadastro = rs.getString("DATACADASTRO");
                int idFilial = rs.getInt("ID_FILIAL");
                cliente = new Cliente(id, nome, sexo, datanascimento, cpf, endereco, telefone, email, datacadastro, idFilial);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cliente;
    }

    public List<Cliente> selecionarTodosCliente() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Cliente> lista = new ArrayList<>();
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
                String sexo = rs.getString("SEXO");
                String datanascimento = rs.getString("DATANASCIMENTO");
                String cpf = rs.getString("CPF");
                String endereco = rs.getString("ENDERECO");
                String telefone = rs.getString("TELEFONE");
                String email = rs.getString("EMAIL");
                String datacadastro = rs.getString("DATACADASTRO");
                int idFilial = rs.getInt("ID_FILIAL");

                lista.add(new Cliente(id, nome, sexo, datanascimento, cpf, endereco, telefone, email, datacadastro, idFilial));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return lista;
    }

    public boolean deletarCliente(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean editarCliente(Cliente cliente) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getSexo());
            statement.setString(3, cliente.getDataNascimento());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getEndereco());
            statement.setString(6, cliente.getTelefone());
            statement.setString(7, cliente.getEmail());
            statement.setLong(8, cliente.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
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
