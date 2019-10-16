/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile.pi3.dao.servlet.cliente;

import br.senac.sp.narguile.pi3.dao.ClienteDAO;
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
 * @author serafim
 */
@WebServlet(name = "ExcluirCliente", urlPatterns = {"/ExcluirCliente"})
public class ExcluirCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Cliente/resultadoConsulta.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("id");
        long id = Long.parseLong(codigo);
        //Conexão para abertura e fechamento
        try {
            ClienteDAO.excluirCliente(id);
            JOptionPane.showMessageDialog(null, "Cliente desativado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao desativar cliente. Erro encontrado: "+e);
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Cliente/resultadoConsulta.jsp");
        dispatcher.forward(request, response);
    }
}
