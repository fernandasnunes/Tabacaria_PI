/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pg.tabacaria.servlet.filter;

import br.senac.pg.tabacaria.servlet.usuario.UsuarioSistema;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fernando.tsuda
 */
@WebFilter(filterName = "AutorizacaoFilter",
        servletNames = {"HomeServlet"},
        urlPatterns = {"/pgProtect/*"})

public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession();

        if (sessao.getAttribute("usuario") == null) {
            // Usuario nao fez login -> redirecionar usuario para tela de login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        // Usuario já esta logado -> Recupera objeto da sessao
        UsuarioSistema usuario
                = (UsuarioSistema) sessao.getAttribute("usuario");

        if (verificarAutorizacao(usuario, httpRequest)) {
            // Usuario pode acessar a URL
            chain.doFilter(request, response);
        } else {
     
            httpResponse.sendRedirect(httpRequest.getContextPath()
                    + "/pgProtect/Home");
            
        }
    }

    private boolean verificarAutorizacao(
            UsuarioSistema usuario,
            HttpServletRequest httpRequest) {
        String urlAcessada = httpRequest.getRequestURI();
        if (urlAcessada.endsWith("/login")) {
            return true;
        } else if (urlAcessada.contains("/Filial")
                && usuario.verificarPapel("GERENTE")) {
            return true;
        } else if (urlAcessada.contains("/Cliente")
                && (usuario.verificarPapel("GERENTE") || usuario.verificarPapel("FUNCIONARIO"))) {
            return true;
        } else if (urlAcessada.contains("/Produto")
                && (usuario.verificarPapel("GERENTE") || usuario.verificarPapel("FUNCIONARIO"))) {
            return true;
        } else if (urlAcessada.contains("/Funcionario")
                && (usuario.verificarPapel("GERENTE") || usuario.verificarPapel("FUNCIONARIO"))) {
            return true;
        } else if (urlAcessada.contains("/Home")
                && (usuario.verificarPapel("GERENTE") || usuario.verificarPapel("FUNCIONARIO"))) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

}
