/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cinema;

import controller.HomeController;
import dal.CinemaDBContext;
import dal.MovieDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.cinema.Cinema;
import model.movie.Movie;

/**
 *
 * @author Ducky
 */
public class CinemaBookingController extends HomeController {

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
        request.getSession().setAttribute("isBooking", true);
        
        ArrayList<Cinema> cinemasNow = new ArrayList<>();
        
        CinemaDBContext cdb = new CinemaDBContext();
        
        java.util.Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String now = sdf.format(date);
        cinemasNow = cdb.getCinemasAfterNow(now);
        
        ArrayList<Cinema> cinemas = cdb.getCinemas();

        request.setAttribute("cinemasNow", cinemasNow);
        request.setAttribute("cinemas", cinemas);
        
//        request.setAttribute("isHave", cdb.getCinemas());
//        response.getWriter().write();
        loadHeaderFooter(request, response);
        request.setAttribute("pageInclude", "/view/cinema/details.jsp");
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
        CinemaDBContext cdb = new CinemaDBContext();
        int movieid = Integer.parseInt(request.getParameter("movieid"));
        Date day = Date.valueOf(request.getParameter("day"));
        MovieDBContext mdb = new MovieDBContext();
        ArrayList<Movie> movies = mdb.getMoviesByDate(day);
        response.setCharacterEncoding("UTF-8");
        if (movies.isEmpty()) {
            response.getWriter().print("Xin lỗi, không có xuất chiếu vào ngày này, hãy chọn một ngày khác.");
        }else{
            response.getWriter().print("");
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
