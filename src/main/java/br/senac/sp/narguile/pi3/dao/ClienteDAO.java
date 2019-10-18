/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile.pi3.dao;

import br.senac.sp.narguile.pi3.model.Cliente;
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
             String bairro = rs.getString("BAIRRO");
             String cidade = rs.getString("CIDADE");
             String estado = rs.getString("ESTADO");
             String cep = rs.getString("CEP");
             String sexo = rs.getString("SEXO");
             String telefone = rs.getString("TELEFONE");
             String celular = rs.getString("CELULAR");
             Date cadData = rs.getDate("CADASTRO");
             
             cliente.setId(id);
             cliente.setEndereco(endereco);
             cliente.setBairro(bairro);
             cliente.setCidade(cidade);
             cliente.setEstado(estado);
             cliente.setCep(cep);
             cliente.setSexo(sexo);
             cliente.setTelefone(telefone);
             cliente.setCelular(celular);
             cliente.setCadastroData(cadData);
             
             clientes.add(cliente);   
         }   
               
        }catch (Exception e) {
            
            e.printStackTrace();
            
        }
        return clientes;
    } 

    public static void inserirCliente(Cliente cliente) throws SQLException, Exception {

        String sqlInserir =
            "INSERT INTO CLIENTE(NOME,ENDERECO,BAIRRO,CIDADE,ESTADO,CEP,SEXO,TELEFONE,CELULAR,CADASTRO,ATIVO)"
            + " VALUES (?,?,?,?,?,?,?,?,?, NOW(),?)";
    
                Connection connection = null;
            
                PreparedStatement preparedStatement = null;
    
                try {
                //Abre uma conexão com o banco de dados
                connection = obterConexao();
                //Cria um statement para execução de instruções SQL
                preparedStatement = connection.prepareStatement(sqlInserir);
                
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getBairro());
                preparedStatement.setString(4, cliente.getCidade());
                preparedStatement.setString(5, cliente.getEstado());
                preparedStatement.setString(6, cliente.getCep());
                preparedStatement.setString(7, cliente.getSexo());
                preparedStatement.setString(8, cliente.getTelefone());
                preparedStatement.setString(9, cliente.getCelular());
                preparedStatement.setString(10, "S");
                
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

    public static Cliente pesquisar(Long id) throws SQLException, ClassNotFoundException{
        
         String sqlPesquisa = "SELECT * FROM CLIENTE WHERE ID = ? AND ATIVO = ?";
         
         Connection connection = null;
       
         PreparedStatement preparedStatement = null;
     
         ResultSet result = null;
         
            try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sqlPesquisa);
            //Configura os parâmetros do "PreparedStatement"
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, "S");

            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            if (result.next()) {
                
                Cliente cliente = new Cliente();
                
                cliente.setId(result.getLong("ID"));
                cliente.setNome(result.getString("nome"));
                cliente.setEndereco(result.getString("endereco"));
                cliente.setBairro(result.getString("bairro"));
                cliente.setCidade(result.getString("cidade"));
                cliente.setEstado(result.getString("estado"));
                cliente.setCep(result.getString("cep"));
                cliente.setSexo(result.getString("sexo"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setCelular(result.getString("celular"));
                cliente.setCadastroData(result.getDate("cadastro"));
                
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
                 +" SET NOME = ?, ENDERECO = ?,BAIRRO = ?,CIDADE = ?, ESTADO = ?, CEP = ?,SEXO = ? ,TELEFONE = ?, CELULAR = ?"
                 +" WHERE id = ? ";
         
          preparedStatement = connection.prepareStatement(sqlUpdate);
         
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getBairro());
                preparedStatement.setString(4, cliente.getCidade());
                preparedStatement.setString(5, cliente.getEstado());
                preparedStatement.setString(6, cliente.getCep());
                preparedStatement.setString(7, cliente.getSexo());
                preparedStatement.setString(8, cliente.getTelefone());
                preparedStatement.setString(9, cliente.getCelular());
                preparedStatement.setLong(10, cliente.getId());
                
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
            
            preparedStatement.setString(1, "N");
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













