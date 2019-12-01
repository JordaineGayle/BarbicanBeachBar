/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enums;

/**
 *
 * @author Jordaine Gayle
 */
public class Enums {
    
    //Enum for the userTypes
    public enum UserType{
        Admin, 
        Customer,
        Guest
    }
    
    //enum for the orderSstatus
    public enum Status{
        InProgress,
        Completed,
        Cancelled
    }
    
}
