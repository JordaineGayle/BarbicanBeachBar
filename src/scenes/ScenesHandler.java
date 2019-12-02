/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import barbicanbeachbaroopproject2019.Main;
import com.enums.Enums;
import com.helpers.CacheHelper;
import com.helpers.CustomSceneBuilder;
import com.helpers.PathHelper;
import com.helpers.RuntimeHelper;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jordaine Gayle
 */
public class ScenesHandler {
    
    private static Stage login_stage;
    
    private static Stage dashboard_stage;
    
    private static Stage register_stage;
    
    private static Stage item_stage;
    
    private static Stage edit_item_stage;
    
    private static Stage alert_stage;
    
    private static Stage cart_stage;
    
    private static Stage customer_stage;
    
    private static Parent cusParent;
    
    private static Parent dashParent;
    
    
    
    public static void LoginStage(Stage s){
        try{
            
            Parent root = FXMLLoader.load(Main.class.getResource("/views/loginscreen.fxml"));

            Scene scene = new Scene(root);

            s.setTitle("Homepage");
            setIcon(s);
            
            s.setScene(scene);

            s.setResizable(false);

            s.sizeToScene();

            login_stage = s;
            
            s.show();
            

            

        }catch(IOException e){
            
            e.printStackTrace();
        }
    }
   
    
    public static void DashboardStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/dashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            dashParent = root;
            
            
            setIcon(s);
            s.setScene(new Scene(root));  
            s.setTitle("Administrator Dashboard");
            s.setMaximized(true);
            
            s.hide();
            
            s.show();
            
            dashboard_stage = s;
            
            RuntimeHelper.loadItems(root);
            
            //RuntimeHelper.loadOrders(root);
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.exit(0);
                }
            });
            
            login_stage.close();
            
        } catch(IOException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void CustomerStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/customerdash.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            cusParent = root;
            
            s.setScene(new Scene(root));  
            s.setTitle("Customer Dashboard");
            setIcon(s);
            s.setMaximized(true);
            
            s.hide();
            
            s.show();
            
            customer_stage = s;
            
            RuntimeHelper.loadItems(root);
            
            //RuntimeHelper.loadOrders(root);
            //System.out.println(customer_stage);
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.exit(0);
                }
            });
            
            login_stage.close();
            
        } catch(IOException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void RegisterStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/registerscreen.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            setIcon(s);
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Register");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            register_stage = s;
            
//            if(CacheHelper.getUserType() == Enums.UserType.Admin){
//                s.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                    public void handle(WindowEvent we) {
//                        dashboard_stage.hide();
//                        dashboard_stage.close();
//
//                        ScenesHandler.DashboardStage(new Stage());
//                    }
//                });
//            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void ItemStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/additem.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            setIcon(s);
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Add Items");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            item_stage = s;
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                       RuntimeHelper.loadAdminItems(ScenesHandler.getDashboardParent());
                }
            });
            
            
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void EditItemStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/edititem.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            setIcon(s);
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            
            s.setTitle("EditItem");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            edit_item_stage = s;
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {

                       RuntimeHelper.loadAdminItems(ScenesHandler.getDashboardParent());
                }
            });
            
            
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void AlertStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/alertdialog.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            
            setIcon(s);
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("General Message");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if(customer_stage!=null){
                        RuntimeHelper.loadItems(customer_stage.getScene().getRoot());
                    }else if(dashboard_stage != null){
                        RuntimeHelper.loadItems(dashboard_stage.getScene().getRoot());
                    }
                    
                }
            });
            
            alert_stage = s;
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void CartStage(Stage s){
        try {
            
            FXMLLoader fxmlLoader;
            
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/cart.fxml"));
            
            Parent root = (Parent) fxmlLoader.load();
            
            setIcon(s);
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Proceed to purchase");
            
            s.setScene(new Scene(root));
            
            //s.setResizable(false);
            
            s.setMaximized(true);
            
            s.hide();
            
            s.show();
            
            cart_stage = s;
            
            RuntimeHelper.loadItemsInCart(root);
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
//                    customer_stage.hide();
//                    customer_stage.close();
//                    
//                    CustomerStage(new Stage());
                    
                    RuntimeHelper.loadItems(customer_stage.getScene().getRoot());
                }
            });
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Parent getCustomerParent(){
        return cusParent;
    }
    
    public static Parent getDashboardParent(){
        return dashParent;
    }
    
    public static Stage getLoginStage(){
        return login_stage;
    }
    
    public static Stage getDashboardStage(){
        return dashboard_stage;
    }
    
    public static Stage getCustomerStage(){
        return customer_stage;
    }
    
    public static Stage getRegisterStage(){
        return register_stage;
    }
    
    public static Stage getItemStage(){
        return item_stage;
    }
    
    public static Stage getEditItemStage(){
        return edit_item_stage;
    }
    
    public static Stage getAlertStage(){
        return alert_stage;
    }
    
    public static Stage getCartStage(){
        return cart_stage;
    }
    
    private static void setIcon(Stage s){
        String localUrl2 = "";
        File file1 = new File(PathHelper.imageAbsPath+"icons8_blockchain_new_logo_48px.png");
 
        try {
            localUrl2 = file1.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            
        }
        
        Image img2 = new Image(localUrl2);
        
        s.getIcons().add(img2);
    }
    
}
