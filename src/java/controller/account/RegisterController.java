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
public class RegisterController extends HomeController {




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
        if (account == null){;
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("displayname", request.getParameter("displayname"));
            request.setAttribute("phone", request.getParameter("phone"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("dob", request.getParameter("dob"));
            request.setAttribute("pageInclude", "account/register.jsp");
            super.loadHeaderFooter(request, response);
        }else{
            response.sendRedirect("/account/login");
        }

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
        
        Account account = new Account();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String displayname = request.getParameter("displayname"); 
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        Date dob = Date.valueOf(request.getParameter("dob"));
        account.setUsername(username);
        account.setPassword(password);
        account.setDisplayName(displayname);
        account.setPhone(phone);
        account.setEmail(email);
        account.setGender(gender);
        account.setDob(dob);
        AccountDBContext adb = new AccountDBContext();
        if (!adb.isValidAccount(username)){
            request.setAttribute("username", username);
            request.setAttribute("displayname", displayname);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("dob", dob);
            request.setAttribute("invalidUser", false);
            request.setAttribute("pageInclude", "account/register.jsp");
            super.loadHeaderFooter(request, response);
            request.getRequestDispatcher("../view/home.jsp").forward(request, response);
        }
        else if (password.compareTo(request.getParameter("comfirmPassword")) != 0){
            request.setAttribute("username", username);
            request.setAttribute("displayname", displayname);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("gender", gender);
            request.setAttribute("dob", dob);
            request.setAttribute("pageInclude", "account/register.jsp");
            request.setAttribute("isPassMatch", false);
            super.loadHeaderFooter(request, response);
            request.getRequestDispatcher("../view/home.jsp").forward(request, response);
        }else{
            adb.insertAccount(account);
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
