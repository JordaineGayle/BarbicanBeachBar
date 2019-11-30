/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.enums.Enums;
import com.enums.Enums.UserType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.helpers.CacheHelper;
import com.helpers.FileHelper;
import com.helpers.GeneralHelper;
import com.helpers.UserHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import com.models.User;
import scenes.ScenesHandler;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author JGayle
 */
public class RegisterController implements IInitWrapper, IDisplayUserError {

    @FXML
    private TextField fname = new TextField();
    
    @FXML
    private TextField lname = new TextField();
    
    @FXML
    private TextField email = new TextField();
    
    @FXML
    private TextField phone = new TextField();
    
    @FXML
    private PasswordField pword = new PasswordField();
    
    @FXML
    private Label result = new Label();
    
    private User user;
    
    private StringProperty error = new SimpleStringProperty();
    
    private String errorState = "";
    
    private boolean PasswordValid = false;
    
    private boolean PhoneValid = false;
    
    @FXML
    protected void register(){
        
        user = new User();

        boolean isValidFields = PassedFieldValidation();
        
        if(isValidFields){
            
            setDefault();
            
            
            if(processUserCreation()){
                error.set("Registered Successfully");
            }
        }else{
            if(!errorState.isEmpty()){
                setError(errorState);
            }
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initBindings();
        
        setDefault();
        
        activateListeners();
    }
    
    @Override
    public void initBindings(){
        result.textProperty().bindBidirectional(error);
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
    
    @Override
    public void activateListeners(){
        setEventListener(fname,"Enter a valid first name.");
        setEventListener(lname,"Enter a valid last name.");
        setEventListener(email,"Enter a valid email address.");
        validatePhone();
        validatePassword();
    }
    
    private void setEventListener(TextInputControl text, String msg){
        
        text.textProperty().addListener( (e,oldv,newv)->{
            if("".equals(oldv) || "".equals(newv)){
                
                setError(msg);
            }else{
                setDefault();
            }
        });
        
        
    }
    
    
    
    private boolean PassedFieldValidation(){
        if(fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || pword.getText().isEmpty()){
            setError("All fields are mandatory for this request.");
            
            return false;
        }else{
            if(PasswordValid && PhoneValid){
                return true;
            }
            
            return false;
        }
    }
    
    private boolean validatePassword(){
        
        pword.lengthProperty().addListener((e,oldv,newv)->{
            //System.out.println(newv);
            if(oldv.intValue() < 6 || newv.intValue() < 6){
                setError("Password length must be grater than 6 characters.");
                errorState = "Password length must be grater than 6 characters.";
                PasswordValid = false;
            }else{
                setDefault();
                PasswordValid = true;
            }
        });
        
        return PasswordValid;
    }
    
    private boolean validatePhone(){
        
        phone.lengthProperty().addListener((e,oldv,newv)->{
            //System.out.println(newv);
            if((oldv.intValue() < 10 || oldv.intValue() > 15) || (newv.intValue() < 10 || newv.intValue() > 15)){
                setError("Phone number must be 10 - 15 characters in length.");
                errorState = "Phone number must be 10 - 15 characters in length.";
                PhoneValid = false;
            }else{
                setDefault();
                PhoneValid = true;
            }
        });
        
        return PhoneValid;
    }
    
    private boolean processUserCreation(){
        
        if(CacheHelper.getUserType() == UserType.Admin){
            user = new User(fname.getText(),lname.getText(),email.getText().toLowerCase(),GeneralHelper.EncodeString(pword.getText()),phone.getText(),UserType.Admin);
        }else{
            user = new User(fname.getText(),lname.getText(),email.getText().toLowerCase(),GeneralHelper.EncodeString(pword.getText()),phone.getText(),UserType.Customer);
        }
        
        try{
            UserHelper uhelper = new UserHelper();
            uhelper.AddUser(user);
            return true;
        }catch(Exception e){
            setError(e.getMessage());
            return false;
        }
    }

    
}
