/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.CacheHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import scenes.ScenesHandler;

/**
 * FXML Controller class
 *
 * @author jgayle
 */
public class DashboardController implements IInitWrapper, IDisplayUserError {

    @FXML
    private Label username = new Label();
    
    @FXML
    private Label usertype = new Label();
    
    @FXML
    private Button additemBtn = new Button();
    
    
    private StringProperty uNameBinder = new SimpleStringProperty();
    
    private StringProperty uTypeBinder = new SimpleStringProperty();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBindings();
    }    

    @Override
    public void initBindings() {
        
        initBinders();
        
        username.textProperty().bindBidirectional(uNameBinder);
        
        usertype.textProperty().bindBidirectional(uTypeBinder);
    }

    @Override
    public void activateListeners() {
        
    }

    @Override
    public void setError(String msg) {
       
    }

    @Override
    public void setDefault() {
        
    }
    
    private void initBinders(){
        
        uNameBinder.set(CacheHelper.getUsername());
        
        uTypeBinder.set(CacheHelper.getUserType().toString());
    }
    
    
    @FXML
    private void openAddItemDialog(){
        
        ScenesHandler.ItemStage(new Stage());
        
    }
}
