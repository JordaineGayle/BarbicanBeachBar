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
    
    private static String Useremail;
    
    public CacheHelper(){
        Username= "";
        
        UType = UserType.Guest;
        
        Useremail = "";
    }
    
    public static String getUsername(){
        return Username;
    }
    
    public static String getUseremail(){
        return Useremail;
    }
    
    public static UserType getUserType(){
        return UType;
    }
    
    public static void setUsername(String name){
        Username = name;
    }
    
    public static void setUseremail(String email){
        Useremail = email;
    }
    
    public static void setUsertype(UserType utype){
        UType = utype;
    }
}
