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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.account.Account;

/**
 *
 * @author Ducky
 */
public class TransactionController extends HomeController {

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
        Account account = (Account) request.getSession().getAttribute("account");
        AccountDBContext adb = new AccountDBContext();
        if (account != null) {
            String raw_page = request.getParameter("page");
            if (raw_page == null || raw_page.length() == 0) {
                raw_page = "1";
            }
            int page = Integer.parseInt(raw_page);
            int pagesize = 10;

            int count = adb.getTransactionCount(account.getUsername());
            int totalPage = (count % pagesize == 0) ? count / pagesize : (count / pagesize) + 1;


            account = adb.getTransactions(account, page, pagesize);
            loadHeaderFooter(request, response);
            request.setAttribute("account", account);
            request.setAttribute("mainBody", "../account/transaction.jsp");
            request.setAttribute("mainTitle", "Lịch sử giao dịch");
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", page);
            request.setAttribute("pageInclude", "../view/account/container.jsp");
            request.getRequestDispatcher("../view/home.jsp").forward(request, response);
            return;
        } else {
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
