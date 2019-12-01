/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

/**
 *
 * @author Jarseno Samuels
 */
public class Cart {
    
    //private int cartId;
    
    private int itemId;
    
    private String itemName;
    
    private int quantity;
    
    private double price;
    
    private double totalPrice;
    
    private String userId;
    
    public Cart(){
        
        itemId =  0;
        
        itemName = "";
        
        quantity =  0;
        
        price =  0;
        
        totalPrice = 0;
        
        userId = "";
    }
    
    public Cart(int itmId, String iName, int quan, double pr,double tpr, String usr){
        
        itemId = itmId;
        
        itemName = iName;
        
        quantity = quan;
        
        price = pr;
        
        totalPrice = tpr;
        
        userId = usr;
    }
    
    
    
    public void setItemId(int id){
        itemId = id;
    }
    
    public void setUserId(String id){
        userId = id;
    }
    
    public void setItemName(String iName){
        itemName = iName;
    }
    
    public void setQuantity(int qid){
        quantity = qid;
    }
    
    public void setPrice(double pr){
        price = pr;
    }
    
    public void setTotalPrice(double tpr){
        totalPrice = tpr;
    }
    
//    public int getCartId(int cid){
//        return cartId;
//    }
    
    public int getItemId(){
        return itemId;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public double getPrice(){
        return price;
    }
    
    public double getTotalPrice(){
        return totalPrice;
    }
}
