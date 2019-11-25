/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author jorda
 */
public class Order {
    
    private int orderId;

    private ArrayList<Item> items;

    private User user;

    private int totalPrepTime;

    private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());


    public Order(){

        orderId = 0;

        items = new ArrayList<>();

        user  = new User();

        totalPrepTime = 0;

    }

    public Order(int orderId, ArrayList<Item> items, User user, int prepTime){
        
        this.orderId = orderId;

        this.items = items;

        this.user = user;

        this.totalPrepTime = prepTime;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTotalPrepTime(int prepTime){
        this.totalPrepTime = prepTime;
    }

    public int getOrderId(){
        return this.orderId;
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public User getUser(){
        return this.user;
    }

    public int getPrepTime(){
        return this.totalPrepTime;
    }

    public Timestamp getTimeStamp(){
        return this.timeStamp;
    }

}
