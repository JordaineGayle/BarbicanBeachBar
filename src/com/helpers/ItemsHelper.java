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
import com.models.Item;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author jorda
 */
public class ItemsHelper implements IDataManipulation<Item> {

    private FileHelper fp = new FileHelper("items.json");
    
    private final Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    
    @Override
    public boolean Add(Item obj) {
       
        if(obj == null){
            return false;
        }
        
        ArrayList<Item> items = GetAll();
        
        int currentId = GetCurrentId();
        
        obj.setItemId(currentId+1);
        
        items.add(obj);
        
        String data = toJson(items);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ItemsHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean Add(ArrayList<Item> lObj) {
        
        if(lObj == null){
            return false;
        }
        
        String data = toJson(lObj);
        
        try {
            fp.write(data);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ItemsHelper.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    @Override
    public boolean Delete(Object id) {
        
        if(id == null){
            return false;
        }
        
        int currentId = (int)id;
        
        ArrayList<Item> items = GetAll();
        
        boolean removed = items.removeIf( e-> e.getItemId() == currentId);
        
        return removed;
        
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
    public boolean Edit(Item obj) {
        
        if(obj == null){
            return false;
        }
        
        int id = obj.getItemId();
        
        if(Delete(id)){
            
            if(Add(obj)){
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
                Edit((Item)i);
            }
            
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Item GetSingle(Object id) {
        
        ArrayList<Item> items = GetAll();
        
        int currentId = (int)id;
        
        Stream<Item> i = items.stream().filter(e->e.getItemId() == currentId);
        
        return i.findFirst().get();
    }

    @Override
    public ArrayList<Item> GetAll() {
        
        try{
            
            String jString = fp.read();
            
            return fromJsonArray(jString);
            
        }catch(Exception e){
            e.printStackTrace();
            
            return new ArrayList<Item>(){};
        }
        
    }

    @Override
    public ArrayList<Item> Query(Object params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toJson(Item o) {
        
        String jsonString = json.toJson(o);
        
        return jsonString;
        
    }

    @Override
    public String toJson(ArrayList<Item> o) {
        
        String jsonString = json.toJson(o);
        
        return jsonString;
    }

    @Override
    public Item fromJson(String str){
        
        Item jsonObj = json.fromJson(str,Item.class);
        
        return jsonObj;
    }

    @Override
    public ArrayList<Item> fromJsonArray(String str) 
    {
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
        
        ArrayList<Item> jsonObj = json.fromJson(str,type);
        
        return jsonObj;
                
    } 

    @Override
    public int GetCurrentId()
    {
        return GetAll().size();
    }
    
    
}
