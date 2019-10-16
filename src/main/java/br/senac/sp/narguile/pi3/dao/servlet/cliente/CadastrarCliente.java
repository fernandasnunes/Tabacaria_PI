
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
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular"); 
            
            Cliente cliente = new Cliente (nome,endereco,bairro,cidade,estado,cep,sexo,telefone,celular);
        
        try {
            
            ClienteDAO.inserirCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado");
        }   catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente. Erro encontrado: "+ e);
        }
        
        request.setAttribute("cliente", cliente);                
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher(
                        "WEB-INF/Cliente/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
   }
}
