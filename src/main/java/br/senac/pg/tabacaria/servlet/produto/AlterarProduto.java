/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.produto;

import br.senac.pg.tabacaria.dao.ProdutoDAO;
import br.senac.pg.tabacaria.model.Categoria;
import br.senac.pg.tabacaria.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author matheus
 */
@WebServlet(name = "AlterarProduto", urlPatterns = {"/AlterarProduto"})
public class AlterarProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        List<Categoria> categorias = new ArrayList<Categoria>();
        
        Produto produto = null;
        try {
            produto = ProdutoDAO.obter(id);
            categorias = ProdutoDAO.obterCategoria();
        } catch (Exception e) {
             e.printStackTrace();
        }
        request.setAttribute("id", id);
        request.setAttribute("prod", produto);
        request.setAttribute("categoria", categorias);

        //Request diretorio
        request.getRequestDispatcher("WEB-INF/Produto/alterarProduto.jsp")
                .forward(request, response); 
        
        
    }

     @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("codProd"));
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String[] categorias = request.getParameterValues("cat");        
        String qtdStr = request.getParameter("qtd");
        String precoCompraStr = request.getParameter("prcompra");
        String precoVendaStr = request.getParameter("prvenda");
        String descricao = request.getParameter("descricao");
        Double precoCompra = new Double(precoCompraStr);
        Double precoVenda = new Double(precoVendaStr);
        int qtd = Integer.parseInt(qtdStr);      
        
        List<Categoria> categorias2 = new ArrayList<Categoria>();
        
        Produto p = new Produto(nome, marca,categorias, qtd, precoCompra, precoVenda,descricao );
        p.setId(id);
        try {
           categorias2 = ProdutoDAO.obterCategoria();
           ProdutoDAO.alterar(p);
           ProdutoDAO.alterarCategoriaProduto(categorias, id);
           JOptionPane.showMessageDialog(null, "Dado(s) do produto alterado(s)");
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s) do produto. Erro encontrado: "+e);
        }
        request.setAttribute("id", id);
        request.setAttribute("prod", p);
        request.setAttribute("categoria", categorias2);
        

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Produto/alterarProduto.jsp");
        dispatcher.forward(request, response);

    }

}
