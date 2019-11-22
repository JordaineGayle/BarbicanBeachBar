/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbicanbeachbaroopproject2019;

import com.enums.Enums;
import com.enums.Enums.UserType;
import com.helpers.CacheHelper;
import com.helpers.UserHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import com.scenes.ScenesHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jorda
 */
public class LoginController implements IInitWrapper,IDisplayUserError {

    @FXML
    private TextField username = new TextField();
    
    @FXML
    private TextField password = new PasswordField();
    
    @FXML
    private Label result = new Label();
    
    @FXML
    private RadioButton admin = new RadioButton();
    
    @FXML
    private RadioButton customer = new RadioButton();
    
    private StringProperty error =  new SimpleStringProperty();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initBindings();
        
        setDefault();
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
        
        UserHelper uhelper = new UserHelper();
        
        UserType utype = getUsertype();
        
        if(utype == null){
            setError("Please select a valid user type.");
        }else{
            
            try{
                
                uhelper.loginUser(username.getText(), password.getText(),utype);
                      
                error.set("Logged in successfully....Please wait");
                
                ScenesHandler.DashboardStage(new Stage());
                
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
