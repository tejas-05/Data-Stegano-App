/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.randd;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Ramu Maloth
 */
public class ImageConvertFilter {
     BufferedImage  image;
   int width;
   int height;
   File path;
   public ImageConvertFilter() {
   
   }
   
   public void grayImage(File p){
    this.path = p;
       System.out.println("Image Absoluate Path is "+path.getAbsolutePath());
      try {
         File input = new File(p.getAbsolutePath());
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         for(int i=0; i<height; i++) {
         
            for(int j=0; j<width; j++) {
            
               Color c = new Color(image.getRGB(j, i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *240);
               Color newColor = new Color(red+green+blue,red+green+blue,red+green+blue);
               
               image.setRGB(j,i,newColor.getRGB());
            }
         }
         
         File ouptut = new File(p.getAbsolutePath());
         boolean flag = ImageIO.write(image, "jpg", ouptut);
         
      } catch (Exception e) {
      e.printStackTrace();
      }
   }
   
   static public void main(String args[]) throws Exception {
      //GrayScale obj = new GrayScale();
        File path = new File("F:/workspace/ReversibleDataHiding/build/web/listofpic/465859.jpg");
        ImageConvertFilter con = new ImageConvertFilter();
        con.grayImage(path);
   }
}
