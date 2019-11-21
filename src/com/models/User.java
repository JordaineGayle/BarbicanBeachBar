/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.enums.Enums;
import com.enums.Enums.UserType;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author jorda
 */
public class User {
    
    private long Id;
    
    private String FirstName;
    
    private String LastName;
    
    private String EmailAddress;
    
    private String Password;
    
    private String PhoneNumber;
    
    private UserType UserType;
    
    private Timestamp Timestamp = new Timestamp(System.currentTimeMillis());
    
    public User(){
        
        FirstName = "";
        
        LastName = "";
        
        EmailAddress = "";
        
        PhoneNumber = "";
        
        UserType = Enums.UserType.Customer;
        
        Password = "";
        
    }
    
    public User(String fName, String lName, String eMail, String pWord,String pNumber, UserType uType){
        
        this.FirstName = fName;
        
        this.LastName = lName;
        
        this.EmailAddress = eMail;
        
        this.PhoneNumber = pNumber;
        
        this.UserType = uType;
        
        this.Password = pWord;
        
    }
    
    public long getUserId(){
        return this.Id;
    }
    
    public String getFirstName(){
        return this.FirstName;
    }
    
    public String getLastName(){
        return this.LastName;
    }
    
    public String getEmailAddress(){
        return this.EmailAddress;
    }
    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
    
    public UserType getUserType(){
        return this.UserType;
    }
    
    public String getPassword(){
        return this.Password;
    }
    
    public Timestamp getTimeStamp(){
        return this.Timestamp;
    }
    
    
    public void setFirstName(String FirstName){
        this.FirstName = FirstName;
    }
    
    public void setLastName(String LastName){
        this.LastName= LastName;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    public void setEmailAddress(String EmailAddress){
        this.EmailAddress = EmailAddress;
    }
    
    public void setPhoneNumber(String PhoneNumber){
        this.PhoneNumber = PhoneNumber;
    }
    
    public void setUserType(UserType UserType){
        this.UserType = UserType;
    }
    
    public void setUserId(long id){
        this.Id = id;
    }
    
}
