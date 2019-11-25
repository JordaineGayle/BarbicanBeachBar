/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbicanbeachbaroopproject2019;

import scenes.ScenesHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jorda
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
            
            ScenesHandler.LoginStage(new Stage());
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
