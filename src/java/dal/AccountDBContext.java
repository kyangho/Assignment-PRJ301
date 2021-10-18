/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

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
public class AccountDBContext extends DBContext{
    public void insertAccount(Account account){
        String sql = "INSERT INTO [Account]\n" +
                    "([username]\n" +
                    ",[password]\n" +
                    ",[displayname])\n" +
                    "VALUES\n" +
                    "(?,?,?)";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getDisplayName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Account getAccount(String username, String password){
        String sql = "SELECT a.[username], a.[password], a.[displayname],a.[email], a.[phone], f.[url], g.[gname]\n" +
                    " FROM [Account] as a\n" +
                    " LEFT JOIN [GroupAccount] as gc ON a.[username] = gc.[username]\n" +
                    " LEFT JOIN [Group] as g ON g.[gid] = gc.[gid]\n" +
                    " LEFT JOIN [GroupFeature] as gf ON gf.[gid] = g.[gid]\n" +
                    " LEFT JOIN [Feature] as f on f.[fid] = gf.[fid]\n" +
                    " WHERE a.[username] = ? AND a.[password] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account account = new Account(rs.getString("username"), rs.getString("password"), rs.getString("displayname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                String url = rs.getString("url");
                if (url != null){
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
}
