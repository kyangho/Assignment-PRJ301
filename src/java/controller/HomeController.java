    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.MovieDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.account.Account;
import model.movie.Movie;

/**
 *
 * @author Ducky
 */

public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void loadHeaderFooter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
//        Account account = (Account)request.getSession().getAttribute("account");
//        request.setAttribute("account", account);
//        request.setAttribute("dob", account.getDob().toString());
        String home_href = "/Assignment-PRJ301/home";
        String login_href = "/Assignment-PRJ301/account/login";
        String login_href_value = "Đăng nhập";
        String register_href = "/Assignment-PRJ301/account/register";
        String register_href_value = "Đăng ký";
        String logout_href = "/Assignment-PRJ301/account/logout";
        String logout_href_value = "Đăng xuất";
        if (request.getSession().getAttribute("account") == null){
            logout_href = "";
            logout_href_value = "";
            
        }else{
            login_href_value = "Hello, ";
            register_href = "";
            register_href_value = "";
            Account account = (Account)request.getSession().getAttribute("account");
            login_href_value += account.getDisplayName();
            login_href = "/Assignment-PRJ301/account/info";
        }
        request.setAttribute("home_href", home_href);
        request.setAttribute("login_href", login_href);
        request.setAttribute("login_href_value", login_href_value);
        request.setAttribute("register_href", register_href);
        request.setAttribute("register_href_value", register_href_value);
        request.setAttribute("logout_href", logout_href);
        request.setAttribute("logout_href_value", logout_href_value);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadHeaderFooter(request, response);
        request.getSession().removeAttribute("isBooking");
        MovieDBContext mdb = new MovieDBContext();
        ArrayList<Movie> movies = new ArrayList<>();
        java.util.Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        movies = mdb.getMoviesByDate(Date.valueOf(sdf.format(date)));
        request.setAttribute("movies", movies);
        request.setAttribute("pageInclude", "/view/movie/list.jsp");
        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
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
