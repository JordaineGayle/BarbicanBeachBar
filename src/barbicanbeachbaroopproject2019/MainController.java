/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbicanbeachbaroopproject2019;

import static barbicanbeachbaroopproject2019.Main.main;
import com.helpers.FileHelper;
import com.scenes.ScenesHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author jorda
 */
public class MainController implements Initializable {
    
    
    
    @FXML
    protected void loginScreen(ActionEvent event) {
        
        ScenesHandler.DashboardStage(new Stage());
    }
    
    @FXML
    protected void registerScreen(ActionEvent event){
        ScenesHandler.RegisterStage(new Stage());
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
