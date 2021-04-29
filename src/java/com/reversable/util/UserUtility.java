/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.util;

import com.reversable.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ramu Maloth
 */
public class UserUtility {
    private static Connection con = null;
    private static PreparedStatement ps =null;
    private static ResultSet rs = null;
    
    public static String getUserEmail(String loginId){
    String usremail = null;
        try {
            con = DBConnection.getDBConnection();
            String sqlQuery = "select email from usrdata where loginid = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, loginId);
            rs = ps.executeQuery();
            rs.next();
            usremail = rs.getString("email");
        } catch (Exception e) {
            System.out.println("User Email is "+usremail);
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
                
                
            } catch (Exception e) {
            }
        }
    return usremail;
    }
    
    
     public static String getDecryptMessagePassword(int id){
     String pswd = null;
        try {
            con = DBConnection.getDBConnection();
            String sqlQuery = "select pswd from sharedpics where id = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            pswd = rs.getString("pswd");
        } catch (Exception e) {
            System.out.println("User Email is "+e.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
    return pswd;
    }
     
      public static String getEncryptedMessage(int id){
      String encryptionMessage = null;
        try {
            con = DBConnection.getDBConnection();
            String sqlQuery = "select encodemessage from sharedpics where id = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            encryptionMessage = rs.getString("encodemessage");
        } catch (Exception e) {
            System.out.println("Encode Message is "+e.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
    return encryptionMessage;
    }
      
       public static boolean checkPassword(int rowid,String picid,String pswd,String useremail){
        int rmid = 0;
        boolean flag = false;
        try {
            con = DBConnection.getDBConnection();
            String sqlQuery = "select count(*) from sharedpics where id = ? and picid = ? and touseremail = ? and pswd = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, rowid);
            ps.setString(2, picid);
            ps.setString(3, useremail);
            ps.setString(4, pswd);                        
            rs = ps.executeQuery();
            rs.next();
            rmid = rs.getInt(1);
            if(rmid>0){
            flag = true;
            }else{
            flag = false;
            }
        } catch (Exception e) {
            System.out.println("Encode Message is "+e.getMessage());
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
    return flag;
    }

      
}
