/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.models.Cart;
import com.models.Item;
import com.models.Order;
import controllers.AlertdialogController;
import controllers.CustomerdashController;
import controllers.DashboardController;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
        
        vb.getChildren().add(new Label("Price: USD $"+String.valueOf(item.getPrice())));
        
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
                        
                        obtainedCart.setQuantity(yourQuantity += obtainedCart.getQuantity());
                        
                        obtainedCart.setTotalPrice( obtainedCart.getQuantity()*item.getPrice() );
                        
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
    
    
    
    public static HBox BuildCartItemHBox(Cart item){
        
       HBox hb = new HBox();
       
       Region r = new Region();
       HBox.setHgrow(r, Priority.ALWAYS);
       
       Region r1 = new Region();
       HBox.setHgrow(r1, Priority.ALWAYS);
       
       Region r2 = new Region();
       HBox.setHgrow(r2, Priority.ALWAYS);
       
       hb.setMinHeight(HBox.USE_COMPUTED_SIZE);
       
       hb.getChildren().add(BuildLabel(item.getItemName()));
       hb.getChildren().add(r);
       hb.getChildren().add(BuildLabel("USD $"+item.getPrice()));
       hb.getChildren().add(r1);
       hb.getChildren().add(BuildLabel("x "+item.getQuantity()));
       hb.getChildren().add(r2);
       hb.getChildren().add(BuildLabel("USD $"+item.getTotalPrice()));
       
       hb.setSpacing(15);
       hb.setPadding(new Insets(10,10,10,10));
       return hb;
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
    
    public static Label BuildLabel(Object str){
        
        Label l = new Label(String.valueOf(str).toUpperCase());
        
        l.setFont(Font.font("Arial", 11));
        
        l.setTextFill(Paint.valueOf("WHITE"));
        
        l.setAlignment(Pos.CENTER_LEFT);
        
        l.setMinWidth(228);
        
        return l;
        
    }
    
    public static HBox BuildCustomerOrderItemHBox(Order item){
        
       HBox hb = new HBox();
       
       
        VBox vb = new VBox();
        
        vb.setPadding(new Insets(10,10,10,10));
    
        Label statusLabel = new Label("Status: "+item.getOrderStatus().name());
       
        statusLabel.setFont(Font.font("Arial Black"));
        
        statusLabel.setTextFill(Paint.valueOf("WHITE"));
        
        statusLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(statusLabel);
        
        Label orderLabel = new Label("Order#: "+item.getOrderNumber());
       
        orderLabel.setFont(Font.font("Arial"));
        
        orderLabel.setTextFill(Paint.valueOf("WHITE"));
        
        orderLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(orderLabel);
       
        HBox innerBox = new HBox();
       
        List<String> nameList = item.getItems().stream().map(Item::getName).collect(Collectors.toList());
       
        Label itemLabel = new Label(String.join("\n",nameList));
        
        itemLabel.setTextFill(Paint.valueOf("WHITE"));
        
        itemLabel.setPadding(new Insets(10,0,0,0));
       
        innerBox.getChildren().add(itemLabel);
       
        Label timeLabel = new Label(String.valueOf(item.getPrepTime())+" min");
        
        timeLabel.setPadding(new Insets(10,0,0,0));
       
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        
        timeLabel.setFont(Font.font("Candara"));
       
        timeLabel.setTextFill(Paint.valueOf("#dd0e0e"));
        
        //timeLabel.setTooltip("time");
        
        innerBox.getChildren().add(timeLabel);
       
        vb.getChildren().add(innerBox);
        
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_delete_32px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img2 = new Image(localUrl2,true);
        
        ImageView imv2 = new ImageView(img2);
        
        imv2.setFitWidth(20);
        imv2.setFitHeight(20);
        
        Button delete = new Button();
        delete.setGraphic(imv2);
        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #b71c1c;");
        //delete.setPrefWidth(88);
        //delete.setPrefHeight(25);
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                OrdersHelper oh = new OrdersHelper();
                
                if(oh.Delete(item.getOrderId())){
                    
                    ScenesHandler.AlertStage(new Stage());
                    
                    AlertdialogController.showError("Order#: "+item.getOrderNumber()+" has successfully deleted.");
                }
            
            }
        });
       
        delete.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        delete.setCursor(Cursor.HAND);
        
        vb.getChildren().add(delete);
       
        hb.getChildren().add(vb);
       
        return hb;
    }
    
    
    
    
    
    
    /*Admin section*/
    
    public static HBox BuildAdminOrderItemHBox(Order item){
        
       HBox hb = new HBox();
       
        VBox vb = new VBox();
        
        vb.setPadding(new Insets(10,10,10,10));
       
        Label nameLabel = new Label("User: "+item.getUser().getFirstName()+" "+item.getUser().getLastName());
       
        nameLabel.setFont(Font.font("Arial Black"));
        
        nameLabel.setTextFill(Paint.valueOf("WHITE"));
        
        //nameLabel.setPadding(new Insets(10,0,0,10));
        
        vb.getChildren().add(nameLabel);
        
      
        Label statusLabel = new Label("Status: "+item.getOrderStatus().name());
       
        statusLabel.setFont(Font.font("Arial Black"));
        
        statusLabel.setTextFill(Paint.valueOf("WHITE"));
        
        statusLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(statusLabel);
       
       
        HBox innerBox = new HBox();
       
        List<String> nameList = item.getItems().stream().map(Item::getName).collect(Collectors.toList());
       
        Label itemLabel = new Label(String.join("\n",nameList));
        
        itemLabel.setTextFill(Paint.valueOf("WHITE"));
        
        itemLabel.setPadding(new Insets(10,0,0,0));
       
        innerBox.getChildren().add(itemLabel);
       
        Label timeLabel = new Label(String.valueOf(item.getPrepTime())+" min");
        
        timeLabel.setPadding(new Insets(10,0,0,0));
       
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        
        timeLabel.setFont(Font.font("Candara"));
       
        timeLabel.setTextFill(Paint.valueOf("#dd0e0e"));
        
        //timeLabel.setTooltip("time");
        
        innerBox.getChildren().add(timeLabel);
       
        vb.getChildren().add(innerBox);
        
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_delete_32px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img2 = new Image(localUrl2,true);
        
        ImageView imv2 = new ImageView(img2);
        
        imv2.setFitWidth(20);
        imv2.setFitHeight(20);
        
        Button delete = new Button();
        delete.setGraphic(imv2);
        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #b71c1c;");
        //delete.setPrefWidth(88);
        //delete.setPrefHeight(25);
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                OrdersHelper oh = new OrdersHelper();
                
                if(oh.Delete(item.getOrderId())){
                    
                    ScenesHandler.AlertStage(new Stage());
                    
                    AlertdialogController.showError("Order#: "+item.getOrderNumber()+" has successfully deleted.");
                }
            
            }
        });
       
        delete.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        delete.setCursor(Cursor.HAND);
        
        vb.getChildren().add(delete);
        
        hb.getChildren().add(vb);
        
       
        return hb;
    }
    
    public static VBox BuildAdminItemVBox(Item item){
        
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
        
        vb.getChildren().add(new Label("Price: USD $"+String.valueOf(item.getPrice())));
        
        HBox hb = new HBox();
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        
        
        
        String localUrl1 = "";
        File file = new File(PathHelper.imageAbsPath+"icons8_edit_property_32px.png");
 
        try {
            localUrl1 = file.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img1 = new Image(localUrl1,true);
       
        ImageView imv1 = new ImageView(img1);
        
        
        imv1.setFitWidth(20);
        imv1.setFitHeight(20);
        
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_delete_32px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img2 = new Image(localUrl2,true);
        
        ImageView imv2 = new ImageView(img2);
        
        imv2.setFitWidth(20);
        imv2.setFitHeight(20);
        
        Button edit = new Button("Edit");
        edit.setGraphic(imv1);
        edit.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        edit.setTextFill(Paint.valueOf("WHITE"));
        edit.setStyle("-fx-background-color: #212121; -fx-background-radius: 30px;");
        edit.setPrefWidth(88);
        edit.setPrefHeight(25);
        
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                CacheHelper.setItem(item);
                
                ScenesHandler.EditItemStage(new Stage());
            }
        });
        
        
        //loadOrders
        
        
        Button delete = new Button("Delete");
        delete.setGraphic(imv2);
        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #212121; -fx-background-radius: 30px;");
        delete.setPrefWidth(88);
        delete.setPrefHeight(25);
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ItemsHelper ihp = new ItemsHelper();
                
                if(ihp.Delete(item.getItemId())){
                   
                    ScenesHandler.getDashboardStage().close();
                    
                    ScenesHandler.DashboardStage(new Stage());
                }
            }
        });
        
        
        hb.getChildren().add(edit);
        hb.getChildren().add(r);
        hb.getChildren().add(delete);
        
        vb.getChildren().add(hb);
        
        return vb;
    }
}
