/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.sql.Timestamp;

import com.helpers.GeneralHelper;
import java.util.Objects;

/**
 *
 * @author jorda
 */
public class Item implements Comparable<Item> {
    
    private int itemId;
    
    private String uniqueName;
    
    private String name;
    
    private double price;
    
    private int preparationtTime;
    
    private int quantity;
    
    private String imageUrl;
    
    private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    
    public Item(){
        itemId = 0;

        name = "";

        uniqueName = GeneralHelper.EncodeString(name.toLowerCase());

        price = 0.00;

        preparationtTime = 0;

        quantity = 0;
        
        imageUrl = "";
    }

    public Item(int itemId, String name, String imageUrl,double price, int prepTime, int quantity){

        this.itemId = itemId;

        this.name = name;

        uniqueName = GeneralHelper.EncodeString(name.toLowerCase());

        this.price = price;

        this.preparationtTime = prepTime;

        this.quantity = quantity;
        
        this.imageUrl = imageUrl;

    }

    public void setItemId(int id){
        this.itemId = id;
    }

    public void setImageUrl(String url){
        this.imageUrl = url;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setPrepTime(int prepTime){
        this.preparationtTime = prepTime;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getItemId(){
        return this.itemId;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getPrepTime(){
        return this.preparationtTime;
    }

    public int getQuantity(){
        return this.quantity;
    }
    
    public String getUniqueName(){
        return this.uniqueName;
    }

    public Timestamp getTimestamp(){
        return this.timeStamp;
    }
    
    public String getImageUrl(){
        return this.imageUrl;
    }

    @Override
    public int compareTo(Item o) {

        return this.getItemId() - o.getItemId();
            
    }
    

    // Two employees are equal if their IDs are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.getItemId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}
