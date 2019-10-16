package br.senac.sp.narguile.pi3.dao;

import br.senac.sp.narguile.pi3.model.Funcionario;
import br.senac.sp.narguile.pi3.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    //Conexao do banco
    Connection conexao;

    //Construtor
    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //
        Connection conn = null;
        // Passo 1: Registar Driver JBDC
        Class.forName("com.mysql.jdbc.Driver");
        // Passo 2: Obter a conexão
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tabacaria",
                "root",
                "");

        return conn;
    }

    //Inserir
    public static void inserir(Usuario usuario) throws SQLException, Exception {
        //Comando do banco
        String sql = "insert into usuarios "
                + "(login, nome, senha, ativo) VALUES (?, ?, ?, ?)";
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
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getNome());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, "S");

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
//        connection = null;
//        preparedStatement = null;
//        ResultSet result = null;
//        try {
//            String sqlid = "select max(id) as id from USUARIOS";
//            //Abre uma conexão com o banco de dados
//            connection = obterConexao();
//            //Cria um statement para execução de instruções SQL
//            preparedStatement = connection.prepareStatement(sqlid);
//
//            result = preparedStatement.executeQuery();
//
//            if (result.next()) {
//                long id = result.getLong("id");
//                inserirFuncionarioUsuario(usuario.getId(), id);
//            }
//
//        } finally {
//            //Se o statement ainda estiver aberto, realiza seu fechamento
//            if (preparedStatement != null && !preparedStatement.isClosed()) {
//                preparedStatement.close();
//            }
//            //Se a conexão ainda estiver aberta, realiza seu fechamento
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//        }

    }

//    private static void inserirFuncionarioUsuario(long idf, long id)
//            throws SQLException, Exception {
//        String sql = "INSERT INTO FUNCIONARIO_USUARIOS (ID_FUNCIONARIO, ID_USUARIOS) "
//                + "VALUES (?, ?)";
//
//        //Conexão para abertura e fechamento
//        Connection connection = null;
//        //Statement para obtenção através da conexão, execução de
//        //comandos SQL e fechamentos
//        PreparedStatement preparedStatement = null;
//
//        try {
//            //Abre uma conexão com o banco de dados
//            connection = obterConexao();
//            //Cria um statement para execução de instruções SQL
//            preparedStatement = connection.prepareStatement(sql);
//            //Configura os parâmetros do "PreparedStatement"
//            //preparedStatement.setDate(1, usuario.getDatahora());
//            preparedStatement.setLong(1, id);
//            preparedStatement.setLong(2, idf);
//
//            //Executa o comando no banco de dados
//            preparedStatement.execute();
//        } finally {
//            //Se o statement ainda estiver aberto, realiza seu fechamento
//            if (preparedStatement != null && !preparedStatement.isClosed()) {
//                preparedStatement.close();
//            }
//            //Se a conexão ainda estiver aberta, realiza seu fechamento
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//        }
//    }

    //Alterar
    public static void alterar(Usuario usuario) throws SQLException, Exception {
//Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement p = null;
        try {
            //Abre uma conexão com o banco de dados
            connection = obterConexao();
            String sql = "UPDATE usuarios "
                    + " SET nome = ?, login = ?, senha = ?, ativo = ? "
                    + " WHERE id = ?";
            //Cria um statement para execução de instruções SQL
            p = connection.prepareStatement(sql);
            //Configura os parâmetros do "PreparedStatement"            
            p.setString(1, usuario.getNome());
            p.setString(2, usuario.getLogin());
            p.setString(3, usuario.getSenha());
            p.setString(4, "S");
            p.setLong(5, usuario.getId());
            p.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Alterar Senha
//    public void alterarSenha(Usuario usuario) {
//
//        try {
//            //Comando do banco
//            String sql = "UPDATE usuarios "
//                    + " SET senha = ? "
//                    + " WHERE id = ?";
//
//            PreparedStatement p;
//            //Setando valores
//            p = this.conexao.prepareStatement(sql);
//            p.setString(1, usuario.getSenha());
//            p.setInt(2, usuario.getId());
//            p.execute();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    //Listar
    public List<Usuario> listarUsuarios() {
        //Lista
        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            //Comando do banco
            String sql = "SELECT * FROM usuarios WHERE ativo='" + "S" + "'";
            java.sql.Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                //Setando valores
                Long id = rs.getLong("id");
                String login = rs.getString("login");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String ativo = rs.getString("ativo");

                usuario.setId(id);
                usuario.setLogin(login);
                usuario.setNome(nome);
                usuario.setSenha(senha);
                usuario.setAtivo(ativo);

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static Usuario obter(Long id)
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o usuario
        //com o ID informado e que esteja ativo ("habilitado" com "true")
        String sql = "SELECT * FROM USUARIOS WHERE ID=? AND ATIVO=?";

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
                //Cria uma instância de Usuario e popula com os valores do BD

                Usuario usuario = new Usuario();
                usuario.setId(result.getLong("ID"));
                usuario.setNome(result.getString("NOME"));
                usuario.setLogin(result.getString("LOGIN"));
                usuario.setSenha(result.getString("SENHA"));
                //Retorna o resultado
                return usuario;
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

    //Remover
    public static void remove(Long codigo) throws SQLException, Exception {
        //Conexão para abertura e fechamento
        Connection connection = null;
        //Statement para obtenção através da conexão, execução de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;

        try {
            //Abre uma conexão com o banco de dados

            connection = obterConexao();
            String sql = "UPDATE usuarios SET ativo = ? WHERE id=?";
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

    public static String verificar(String login, String senha) {
        Connection conn = null;
        String ret = null;
        String user = null;
        String pass = null;

        PreparedStatement preparedStatement = null;
        try {
            conn = obterConexao();
            String sql = "SELECT * FROM usuarios WHERE ativo='" + "S" + " "
                    + "AND login= '" + login + "'";
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                user = rs.getString("login");
                pass = rs.getString("senha");

                if (login.equals(user) && senha.equals(pass)) {
                    ret = rs.getString("nome");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static List<Funcionario> obterFuncionario()
            throws SQLException, Exception {
        //Compõe uma String de consulta que considera apenas o usuario        
        String sql = "SELECT * FROM FUNCIONARIO";

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
            List<Funcionario> funcionarios = new ArrayList<Funcionario>();

            //Verifica se há pelo menos um resultado
            while (result.next()) {
                //Cria uma instância de Usuario e popula com os valores do BD

                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getLong("ID"));
                funcionario.setNome(result.getString("NOME"));
                funcionarios.add(funcionario);
                //Retorna o resultado
            }

            return funcionarios;
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

      
    }
}
