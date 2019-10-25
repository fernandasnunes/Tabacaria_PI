/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.funcionario;

import br.senac.pg.tabacaria.dao.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ExcluirFuncionario", urlPatterns = {"/ExcluirFuncionario"})
public class ExcluirFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Funcionario/exibirFuncionario.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String codigo = request.getParameter("id");
        long id = Long.parseLong(codigo);
        try 
        {
           FuncionarioDAO.remove(id);
           JOptionPane.showMessageDialog(null, "Funcionário desativado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar funcionario. Erro encontrado: "+e);
        }
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Funcionario/exibirFuncionario.jsp");
        dispatcher.forward(request, response);
    }
}
