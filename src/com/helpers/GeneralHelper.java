/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.util.Base64;

/**
 *
 * @author jorda
 */
public class GeneralHelper {
    
    public String EncodeString(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    
    public String DecodeString(String str){
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    };
    
}
