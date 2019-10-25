/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.cliente;

import br.senac.pg.tabacaria.dao.ClienteDAO;
import br.senac.pg.tabacaria.model.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        
        Cliente cliente = null;
        String nome= request.getParameter("nome");
       try{
            cliente = ClienteDAO.pesquisar(nome);
            request.setAttribute("nome", cliente.getNome());
            request.setAttribute("sexo", cliente.getSexo());
            request.setAttribute("datanascimente", cliente.getDataNascimento());
            request.setAttribute("cpf", cliente.getCpf());
            request.setAttribute("endereco", cliente.getEndereco());
            request.setAttribute("telefone", cliente.getTelefone());
            request.setAttribute("email", cliente.getEmail());
            
            this.getServletContext().getRequestDispatcher("/alterarCliente.jsp").forward(request, response);
            
        }catch(Exception e ){
            
        }
    }
    
     @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
       
    
  }
}