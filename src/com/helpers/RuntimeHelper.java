/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.models.Cart;
import com.models.Item;
import java.util.ArrayList;
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
 * @author jgayle
 */
public class RuntimeHelper {
    
    public static void loadItems(Parent p){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Item> items = ih.GetAll();
   
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
    
    public static void loadItemsInCart(Parent p){
        
        ItemsHelper ih = new ItemsHelper();
        
        ArrayList<Cart> items = ih.cartItems;
   
        if(items != null){
    
            ObservableList<Node> node = p.getChildrenUnmodifiable();

            System.out.println(node);
            
            ScrollPane sp = (ScrollPane)node.get(1);

            node = sp.getContent();
            
            System.out.println(node);
            
            HBox hb = (HBox)node.get(0);

            node = hb.getChildren();
            
            System.out.println(node);
//
//            ScrollPane sp = (ScrollPane) node.get(1);
//
//            VBox tp = (VBox) sp.getContent();
//            
//            for(Cart item : items){
//
//                System.out.println(item.getItemName());
//                
//                //tp.getChildren().add(CustomSceneBuilder.BuildCustomerItemVBox(item));
//
//            }
        }
    }
    
    
}
    
