/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.GeneralHelper;
import com.helpers.ItemsHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import com.models.Item;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author jgayle
 */
public class ItemController implements IInitWrapper, IDisplayUserError {

    @FXML
    private TextField itemname = new TextField();
    
    @FXML 
    private TextField imageurl = new TextField();
    
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
    
    //private ItemsHelper ihelper = new ItemsHelper();
    
    private boolean itemExist;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBindings();
        activateListeners();
    }
    
    @Override
    public void initBindings() {
        result.textProperty().bindBidirectional(error);
    }

    @Override
    public void activateListeners() {
        itemNameExistListener();
        setEventListener(preptime,"please set a valid prepartion time.");
        setEventListener(price,"please set a valid price.");
        setEventListener(quantity,"please set a valid quantity.");
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
    
    
    @FXML
    private void save(){
        
        boolean validated = validateInputs();
     
        if(validated){
            ItemsHelper ihelper = new ItemsHelper();
            
            Item item = new Item(0, itemname.getText(),imageurl.getText(),Double.parseDouble(price.getText()), Integer.parseInt(preptime.getText()), Integer.parseInt(quantity.getText()));
            
            boolean added = ihelper.Add(item);
            
            if(added){
                setDefault();
                error.set("Added successfully!");
            }else{
                setError("Failed to add item.");
            }
            
        }else{
            setError("Please correct all issues befor submitting form.");
        }
    }
    
    private void setEventListener(TextInputControl text, String msg){
        
        text.textProperty().addListener( (e,oldv,newv)->{
            if(!GeneralHelper.isNumeric(oldv) || !GeneralHelper.isNumeric(newv)){
                
                setError(msg);
            }else{
                setDefault();
            }
        });
        
        
    }
    
    private void itemNameExistListener(){
        itemname.textProperty().addListener( (e,oldv,newv)->{
            checkItemValidity(newv);

        });
    }
    
    private boolean validateInputs(){
        
        if(!GeneralHelper.isNumeric(preptime.getText())){
            
            
            
            return false;
        }else{
            int i = Integer.parseInt(preptime.getText());
            
            if(i < 0){
                return false;
            }
        }
        
        if(!GeneralHelper.isNumeric(price.getText())){
            
            return false;
        }else{
            double i = Double.parseDouble(price.getText());
            
            if(i < 0){
                return false;
            }
        }
        
        if(!GeneralHelper.isNumeric(quantity.getText())){
            
            return false;
        }else{
            int i = Integer.parseInt(quantity.getText());
            
            
            if(i < 0){
                return false;
            }
        }
        
        if(checkItemValidity(itemname.getText())){
            return false;
        }
        
        return true;
    }
    
    
    private boolean checkItemValidity(String str){
        ItemsHelper ihelper = new ItemsHelper();
        ArrayList<Item> items = ihelper.GetAll();
        
        
        if(items!=null){
            
            long totalMatch = items.stream().filter((w)-> w.getUniqueName().equals(GeneralHelper.EncodeString(str.toLowerCase()))).count();
            //System.out.println(totalMatch);
            if(totalMatch > 0){
                setError("item name already exist.");
                return true;
            }else{
                setDefault();
                return false;
            }
        }
        
        return false;
        
    }
}
