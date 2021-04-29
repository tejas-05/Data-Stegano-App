/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reversable.util;

/**
 *
 * @author Ramu Maloth
 */
public class GetFileExtension {
      private static GetFileExtension single_instance = null;

    private GetFileExtension() {
    }

    private static GetFileExtension getInstance() {
        if (single_instance == null) {
            single_instance = new GetFileExtension();
        }
        return single_instance;
    }

    public static String getFileExtension(String fileName) {
        String extension = "";
        try {
            extension = fileName.substring(fileName.lastIndexOf("."));
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }
}
