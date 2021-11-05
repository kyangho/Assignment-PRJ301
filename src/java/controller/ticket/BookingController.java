/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ticket;

import controller.HomeController;
import dal.TicketDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.account.Account;
import model.cinema.Cinema;
import model.cinema.PerformanceNumber;
import model.movie.Movie;

/**
 *
 * @author Ducky
 */
public class BookingController extends HomeController {

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
        Account account = (Account)request.getSession().getAttribute("account");
        Boolean isBooking = (Boolean)request.getSession().getAttribute("isBooking");
        
        if(account == null || isBooking == null || !isBooking){
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        request.getSession().removeAttribute("isBooking");
        
        Cinema cinema = (Cinema)request.getSession().getAttribute("cinema");
        Date day = (Date)request.getSession().getAttribute("day");
        PerformanceNumber performanceNumber = (PerformanceNumber)request.getSession().getAttribute("performance");
        Movie movie = (Movie)request.getSession().getAttribute("movie");
        
        request.getSession().removeAttribute("cinema");
        request.getSession().removeAttribute("day");
        request.getSession().removeAttribute("performance");
        request.getSession().removeAttribute("movie");
        
        
        String[] seats = request.getParameter("seat").split(",");
        String[] prices  = request.getParameter("price").trim().split("\\s+");
        
        TicketDBContext tdb = new TicketDBContext();
        boolean isSuccess = tdb.insertTicket(cinema.getId(), movie.getId(), seats, prices, day, performanceNumber.getNumber(), account.getUsername());
        
        request.setAttribute("movie", movie);
        request.setAttribute("cinema", cinema);
        request.setAttribute("performanceNumber", performanceNumber);
        request.setAttribute("day", day);
        request.setAttribute("seats", String.join(", ", seats));
        request.setAttribute("isSuccess", isSuccess);
        
        
        loadHeaderFooter(request, response);
        request.setAttribute("pageInclude", "/view/ticket/ticket.jsp");
        request.getRequestDispatcher("../../view/home.jsp").forward(request, response);
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
