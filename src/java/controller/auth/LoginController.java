/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import controller.HomeController;
import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.account.Account;

/**
 *
 * @author Ducky
 */
public class LoginController extends HomeController {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean isFailed = null;
        
        try{
            isFailed = Boolean.parseBoolean(request.getParameter("isFailed"));
        }catch(Exception e){
            request.setAttribute("isFailed", null);
        }
        request.setAttribute("pageInclude", "/view/auth/login.jsp");
        super.loadHeaderFooter(request, response);
        request.getRequestDispatcher("../view/home.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        AccountDBContext adb = new AccountDBContext();
        Account account = adb.getAccount(username, password);
        if (account != null){
            request.setAttribute("isFailed", false);
            request.getSession().setAttribute("account", account);
            request.getSession().setAttribute("account", account);
            response.sendRedirect(request.getContextPath() + "/home");
        }else{
            request.setAttribute("isFailed", true);
            request.getSession().setAttribute("account", null);
            response.sendRedirect(request.getContextPath() + "/account/login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
