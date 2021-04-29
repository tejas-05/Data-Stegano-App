/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Ramu Maloth
 */
public class GenerateImageNames {
    public static int generatePicId(){
     int picID = (new Random()).nextInt(900000) + 100000;
     return picID;
    }
      public static void main(String[] args) {
          System.out.println("PIC ID = "+GenerateImageNames.generatePicId());
       /* ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=100000; i<1100000; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<10; i++) {
            System.out.println(list.get(i));
        }*/
    }
}
