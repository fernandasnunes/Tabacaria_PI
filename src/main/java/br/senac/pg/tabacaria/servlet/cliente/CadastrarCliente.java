package br.senac.pg.tabacaria.servlet.cliente;

import br.senac.pg.tabacaria.dao.ClienteDAO;
import br.senac.pg.tabacaria.model.Cliente;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("WEB-INF/Cliente/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
    }
    
   @Override
        protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
            
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("datanascimento");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataCadastro = sdf.format(data);
        
            
            Cliente cliente = new Cliente (nome, sexo, dataNascimento, cpf, endereco, telefone, email, dataCadastro);
        
        try {
            
            ClienteDAO.inserirCliente(cliente);
          
        }   catch (Exception e) {
           
        }
        
        request.setAttribute("cliente", cliente);                
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "WEB-INF/Cliente/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
   }
}