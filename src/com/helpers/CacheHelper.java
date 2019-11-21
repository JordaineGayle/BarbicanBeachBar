/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.enums.Enums.UserType;

/**
 *
 * @author jorda
 */
public class CacheHelper {
    
    private static String Username;
    
    private static UserType UType;
    
    public CacheHelper(){
        Username= "";
        
        UType = UserType.Guest;
    }
    
    public static String getUsername(){
        return Username;
    }
    
    public static UserType getUserType(){
        return UType;
    }
    
    public static void setUsername(String name){
        Username = name;
    }
    
    public static void setUsertype(UserType utype){
        UType = utype;
    }
}
