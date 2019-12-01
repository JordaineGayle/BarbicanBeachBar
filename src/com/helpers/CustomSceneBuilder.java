/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.enums.Enums;
import com.enums.Enums.Status;
import com.models.Cart;
import com.models.Item;
import com.models.Order;
import controllers.AlertdialogController;
import controllers.CustomerdashController;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import scenes.ScenesHandler;

/**
 *
 * @author Jordaine Gayle
 * 
 * This class was built to load dynamic data onto the view
 */
public class CustomSceneBuilder {
    
    
    protected static boolean alertAnswer = false;
    
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
        
        btn.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        btn.setTextFill(Paint.valueOf("WHITE"));
        btn.setStyle("-fx-background-color: #212121; -fx-background-radius: 30px;");
        
        btn.setTextAlignment(TextAlignment.CENTER);
        
        btn.setMinWidth(200);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(GeneralHelper.isNumeric(tf.getText())){
                    
                    ItemsHelper items = new ItemsHelper();
                    
                    int yourQuantity = Integer.parseInt(tf.getText());
                    
                    if(yourQuantity <= 0){
                        
                        ScenesHandler.AlertStage(new Stage());
                        AlertdialogController.showError("Invalid quantity submitted. Connot be less than nor 0");
                        return;
                    }
                    
                    //if(yourQuantity)
                    
                    Cart obtainedCart = null;
                    
                    for(Cart e : ItemsHelper.cartItems){
                    
                        if((e.getItemId() == item.getItemId()) && CacheHelper.getUseremail().equals(e.getUserId()) ){
                            obtainedCart = e;
                            break;
                        }
                        
                    }
                    
                    if(obtainedCart != null){
                     
                        ArrayList<Cart> cartitems = new ArrayList<>();
                        
                        ItemsHelper.cartItems.forEach(i->{
                        
                            if( (i.getItemId() != item.getItemId()) && !CacheHelper.getUseremail().equals(i.getUserId())){
                                cartitems.add(i);
                            }else if((i.getItemId() == item.getItemId()) && !CacheHelper.getUseremail().equals(i.getUserId())){
                                cartitems.add(i);
                            }
                            
                        });
                        
                        ItemsHelper.cartItems = cartitems;
                        
                    }else{
                        obtainedCart = new Cart(item.getItemId(),item.getName(),0,item.getPrice(),yourQuantity*item.getPrice(),CacheHelper.getUseremail());
                    }
                    
                    int inStock = item.getQuantity();
                    
                    if(yourQuantity <= inStock){
                        
                        obtainedCart.setQuantity(yourQuantity += obtainedCart.getQuantity());
                        
                        obtainedCart.setTotalPrice( obtainedCart.getQuantity()*item.getPrice() );
                        
                        ItemsHelper.cartItems.add(obtainedCart);
                        
                        GeneralHelper.setCartCount();
                        
                        //CustomerdashController.customerDashIntProp.set(String.valueOf(GeneralHelper.getMyCartCount()));
                    }else{
                       
                        if(obtainedCart.getQuantity()>0){
                             ItemsHelper.cartItems.add(obtainedCart);
                        }
                        
                        ScenesHandler.AlertStage(new Stage());
                        AlertdialogController.showError("out of stock.");
                    }
                    
                }else{
                    ScenesHandler.AlertStage(new Stage());
                    AlertdialogController.showError("Invalid quantity submitted.");
                }
                
            }
        });
        
        GeneralHelper.buttonHover(btn, "-fx-background-color:#424242", "-fx-background-color: #212121");
        
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
        
        Label priceLabel = new Label("Price: "+item.getTotalPrice());
        
        priceLabel.setTextFill(Paint.valueOf("WHITE"));
        
        priceLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(priceLabel);
        
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
        
        timeLabel.setPadding(new Insets(0,0,0,20));
       
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        
        timeLabel.setFont(Font.font("Candara"));
       
        timeLabel.setTextFill(Paint.valueOf("#76FF03"));
        
        //timeLabel.setTooltip("time");
        
        innerBox.getChildren().add(timeLabel);
       
        vb.getChildren().add(innerBox);
        
        Label tsLabel = new Label("Date: "+item.getTimeStamp().toString());
        
        tsLabel.setTextFill(Paint.valueOf("WHITE"));
        
        tsLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(tsLabel);
        
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_delete_32px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img2 = new Image(localUrl2);
        
        ImageView imv2 = new ImageView(img2);
        
        imv2.setFitWidth(20);
        imv2.setFitHeight(20);
        
        Button delete = new Button();
        delete.setGraphic(imv2);
//        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
//        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #b71c1c;");
        GeneralHelper.buttonHover(delete, "-fx-background-color:#ef5350", "-fx-background-color: #b71c1c");
        //delete.setPrefWidth(88);
        //delete.setPrefHeight(25);
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                OrdersHelper oh = new OrdersHelper();
                
                if(oh.Delete(item.getOrderId())){
                    if(AlertDialog("Are you sure you want\nto delete this item?","Delete Item")){
                        ScenesHandler.AlertStage(new Stage());
                    
                        AlertdialogController.showError("Order#: "+item.getOrderNumber()+" has successfully deleted.");

                        RuntimeHelper.loadOrders(ScenesHandler.getCustomerParent());
                    }
                }
            
            }
        });
       
        delete.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        delete.setCursor(Cursor.HAND);
        
        vb.getChildren().add(delete);
       
        hb.getChildren().add(vb);
        
        if(item.getOrderStatus() == Status.InProgress){
            TimeHelper tm = new TimeHelper(item.getPrepTime(),timeLabel.textProperty(),item.getOrderId());
        
            tm.createThreads();
        }
       
        return hb;
    }
    
    
    
    /*Admin section*/
    
    public static HBox BuildAdminOrderItemHBox(Order item){
        
       HBox hb = new HBox();
       
        VBox vb = new VBox();
        
        vb.setPadding(new Insets(10,10,10,10));
       
        Label nameLabel = new Label("Username: "+item.getUser().getFirstName()+" "+item.getUser().getLastName());
       
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
       
        Label priceLabel = new Label("Price: "+item.getTotalPrice());
        
        priceLabel.setTextFill(Paint.valueOf("WHITE"));
        
        priceLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(priceLabel);
        
        Label itemLabel = new Label(String.join("\n",nameList));
        
        itemLabel.setTextFill(Paint.valueOf("WHITE"));
        
        itemLabel.setPadding(new Insets(10,0,0,0));
       
        innerBox.getChildren().add(itemLabel);
       
        Label timeLabel = new Label(String.valueOf(item.getPrepTime())+" min");
        
        timeLabel.setPadding(new Insets(0,0,0,20));
       
        timeLabel.setAlignment(Pos.CENTER_RIGHT);
        
        timeLabel.setFont(Font.font("Candara"));
       
        timeLabel.setTextFill(Paint.valueOf("#76FF03"));
        
        //timeLabel.setTooltip("time");
        
        innerBox.getChildren().add(timeLabel);
       
        vb.getChildren().add(innerBox);
        
        Label tsLabel = new Label("Date: "+item.getTimeStamp().toString());
        
        tsLabel.setTextFill(Paint.valueOf("WHITE"));
        
        tsLabel.setPadding(new Insets(10,0,0,0));
       
        vb.getChildren().add(tsLabel);
        
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_delete_32px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CustomSceneBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image img2 = new Image(localUrl2);
        
        ImageView imv2 = new ImageView(img2);
        
        imv2.setFitWidth(20);
        imv2.setFitHeight(20);
        
        Button delete = new Button();
        delete.setGraphic(imv2);
//        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
//        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #b71c1c;");
        GeneralHelper.buttonHover(delete, "-fx-background-color:#ef5350", "-fx-background-color: #b71c1c");
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                OrdersHelper oh = new OrdersHelper();
                
                if(oh.Delete(item.getOrderId())){
                    if(AlertDialog("Are you sure you want\nto delete this item?","Delete Item")){
                        ScenesHandler.AlertStage(new Stage());
                    
                        AlertdialogController.showError("Order#: "+item.getOrderNumber()+" has successfully deleted.");

                        RuntimeHelper.loadOrders(ScenesHandler.getDashboardParent());
                    }
                }
            
            }
        });
       
        delete.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        delete.setCursor(Cursor.HAND);
        
        vb.getChildren().add(delete);
        
        hb.getChildren().add(vb);
        
        if(item.getOrderStatus() == Status.InProgress){
            TimeHelper tm = new TimeHelper(item.getPrepTime(),timeLabel.textProperty(),item.getOrderId());
        
            tm.createThreads();
        }
        
       
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
        
        vb.getChildren().add(new Label("In Stock: "+String.valueOf(item.getQuantity())));
        
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
        
        GeneralHelper.buttonHover(edit, "-fx-background-color:#424242; -fx-background-radius: 30px;", "-fx-background-color: #212121; -fx-background-radius: 30px;");
        
        
        //loadOrders
        
        
        Button delete = new Button("Delete");
        delete.setGraphic(imv2);
        delete.setFont(Font.font("Candara", FontPosture.ITALIC,12));
        delete.setTextFill(Paint.valueOf("WHITE"));
        delete.setStyle("-fx-background-color: #212121; -fx-background-radius: 30px;");
        delete.setPrefWidth(88);
        delete.setPrefHeight(25);
        
        GeneralHelper.buttonHover(delete, "-fx-background-color:#424242; -fx-background-radius: 30px;", "-fx-background-color: #212121; -fx-background-radius: 30px;");
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ItemsHelper ihp = new ItemsHelper();
                
                if(AlertDialog("Are you sure you want\nto delete this item?","Delete Item")){
                    if(ihp.Delete(item.getItemId())){
                        RuntimeHelper.loadAdminItems(ScenesHandler.getDashboardParent());
                    }
                }
            }
        });
        
        
        hb.getChildren().add(edit);
        hb.getChildren().add(r);
        hb.getChildren().add(delete);
        
        vb.getChildren().add(hb);
        
        return vb;
    }
    
    
    public static boolean AlertDialog(String message, String title){
        
        if(title == null || title.isEmpty()){
            title = "Confirm";
        }
        
        Stage s = new Stage();
        
        VBox vb = new VBox();
        
        Region vr = new Region();
        
        VBox.setVgrow(vr, Priority.ALWAYS);
        
        Scene scene = new Scene(vb);
        
        s.initModality(Modality.APPLICATION_MODAL);
        
        s.setResizable(false);
        
        s.setMinHeight(180);
        
        s.setMinHeight(100);
        
        s.setTitle(title);
        
        vb.setPrefSize(180, 100);
        
        vb.setPadding(new Insets(10,10,10,10));
        
        vb.setStyle("-fx-background-color: #212121;");
        
        Label msg = new Label(message);
        
        msg.setFont(Font.font("Candara", FontPosture.ITALIC, 12));
        
        msg.setTextFill(Paint.valueOf("WHITE"));
        
        msg.setPrefWidth(180);
        
        msg.setAlignment(Pos.CENTER);
        
        vb.getChildren().add(msg);
        vb.getChildren().add(vr);
        
        HBox hb = new HBox();
        
        Region r = new Region();
        HBox.setHgrow(r,Priority.ALWAYS);
        
        Region r1 = new Region();
        HBox.setHgrow(r1,Priority.ALWAYS);
        
        Region r2 = new Region();
        HBox.setHgrow(r2,Priority.ALWAYS);
        
        hb.setStyle("-fx-background-color:transparent;");
        
        Button yes = new Button("Yes");
        
        Button no = new Button("No");
        
        yes.setFont(Font.font("Candara", FontPosture.ITALIC, 10));
        
        yes.setTextFill(Paint.valueOf("WHITE"));
        
        no.setFont(Font.font("Candara", FontPosture.ITALIC, 10));
        
        no.setTextFill(Paint.valueOf("WHITE"));
        
        yes.setStyle("-fx-background-color:#e53935; -fx-bachround-radius: 30px");
        
        no.setStyle("-fx-background-color:#43A047; -fx-bachround-radius: 30px");
        
        no.setCursor(Cursor.HAND);
        
        yes.setCursor(Cursor.HAND);
        
        yes.setPrefSize(60, 20);
        
        no.setPrefSize(60, 20);
        
        no.onMouseEnteredProperty();
        
        GeneralHelper.buttonHover(yes,"-fx-background-color:#e57373","-fx-background-color:#e53935");
        GeneralHelper.buttonHover(no,"-fx-background-color:#81C784","-fx-background-color:#43A047");
        
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                alertAnswer = false;
                s.close();
            }
        });
        
        
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                alertAnswer = true;
                s.close();
            }
        });
        
        hb.getChildren().add(r1);
        hb.getChildren().add(no);
        hb.getChildren().add(r);
        hb.getChildren().add(yes);
        hb.getChildren().add(r2);
        vb.getChildren().add(hb);
        
        s.setScene(scene);
        
        s.showAndWait();
        
        
        
        return alertAnswer;
    }
    
}
