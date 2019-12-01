/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.enums.Enums;
import com.enums.Enums.UserType;
import com.google.gson.reflect.TypeToken;
import com.helpers.CacheHelper;
import com.helpers.GeneralHelper;
import com.helpers.UserHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import scenes.ScenesHandler;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Jordaine Gayle
 */
public class LoginController implements IInitWrapper,IDisplayUserError {

    @FXML
    private TextField username = new TextField();
    
    @FXML
    private TextField password = new PasswordField();
    
    @FXML
    private Label result = new Label();
    
    @FXML
    private Button loginBtn = new Button();
    
    @FXML
    private RadioButton admin = new RadioButton();
    
    @FXML
    private Button regbtn = new Button();
    
    @FXML
    private RadioButton customer = new RadioButton();
    
    private StringProperty error =  new SimpleStringProperty();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initBindings();
        
        setDefault();
        
        GeneralHelper.buttonHover(regbtn, "-fx-background-color:#43A047; -fx-background-radius: 30px;", "-fx-background-color: #1B5E20; -fx-background-radius: 30px;");
        GeneralHelper.buttonHover(loginBtn, "-fx-background-color:#EEEEEE; -fx-background-radius: 30px;", "-fx-background-color:#fff; -fx-background-radius: 30px;");

    }
    
    @Override
    public void initBindings(){
        result.textProperty().bindBidirectional(error);
    }

    @Override
    public void setError(String msg) {
        result.setStyle("-fx-text-fill: #fff");
        error.set("*"+msg+"*");
    }

    @Override
    public void setDefault() {
        result.setStyle("-fx-text-fill: #fff");
        error.set("");
    }
    
    @FXML
    protected void login(){
       //ScenesHandler.CustomerStage(new Stage());
        UserHelper uhelper = new UserHelper();
        
        UserType utype = getUsertype();
        
        if(utype == null){
            setError("Please select a valid userType.");
        }else{
            
            try{
                
                uhelper.loginUser(username.getText(), password.getText(),utype);
                
                error.set("Logged in successfully....Please wait");
                
                if(utype == UserType.Admin){
                    
                    ScenesHandler.DashboardStage(new Stage());
                }else{
                    ScenesHandler.CustomerStage(new Stage());
                }
                
            }catch(Exception e){
                setError(e.getMessage());
            }
            
        }
    }
    
    @FXML
    protected void registerScreen(ActionEvent event){
        ScenesHandler.RegisterStage(new Stage());
    }
    
    @Override
    public void activateListeners() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private UserType getUsertype(){
        if(admin.isSelected()){
            return UserType.Admin;
        }else if(customer.isSelected()){
            return UserType.Customer;
        }else{
            return null;
        }
    }
}
