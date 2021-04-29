/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.actions;

import com.reversable.db.DBConnection;
import com.reversable.util.DataEncryptionAlgoritham;
import com.reversable.util.DataHidingScheme;
import com.reversable.util.GenerateImageNames;
import com.reversable.util.GetFileExtension;
import com.reversable.util.UserUtility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Ramu Maloth
 */
@MultipartConfig
public class UploadPicAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession hs = request.getSession();
        String loginUserName = hs.getAttribute("loginuser").toString();

        String shareto = request.getParameter("shareto");
        String encodemessage = request.getParameter("encodemessage");
        String encryptpassword = request.getParameter("encryptpassword");
        System.out.println("Encryption password = " + encryptpassword);
        String shareUserEmail = UserUtility.getUserEmail(shareto);

        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);
        InputStream is = filePart.getInputStream();
        int newImageId = GenerateImageNames.generatePicId();
        String imageExtension = GetFileExtension.getFileExtension(fileName);
        FileOutputStream fis1 = null;
        String newFileId = newImageId + "" + imageExtension;
        File directory = new File(getServletContext().getRealPath("/listofpic"));   //This is the folder of your application
        //FileInputStream iss = new FileInputStream(inputStream.available());
        fis1 = new FileOutputStream(directory.getAbsolutePath() + "\\" + newFileId);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[(int) is.available()];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        fis1.write(data);
        String fPath = directory.getAbsolutePath() + "\\" + newFileId;
        fis1.close();
        File picPath = new File(fPath);
        String outPath = directory.getAbsolutePath() + "\\" + "ramama" + imageExtension;
        File oPath = new File(outPath);
        // DataHidingScheme dhs = new DataHidingScheme();
        DataHidingScheme.embedMessage(picPath, picPath, encodemessage, 5, encryptpassword);
        System.out.println(DataHidingScheme.getMessage());
        InputStream wtrmarkImage = new FileInputStream(picPath);

        Connection con = null;
        PreparedStatement ps = null;

        try {
            DataEncryptionAlgoritham dea = new DataEncryptionAlgoritham();
            String encryptMessage = dea.encrypt(encodemessage);
             String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
             
            con = DBConnection.getDBConnection();
            String sqlQuery = "insert into sharedpics(fromuser,touser,touseremail,encodemessage,picid,pic,shareddate,pswd) values(?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sqlQuery);
            ps.setString(1, loginUserName);
            ps.setString(2, shareto);
            ps.setString(3, shareUserEmail);
            ps.setString(4, encryptMessage);
            ps.setString(5, newFileId);
            ps.setBinaryStream(6, wtrmarkImage);
            ps.setString(7, timeStamp);
            ps.setString(8, encryptpassword);
            int no = ps.executeUpdate();
            if(no > 0){
            response.sendRedirect("UploadPic.jsp?msg=success");
            }else{
            response.sendRedirect("UploadPic.jsp?msg=faild");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private String getFileName(Part filePart) {
        for (String cd : filePart.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
