package br.senac.pg.tabacaria.servlet.filial;


import br.senac.pg.tabacaria.dao.FilialDAO;
import br.senac.pg.tabacaria.model.Filial;
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

@WebServlet("/pgProtect/Filial/*")
public class FilialServlet extends HttpServlet {

    private FilialDAO filialDAO;

    public void init() {
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
                    inserirFilial(request, response);
                    break;
                case "/Deletar":
                    deletarFilial(request, response);
                    break;
                case "/Editar":
                    formularioEdicao(request, response);
                    break;
                case "/Edicao":
                    editarFilial(request, response);
                    break;
                case "/Listar":
                    listarFilial(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarFilial(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Filial> listaFilial = filialDAO.selecionarTodasFilial();
        request.setAttribute("listaFilial", listaFilial);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Filial/pesquisarFilial.jsp");
        dispatcher.forward(request, response);
    }

    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Filial/cadastrarFilial.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Filial existingFilial = filialDAO.selecionarFilial(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Filial/cadastrarFilial.jsp");
        request.setAttribute("filial", existingFilial);
        dispatcher.forward(request, response);
    }

    private void inserirFilial(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String localidade = request.getParameter("localidade");
        String responsavel = request.getParameter("responsavel");
        String dataCadastro = LocalDate.now().toString();
        Filial novafilial = new Filial(localidade, responsavel, dataCadastro);
        filialDAO.inserirFilial(novafilial);
        response.sendRedirect("Listar");
    }

    private void editarFilial(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String localidade = request.getParameter("localidade");
        String responsavel = request.getParameter("responsavel");
        String dataCadastro = request.getParameter("dataCadastro");
        Filial filial = new Filial(id, localidade, responsavel, dataCadastro);
        filialDAO.editarFilial(filial);
        response.sendRedirect("Listar");
    }

    private void deletarFilial(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        filialDAO.deletarFilial(id);
        response.sendRedirect("Listar");

    }
}
