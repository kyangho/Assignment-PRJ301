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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.account.Account;
import model.account.Feature;
import model.account.Ticket;
import model.account.Transaction;
import model.cinema.PerformanceNumber;

/**
 *
 * @author Ducky
 */
public class AccountDBContext extends DBContext {

    public boolean isValidAccount(String username) {
        String sql = "SELECT a.[username]\n"
                + " FROM [Account] as a\n"
                + " WHERE a.[username] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public boolean insertAccount(Account account) {
        boolean isCorrect = true;
        try {
            connection.setAutoCommit(false);

            //Check duplicate account
            if (!isValidAccount(account.getUsername())) {
                isCorrect = false;
                return isCorrect;
            }
            //Insert account
            String sql_insert_account = "INSERT INTO [Account]\n"
                    + "           ([username],[password],[displayname],[email],[phone],[dob],[gender])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            PreparedStatement stm_insert_account = connection.prepareStatement(sql_insert_account);
            stm_insert_account.setString(1, account.getUsername());
            stm_insert_account.setString(2, account.getPassword());
            stm_insert_account.setString(3, account.getDisplayName());
            stm_insert_account.setString(4, account.getEmail());
            stm_insert_account.setString(5, account.getPhone());
            stm_insert_account.setDate(6, account.getDob());
            stm_insert_account.setBoolean(7, account.isGender());
            stm_insert_account.executeUpdate();

            //Insert account's group
            String sql_insert_groupAccount = "INSERT INTO [GroupAccount]\n"
                    + "           ([gid],[username])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement stm_insert_groupAccount = connection.prepareStatement(sql_insert_groupAccount);
            stm_insert_groupAccount.setInt(1, 3);
            stm_insert_groupAccount.setString(2, account.getUsername());
            stm_insert_groupAccount.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            isCorrect = false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isCorrect;
    }

    public Account getAccount(String username, String password) {
        String sql = "SELECT a.[username], a.[password], a.[displayname],a.[dob] ,a.[email],a.[gender] ,a.[phone],f.[fid] ,f.[url], g.[gname]\n"
                + " FROM [Account] as a\n"
                + " LEFT JOIN [GroupAccount] as gc ON a.[username] = gc.[username]\n"
                + " LEFT JOIN [Group] as g ON g.[gid] = gc.[gid]\n"
                + " LEFT JOIN [GroupFeature] as gf ON gf.[gid] = g.[gid]\n"
                + " LEFT JOIN [Feature] as f on f.[fid] = gf.[fid]\n"
                + " WHERE a.[username] = ? AND a.[password] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = account = new Account(rs.getString("username"), rs.getString("password"), rs.getString("displayname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setDob(Date.valueOf(rs.getString("dob")));
                account.setGender(rs.getBoolean("gender"));
                while (rs.next()) {
                    if (account != null) {
                        
                        int fid = rs.getInt("fid");
                        if (fid != 0) {
                            Feature f = new Feature();
                            f.setId(fid);
                            f.setUrl(rs.getString("url"));
                            account.getFeatures().add(f);
                        }
                    }
                }
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateAccount(Account account) {
        String sql = "UPDATE [Account]\n"
                + "   SET [displayname] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [username] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getDisplayName());
            stm.setString(2, account.getEmail());
            stm.setString(3, account.getPhone());
            stm.setDate(4, account.getDob());
            stm.setBoolean(5, account.isGender());
            stm.setString(6, account.getUsername());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public int getTransactionCount(String username) {
        try {
            String sql = "SELECT COUNT(*) as total FROM [Transaction] WHERE [username] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Account getTransactions(Account account, int pageIndex, int pageSize) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String sql_transaction = "  SELECT t.[transaction_id]\n"
                + "	  ,t.[transaction_made_date]\n"
                + "FROM\n"
                + "(\n"
                + "SELECT ROW_NUMBER() OVER (ORDER BY tr.[transaction_id] desc) as rownum, tr.[transaction_id], tr.[transaction_made_date]\n"
                + "FROM [Transaction] as tr\n"
                + "LEFT JOIN [Account] as a on a.[username] = tr.[username] \n"
                + "WHERE a.[username] = ?) t\n"
                + "WHERE \n"
                + "rownum >= (? - 1)*? + 1 AND rownum <= ? * ?";
        try {
            PreparedStatement stm_transaction = connection.prepareStatement(sql_transaction);
            stm_transaction.setString(1, account.getUsername());
            stm_transaction.setInt(2, pageIndex);
            stm_transaction.setInt(3, pageSize);
            stm_transaction.setInt(4, pageIndex);
            stm_transaction.setInt(5, pageSize);
            ResultSet rs_transaction = stm_transaction.executeQuery();
            while (rs_transaction.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs_transaction.getInt("transaction_id"));
                transaction.setTransaction_made_date(rs_transaction.getDate("transaction_made_date"));
                TicketDBContext tdb = new TicketDBContext();
                transaction.setTickets(tdb.getTicketsWithTransactionId(transaction.getId()));
                float price = 0;
                for (Ticket t : transaction.getTickets()) {
                    price += t.getPrice();
                }
                transaction.setPrice(price);
                MovieDBContext mdb = new MovieDBContext();
                int movieId = transaction.getTickets().get(0).getMovie_id();
                transaction.setMovie(mdb.getMovie(movieId));
                String sql_performance = "SELECT [performance_number]\n"
                        + "      ,[performance_from_time]\n"
                        + "      ,[performance_to_time]\n"
                        + "  FROM [Performance_Number]\n"
                        + "  WHERE [performance_number] = ? ";
                PreparedStatement stm_performance = connection.prepareStatement(sql_performance);
                int performanceNumber = transaction.getTickets().get(0).getPerformance_number();
                stm_performance.setInt(1, performanceNumber);
                ResultSet rs_performance = stm_performance.executeQuery();
                if (rs_performance.next()) {
                    PerformanceNumber pn = new PerformanceNumber();
                    pn.setNumber(performanceNumber);
                    pn.setFromTime(rs_performance.getTime("performance_from_time"));
                    pn.setToTime(rs_performance.getTime("performance_to_time"));
                    transaction.setPerformanceNumber(pn);
                }
                CinemaDBContext cdb = new CinemaDBContext();
                int cinemaId = transaction.getTickets().get(0).getCinema_id();
                transaction.setCinema(cdb.getCinema(cinemaId));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        account.setTransactions(transactions);
        return account;
    }
}
