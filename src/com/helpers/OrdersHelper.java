/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.interfaces.IDataManipulation;
import com.models.Cart;
import com.models.Order;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Jordaine Gayle
 */
public class OrdersHelper implements IDataManipulation<Order> {

    private FileHelper fp = new FileHelper("orders.json");
    
    private final Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    
    public static ArrayList<Cart> cartItems = new ArrayList<Cart>();
    
    @Override
    public boolean Add(Order obj) {
       
        if(obj == null){
            return false;
        }
        
        ArrayList<Order> items = GetAll();
        
        if(items == null){
            items = new ArrayList<>();
        }
        
        int currentId = GetCurrentId();
        
        obj.setOrderId(currentId+1);
        
        items.add(obj);
        
        String data = toJson(items);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OrdersHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    private boolean ImplicitAdd(Order obj) {
       
        if(obj == null){
            return false;
        }
        
        ArrayList<Order> items = GetAll();
        
        if(items == null){
            items = new ArrayList<>();
        }
        
        items.add(obj);
        
        String data = toJson(items);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OrdersHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    @Override
    public boolean Add(ArrayList<Order> lObj) {
        
        if(lObj == null){
            return false;
        }
        
        String data = toJson(lObj);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OrdersHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean Delete(Object id) {
        
        if(id == null){
            return false;
        }
        
        int currentId = (int)id;
        
        ArrayList<Order> items = GetAll();
        
        if(items == null){
            items = new ArrayList<>();
        }
        
        boolean removed = items.removeIf( e-> e.getOrderId() == currentId);
        
        String data = toJson(items);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(OrdersHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean Delete(ArrayList<Object> lObj) {
        
        try{
            if(lObj == null){
                return false;
            }

            for(Object obj : lObj){
                Delete(obj);
            }

            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean Edit(Order obj) {
        
        if(obj == null){
            return false;
        }
        
        int id = obj.getOrderId();
        
        if(Delete(id)){
            
            if(ImplicitAdd(obj)){
                return true;
            }
            
        }else{
            return false;
        }
        return false;
    }

    @Override
    public boolean Edit(ArrayList<Object> lObj) {
        
        try{
            for(Object i : lObj){
                Edit((Order)i);
            }
            
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Order GetSingle(Object id) {
        
        ArrayList<Order> items = GetAll();
        
        if(items == null){
            items = new ArrayList<>();
        }
        
        int currentId = (int)id;
        
        Stream<Order> i = items.stream().filter(e->e.getOrderId() == currentId);
        
        return i.findFirst().get();
    }

    @Override
    public ArrayList<Order> GetAll() {
        
        try{
            
            String jString = fp.read();
            
            return fromJsonArray(jString);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return new ArrayList<Order>(){};
        
    }

    @Override
    public ArrayList<Order> Query(Object params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJson(Order o) {
        
        String jsonString = json.toJson(o);
        
        return jsonString;
        
    }

    @Override
    public String toJson(ArrayList<Order> o) {
        
        String jsonString = json.toJson(o);
        
        return jsonString;
    }

    @Override
    public Order fromJson(String str){
        
        Order jsonObj = json.fromJson(str,Order.class);
        
        return jsonObj;
    }

    @Override
    public ArrayList<Order> fromJsonArray(String str) 
    {
        Type type = new TypeToken<ArrayList<Order>>(){}.getType();
        
        ArrayList<Order> jsonObj = json.fromJson(str,type);
        
        return jsonObj;
                
    } 

    @Override
    public int GetCurrentId()
    {
        ArrayList<Order> items = GetAll();
        
        if(items != null){
            
            if(items.size() <= 0){
                return 0;
            }else{
                Collections.sort(items);
            
                return items.get(items.size()-1).getOrderId();
            }
            
            
        }
        
        return 0;
        
    }
    
    
}

