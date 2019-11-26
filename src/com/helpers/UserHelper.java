/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.enums.Enums.UserType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.models.User;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jorda
 */
public class UserHelper {
    
    public ArrayList<User> getUsers(){
        
        FileHelper fp = new FileHelper("user.json");
        
        try{
            
            Gson json = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
            
            String data = fp.read();
            
            Type u = new TypeToken<ArrayList<User>>(){}.getType();
            
            ArrayList<User> users = json.fromJson(data,u);
            
            if(users == null){
                return new ArrayList<User>();
            }
            
            return users;
            
        }catch(JsonSyntaxException | IOException e){
            e.printStackTrace();
        }
        
        return new ArrayList<User>();
    }
    
    public int getCurrentUserId(){
        return getUsers().size();    
    }
    
    public boolean userExist(String email){
        
        if(getCurrentUserId() <= 0){
            return false;
        }
        
        return getUsers().stream().anyMatch((user) -> (user.getEmailAddress().toLowerCase().equals(email.toLowerCase())));
    }
    
    public void AddUser(User u) throws Exception{
        
        if(userExist(u.getEmailAddress())){
            throw new Exception("User already exist.");
        }

        ArrayList<User> userlist = getUsers();

        u.setUserId(getCurrentUserId()+1);

        userlist.add(u);

        Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        String jsonString = json.toJson(userlist);

        FileHelper fhelper = new FileHelper("user.json");
        try{ 
            int success = fhelper.write(jsonString);
        }catch(Exception e){
            throw new Exception("Failed to create user.");
        }
    }
    
    public User getUserByEmail(String email){
        
        for (User user : getUsers()) {
            if(user.getEmailAddress().toLowerCase().equals(email.toLowerCase())){
                return user;
            }
        }
        
        return null;
    }
    
    public User getUserByEmailType (String email, UserType utype){
        
        for (User user : getUsers()) {
            if(user.getEmailAddress().toLowerCase().equals(email.toLowerCase()) && user.getUserType().equals(utype)){
                return user;
            }
        }
        
        return null;
    }
    
   public boolean loginUser(String email, String pword, UserType utype) throws Exception{
       
       if(email.isEmpty() || pword.isEmpty() || utype == null){
           throw new Exception("Please enter valid credentials.");
       }
       
       User user = getUserByEmailType(email,utype);
       
       if(user!=null){
        if(GeneralHelper.DecodeString(user.getPassword()).equals(pword)){

             CacheHelper.setUsername(user.getFirstName()+" "+user.getLastName());

             CacheHelper.setUsertype(user.getUserType());

             return true;
         }
       }
       
       
       
       throw new Exception("Login failed. #Invalid login credentials.");
       
   }
    
}
