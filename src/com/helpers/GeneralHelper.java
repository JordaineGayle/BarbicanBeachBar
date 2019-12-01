/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.models.Cart;
import controllers.CustomerdashController;
import java.util.Base64;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;

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
    
    public static int getMyCartCount(){
        
        int count = 0;
        
        for(Cart c : ItemsHelper.cartItems){
            if(CacheHelper.getUseremail().equals(c.getUserId())){
                count++;
            }
        }
        
        return count;
    }
    
    public static void setCartCount(){
        if(String.valueOf(GeneralHelper.getMyCartCount()).equals("0")){
            CustomerdashController.customerDashIntProp.set("");
        }else{
            CustomerdashController.customerDashIntProp.set(String.valueOf(GeneralHelper.getMyCartCount()));
        }
    }
    
    public static void buttonHover(Node node, String hStyle, String uhStyle){
        node.styleProperty().bind(
        Bindings
        .when(node.hoverProperty())
          .then(
            new SimpleStringProperty(hStyle)
          )
          .otherwise(
            new SimpleStringProperty(uhStyle)
          )
        );
    }
    
}
