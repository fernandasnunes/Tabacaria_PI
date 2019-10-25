/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.funcionario;

import br.senac.pg.tabacaria.dao.FuncionarioDAO;
import br.senac.pg.tabacaria.model.Funcionario;
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
 * @author fernanda
 */
@WebServlet(name = "AlterarFuncionario", urlPatterns = {"/AlterarFuncionario"})
public class AlterarFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        
        Funcionario funcionario = null;
        try {
            funcionario = FuncionarioDAO.obter(id);
            
        } catch (Exception e) {
             e.printStackTrace();
        }
        request.setAttribute("id", id);
        request.setAttribute("func", funcionario);
        request.getRequestDispatcher("WEB-INF/Funcionario/alterarFuncionario.jsp")
                .forward(request, response);            
    }

     @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("codFunc"));
        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");                    

        Funcionario f = new Funcionario(nome, cargo,endereco, bairro, cidade, estado,cep, sexo,telefone,celular );                        
        f.setId(id);
        try {
           
           FuncionarioDAO.alterar(f);
           JOptionPane.showMessageDialog(null, "Dado(s) de funcionário alterado(s)");
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s) de funcionário. Erro encontrado: "+e);
        }
        request.setAttribute("id", id);
        request.setAttribute("func", f);              
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Funcionario/alterarFuncionario.jsp");
        dispatcher.forward(request, response);

    }
}
