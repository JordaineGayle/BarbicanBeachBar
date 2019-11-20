/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbicanbeachbaroopproject2019;

import com.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author jorda
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField fname;
    
    @FXML
    private TextField lname;
    
    @FXML
    private TextField email;
    
    @FXML
    private TextField phone;
    
    @FXML
    private PasswordField pword;
    
    @FXML
    private Label result;
    
    private User user;
    
    private StringProperty error = new SimpleStringProperty();
    
    
    @FXML
    protected void register(){
        
        user = new User();

        if(fname.getText().equalsIgnoreCase("") || fname.getText()==null){
            error.set("Please enter a valid Firstname.");
            result.setText("Please enter a valid Firstname.");
            System.out.println(error.get());
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fname = new TextField();
        result = new Label();
    }
    
}
