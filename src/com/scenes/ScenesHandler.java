/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scenes;

import barbicanbeachbaroopproject2019.Main;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jgayle
 */
public class ScenesHandler {
    
    private static Stage login_stage;
    
    private static Stage dashboard_stage;
    
    public static void LoginStage(Stage s) throws IOException{
        try{
            
        Parent root = FXMLLoader.load(Main.class.getResource("loginscreen.fxml"));
        
        Scene scene = new Scene(root);
        
        s.setScene(scene);
        s.setResizable(false);
        s.sizeToScene();
        s.show();
        
        login_stage = s;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void DashboardStage(Stage s){
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader(Main.class.getResource("/com/dashboard/Dashboard.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            s.setScene(new Scene(root));  
            
            s.show();
            
            login_stage = s;
            
            dashboard_stage.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Stage getLoginStage(){
        return login_stage;
    }
    
    public Stage getDashboardStage(){
        return dashboard_stage;
    }
    
    
}
