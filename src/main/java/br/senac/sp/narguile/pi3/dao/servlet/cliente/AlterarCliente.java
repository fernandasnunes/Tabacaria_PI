/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.narguile.pi3.dao.servlet.cliente;

import br.senac.sp.narguile.pi3.dao.ClienteDAO;
import br.senac.sp.narguile.pi3.model.Cliente;
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
 * @author raffael
 */
@WebServlet(name = "AlterarCliente", urlPatterns = {"/AlterarCliente"})
public class AlterarCliente extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("id"));
        
       Cliente cliente = null;
        try {
            cliente = ClienteDAO.pesquisar(id);
            
        } catch (Exception e) {
             
        }
        request.setAttribute("id", id);
        request.setAttribute("cliente", cliente);
        //Request diretorio
        request.getRequestDispatcher("WEB-INF/Cliente/alterarCliente.jsp")
                .forward(request, response);        
    }
    
     @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("codCliente"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");  
        
           Cliente cliente = new Cliente (nome,endereco,bairro,cidade,estado,cep,sexo,telefone,celular);
           cliente.setId(id);
           
        try {
           
          ClienteDAO.alterarCliente(cliente);
           JOptionPane.showMessageDialog(null, "Dado(s) do cliente alterado(s)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dado(s) de cliente. Erro encontrado: "+e);
        }
        request.setAttribute("id", id);
        request.setAttribute("cliente", cliente);              
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Cliente/alterarCliente.jsp");
        dispatcher.forward(request, response);   
    
  }
}