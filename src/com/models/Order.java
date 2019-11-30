/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.enums.Enums;
import com.enums.Enums.Status;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author jorda
 */
public class Order implements Comparable<Order> {
    
    private int orderId;

    private ArrayList<Item> items;

    private User user;

    private int totalPrepTime;
    
    private double totalPrice;
    
    private Status orderStatus;
    
    private String orderNumber;

    private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());


    public Order(){

        orderId = 0;

        items = new ArrayList<>();

        user  = new User();

        totalPrepTime = 0;
        
        totalPrice = 0;
        
        orderStatus = Enums.Status.InProgress;
        
        orderNumber = "";

    }

    public Order(int orderId, ArrayList<Item> items, User user, int prepTime, double totalPrice, String onum){
        
        this.orderId = orderId;

        this.items = items;

        this.user = user;

        this.totalPrepTime = prepTime;
        
        this.totalPrice = totalPrice;
        
        orderStatus = Enums.Status.InProgress;
        
        orderNumber = onum;
    }

    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    
    public void setOrderStatus(Status status){
        this.orderStatus = status;
    }
    
    public void setOrderNumber(String onum){
        this.orderNumber = onum;
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
    
    public void setTotalPrice(double tPrice){
        this.totalPrice = tPrice;
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
    
    public String getOrderNumber(){
        return this.orderNumber;
    }

    public int getPrepTime(){
        return this.totalPrepTime;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public Status getOrderStatus(){
        return this.orderStatus;
    }

    public Timestamp getTimeStamp(){
        return this.timeStamp;
    }
    
    @Override
    public int compareTo(Order o) {

        return this.getOrderId() - o.getOrderId();
            
    }
    

    // Two employees are equal if their IDs are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.getOrderId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

}
