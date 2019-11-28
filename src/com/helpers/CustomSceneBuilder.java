/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.models.Cart;
import com.models.Item;
import controllers.AlertdialogController;
import controllers.CustomerdashController;
import java.util.ArrayList;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import scenes.ScenesHandler;

/**
 *
 * @author jgayle
 */
public class CustomSceneBuilder {
    
    public static VBox BuildCustomerItemVBox(Item item){
        
        VBox vb = new VBox();

        vb.fillWidthProperty();

        vb.setStyle("-fx-background-color:white;-fx-background-radius:15px;");

        vb.setSpacing(10);

        vb.setPadding(new Insets(10,10,10,10));

        vb.setEffect(BuildDShadow());
        
        ImageView imv = new ImageView();
        
        String url = item.getImageUrl();
        
        if(url.isEmpty()){
            url = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6c/No_image_3x4.svg/1280px-No_image_3x4.svg.png";
        }
        
        Image img = new Image(url,true);
        
        imv.setImage(img);
        
        imv.setFitWidth(200);
        
        imv.setFitHeight(150);
        
        vb.getChildren().add(imv);

        vb.getChildren().add(new Label("Item: "+item.getName().toUpperCase()));
        
        vb.getChildren().add(new Label("Prepartion Time: "+String.valueOf(item.getPrepTime())+" min"));
        
        vb.getChildren().add(new Label("Price: USD $"+String.valueOf(item.getPrepTime())));
        
        TextField tf = new TextField();
        
        tf.setPromptText("quantity - remaining = "+String.valueOf(item.getQuantity()));
        
        vb.getChildren().add(tf);
        
        Button btn = new Button("Add to cart");
        
        btn.setTextAlignment(TextAlignment.CENTER);
        
        btn.setMinWidth(200);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(GeneralHelper.isNumeric(tf.getText())){
                    
                    int yourQuantity = Integer.parseInt(tf.getText());
                    
                    Cart obtainedCart = null;
                    
                    for(Cart e : ItemsHelper.cartItems){
                    
                        if(e.getItemId() == item.getItemId()){
                            obtainedCart = e;
                            break;
                        }
                        
                    }
                    
                    if(obtainedCart != null){
                     
                        ArrayList<Cart> cartitems = new ArrayList<>();
                        
                        ItemsHelper.cartItems.forEach(i->{
                        
                            if(i.getItemId() != item.getItemId()){
                                cartitems.add(i);
                            }
                            
                        });
                        
                        ItemsHelper.cartItems = cartitems;
                        
                    }else{
                        obtainedCart = new Cart(item.getItemId(),item.getName(),0,item.getPrice(),yourQuantity*item.getPrice());
                    }
                    
                    int inStock = item.getQuantity();
                    
                    if(yourQuantity < inStock){
                        
                        obtainedCart.setQuantityId(yourQuantity += obtainedCart.getQuantity());
                        
                        ItemsHelper.cartItems.add(obtainedCart);
                        
                        CustomerdashController.customerDashIntProp.set(String.valueOf(ItemsHelper.cartItems.size()));
                    }else{
                        ItemsHelper.cartItems.add(obtainedCart);
                        ScenesHandler.AlertStage(new Stage());
                        AlertdialogController.showError("out of stock.");
                    }
                    
                }else{
                    ScenesHandler.AlertStage(new Stage());
                    AlertdialogController.showError("Invalid quantity submitted.");
                }
                
            }
        });
        
        vb.getChildren().add(btn);
        return vb;
    }
    
    public  static AnchorPane BuildAnchorPane(){
        return null;
    }
    
    public  static DropShadow BuildDShadow(){
        
        DropShadow ds = new DropShadow();
        
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        
        ds.setWidth(32.61);
        
        ds.setHeight(40.89);
        
        ds.setRadius(17.88);
        
        ds.setSpread(0.19);
        
        ds.setColor(Color.rgb(0, 0, 0, 0.16));
        
        return ds;
    }
    
}
