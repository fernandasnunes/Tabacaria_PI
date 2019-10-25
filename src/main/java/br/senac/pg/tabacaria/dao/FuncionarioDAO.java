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
        String sql = "INSERT INTO FUNCIONARIO (NOME, CARGO, ENDERECO, SEXO, TELEFONE, DATACADASTRO, ATIVO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
            preparedStatement.setString(8, funcionario.getSexo());
            preparedStatement.setString(9, funcionario.getTelefone());
            preparedStatement.setString(9, funcionario.getDataCadastro());
            preparedStatement.setBoolean(11, true);
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
            preparedStatement.setBoolean(2, true);
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
                funcionario.setSexo(result.getString("SEXO"));
                funcionario.setTelefone(result.getString("TELEFONE"));
                funcionario.setDataCadastro(result.getString("DATACADASTRO"));
                funcionario.setAtivo(result.getBoolean("ATIVO"));
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
      
    public static void remove(Funcionario funcinario) throws SQLException, Exception{
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
            preparedStatement.setBoolean(1, false);
            preparedStatement.setLong(2, funcinario.getId());
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
                + " SET nome = ?, cargo = ?, endereco = ?, sexo = ?, telefone = ? WHERE id = ?";
            //Cria um statement para execução de instruções SQL
            preparedStatement = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"
            //Comando do banco            

             
            //Setando valores
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setString(3, funcionario.getEndereco());
            preparedStatement.setString(8, funcionario.getSexo());
            preparedStatement.setString(9, funcionario.getTelefone());
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
