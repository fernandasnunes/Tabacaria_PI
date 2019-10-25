/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.usuario;

import br.senac.pg.tabacaria.dao.UsuarioDAO;
import br.senac.pg.tabacaria.model.Usuario;
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
 * @author matheus
 */
@WebServlet(name = "AlterarUsuario", urlPatterns = {"/AlterarUsuario"})
public class AlterarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        
        Usuario usuario = null;
        try {
            usuario = UsuarioDAO.obter(id);
            
        } catch (Exception e) {
             e.printStackTrace();
        }
        request.setAttribute("id", id);
        request.setAttribute("usuario", usuario);
        //Request diretorio
        request.getRequestDispatcher("WEB-INF/Usuario/alterarUsuario.jsp")
                .forward(request, response);            
    }

     @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("codUsu"));
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String ativo = "S";

        Usuario u = new Usuario(nome, login,senha, ativo);                        
        u.setId(id);
        try {           
           UsuarioDAO.alterar(u);
           JOptionPane.showMessageDialog(null, "Dado(s) do usuário alterado(s)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s) de usuário. Erro encontrado: "+e);
        }
        request.setAttribute("id", id);
        request.setAttribute("func", u);              
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Usuario/alterarUsuario.jsp");
        dispatcher.forward(request, response);

    }
}
