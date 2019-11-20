/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scenes;

import barbicanbeachbaroopproject2019.Main;
import barbicanbeachbaroopproject2019.MainController;
import barbicanbeachbaroopproject2019.RegisterController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jgayle
 */
public class ScenesHandler {
    
    private static Stage login_stage;
    
    private static Stage dashboard_stage;
    
    private static Stage register_stage;
    
    public static void LoginStage(Stage s) throws IOException{
        try{
            
            Parent root = FXMLLoader.load(Main.class.getResource("loginscreen.fxml"));

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
            fxmlLoader = new FXMLLoader(Main.class.getResource("/com/dashboard/Dashboard.fxml"));
           // fxmlLoader.setController(MainController.class);
            Parent root = (Parent) fxmlLoader.load();
            
            s.setScene(new Scene(root));  
            
            s.show();
            
            dashboard_stage = s;
            
            login_stage.close();
            
        } catch(IOException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void RegisterStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("registerscreen.fxml"));
            //fxmlLoader.setController(RegisterController.class);
            Parent root = (Parent) fxmlLoader.load();
            
            s.initModality(Modality.APPLICATION_MODAL);
            
            s.setTitle("Register");
            
            s.setScene(new Scene(root));
            
            s.setResizable(false);

            s.sizeToScene();
            
            s.show();
            
            register_stage = s;
            
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
    
    public static Stage getRegisterStage(){
        return register_stage;
    }
    
    
}
