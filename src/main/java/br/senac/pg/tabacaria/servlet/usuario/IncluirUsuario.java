/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.usuario;

import br.senac.pg.tabacaria.dao.UsuarioDAO;
import br.senac.pg.tabacaria.model.Funcionario;
import br.senac.pg.tabacaria.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author raffael
 */
@WebServlet(name = "IncluirUsuario", urlPatterns = {"/IncluirUsuario"})
public class IncluirUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
       
                // Carregar aqui os departamentos
        List<Funcionario> funcionario = new ArrayList<Funcionario>();

        try {
           funcionario = UsuarioDAO.obterFuncionario();
        } catch (Exception e) {
             e.printStackTrace();
        }
          
        request.setAttribute("funcionario", funcionario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Usuario/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("func");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String ativo = "S";

        Usuario u = new Usuario(login, nome, senha, ativo);
        // Carregar aqui os departamentos
        List<Funcionario> funcionario = new ArrayList<Funcionario>();
        
        try {
//            funcionario = UsuarioDAO.obterFuncionario();
            UsuarioDAO.inserir(u);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar usuário. Erro encontrado: "+ e);
        }

        request.setAttribute("usuario", u);
//        request.setAttribute("func", funcionario);

        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "WEB-INF/Usuario/cadastrarUsuario.jsp");
        dispatcher.forward(request, response);

    }
}
