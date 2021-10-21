/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.account.Account;

/**
 *
 * @author Ducky
 */
public class Validation {
    public static Boolean isValidAccount(Account account){
        if (account.getDisplayName() == null
         || account.getDob() == null
         || account.getEmail() == null
         || account.getPassword() == null
         || account.getPhone() == null
         || account.getUsername() == null) return false;
        return true;
    }
}
