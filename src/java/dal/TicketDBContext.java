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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ticket.Price;

/**
 *
 * @author Ducky
 */
public class TicketDBContext extends DBContext{
    public Price getStandardPrice(Date date){
        Price price = new Price();
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        int dayOfWeek = Integer.parseInt(sdf.format(date));
        String sql = "SELECT [price_id]\n" +
                    "      ,[price_type]\n" +
                    "      ,[day]\n" +
                    "      ,[price]\n" +
                    "  FROM [Price]\n" +
                    "  Where [price_type] = ? AND [day] = ?";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "standard");
            stm.setInt(2, dayOfWeek);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                price.setId(rs.getInt("price_id"));
                price.setType(rs.getString("price_type"));
                price.setDay(rs.getInt("day"));
                price.setPrice(rs.getFloat("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
    public Price getVipprimePrice(Date date){
        Price price = new Price();
        SimpleDateFormat sdf = new SimpleDateFormat("u");
        int dayOfWeek = Integer.parseInt(sdf.format(date));
        String sql = "SELECT [price_id]\n" +
                    "      ,[price_type]\n" +
                    "      ,[day]\n" +
                    "      ,[price]\n" +
                    "  FROM [Price]\n" +
                    "  Where [price_type] = ? AND [day] = ?";
        
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "vipprime");
            stm.setInt(2, dayOfWeek);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                price.setId(rs.getInt("price_id"));
                price.setType(rs.getString("price_type"));
                price.setDay(rs.getInt("day"));
                price.setPrice(rs.getFloat("price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
    public boolean insertTicket(int cinemaId, int movieId, String[] seats, String[] prices,
            Date purcharseDate, int performanceNumber, String username){
        String sql_transaction = "INSERT INTO [Transaction]\n" +
                    "           ([transaction_made_date]\n" +
                    "           ,[username])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
        String sql_ticket = "INSERT INTO [Ticket]\n" +
                    "           ([cinema_id]\n" +
                    "           ,[row_number]\n" +
                    "           ,[seat_number]\n" +
                    "           ,[movie_id]\n" +
                    "           ,[ticket_purchase_date]\n" +
                    "           ,[performance_number]\n" +
                    "           ,[price_id]\n" +
                    "           ,[transaction_id])\n" +
                    "     VALUES\n" +
                    "           (?,?,?,?,?,?,?,?)";
        
        try{
            connection.setAutoCommit(false);
            PreparedStatement stm_transaction = connection.prepareStatement(sql_transaction);
            stm_transaction.setDate(1, purcharseDate);
            stm_transaction.setString(2, username);
            stm_transaction.executeUpdate();
            
            String sql_get_identity = "SELECT @@IDENTITY as transaction_id";
            PreparedStatement stm_get_identity = connection.prepareStatement(sql_get_identity);
            ResultSet rs_get_identity = stm_get_identity.executeQuery();
            int transaction_id = 0;
            if (rs_get_identity.next()){
                transaction_id = rs_get_identity.getInt("transaction_id");
            }
            
            for (int i = 0; i < seats.length; i++){
                PreparedStatement stm_ticket = connection.prepareStatement(sql_ticket);
                stm_ticket.setInt(1, cinemaId);
                stm_ticket.setInt(2, seats[i].trim().charAt(0) - 'A' + 1);
                stm_ticket.setInt(3, Integer.parseInt(seats[i].trim().substring(1)));
                stm_ticket.setInt(4, movieId);
                stm_ticket.setDate(5, purcharseDate);
                stm_ticket.setInt(6, performanceNumber);
                stm_ticket.setInt(7, Integer.parseInt(prices[i]));
                stm_ticket.setInt(8, transaction_id);
                stm_ticket.executeUpdate();
            }
            
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TicketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TicketDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
