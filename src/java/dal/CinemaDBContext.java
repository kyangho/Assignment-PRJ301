/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.cinema.Cinema;
import model.cinema.PerformanceNumber;
import model.cinema.RowSeat;
import model.movie.Movie;
import model.movie.MovieShowing;

/**
 *
 * @author Ducky
 */
public class CinemaDBContext extends DBContext{
    public ArrayList<Cinema> getCinemas(){
        String sql_cinema = "SELECT [cinema_id]\n" +
                            "      ,[cinema_name]\n" +
                            "      ,[cinema_address]\n" +
                            "      ,[cinema_phone]\n" +
                            "  FROM [Cinema]";
        String sql_cinema_movieShowing = "SELECT c.[cinema_id]\n" +
                            "      ,ms.[movie_showing_id]\n" +
                            "	  ,ms.[showing_from_date]\n" +
                            "	  ,ms.[showing_to_date]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Cinema_Movie_Showing] as cms ON cms.cinema_id = c.cinema_id\n" +
                            "  LEFT JOIN [Movie_Showing] as ms on ms.movie_showing_id = cms.movie_showing_id\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_rowSet = "SELECT c.[cinema_id]\n" +
                            "      ,rs.[row_number]\n" +
                            "	  ,rs.[seat_count]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Row_Seat] as rs on rs.[cinema_id] = c.[cinema_id]\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_performanceNumber = "SELECT c.[cinema_id]\n" +
                            "      ,pn.[performance_number]\n" +
                            "	  ,pn.[performance_from_time]\n" +
                            "	  ,pn.[performance_to_time]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Cinema_Performance_Number] as cpn on cpn.[cinema_id] = c.[cinema_id]\n" +
                            "  LEFT JOIN [Performance_Number] as pn on pn.[performance_number] = cpn.[performance_number]\n" +
                            "  WHERE c.[cinema_id] = ?";
        
        ArrayList<Cinema> cinemas = new ArrayList<>();
        
        try{
            PreparedStatement stm_cinema = connection.prepareStatement(sql_cinema);
            ResultSet rs_cinema = stm_cinema.executeQuery();
            while(rs_cinema.next()){
                Cinema cinema = new Cinema();
                cinema.setId(rs_cinema.getInt("cinema_id"));
                cinema.setName(rs_cinema.getString("cinema_name"));
                cinema.setAddress(rs_cinema.getString("cinema_address"));
                cinema.setPhone(rs_cinema.getString("cinema_phone"));
                
                //Get movie showing for each cinema
                ArrayList<MovieShowing> movieShowings = new ArrayList<>();
                PreparedStatement stm_cinema_movieShowing = connection.prepareStatement(sql_cinema_movieShowing);
                stm_cinema_movieShowing.setInt(1, cinema.getId());
                ResultSet rs_cinema_movieShowing = stm_cinema_movieShowing.executeQuery();
                while(rs_cinema_movieShowing.next()){
                    MovieShowing movieShowing = new MovieShowing();
                    movieShowing.setId(rs_cinema_movieShowing.getInt("movie_showing_id"));
                    movieShowing.setFromShowing(rs_cinema_movieShowing.getDate("showing_from_date"));
                    movieShowing.setToShowing(rs_cinema_movieShowing.getDate("showing_to_date"));
                    movieShowings.add(movieShowing);
                }
                cinema.setMovieShowings(movieShowings);
                //Get row seats for each cinema
                ArrayList<RowSeat> rowSeats = new ArrayList<>();
                PreparedStatement stm_cinema_rowSeat = connection.prepareStatement(sql_cinema_rowSet);
                stm_cinema_rowSeat.setInt(1, cinema.getId());
                ResultSet rs_cinema_rowSeat = stm_cinema_rowSeat.executeQuery();
                while(rs_cinema_rowSeat.next()){
                    RowSeat rowSeat = new RowSeat();
                    rowSeat.setRow(rs_cinema_rowSeat.getInt("row_number"));
                    rowSeat.setSeat(rs_cinema_rowSeat.getInt("seat_count"));
                    rowSeats.add(rowSeat);
                }
                cinema.setRowSeats(rowSeats);
                //Get performance number for each cinema
                ArrayList<PerformanceNumber> performanceNumbers = new ArrayList<>();
                PreparedStatement stm_cinema_performanceNumber = connection.prepareStatement(sql_cinema_performanceNumber);
                stm_cinema_performanceNumber.setInt(1, cinema.getId());
                ResultSet rs_cinema_performanceNumber = stm_cinema_performanceNumber.executeQuery();
                while(rs_cinema_performanceNumber.next()){
                    PerformanceNumber performanceNumber = new PerformanceNumber();
                    performanceNumber.setNumber(rs_cinema_performanceNumber.getInt("performance_number"));
                    performanceNumber.setFromTime(rs_cinema_performanceNumber.getTime("performance_from_time"));
                    performanceNumber.setToTime(rs_cinema_performanceNumber.getTime("performance_to_time"));
                    performanceNumbers.add(performanceNumber);
                }
                cinema.setPerformanceNumbers(performanceNumbers);
                
                //add to list
                cinemas.add(cinema);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cinemas;
    }
    public ArrayList<Cinema> getCinemasAfterNow(String now){
        String sql_cinema = "SELECT [cinema_id]\n" +
                            "      ,[cinema_name]\n" +
                            "      ,[cinema_address]\n" +
                            "      ,[cinema_phone]\n" +
                            "  FROM [Cinema]";
        String sql_cinema_movieShowing = "SELECT c.[cinema_id]\n" +
                            "      ,ms.[movie_showing_id]\n" +
                            "	  ,ms.[showing_from_date]\n" +
                            "	  ,ms.[showing_to_date]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Cinema_Movie_Showing] as cms ON cms.cinema_id = c.cinema_id\n" +
                            "  LEFT JOIN [Movie_Showing] as ms on ms.movie_showing_id = cms.movie_showing_id\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_rowSet = "SELECT c.[cinema_id]\n" +
                            "      ,rs.[row_number]\n" +
                            "	  ,rs.[seat_count]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Row_Seat] as rs on rs.[cinema_id] = c.[cinema_id]\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_performanceNumber = "SELECT c.[cinema_id], pn.[performance_number]\n" +
                                            "      , pn.[performance_from_time], pn.[performance_to_time]\n" +
                                            "FROM [Cinema] as c\n" +
                                            "LEFT JOIN [Cinema_Performance_Number] as cpn on cpn.[cinema_id] = c.[cinema_id]\n" +
                                            "LEFT JOIN [Performance_Number] as pn on pn.[performance_number] = cpn.[performance_number]\n" +
                                            "WHERE c.[cinema_id] = ? AND pn.[performance_from_time] > ?";
        
        ArrayList<Cinema> cinemas = new ArrayList<>();
        
        try{
            PreparedStatement stm_cinema = connection.prepareStatement(sql_cinema);
            ResultSet rs_cinema = stm_cinema.executeQuery();
            while(rs_cinema.next()){
                Cinema cinema = new Cinema();
                cinema.setId(rs_cinema.getInt("cinema_id"));
                cinema.setName(rs_cinema.getString("cinema_name"));
                cinema.setAddress(rs_cinema.getString("cinema_address"));
                cinema.setPhone(rs_cinema.getString("cinema_phone"));
                
                //Get movie showing for each cinema
                ArrayList<MovieShowing> movieShowings = new ArrayList<>();
                PreparedStatement stm_cinema_movieShowing = connection.prepareStatement(sql_cinema_movieShowing);
                stm_cinema_movieShowing.setInt(1, cinema.getId());
                ResultSet rs_cinema_movieShowing = stm_cinema_movieShowing.executeQuery();
                while(rs_cinema_movieShowing.next()){
                    MovieShowing movieShowing = new MovieShowing();
                    movieShowing.setId(rs_cinema_movieShowing.getInt("movie_showing_id"));
                    movieShowing.setFromShowing(rs_cinema_movieShowing.getDate("showing_from_date"));
                    movieShowing.setToShowing(rs_cinema_movieShowing.getDate("showing_to_date"));
                    movieShowings.add(movieShowing);
                }
                cinema.setMovieShowings(movieShowings);
                //Get row seats for each cinema
                ArrayList<RowSeat> rowSeats = new ArrayList<>();
                PreparedStatement stm_cinema_rowSeat = connection.prepareStatement(sql_cinema_rowSet);
                stm_cinema_rowSeat.setInt(1, cinema.getId());
                ResultSet rs_cinema_rowSeat = stm_cinema_rowSeat.executeQuery();
                while(rs_cinema_rowSeat.next()){
                    RowSeat rowSeat = new RowSeat();
                    rowSeat.setRow(rs_cinema_rowSeat.getInt("row_number"));
                    rowSeat.setSeat(rs_cinema_rowSeat.getInt("seat_count"));
                    rowSeats.add(rowSeat);
                }
                cinema.setRowSeats(rowSeats);
                //Get performance number for each cinema
                ArrayList<PerformanceNumber> performanceNumbers = new ArrayList<>();
                PreparedStatement stm_cinema_performanceNumber = connection.prepareStatement(sql_cinema_performanceNumber);
                stm_cinema_performanceNumber.setInt(1, cinema.getId());
                stm_cinema_performanceNumber.setString(2, now);
                ResultSet rs_cinema_performanceNumber = stm_cinema_performanceNumber.executeQuery();
                while(rs_cinema_performanceNumber.next()){
                    PerformanceNumber performanceNumber = new PerformanceNumber();
                    performanceNumber.setNumber(rs_cinema_performanceNumber.getInt("performance_number"));
                    performanceNumber.setFromTime(rs_cinema_performanceNumber.getTime("performance_from_time"));
                    performanceNumber.setToTime(rs_cinema_performanceNumber.getTime("performance_to_time"));
                    performanceNumbers.add(performanceNumber);
                }
                cinema.setPerformanceNumbers(performanceNumbers);
                
                //add to list
                if (!performanceNumbers.isEmpty())
                    cinemas.add(cinema);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cinemas;
    }
    public Cinema getCinema(int id){
        String sql_cinema = "SELECT [cinema_id]\n" +
                            "      ,[cinema_name]\n" +
                            "      ,[cinema_address]\n" +
                            "      ,[cinema_phone]\n" +
                            "  FROM [Cinema]" +
                            "  WHERE [cinema_id] = ?";
        String sql_cinema_movieShowing = "SELECT c.[cinema_id]\n" +
                            "      ,ms.[movie_showing_id]\n" +
                            "	  ,ms.[showing_from_date]\n" +
                            "	  ,ms.[showing_to_date]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Cinema_Movie_Showing] as cms ON cms.cinema_id = c.cinema_id\n" +
                            "  LEFT JOIN [Movie_Showing] as ms on ms.movie_showing_id = cms.movie_showing_id\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_rowSet = "SELECT c.[cinema_id]\n" +
                            "      ,rs.[row_number]\n" +
                            "	  ,rs.[seat_count]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Row_Seat] as rs on rs.[cinema_id] = c.[cinema_id]\n" +
                            "  WHERE c.[cinema_id] = ?";
        String sql_cinema_performanceNumber = "SELECT c.[cinema_id]\n" +
                            "      ,pn.[performance_number]\n" +
                            "	  ,pn.[performance_from_time]\n" +
                            "	  ,pn.[performance_to_time]\n" +
                            "  FROM [Cinema] as c\n" +
                            "  LEFT JOIN [Cinema_Performance_Number] as cpn on cpn.[cinema_id] = c.[cinema_id]\n" +
                            "  LEFT JOIN [Performance_Number] as pn on pn.[performance_number] = cpn.[performance_number]\n" +
                            "  WHERE c.[cinema_id] = ?";
        
        Cinema cinema = new Cinema();
        try{
            PreparedStatement stm_cinema = connection.prepareStatement(sql_cinema);
            stm_cinema.setInt(1, id);
            ResultSet rs_cinema = stm_cinema.executeQuery();
            if(rs_cinema.next()){
                cinema.setId(rs_cinema.getInt("cinema_id"));
                cinema.setName(rs_cinema.getString("cinema_name"));
                cinema.setAddress(rs_cinema.getString("cinema_address"));
                cinema.setPhone(rs_cinema.getString("cinema_phone"));
                
                //Get movie showing for each cinema
                ArrayList<MovieShowing> movieShowings = new ArrayList<>();
                PreparedStatement stm_cinema_movieShowing = connection.prepareStatement(sql_cinema_movieShowing);
                stm_cinema_movieShowing.setInt(1, cinema.getId());
                ResultSet rs_cinema_movieShowing = stm_cinema_movieShowing.executeQuery();
                while(rs_cinema_movieShowing.next()){
                    MovieShowing movieShowing = new MovieShowing();
                    movieShowing.setId(rs_cinema_movieShowing.getInt("movie_showing_id"));
                    movieShowing.setFromShowing(rs_cinema_movieShowing.getDate("showing_from_date"));
                    movieShowing.setToShowing(rs_cinema_movieShowing.getDate("showing_to_date"));
                    movieShowings.add(movieShowing);
                }
                cinema.setMovieShowings(movieShowings);
                //Get row seats for each cinema
                ArrayList<RowSeat> rowSeats = new ArrayList<>();
                PreparedStatement stm_cinema_rowSeat = connection.prepareStatement(sql_cinema_rowSet);
                stm_cinema_rowSeat.setInt(1, cinema.getId());
                ResultSet rs_cinema_rowSeat = stm_cinema_rowSeat.executeQuery();
                while(rs_cinema_rowSeat.next()){
                    RowSeat rowSeat = new RowSeat();
                    rowSeat.setRow(rs_cinema_rowSeat.getInt("row_number"));
                    rowSeat.setSeat(rs_cinema_rowSeat.getInt("seat_count"));
                    rowSeats.add(rowSeat);
                }
                cinema.setRowSeats(rowSeats);
                //Get performance number for each cinema
                ArrayList<PerformanceNumber> performanceNumbers = new ArrayList<>();
                PreparedStatement stm_cinema_performanceNumber = connection.prepareStatement(sql_cinema_performanceNumber);
                stm_cinema_performanceNumber.setInt(1, cinema.getId());
                ResultSet rs_cinema_performanceNumber = stm_cinema_performanceNumber.executeQuery();
                while(rs_cinema_performanceNumber.next()){
                    PerformanceNumber performanceNumber = new PerformanceNumber();
                    performanceNumber.setNumber(rs_cinema_performanceNumber.getInt("performance_number"));
                    performanceNumber.setFromTime(rs_cinema_performanceNumber.getTime("performance_from_time"));
                    performanceNumber.setToTime(rs_cinema_performanceNumber.getTime("performance_to_time"));
                    performanceNumbers.add(performanceNumber);
                }
                cinema.setPerformanceNumbers(performanceNumbers);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cinema;
    }
    public ArrayList<Cinema> getCinemasByMovieId(int movieId){
        ArrayList<Cinema> cinemas = getCinemas();
        MovieDBContext mdb = new MovieDBContext();
        ArrayList<Movie> movies = mdb.getMovies();

        for(Cinema c : cinemas){
            for(MovieShowing cms : c.getMovieShowings()){
                for (Movie m : movies){
                    if(cms.getId() == m.getMovieShowing().getId()){
                        cinemas.remove(c);
                    }
                }
            }
        }
        return cinemas;
    }
    public int[][] getSeatBooked(int cinemaId, int movieId ,int performanceNumber, Date purcharseDate){
        String sql = "SELECT [row_number]\n" +
                    "      ,[seat_number]\n" +
                    "  FROM [Ticket]\n" +
                    "  WHERE [cinema_id] = ?\n" +
                    "       AND [movie_id] = ?\n" +
                    "	    AND [performance_number] = ?\n" +
                    "	    AND [ticket_purchase_date] = ?";
        int [][] seats = new int[100][100];
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cinemaId);
            stm.setInt(2, movieId);
            stm.setInt(3, performanceNumber);
            stm.setDate(4, purcharseDate);
            ResultSet rs = stm.executeQuery();
            int i = 0;
            while(rs.next()){
                seats[rs.getInt("row_number")][rs.getInt("seat_number")] = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CinemaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seats;
    }
}
