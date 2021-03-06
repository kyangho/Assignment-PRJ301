/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.account;

import controller.HomeController;
import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.account.Account;

/**
 *
 * @author Ducky
 */
public class UpdateAccountController extends HomeController {

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
        Account account = (Account)request.getSession().getAttribute("account");
        if (account != null){
            request.setAttribute("isUpdateSuccess", null);
            request.setAttribute("account", account);
            request.setAttribute("dob", account.getDob().toString());
            request.setAttribute("mainBody", "../account/update.jsp");
            request.setAttribute("mainTitle", "Cập nhật thông tin");
            request.setAttribute("pageInclude", "../view/account/container.jsp");
            loadHeaderFooter(request, response);
            request.getRequestDispatcher("../view/home.jsp").forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/account/login");
            return;
        }
        
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
        
        String displayName = request.getParameter("displayName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Date dob = Date.valueOf(request.getParameter("dob"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        
        Account account = (Account)request.getSession().getAttribute("account");
        account.setDisplayName(displayName);
        account.setEmail(email);
        account.setPhone(phone);
        account.setDob(dob);
        account.setGender(gender);
        AccountDBContext adb = new AccountDBContext();
        boolean isUpdateSuccess = adb.updateAccount(account);
        request.setAttribute("account", account);
        request.setAttribute("isUpdateSuccess", isUpdateSuccess);
        request.setAttribute("mainBody", "../account/update.jsp");
        request.setAttribute("mainTitle", "Cập nhật thông tin");
        request.setAttribute("pageInclude", "../view/account/container.jsp");
        loadHeaderFooter(request, response);
        request.getRequestDispatcher("../view/home.jsp").forward(request, response);
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
