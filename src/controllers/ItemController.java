/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author jgayle
 */
public class ItemController implements IInitWrapper, IDisplayUserError {

    @FXML
    private TextField itemname = new TextField();
    
    @FXML
    private TextField preptime = new TextField();
    
    
    @FXML
    private TextField price = new TextField();
    
    
    @FXML
    private TextField quantity = new TextField();
    
    
    @FXML
    private Button savebtn = new Button();
    
    @FXML
    private Label result = new Label();
    
    private StringProperty error =  new SimpleStringProperty();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBindings();
    }
    
    @Override
    public void initBindings() {
        result.textProperty().bindBidirectional(error);
    }

    @Override
    public void activateListeners() {
        itemNameExistListener();
    }

    @Override
    public void setError(String msg){
        result.setStyle("-fx-text-fill: #b71c1c");
        error.set("*"+msg+"*");
    }
    
    @Override
    public void setDefault(){
        result.setStyle("-fx-text-fill: #1B5E20");
        error.set("");
    }
    
    private void save(){
        
        
        
    }
    
    
    private void itemNameExistListener(){
        
        
        
        itemname.textProperty().addListener( (e,oldv,newv)->{
            
        });
        
    }
    
}
