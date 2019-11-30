/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.enums.Enums;
import com.enums.Enums.UserType;
import com.models.Cart;
import com.models.Item;
import com.models.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import scenes.ScenesHandler;

/**
 *
 * @author Jordaine Gayle
 */
public class RuntimeHelper {
    
    public static void loadItems(Parent p){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Item> items = ih.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
    
            ObservableList<Node> node = p.getChildrenUnmodifiable();

            BorderPane bp = (BorderPane)node.get(2);

            node = bp.getChildren();

            ScrollPane sp = (ScrollPane) node.get(1);

            TilePane tp = (TilePane) sp.getContent();
            
            for(Item item : items){

                tp.getChildren().add(CustomSceneBuilder.BuildCustomerItemVBox(item));

            }
        }
    }
    
    public static void loadAdminItems(Parent p){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Item> items = ih.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
    
            ObservableList<Node> node = p.getChildrenUnmodifiable();

            BorderPane bp = (BorderPane)node.get(2);

            node = bp.getChildren();

            ScrollPane sp = (ScrollPane) node.get(1);

            TilePane tp = (TilePane) sp.getContent();
            
            for(Item item : items){

                tp.getChildren().add(CustomSceneBuilder.BuildAdminItemVBox(item));

            }
        }
    }
    
    public static void loadItemsPartial(Parent p){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Item> items = ih.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
    
            ScrollPane sp = (ScrollPane)p;

            TilePane tp = (TilePane) sp.getContent();
            
            tp.getChildren().clear();
            
            
            if(CacheHelper.getUserType() == UserType.Customer){
                for(Item item : items){

                    tp.getChildren().add(CustomSceneBuilder.BuildCustomerItemVBox(item));

                }
            }else{
                
                for(Item item : items){

                    tp.getChildren().add(CustomSceneBuilder.BuildAdminItemVBox(item));

                }
            }
        }
    }
    
    public static void loadIOrdersPartial(Parent p){
        
        OrdersHelper ih = new OrdersHelper();
        
        ArrayList<Order> items = ih.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
    
            ScrollPane sp = (ScrollPane)p;

            VBox vp = (VBox) sp.getContent();
            
            vp.getChildren().clear();
            
            
            
            if(CacheHelper.getUserType() == UserType.Customer){
                for(Order item : items){
                    if(CacheHelper.getUseremail().toLowerCase().equals(item.getUser().getEmailAddress().toLowerCase())){
                        vp.getChildren().add(CustomSceneBuilder.BuildCustomerOrderItemHBox(item));
                    }
                }
            }else{
                
                for(Order item : items){

                    vp.getChildren().add(CustomSceneBuilder.BuildAdminOrderItemHBox(item));

                }
            }
        }
    }
    
    public static void loadItemsInCart(Parent p){
        
        int counter = 0;
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Cart> items = ih.cartItems;
   
        if(items != null){
    
            ObservableList<Node> node = p.getChildrenUnmodifiable();

            System.out.println(node);
            
            ScrollPane sp = (ScrollPane)node.get(1);
            
            VBox vb = (VBox)sp.getContent();

            //node = vb.getChildren();
            
            System.out.println(node);

            for(Cart item : items){

                //item.setTotalPrice(0);
                
                HBox hb = CustomSceneBuilder.BuildCartItemHBox(item);
                
                if(counter%2 == 0){
                    hb.setStyle("-fx-background-color: rgba(100,100,100,0.5);");
                    vb.getChildren().add(hb);
                }else{
                    hb.setStyle("-fx-background-color: rgba(150,150,150,1);");
                    vb.getChildren().add(hb);
                }
                
                
                
                counter++;

            }
        }
    }
    
    public static void searchItems(Parent p, List<String> res){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Item> items = ih.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
            
            ScrollPane sp = (ScrollPane)p;

            TilePane tp = (TilePane) sp.getContent();
            
            tp.getChildren().clear();
            
            if(CacheHelper.getUserType() == UserType.Customer){
                for(Item item : items){

                    for(String s : res){
                        if(item.getName().toLowerCase().contains(s.trim().toLowerCase())){

                            tp.getChildren().add(CustomSceneBuilder.BuildCustomerItemVBox(item));
                            break;
                        }
                    }

                }
            }else{
                for(Item item : items){

                    for(String s : res){
                        if(item.getName().toLowerCase().contains(s.trim().toLowerCase())){

                            tp.getChildren().add(CustomSceneBuilder.BuildAdminItemVBox(item));
                            break;
                        }
                    }

                }
            }
        }
    }

    
    public static void loadOrders(Parent p){
        
        ObservableList<Node> node = p.getChildrenUnmodifiable();
        
        VBox vb = (VBox)node.get(1);
        
        node = vb.getChildren();
        
        ScrollPane sp = (ScrollPane)node.get(1);
        
        VBox vb1 = (VBox)sp.getContent();
        
        OrdersHelper oh = new OrdersHelper();
        
        ArrayList<Order> items = oh.GetAll();
        
        Collections.sort(items);
   
        if(items != null){
    
            if(CacheHelper.getUserType() == UserType.Customer){
                
                for(Order order : items){
               
                    if(CacheHelper.getUseremail().toLowerCase().equals(order.getUser().getEmailAddress().toLowerCase())){
                        vb1.getChildren().add(CustomSceneBuilder.BuildCustomerOrderItemHBox(order));
                    }
                }
                
            }else{
                
                for(Order order : items){
                    vb1.getChildren().add(CustomSceneBuilder.BuildAdminOrderItemHBox(order));
                }
            }
            
            
        }
    }

}
    
