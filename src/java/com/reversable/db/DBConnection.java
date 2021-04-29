/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
     public static Connection con = null;
    
    public static Connection getDBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reversable","root","root");
            if(con!=null){
            return con;
            }
        } catch (Exception e) {
            System.out.println("Error at DB Connection "+e.getMessage());
        }
        return con;
        
    }
    public static void main(String[] args) {
        Connection con = DBConnection.getDBConnection();
        System.out.println("COn Object is "+con);
    }
}
