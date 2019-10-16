    package br.senac.sp.narguile_pi3.servlet;

import br.senac.sp.narguile.pi3.dao.ProdutoDAO;
import br.senac.sp.narguile.pi3.model.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
/**
 *
 * @author rafael
 */
@WebServlet(name = "ConsultaProduto", urlPatterns = {"/ConsultaProduto"})
    public class ConsultaProduto extends HttpServlet {
    
      @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Produto/pesquisaProduto.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        //Variavel do id
        long id = Long.parseLong(request.getParameter("id"));
        
        Produto produto = null;
        try {
            if(ProdutoDAO.obter(id) == null){
                JOptionPane.showMessageDialog(null, "Produto não encontrado");
            }else
            produto = ProdutoDAO.obter(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado");             
        }
        request.setAttribute("prod", produto);
        
        //Request diretorio
        request.getRequestDispatcher("WEB-INF/Produto/exibirProduto.jsp").forward(request, response); 

    }
    
}
