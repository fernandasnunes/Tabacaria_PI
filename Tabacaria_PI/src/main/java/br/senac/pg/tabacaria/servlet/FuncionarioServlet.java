package br.senac.pg.tabacaria.servlet;


import br.senac.pg.tabacaria.dao.CargoDAO;
import br.senac.pg.tabacaria.dao.FilialDAO;
import br.senac.pg.tabacaria.dao.FuncionarioDAO;
import br.senac.pg.tabacaria.model.Filial;
import br.senac.pg.tabacaria.model.Funcionario;
import br.senac.pg.tabacaria.model.Cargo;
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
    private CargoDAO cargoDAO;

    public void init() {
        funcionarioDAO = new FuncionarioDAO();
        filialDAO = new FilialDAO();
        cargoDAO = new CargoDAO();
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
        List<Cargo> cargos = cargoDAO.selecionarTodosCargo();
        request.setAttribute("filiais", filiais);
        request.setAttribute("cargos", cargos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        List<Filial> filiais = filialDAO.selecionarTodasFilial();
        List<Cargo> cargos = cargoDAO.selecionarTodosCargo();
        Funcionario existingUser = funcionarioDAO.selecionarFuncionario(id);
        request.setAttribute("cargos", cargos);
        request.setAttribute("filiais", filiais);
        request.setAttribute("cargoAtual", existingUser.getIdCargo());
        request.setAttribute("filialAtual", existingUser.getIdFilial());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        request.setAttribute("funcionario", existingUser);
        dispatcher.forward(request, response);

    }

    private void inserirFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String datacadastro = LocalDate.now().toString();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        int idFilial = Integer.parseInt(request.getParameter("filial"));
        int idCargo = Integer.parseInt(request.getParameter("cargo"));
        
        Funcionario novoFuncionario = new Funcionario(nome, idCargo, endereco, sexo, telefone, datacadastro, login, senha, idFilial);
        funcionarioDAO.inserirFuncionario(novoFuncionario);
        response.sendRedirect("Listar");

    }

    private void editarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String datacadastro = LocalDate.now().toString();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        int idFilial = Integer.parseInt(request.getParameter("filial"));
        int idCargo = Integer.parseInt(request.getParameter("cargo"));
        
        Funcionario funcionario = new Funcionario(id, nome, idCargo, endereco, sexo, telefone, datacadastro, login, senha, idFilial);
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
