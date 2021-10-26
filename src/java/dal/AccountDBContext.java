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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.account.Account;
import model.account.Feature;

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
            if (!isValidAccount(account.getUsername())){
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
        String sql = "SELECT a.[username], a.[password], a.[displayname],a.[dob] ,a.[email],a.[gender] ,a.[phone], f.[url], g.[gname]\n"
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
                Account account = new Account(rs.getString("username"), rs.getString("password"), rs.getString("displayname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setDob(Date.valueOf(rs.getString("dob")));
                account.setGender(rs.getBoolean("gender"));
                String url = rs.getString("url");
                if (url != null) {
                    Feature f = new Feature(url);
                    account.getFeatures().add(f);
                }
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updateAccount(Account account){
        String sql = "UPDATE [Account]\n" +
                "   SET [displayname] = ?\n" +
                "      ,[email] = ?\n" +
                "      ,[phone] = ?\n" +
                "      ,[dob] = ?\n" +
                "      ,[gender] = ?\n" +
                " WHERE [username] = ?";
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
}
