/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cinema;

import controller.HomeController;
import dal.CinemaDBContext;
import dal.MovieDBContext;
import dal.TicketDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cinema.Cinema;
import model.cinema.PerformanceNumber;
import model.movie.Movie;
import model.ticket.Price;

/**
 *
 * @author Ducky
 */
public class CinemaRowSeatController extends HomeController {
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
            throws ServletException, IOException{
        Boolean isBooking = (Boolean)request.getSession().getAttribute("isBooking");
        if(isBooking == null || !isBooking){
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        int cinemaId = Integer.parseInt(request.getParameter("id").split("-")[0].trim());
        int movieid = Integer.parseInt(request.getParameter("movieid"));
        Date day = Date.valueOf(request.getParameter("day"));
        int pn = Integer.parseInt(request.getParameter("id").split("-")[1].trim());
        
        MovieDBContext mdb = new MovieDBContext();
        CinemaDBContext cdb = new CinemaDBContext();
        
        Movie movie = mdb.getMovie(Integer.parseInt(request.getParameter("movieid")));
        Cinema cinema = cdb.getCinema(cinemaId);
        
        int[][] seats = cdb.getSeatBooked(cinemaId, movieid, pn, day);
        
        PerformanceNumber performanceNumber = new PerformanceNumber();
        for (PerformanceNumber pnumber : cinema.getPerformanceNumbers()){
            if (pnumber.getNumber() == pn){
                performanceNumber = pnumber;
            }
        }
        
        TicketDBContext tdb = new TicketDBContext();
        Price standardPrice = tdb.getStandardPrice(day);
        Price vipprimePrice = tdb.getVipprimePrice(day);
        request.setAttribute("standardPrice", standardPrice);
        request.setAttribute("vipprimePrice", vipprimePrice);
        request.setAttribute("movie", movie);
        request.setAttribute("cinema", cinema);
        request.setAttribute("day", day);
        request.setAttribute("seats", seats);
        
        request.setAttribute("performance", performanceNumber);
        request.getSession().setAttribute("movie", movie);
        request.getSession().setAttribute("cinema", cinema);
        request.getSession().setAttribute("day", day);
        request.getSession().setAttribute("performance", performanceNumber);
//        request.setAttribute("day", Date.valueOf(request.getParameter("day")));
        request.setCharacterEncoding("UTF-8");
        loadHeaderFooter(request, response);
        request.setAttribute("pageInclude", "/view/cinema/rowseat.jsp");
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
