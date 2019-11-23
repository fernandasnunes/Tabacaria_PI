/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.produto;


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
import java.util.List;


/**
 *
 * @author rafael
 */
@WebServlet("/Produto")
public class FuncionarioServlet extends HttpServlet {
    
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
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/NovoProduto":
                    novoFormulario(request, response);
                    break;
                case "/InserirProduto":
                    inserirProduto(request, response);
                    break;
                case "/DeletarProduto":
                    deletarProduto(request, response);
                    break;
                case "/EditarProduto":
                    formularioEdicao(request, response);
                    break;
                case "/EdicaoProduto":
                    editarProduto(request, response);
                    break;
                
                default:
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Produto/exibirProduto.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Produto/cadastrarProduto.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Produto produtoCadastrado = produtoDAO.selecionarProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Produto/cadastrarProduto.jsp");
        request.setAttribute("produto", produtoCadastrado);
        dispatcher.forward(request, response);

    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        String categoria = request.getParameter("categoria");
        double precoCompra = Double.parseDouble(request.getParameter("preco_compra"));
        double precoVenda = Double.parseDouble(request.getParameter("preco_venda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        Produto novoProduto = new Produto(nome, marca, descricao, categoria, precoCompra, precoVenda, quantidade);
        
        produtoDAO.inserirProduto(novoProduto);
        response.sendRedirect("listProduto");
    }

    private void editarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        String categoria = request.getParameter("categoria");
        double precoCompra = Double.parseDouble(request.getParameter("preco_compra"));
        double precoVenda = Double.parseDouble(request.getParameter("preco_venda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        Produto produto = new Produto(id, nome, marca, descricao, categoria, precoCompra, precoVenda, quantidade);
        produtoDAO.editarProduto(produto);
        response.sendRedirect("listProduto");
    }

    private void deletarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.deletarProduto(id);
        response.sendRedirect("listProduto");

    }
}
