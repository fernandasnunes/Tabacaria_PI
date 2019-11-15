/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.funcionario;


import br.senac.pg.tabacaria.dao.FuncionarioDAO;

import br.senac.pg.tabacaria.model.Funcionario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafael
 */
@WebServlet("/Funcionario")
public class ControllerFuncionario extends HttpServlet {
 private FuncionarioDAO funcionarioDAO;

    public void init() {
        funcionarioDAO = new FuncionarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/NovoFuncionario":
                    novoFormulario(request, response);
                    break;
                case "/InserirFuncionario":
                    inserirFuncionario(request, response);
                    break;
                case "/DeletarFuncionario":
                    deletarCliente(request, response);
                    break;
                case "/EditarFuncionario":
                    formularioEdicao(request, response);
                    break;
                case "/EdicaoFuncionario":
                    editarCliente(request, response);
                    break;
                
                default:
                    listarFuncionario(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Funcionario> listaFuncionario = funcionarioDAO.selecionarTodosFuncionarios();
        request.setAttribute("listaFuncionario", listaFuncionario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Funcionario/exibirFuncionario.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void novoFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    private void formularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        Funcionario funcionarioCadastrado = funcionarioDAO.selecionarFuncionario(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Funcionario/cadastrarFuncionario.jsp");
        request.setAttribute("funcionario", funcionarioCadastrado);
        dispatcher.forward(request, response);

    }

    private void inserirFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        
        Funcionario novoFuncionario = new Funcionario(nome, cargo, endereco, sexo, telefone);     
        funcionarioDAO.inserirFuncionario(novoFuncionario);
        response.sendRedirect("listFuncionario");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String endereco = request.getParameter("endereco");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");

        Funcionario funcionario = new Funcionario(id, nome, cargo, endereco, sexo, telefone);
        funcionarioDAO.inserirFuncionario(funcionario);
        response.sendRedirect("listFuncionario");
    }

    private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        funcionarioDAO.deletarFuncionario(id);
        response.sendRedirect("listFuncionario");

    }
}
