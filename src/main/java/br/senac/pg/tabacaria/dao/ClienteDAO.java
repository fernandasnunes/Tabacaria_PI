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
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
        Connection conexao;
    
        public ClienteDAO(Connection conexao) {
        
            this.conexao = conexao;
    }
      
    public static Connection obterConexao() throws SQLException, ClassNotFoundException {
        
        Connection conn = null;
           
        Class.forName("com.mysql.jdbc.Driver");
        
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/tabacaria",
                "root",
                "");
        
        return  conn;
    }
    
    public List<Cliente> listarClientes() {
        
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        Connection connection = null;
        
        PreparedStatement preparedStatemnet = null;
        
        try{    
            connection = obterConexao();
            String sql = "SELECT * FROM CLIENTE";
            java.sql.Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
         while(rs.next()){
             
             Cliente cliente = new Cliente(); 
         
             Long id = rs.getLong("ID");
             String nome = rs.getString("NOME");
             String endereco = rs.getString("ENDERECO");
             String sexo = rs.getString("SEXO");
             String telefone = rs.getString("TELEFONE");
             String dataNascimento = rs.getString("DATANASCIMENTO");
             String dataCadastro = rs.getString("DATACADASTRO");
             String cpf = rs.getString("CPF");
             String email = rs.getString("EMAIL");
             Boolean ativo = rs.getBoolean("ATIVO");
                     
                     
             cliente.setId(id);
             cliente.setNome(nome);
             cliente.setEndereco(endereco);
             cliente.setSexo(sexo);
             cliente.setTelefone(telefone);
             cliente.setDataNascimento(dataNascimento);
             cliente.setCpf(cpf);
             cliente.setEmail(email);
             cliente.setAtivo(ativo);
             
             clientes.add(cliente);   
         }   
               
        }catch (Exception e) {
            
            e.printStackTrace();
            
        }
        return clientes;
    } 

    public static void inserirCliente(Cliente cliente) throws SQLException, Exception {

        String sqlInserir =
            "INSERT INTO CLIENTE(NOME,ENDERECO,SEXO,TELEFONE,DATANASCIMENTO,CPF,DATACADASTRO,EMAIL,ATIVO)"
            + " VALUES (?,?,?,?,?,?,?,?)";
    
                Connection connection = null;
            
                PreparedStatement preparedStatement = null;
    
                try {
                //Abre uma conexão com o banco de dados
                connection = obterConexao();
                //Cria um statement para execução de instruções SQL
                preparedStatement = connection.prepareStatement(sqlInserir);
                
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getSexo());
                preparedStatement.setString(4, cliente.getTelefone());
                preparedStatement.setString(5, cliente.getDataNascimento());
                preparedStatement.setString(6, cliente.getDataCadastro());
                preparedStatement.setString(6, cliente.getCpf());
                preparedStatement.setString(6, cliente.getEmail());
                preparedStatement.setBoolean(10, cliente.isAtivo());
                
                preparedStatement.execute();
            }
                finally {
                  
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
                }
                
                if (connection != null && !connection.isClosed()) {
                connection.close();
                }
            }
    }

    public static Cliente pesquisar(String nome) throws SQLException, ClassNotFoundException{
        
         String sqlPesquisa = "SELECT * FROM CLIENTE WHERE NOME = ? AND ATIVO = ?";
         
         Connection connection = null;
       
         PreparedStatement preparedStatement = null;
     
         ResultSet result = null;
         
            try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sqlPesquisa);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setString(1, nome);
            preparedStatement.setBoolean(2, true);

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            if (result.next()) {
                
                Cliente cliente = new Cliente();
                
                cliente.setId(result.getLong("ID"));
                cliente.setNome(result.getString("NOME"));               
                cliente.setEndereco(result.getString("ENDERECO"));
                cliente.setSexo(result.getString("SEXO"));
                cliente.setTelefone(result.getString("TELEFONE"));
                cliente.setDataNascimento(result.getString("DATANASCIMENTO"));
                cliente.setDataCadastro(result.getString("DATACADASTRO"));
                cliente.setCpf(result.getString("CPF"));
                cliente.setEmail(result.getString("EMAIL"));
                cliente.setAtivo(result.getBoolean("ATIVO"));
                
                return cliente;
            }
             } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
           
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
       
            return null;
    }
    public static void alterarCliente(Cliente cliente) throws SQLException, ClassNotFoundException{
        
         Connection connection = null;
        
         PreparedStatement preparedStatement = null;
         
         try{
         connection = obterConexao();
         
         String sqlUpdate = "UPDATE cliente "
                 +" SET NOME=?, ENDERECO=?,SEXO=? ,TELEFONE=?, DATANASCIMENTO=?, CPF=?,EMAIL=?"
                 +" WHERE id=? ";
         
          preparedStatement = connection.prepareStatement(sqlUpdate);
         
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getSexo());
                preparedStatement.setString(4, cliente.getTelefone());
                preparedStatement.setString(5, cliente.getDataNascimento());
                preparedStatement.setString(6, cliente.getCpf());
                preparedStatement.setString(6, cliente.getEmail());
                preparedStatement.setLong(6, cliente.getId());
                
                preparedStatement.executeUpdate();
         
         } finally{
           
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
         }
    }
    
    public static void excluirCliente(Long id) throws SQLException, ClassNotFoundException{
             
        Connection connection = null;     
        
        PreparedStatement preparedStatement = null;
      
             
        try {
            
            connection = obterConexao();  
            
            String sqlExcluir = "UPDATE cliente SET ativo = ? WHERE ID = ?";
            
            preparedStatement = connection.prepareStatement(sqlExcluir);
            
            preparedStatement.setBoolean(1, false);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
      
        }finally{
       
            if (preparedStatement != null && !preparedStatement.isClosed()){ 
            
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()){ 
            
                connection.close();
            }
        } 
    }
}













