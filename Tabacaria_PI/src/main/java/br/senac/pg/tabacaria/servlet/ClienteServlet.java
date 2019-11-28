package br.senac.pg.tabacaria.servlet;

import br.senac.pg.tabacaria.dao.ClienteDAO;
import br.senac.pg.tabacaria.model.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pgProtect/Cliente/*")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null) {
            action = "/Listar";
        }
        try {
            switch (action) {
                case "/Novo":
                    novoFormulario(request, response);
                    break;
                case "/Inserir":
                    inserirCliente(request, response);
                    break;
                case "/Deletar":
                    deletarCliente(request, response);
                    break;
                case "/Editar":
                    formularioEdicao(request, response);
                    break;
                case "/Edicao":
                    editarCliente(request, response);
                    break;
                case "/Listar":
                    listarCliente(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listaClientes = clienteDAO.selecionarTodosCliente();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Cliente/pesquisarCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Cliente/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Cliente existingUser = clienteDAO.selecionarCliente(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Cliente/cadastrarCliente.jsp");
        request.setAttribute("cliente", existingUser);
        dispatcher.forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String datanascimento = request.getParameter("datanascimento");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String date = LocalDate.now().toString();
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        Cliente novoCliente = new Cliente(nome, sexo, datanascimento, cpf, endereco, telefone, email, date, ativo);
        clienteDAO.inserirCliente(novoCliente);
        response.sendRedirect("Listar");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String datanascimento = request.getParameter("datanascimento");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String datacadastro = request.getParameter("datacadastro");
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        Cliente cliente = new Cliente(id, nome, sexo, datanascimento, cpf, endereco, telefone, email, datacadastro, ativo);
        clienteDAO.editarCliente(cliente);
        response.sendRedirect("Listar");
    }

    private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.deletarCliente(id);
        response.sendRedirect("Listar");

    }
}
