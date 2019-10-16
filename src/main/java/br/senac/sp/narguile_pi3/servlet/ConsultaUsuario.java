/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile_pi3.servlet;

import br.senac.sp.narguile.pi3.dao.UsuarioDAO;
import br.senac.sp.narguile.pi3.model.Usuario;
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
@WebServlet(name = "ConsultaUsuario", urlPatterns = {"/ConsultaUsuario"})
public class ConsultaUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Usuario/pesquisaUsuario.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        //Variavel do id
        long id = Long.parseLong(request.getParameter("id"));
        
        Usuario usuario = null;
        try {if(UsuarioDAO.obter(id) == null){
                JOptionPane.showMessageDialog(null, "Usuario não encontrado");
            }else
            usuario = UsuarioDAO.obter(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado");
        }
        request.setAttribute("usuario", usuario);
        
        //Request diretorio
        request.getRequestDispatcher("WEB-INF/Usuario/exibirUsuario.jsp").forward(request, response); 

    }
}
