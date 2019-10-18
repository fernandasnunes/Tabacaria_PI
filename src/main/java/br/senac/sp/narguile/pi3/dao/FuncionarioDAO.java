/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile.pi3.dao;

import br.senac.sp.narguile.pi3.model.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fernanda
 */
public class FuncionarioDAO {
    Connection conexao;

    public FuncionarioDAO(Connection conexao) {
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
    public static void inserir(Funcionario funcionario)
            throws SQLException, Exception {
        //Monta a string de inserção de um funcionario no BD,
        //utilizando os dados do funcionario passados como parâmetro
        String sql = "INSERT INTO FUNCIONARIO (NOME, CARGO, ENDERECO, BAIRRO, CIDADE, ESTADO, CEP, SEXO, TELEFONE, CELULAR, CADASTRO, ATIVO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),?)";

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

            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(4, funcionario.getBairro());
            preparedStatement.setString(5, funcionario.getCidade());
            preparedStatement.setString(6, funcionario.getEstado());
            preparedStatement.setString(7, funcionario.getCep());
            preparedStatement.setString(8, funcionario.getSexo());
            preparedStatement.setString(9, funcionario.getTelefone());
            preparedStatement.setString(10, funcionario.getCelular());
            preparedStatement.setString(11, "S");
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
    //Obtém uma instância da classe "Funcionario" através de dados do
    //banco de dados, de acordo com o ID fornecido como parâmetro
    public static Funcionario obter(Long id)
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o funcionario
        //com o ID informado e que esteja ativo ("habilitado" com "true")
        String sql = "SELECT * FROM FUNCIONARIO WHERE ID=? AND ATIVO=?";

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
            preparedStatement.setString(2, "S");
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();

            //Verifica se há pelo menos um resultado
            if (result.next()) {
                //Cria uma instância de Funcionario e popula com os valores do BD

                Funcionario funcionario = new Funcionario();                
                funcionario.setId(result.getLong("ID"));
                funcionario.setNome(result.getString("NOME"));               
                funcionario.setCargo(result.getString("CARGO"));
                funcionario.setEndereco(result.getString("ENDERECO"));
                funcionario.setBairro(result.getString("BAIRRO"));
                funcionario.setCidade(result.getString("CIDADE"));
                funcionario.setEstado(result.getString("ESTADO"));
                funcionario.setCep(result.getString("CEP"));
                funcionario.setSexo(result.getString("SEXO"));
                funcionario.setTelefone(result.getString("TELEFONE"));
                funcionario.setCelular(result.getString("CELULAR"));
                funcionario.setDataCadastro(result.getDate("CADASTRO"));
                //Retorna o resultado
                return funcionario;
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
      
    public static void remove(Long codigo) throws SQLException, Exception{
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
  
        try {
            //Abre uma conexão com o banco de dados
        
           connection = obterConexao();
            String sql = "UPDATE funcionario SET ativo = ? WHERE id=?";
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            //Setando valores
            preparedStatement.setString(1, "N");
            preparedStatement.setLong(2, codigo);
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

    }
    public static void alterar(Funcionario funcionario) throws SQLException, Exception{
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            String sql = "UPDATE funcionario "
                + " SET nome = ?, cargo = ?, endereco = ?, bairro = ?, cidade = ?,"
                + " estado = ?, cep = ?, sexo = ?, telefone = ?, celular = ?"
                + " WHERE id = ?";
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            //Comando do banco            

             
            //Setando valores
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(4, funcionario.getBairro());
            preparedStatement.setString(5, funcionario.getCidade());
            preparedStatement.setString(6, funcionario.getEstado());
            preparedStatement.setString(7, funcionario.getCep());
            preparedStatement.setString(8, funcionario.getSexo());
            preparedStatement.setString(9, funcionario.getTelefone());
            preparedStatement.setString(10, funcionario.getCelular());                       
            preparedStatement.setLong(11, funcionario.getId());                      
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
    }
}
