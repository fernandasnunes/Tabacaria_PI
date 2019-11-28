package br.senac.pg.tabacaria.servlet;

import br.senac.pg.tabacaria.dao.FilialDAO;
import br.senac.pg.tabacaria.dao.FuncionarioDAO;
import br.senac.pg.tabacaria.model.Filial;
import br.senac.pg.tabacaria.model.Funcionario;
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

@WebServlet("/pgProtect/Funcionario/*")
public class FuncionarioServlet extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;
    private FilialDAO filialDAO;

    public void init() {
        funcionarioDAO = new FuncionarioDAO();
        filialDAO = new FilialDAO();
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
                    inserirFuncionario(request, response);
                    break;
                case "/Deletar":
                    deletarFuncionario(request, response);
                    break;
                case "/Editar":
                    formularioEdicao(request, response);
                    break;
                case "/Edicao":
                    editarFuncionario(request, response);
                    break;
                case "/Listar":
                    listarFuncionario(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Funcionario> listaFuncionarios = funcionarioDAO.selecionarTodosFuncionarios();
        request.setAttribute("listaFuncionarios", listaFuncionarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Funcionario/pesquisarFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Filial> filiais = filialDAO.selecionarTodasFilial();
        request.setAttribute("filiais", filiais);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        List<Filial> filiais = filialDAO.selecionarTodasFilial();
        Funcionario existingUser = funcionarioDAO.selecionarFuncionario(id);
        request.setAttribute("filiais", filiais);
        request.setAttribute("filialAtual", existingUser.getIdFilial());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        request.setAttribute("funcionario", existingUser);
        dispatcher.forward(request, response);

    }

    private void inserirFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String datacadastro = LocalDate.now().toString();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idFilial = Integer.parseInt(request.getParameter("filial"));

        Funcionario novoFuncionario = new Funcionario(nome, cargo, endereco, sexo, telefone, datacadastro, login, senha, ativo, idFilial);
        funcionarioDAO.inserirFuncionario(novoFuncionario);
        response.sendRedirect("Listar");

    }

    private void editarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String datacadastro = LocalDate.now().toString();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idFilial = Integer.parseInt(request.getParameter("filial"));
        Funcionario funcionario = new Funcionario(id, nome, cargo, endereco, sexo, telefone, datacadastro, login, senha, ativo, idFilial);
        funcionarioDAO.editarFuncionario(funcionario);
        response.sendRedirect("Listar");
    }

    private void deletarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        funcionarioDAO.deletarFuncionario(id);
        response.sendRedirect("Listar");

    }
}
