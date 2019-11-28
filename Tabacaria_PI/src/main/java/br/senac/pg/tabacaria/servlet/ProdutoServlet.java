package br.senac.pg.tabacaria.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.senac.pg.tabacaria.model.Produto;
import br.senac.pg.tabacaria.dao.ProdutoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rafael
 */
@WebServlet("/pgProtect/Produto/*")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO;

    public void init() {
        produtoDAO = new ProdutoDAO();
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
                    inserirProduto(request, response);
                    break;
                case "/Deletar":
                    deletarProduto(request, response);
                    break;
                case "/Editar":
                    formularioEdicao(request, response);
                    break;
                case "/Edicao":
                    editarProduto(request, response);
                    break;
                case "/Listar":
                    listarProduto(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produto> listaProdutos = produtoDAO.selecionarTodosProduto();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Produto/pesquisaProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Produto/cadastrarProduto.jsp");
        dispatcher.forward(request, response);

    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Produto produtoCadastrado = produtoDAO.selecionarProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Produto/cadastrarProduto.jsp");
        request.setAttribute("produto", produtoCadastrado);
        dispatcher.forward(request, response);

    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        double precoCompra = Double.parseDouble(request.getParameter("preco_compra"));
        double precoVenda = Double.parseDouble(request.getParameter("preco_venda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String dataCadastro = LocalDate.now().toString();
        int idFilial = Integer.parseInt(request.getParameter("idFilial"));

        Produto novoProduto = new Produto(nome, marca, descricao, precoCompra, precoVenda, quantidade, dataCadastro, idFilial);

        produtoDAO.inserirProduto(novoProduto);
        response.sendRedirect("Listar");
    }

    private void editarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        double precoCompra = Double.parseDouble(request.getParameter("preco_compra"));
        double precoVenda = Double.parseDouble(request.getParameter("preco_venda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String dataCadastro = LocalDate.now().toString();
        int idFilial = Integer.parseInt(request.getParameter("idFilial"));

        Produto produto = new Produto(id, nome, marca, descricao, precoCompra, precoVenda, quantidade, dataCadastro, idFilial);
        produtoDAO.editarProduto(produto);
        response.sendRedirect("Listar");
    }

    private void deletarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.deletarProduto(id);
        response.sendRedirect("Listar");

    }
}
