/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Jordaine Gayle
 */
public interface IDataManipulation<T> {
    
    public int GetCurrentId();
    
    public boolean Add(T obj);
    
    public boolean Add(ArrayList<T> lObj);
    
    public boolean Delete(Object id);
    
    public boolean Delete(ArrayList<Object> lObj);
    
    public boolean Edit(T obj);
    
    public boolean Edit(ArrayList<Object> lObj);
    
    public T GetSingle(Object id);
    
    public ArrayList<T> GetAll();
    
    public ArrayList<T> Query(Object params);
    
    public String toJson(T o);
    
    public String toJson(ArrayList<T> o);
    
    public T fromJson(String str);
    
    public ArrayList<T> fromJsonArray(String str);
    
}
