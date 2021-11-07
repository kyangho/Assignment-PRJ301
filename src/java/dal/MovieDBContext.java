/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.movie.Movie;
import model.movie.MovieGenre;
import model.movie.MovieLanguage;
import model.movie.MovieShowing;

/**
 *
 * @author Ducky
 */
public class MovieDBContext extends DBContext{
    public ArrayList<Movie> getMovies(){
        String sql = "SELECT [movie_id]\n" +
                    "      ,[movie_name]\n" +
                    "      ,[movie_director]\n" +
                    "      ,[movie_cast]\n" +
                    "      ,[movie_running_time]\n" +
                    "      ,[movie_duration]\n" +
                    "      ,[movie_rated]\n" +
                    "      ,[movie_description]\n" +
                    "      ,[movie_url_trailer]\n" +
                    "      ,[movie_url_image]\n" +
                    "  FROM [Movie]";
        String sql_genre = "SELECT mmg.movie_id, mmg.movie_genre_id, mg.movie_genre\n" +
                        "  FROM [Movie_Movie_Genre] as mmg\n" +
                        "  LEFT JOIN [Movie_Genre] as mg on mg.movie_genre_id = mmg.movie_genre_id\n" +
                        "  where mmg.movie_id = ?";
        String sql_language = "SELECT mml.[movie_language_id]\n" +
                            "	  ,ml.[movie_language]\n" +
                            "  FROM [Movie_Movie_Language] as mml\n" +
                            "  LEFT JOIN [Movie_Language] as ml on ml.movie_language_id = mml.[movie_language_id]"+
                            "  WHERE mml.[movie_id] = ?";
        String sql_movie_showing = "SELECT top (1) mms.[movie_showing_id]\n" +
                "      ,mms.[movie_id]\n" +
                "	  ,ms.[showing_from_date]\n" +
                "	  ,ms.[showing_to_date]\n" +
                "  FROM [Movie_Movie_Showing] as mms\n" +
                "  LEFT JOIN [Movie_Showing] as ms on ms.[movie_showing_id] = mms.[movie_showing_id]\n" +
                "  where mms.[movie_id] = ?\n" +
                "  order by ms.[showing_from_date] desc";
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Movie movie = new Movie();
                movie.setId(rs.getInt("movie_id"));
                movie.setName(rs.getString("movie_name"));
                movie.setDirector(rs.getString("movie_director"));
                movie.setCast(rs.getString("movie_cast"));
                movie.setDuration(rs.getInt("movie_duration"));
                movie.setRunningTime(rs.getDate("movie_running_time"));
                movie.setRated(rs.getString("movie_rated"));
                movie.setUrlImage(rs.getString("movie_url_image"));
                movie.setUrlTrailer(rs.getString("movie_url_trailer"));
                movie.setDescription(rs.getString("movie_description"));    
                PreparedStatement stm_genre = connection.prepareStatement(sql_genre);
                stm_genre.setInt(1, movie.getId()); 
                ResultSet rs_genre = stm_genre.executeQuery();
                ArrayList<MovieGenre> genres = new ArrayList<>();
                while(rs_genre.next()){
                    MovieGenre movieGenre = new MovieGenre();
                    movieGenre.setId(rs_genre.getInt("movie_genre_id"));
                    movieGenre.setGenre(rs_genre.getString("movie_genre"));
                    genres.add(movieGenre);
                }
                
                PreparedStatement stm_language = connection.prepareStatement(sql_language);
                stm_language.setInt(1, movie.getId());
                ResultSet rs_language = stm_language.executeQuery();
                ArrayList<MovieLanguage> languages = new ArrayList<>();
                
                while(rs_language.next()){
                    MovieLanguage movieLanguage = new MovieLanguage();
                    movieLanguage.setId(rs_language.getInt("movie_language_id"));
                    movieLanguage.setLanguage(rs_language.getString("movie_language"));
                    languages.add(movieLanguage);
                }
                movie.setLanguages(languages);
                movie.setGenre(genres);
                PreparedStatement stm_movie_showing = connection.prepareStatement(sql_movie_showing);
                stm_movie_showing.setInt(1, movie.getId());
                ResultSet rs_movie_showing = stm_movie_showing.executeQuery();
                MovieShowing movieShowing = new MovieShowing();
                if(rs_movie_showing.next()){   
                    movieShowing.setFromShowing(rs_movie_showing.getDate("showing_from_date"));
                    movieShowing.setToShowing(rs_movie_showing.getDate("showing_to_date"));
                }
                movie.setMovieShowing(movieShowing);
                movies.add(movie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return movies;
    }
    public Movie getMovie(int id){
        String sql = "SELECT [movie_id]\n" +
                    "      ,[movie_name]\n" +
                    "      ,[movie_director]\n" +
                    "      ,[movie_cast]\n" +
                    "      ,[movie_running_time]\n" +
                    "      ,[movie_duration]\n" +
                    "      ,[movie_rated]\n" +
                    "      ,[movie_description]\n" +
                    "      ,[movie_url_trailer]\n" +
                    "      ,[movie_url_image]\n" +
                    "  FROM [Movie]" +
                    "  WHERE [movie_id] = ?";
        String sql_genre = "SELECT mmg.movie_id, mmg.movie_genre_id, mg.movie_genre\n" +
                        "  FROM [Movie_Movie_Genre] as mmg\n" +
                        "  LEFT JOIN [Movie_Genre] as mg on mg.movie_genre_id = mmg.movie_genre_id\n" +
                        "  where mmg.movie_id = ?";
        String sql_language = "SELECT mml.[movie_language_id]\n" +
                            "	  ,ml.[movie_language]\n" +
                            "  FROM [Movie_Movie_Language] as mml\n" +
                            "  LEFT JOIN [Movie_Language] as ml on ml.movie_language_id = mml.[movie_language_id]"+
                            "  WHERE mml.[movie_id] = ?";
        String sql_movie_showing = "SELECT top (1) mms.[movie_showing_id]\n" +
                "      ,mms.[movie_id]\n" +
                "	  ,ms.[showing_from_date]\n" +
                "	  ,ms.[showing_to_date]\n" +
                "  FROM [Movie_Movie_Showing] as mms\n" +
                "  LEFT JOIN [Movie_Showing] as ms on ms.[movie_showing_id] = mms.[movie_showing_id]\n" +
                "  where mms.[movie_id] = ?\n" +
                "  order by ms.[showing_from_date] desc";
        Movie movie = new Movie();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                movie.setId(rs.getInt("movie_id"));
                movie.setName(rs.getString("movie_name"));
                movie.setDirector(rs.getString("movie_director"));
                movie.setCast(rs.getString("movie_cast"));
                movie.setDuration(rs.getInt("movie_duration"));
                movie.setRunningTime(rs.getDate("movie_running_time"));
                movie.setRated(rs.getString("movie_rated"));
                movie.setUrlImage(rs.getString("movie_url_image"));
                movie.setUrlTrailer(rs.getString("movie_url_trailer"));
                movie.setDescription(rs.getString("movie_description"));
                PreparedStatement stm_genre = connection.prepareStatement(sql_genre);
                stm_genre.setInt(1, id); 
                ResultSet rs_genre = stm_genre.executeQuery();
                ArrayList<MovieGenre> genres = new ArrayList<>();
                while(rs_genre.next()){
                    MovieGenre movieGenre = new MovieGenre();
                    movieGenre.setId(rs_genre.getInt("movie_genre_id"));
                    movieGenre.setGenre(rs_genre.getString("movie_genre"));
                    genres.add(movieGenre);
                }
                
                PreparedStatement stm_language = connection.prepareStatement(sql_language);
                stm_language.setInt(1, id);
                ResultSet rs_language = stm_language.executeQuery();
                ArrayList<MovieLanguage> languages = new ArrayList<>();
                
                while(rs_language.next()){
                    MovieLanguage movieLanguage = new MovieLanguage();
                    movieLanguage.setId(rs_language.getInt("movie_language_id"));
                    movieLanguage.setLanguage(rs_language.getString("movie_language"));
                    languages.add(movieLanguage);
                }
                movie.setLanguages(languages);
                movie.setGenre(genres);
                
                PreparedStatement stm_movie_showing = connection.prepareStatement(sql_movie_showing);
                stm_movie_showing.setInt(1, id);
                ResultSet rs_movie_showing = stm_movie_showing.executeQuery();
                MovieShowing movieShowing = new MovieShowing();
                if(rs_movie_showing.next()){   
                    movieShowing.setFromShowing(rs_movie_showing.getDate("showing_from_date"));
                    movieShowing.setToShowing(rs_movie_showing.getDate("showing_to_date"));
                }
                movie.setMovieShowing(movieShowing);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return movie;
    }
    public ArrayList<MovieLanguage> getMovieLanguages(){
        ArrayList<MovieLanguage> movieLanguagues = new ArrayList<>();
        
        String sql = "SELECT movie_language_id, movie_language from [Movie_Language]";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                MovieLanguage movieLanguage = new MovieLanguage();
                movieLanguage.setId(Integer.parseInt(rs.getString("movie_language_id")));
                movieLanguage.setLanguage(rs.getString("movie_language"));
                movieLanguagues.add(movieLanguage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return movieLanguagues;
    }
    public ArrayList<MovieGenre> getMovieGenres(){
        ArrayList<MovieGenre> genres = new ArrayList<>();
        
        String sql = "SELECT movie_genre_id\n" +
                    "      ,[movie_genre]\n" +
                    "  FROM [Movie_Genre]";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                MovieGenre movieGenre = new MovieGenre();
                movieGenre.setId(rs.getInt("movie_genre_id"));
                movieGenre.setGenre(rs.getString("movie_genre"));
                genres.add(movieGenre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return genres;
    }
    public ArrayList<Movie> getMoviesByDate(int movieid, Date date){
        MovieDBContext mdb = new MovieDBContext();
        ArrayList<Movie> movies = mdb.getMovies();
        ArrayList<Movie> rsMovies = new ArrayList<>();
        for (Movie m : movies){
            if (m.getMovieShowing().getFromShowing().compareTo(date) <= 0
                    && m.getMovieShowing().getToShowing().compareTo(date)  >= 0 && m.getId() == movieid){
                rsMovies.add(m);
            }else{
            }
        }
        return rsMovies;
    }
    public boolean updateMovie(Movie movie){
        String sql_movie = "UPDATE [Movie]\n" +
                        "   SET [movie_name] = ?\n" +
                        "      ,[movie_director] = ?\n" +
                        "      ,[movie_cast] = ?\n" +
                        "      ,[movie_duration] = ?\n" +
                        "      ,[movie_running_time] = ?\n" +
                        "      ,[movie_url_image] = ?" +
                        "      ,[movie_url_trailer] = ?"+
                        "      ,[movie_rated] = ?\n" +
                        "      ,[movie_description] = ?\n" +
                        " WHERE [movie_id] = ?";
        String sql_movie_genre_insert = "INSERT INTO [Movie_Movie_Genre]\n" +
                        "           ([movie_id]\n" +
                        "           ,[movie_genre_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        String sql_movie_genre_delete = "DELETE FROM [Movie_Movie_Genre]\n" +
                        "      WHERE [movie_id] = ?";
        
        String sql_movie_language_insert = "INSERT INTO [Movie_Movie_Language]\n" +
                        "           ([movie_id]\n" +
                        "           ,[movie_language_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        String sql_movie_language_delete = "DELETE FROM [Movie_Movie_Language]\n" +
                        "      WHERE [movie_id] = ?";
        
        String sql_movie_showing = "INSERT INTO [Movie_Showing]\n" +
                        "           ([showing_from_date]\n" +
                        "           ,[showing_to_date])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        String sql_movie_movie_showing = "INSERT INTO [Movie_Movie_Showing]\n" +
                        "           ([movie_showing_id]\n" +
                        "           ,[movie_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        try{
            connection.setAutoCommit(false);
            //Update movie infor
            PreparedStatement stm = connection.prepareStatement(sql_movie);
            stm.setString(1, movie.getName());
            stm.setString(2, movie.getDirector());
            stm.setString(3, movie.getCast());
            stm.setInt(4, movie.getDuration());
            stm.setDate(5, movie.getRunningTime());
            stm.setString(6, movie.getUrlImage());
            stm.setString(7, movie.getUrlTrailer());
            stm.setString(8, movie.getRated());
            stm.setString(9, movie.getDescription());
            stm.setInt(10, movie.getId());
            stm.executeUpdate();
            
            //Update M2 movie genre
                //delete exist movie genre
            PreparedStatement stm_genre_delete = connection.prepareStatement(sql_movie_genre_delete);
            stm_genre_delete.setInt(1, movie.getId());
            stm_genre_delete.executeUpdate();
                //insert new movie genre
            for(MovieGenre movieGenre : movie.getGenre()){
                PreparedStatement stm_genre_insert = connection.prepareStatement(sql_movie_genre_insert);
                stm_genre_insert.setInt(1, movie.getId());
                stm_genre_insert.setInt(2, movieGenre.getId());
                stm_genre_insert.executeUpdate();
            }
            
            //Update M2 movie genre
                //delete exist movie genre
            PreparedStatement stm_language_delete = connection.prepareStatement(sql_movie_language_delete);
            stm_language_delete.setInt(1, movie.getId());
            stm_language_delete.executeUpdate();
                //insert new movie genre
            for(MovieLanguage movieLanguage : movie.getLanguages()){
                PreparedStatement stm_language_insert = connection.prepareStatement(sql_movie_language_insert);
                stm_language_insert.setInt(1, movie.getId());
                stm_language_insert.setInt(2, movieLanguage.getId());
                stm_language_insert.executeUpdate();
            }
            
            //Insert new movie showing
            PreparedStatement stm_movie_showing = connection.prepareStatement(sql_movie_showing);
            stm_movie_showing.setDate(1, movie.getMovieShowing().getFromShowing());
            stm_movie_showing.setDate(2, movie.getMovieShowing().getToShowing());
            stm_movie_showing.executeUpdate();
            
            //Get identidy
            String sql_get_identity = "SELECT @@IDENTITY as movie_showing_id";
            PreparedStatement stm_get_identity = connection.prepareStatement(sql_get_identity);
            ResultSet rs_get_identity = stm_get_identity.executeQuery();
            int movie_showing_id = 0;
            if (rs_get_identity.next()){
                movie_showing_id = rs_get_identity.getInt("movie_showing_id");
            }
            
            //Insert relationship movie to movie showing
            PreparedStatement stm_movie_movie_showing = connection.prepareStatement(sql_movie_movie_showing);
            stm_movie_movie_showing.setInt(1, movie_showing_id);
            stm_movie_movie_showing.setInt(2, movie.getId());
            stm_movie_movie_showing.executeUpdate();
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }
    public boolean insertMovie(Movie movie){
                String sql_movie = "INSERT INTO [Movie]\n" +
                        "           ([movie_name]\n" +
                        "           ,[movie_director]\n" +
                        "           ,[movie_cast]\n" +
                        "           ,[movie_running_time]\n" +
                        "           ,[movie_duration]\n" +
                        "           ,[movie_rated]\n" +
                        "           ,[movie_description]\n" +
                        "           ,[movie_url_trailer]\n" +
                        "           ,[movie_url_image])\n" +
                        "     VALUES\n" +
                        "           (?,?,?,?,?\n" +
                        "           ,?,?,?,?)";
        String sql_movie_genre_insert = "INSERT INTO [Movie_Movie_Genre]\n" +
                        "           ([movie_id]\n" +
                        "           ,[movie_genre_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";

        String sql_movie_language_insert = "INSERT INTO [Movie_Movie_Language]\n" +
                        "           ([movie_id]\n" +
                        "           ,[movie_language_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";

        String sql_movie_showing = "INSERT INTO [Movie_Showing]\n" +
                        "           ([showing_from_date]\n" +
                        "           ,[showing_to_date])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        String sql_movie_movie_showing = "INSERT INTO [Movie_Movie_Showing]\n" +
                        "           ([movie_showing_id]\n" +
                        "           ,[movie_id])\n" +
                        "     VALUES\n" +
                        "           (?\n" +
                        "           ,?)";
        try{
            connection.setAutoCommit(false);
            //Update movie infor
            PreparedStatement stm = connection.prepareStatement(sql_movie);
            stm.setString(1, movie.getName());
            stm.setString(2, movie.getDirector());
            stm.setString(3, movie.getCast());
            stm.setDate(4, movie.getRunningTime());
            stm.setInt(5, movie.getDuration());
            stm.setString(6, movie.getRated());
            stm.setString(7, movie.getDescription());
            stm.setString(8, movie.getUrlTrailer());
            stm.setString(9, movie.getUrlImage());
            stm.executeUpdate();
            
            //Get movie id identidy
            String sql_get_movide_id = "SELECT @@IDENTITY as movie_id";
            PreparedStatement stm_get_movide_id = connection.prepareStatement(sql_get_movide_id);
            ResultSet rs_get_movide_id = stm_get_movide_id.executeQuery();
            int movie_id = 0;
            if (rs_get_movide_id.next()){
                movie_id = rs_get_movide_id.getInt("movie_id");
            }
            
            //insert new movie genre
            for(MovieGenre movieGenre : movie.getGenre()){
                PreparedStatement stm_genre_insert = connection.prepareStatement(sql_movie_genre_insert);
                stm_genre_insert.setInt(1, movie_id);
                stm_genre_insert.setInt(2, movieGenre.getId());
                stm_genre_insert.executeUpdate();
            }
            
            //insert new movie genre
            for(MovieLanguage movieLanguage : movie.getLanguages()){
                PreparedStatement stm_language_insert = connection.prepareStatement(sql_movie_language_insert);
                stm_language_insert.setInt(1, movie_id);
                stm_language_insert.setInt(2, movieLanguage.getId());
                stm_language_insert.executeUpdate();
            }
            connection.commit();
            
            //Insert new movie showing
            PreparedStatement stm_movie_showing = connection.prepareStatement(sql_movie_showing);
            stm_movie_showing.setDate(1, movie.getMovieShowing().getFromShowing());
            stm_movie_showing.setDate(2, movie.getMovieShowing().getToShowing());
            stm_movie_showing.executeUpdate();
            
            //Get movie id identidy
            String sql_get_identity = "SELECT @@IDENTITY as movie_showing_id";
            PreparedStatement stm_get_identity = connection.prepareStatement(sql_get_identity);
            ResultSet rs_get_identity = stm_get_identity.executeQuery();
            int movie_showing_id = 0;
            if (rs_get_identity.next()){
                movie_showing_id = rs_get_identity.getInt("movie_showing_id");
            }
            
            //Insert relationship movie to movie showing
            PreparedStatement stm_movie_movie_showing = connection.prepareStatement(sql_movie_movie_showing);
            stm_movie_movie_showing.setInt(1, movie_showing_id);
            stm_movie_movie_showing.setInt(2, movie_id);
            stm_movie_movie_showing.executeUpdate();
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(MovieDBContext.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        return true;
    }
}
