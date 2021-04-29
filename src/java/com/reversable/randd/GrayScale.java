/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.randd;

/**
 *
 * @author Ramu Maloth
 */
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GrayScale {

   BufferedImage  image;
   int width;
   int height;
   
   public GrayScale() {
   
      try {
         File input = new File("D:\\imagerandd\\img_6.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *240);
               Color newColor = new Color(red+green+blue,
               
               red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File("D:\\imagerandd\\encryptimg.jpg");
         ImageIO.write(image, "jpg", ouptut);
         
      } catch (Exception e) {
      e.printStackTrace();
      }
   }
   
   static public void main(String args[]) throws Exception {
      GrayScale obj = new GrayScale();
   }
}
