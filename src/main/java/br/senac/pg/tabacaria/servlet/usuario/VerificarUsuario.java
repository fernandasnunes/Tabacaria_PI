package br.senac.pg.tabacaria.servlet.usuario;

import br.senac.pg.tabacaria.dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

public class VerificarUsuario extends HttpServlet {

    @WebServlet(name = "VerificarUsuario", urlPatterns = {"/VerificarUsuario"})
    public class IncluirProduto extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            HttpSession sessao = request.getSession();
            String login = request.getParameter("username");
            String senha = request.getParameter("password");
            String nome = null;

            try {
                UsuarioDAO.verificar(login, senha);
                nome = (String) sessao.getAttribute("nome");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Janelas de abetura de acordo com a senha
            if (nome != null) {
                response.sendRedirect("menu.jsp");
            } else {
                response.sendRedirect("index.html");
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        }

    }

}
