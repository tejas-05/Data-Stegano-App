/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.actions;

import com.reversable.util.DataHidingScheme;
import com.reversable.util.MessageImformation;
import com.reversable.util.UserUtility;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramu Maloth
 */
public class CheckPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String uniqueId = request.getParameter("uniqueId");
        String emailid  = request.getParameter("emailid");
        String picUniqueID = request.getParameter("picUniqueID");
        String encMessage = request.getParameter("encMessage");
        String pswd = request.getParameter("pswd");
        
        boolean flag = UserUtility.checkPassword(Integer.parseInt(uniqueId), picUniqueID, pswd, emailid);
        
        if(flag == true){
        out.println("Valid Password");
        File directory = new File(getServletContext().getRealPath("/listofpic/"+picUniqueID));
            System.out.println("Dir Name "+directory.getAbsolutePath());
            MessageImformation mi = new MessageImformation(directory);
            String data = DataHidingScheme.retrieveMessage(mi, pswd);
            System.out.println("The Decrypted messages is "+data);
            response.sendRedirect("ImageDataView.jsp?msg=success&data="+data);
        
        }else{
        
        response.sendRedirect("ImageDataView.jsp?msg=faild");
        }
        
        
        try {
            
            
        }catch(Exception ex){
            System.out.println("Exception "+ex.getMessage());
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
