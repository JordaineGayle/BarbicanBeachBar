/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import barbicanbeachbaroopproject2019.Main;
import com.enums.Enums;
import com.helpers.CacheHelper;
import com.helpers.RuntimeHelper;
import controllers.RegisterController;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author jgayle
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
    
    public static void LoginStage(Stage s){
        try{
            
            Parent root = FXMLLoader.load(Main.class.getResource("/views/loginscreen.fxml"));

            Scene scene = new Scene(root);

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
            
            s.setScene(new Scene(root));  
            
            s.show();
            
            dashboard_stage = s;
            
            RuntimeHelper.loadAdminItems(root);
            
            RuntimeHelper.loadOrders(root);
            
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
            
            s.setScene(new Scene(root));  
            
            s.setMaximized(true);
            
            s.hide();
            
            s.show();
            
            customer_stage = s;
            
            RuntimeHelper.loadItems(root);
            
            RuntimeHelper.loadOrders(root);
            //System.out.println(customer_stage);
            
            login_stage.close();
            
        } catch(IOException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void RegisterStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/registerscreen.fxml"));
            //fxmlLoader.setController(RegisterController.class);
            Parent root = (Parent) fxmlLoader.load();
            
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
            //fxmlLoader.setController(RegisterController.class);
            Parent root = (Parent) fxmlLoader.load();
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Add Items");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            item_stage = s;
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    dashboard_stage.hide();
                    dashboard_stage.close();
                    
                    ScenesHandler.DashboardStage(new Stage());
                }
            });
            
            
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void EditItemStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/views/additem.fxml"));
            //fxmlLoader.setController(RegisterController.class);
            Parent root = (Parent) fxmlLoader.load();
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Edit Item");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            edit_item_stage = s;
            
            s.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    dashboard_stage.hide();
                    dashboard_stage.close();
                    
                    ScenesHandler.DashboardStage(new Stage());
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
            //fxmlLoader.setController(RegisterController.class);
            Parent root = (Parent) fxmlLoader.load();
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Warning !");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
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
                    customer_stage.hide();
                    customer_stage.close();
                    
                    CustomerStage(new Stage());
                }
            });
            
        } catch(IOException e) {
            e.printStackTrace();
        }
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
    
}
