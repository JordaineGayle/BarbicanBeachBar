/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jgayle
 */
public class AlertdialogController implements Initializable {

    @FXML
    private Label result = new Label();
    
    private static StringProperty alertError = new SimpleStringProperty();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        result.textProperty().bind(alertError);
    }    
    
    public static void showError(String str){
        alertError.set(str);
    }
    
}
