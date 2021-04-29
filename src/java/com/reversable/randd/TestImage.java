/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.randd;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author Ramu Maloth
 */
public class TestImage {
    public static void main(String[] args) {
         float[] matrix = {
        0.111f, 0.111f, 0.111f, 
        0.111f, 0.111f, 0.111f, 
        0.111f, 0.111f, 0.111f, 
    };

    BufferedImageOp op = new ConvolveOp( new Kernel(3, 3, matrix) );
	//BufferedImage blurredImage = op.filter(sourceImage, destImage);
    }
}
