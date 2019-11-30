/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.util.Base64;
import java.util.regex.Pattern;

/**
 *
 * @author //Amoy Scarlett
 */
public class GeneralHelper {
    
    public static String EncodeString(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    
    public static String DecodeString(String str){
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    };
    
    public static boolean isNumeric(String strNum) {
        
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        
        if (strNum == null) {
            return false; 
    
        }
        
        return pattern.matcher(strNum).matches();
    }
    
}
