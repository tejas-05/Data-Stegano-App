/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.randd;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ramu Maloth
 */
public class Test {
    public static void main(String[] args) throws IOException {
         File path = new File("F:/workspace/ReversibleDataHiding/build/web/listofpic/564520.jpg");
         Grayscale1 scale = new Grayscale1();
         Grayscale1.grayImageFilter(path.getAbsolutePath());
    }
}
