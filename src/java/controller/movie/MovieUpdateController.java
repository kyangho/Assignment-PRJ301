/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.movie;

import dal.MovieDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.movie.Movie;
import model.movie.MovieGenre;
import model.movie.MovieLanguage;
import model.movie.MovieShowing;

/**
 *
 * @author Ducky
 */
public class MovieUpdateController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        MovieDBContext mdb = new MovieDBContext();
        Movie movie = mdb.getMovie(id);
        ArrayList<MovieLanguage> movieLanguages = new ArrayList<>();
        movieLanguages = mdb.getMovieLanguages();
        ArrayList<MovieGenre> movieGenres = new ArrayList<>();
        movieGenres = mdb.getMovieGenres();
        request.setAttribute("movie", movie);
        request.setAttribute("movieLanguages", movieLanguages);
        request.setAttribute("movieGenres", movieGenres);
        request.getRequestDispatcher("../../view/movie/update.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        Movie movie = new Movie();
        movie.setId(Integer.parseInt(request.getParameter("id")));
        movie.setName(request.getParameter("name"));
        movie.setDirector(request.getParameter("director"));
        movie.setCast(request.getParameter("cast"));
        movie.setRunningTime(Date.valueOf(request.getParameter("runningTime")));
        MovieShowing movieShowing = new MovieShowing();
        movieShowing.setFromShowing(Date.valueOf(request.getParameter("toShowing")));
        movieShowing.setToShowing(Date.valueOf(request.getParameter("fromShowing")));
        movie.setMovieShowing(movieShowing);
        movie.setRated(request.getParameter("rated"));
        movie.setDescription(request.getParameter("description"));
        movie.setDuration(Integer.parseInt(request.getParameter("duration")));
        movie.setUrlImage(request.getParameter("urlImage"));
        movie.setUrlTrailer(request.getParameter("urlTrailer"));
        ArrayList<MovieGenre> movieGenres = new ArrayList<>();
        try{
            for (String s : request.getParameterValues("genre")){
                MovieGenre movieGenre = new MovieGenre();
                movieGenre.setId(Integer.parseInt(s));
                movieGenres.add(movieGenre);
            }
        }catch(Exception e){
                
        }
        movie.setGenre(movieGenres);
        
        ArrayList<MovieLanguage> movieLanguages = new ArrayList<>();
        try{
            for (String s : request.getParameterValues("language")){
            MovieLanguage movieLanguage = new MovieLanguage();
            movieLanguage.setId(Integer.parseInt(s));
            movieLanguages.add(movieLanguage);
        }
        
        }catch(Exception e){
            
        }
        
        movie.setLanguages(movieLanguages);
        MovieDBContext mdb = new MovieDBContext();
        boolean isUpdate = mdb.updateMovie(movie);
        request.setAttribute("isUpdate", isUpdate);
        request.setAttribute("genre", request.getParameterValues("genre"));
        request.getRequestDispatcher("../../view/testpage.jsp").forward(request, response);
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
