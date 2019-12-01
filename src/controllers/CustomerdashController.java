/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.CacheHelper;
import com.helpers.ItemsHelper;
import com.helpers.RuntimeHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import com.models.Cart;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.ScenesHandler;

/**
 * FXML Controller class
 *
 * @author jgayle
 */
public class CustomerdashController implements IInitWrapper, IDisplayUserError {

    @FXML
    private Label username = new Label();
    
    @FXML
    private Label usertype = new Label();
    
    @FXML
    private Label cartcount = new Label();
    
    @FXML
    private Button refreshBtn = new Button();
    
    @FXML
    private Button cardadd = new Button();
    
    @FXML 
    private ImageView logout = new ImageView();
    
    @FXML
    private TextField searcharea = new TextField();
    
    @FXML
    private ImageView viewcartitems = new ImageView();
    
    @FXML
    private VBox vpane = new VBox();
    
    @FXML
    private TilePane tpane = new TilePane();
    
    private StringProperty error = new SimpleStringProperty();
    
    public static StringProperty customerDashIntProp = new SimpleStringProperty();
    
    private StringProperty uNameBinder = new SimpleStringProperty();
    
    private StringProperty uTypeBinder = new SimpleStringProperty();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBindings();
        activateListeners();
    }    

    @Override
    public void initBindings() {
        uNameBinder.set(CacheHelper.getUsername());
        
        uTypeBinder.set(CacheHelper.getUserType().toString());
        cartcount.textProperty().bindBidirectional(customerDashIntProp);
        username.textProperty().bindBidirectional(uNameBinder);
        
        usertype.textProperty().bindBidirectional(uTypeBinder);
        
    }

    @Override
    public void activateListeners() {
        setListeners();
    }
    
    private void setListeners(){
        
        searcharea.textProperty().addListener((e,oldv,newv) -> {
        
            Parent p = tpane.getParent().getParent().getParent();
            
            if(!newv.isEmpty()){
                
                List<String> str = Arrays.asList(newv.toLowerCase().split(","));
                
                RuntimeHelper.searchItems(p,str);
                
            }else{
                
                RuntimeHelper.loadItemsPartial(p);
            }
            
            
        });
        
    }

    @Override
    public void setError(String msg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDefault() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void viewcart(){
        
        if(ItemsHelper.cartItems.size() <= 0){
            
            ScenesHandler.AlertStage(new Stage());
            AlertdialogController.showError("Cart is empty.");
        }else{
            
            ScenesHandler.CartStage(new Stage());
            
            ItemsHelper.cartItems.forEach(e -> System.out.println(e.getQuantity())); 
        }
        
    }
    
    @FXML
    private void signOff(){
        
        CacheHelper.setUseremail("");
        
        CacheHelper.setUsername("");
        
        CacheHelper.setUsertype(null);
        
        CustomerdashController.customerDashIntProp.set("");
        
        ScenesHandler.getCustomerStage().close();
        
        ScenesHandler.LoginStage(new Stage());
        
    }
    
    @FXML
    private void refreshScreen(){
        Parent p = tpane.getParent().getParent().getParent();
        RuntimeHelper.loadItemsPartial(p);
        
        Parent vp = vpane.getParent().getParent().getParent();
        RuntimeHelper.loadIOrdersPartial(vp);
    }
    
    
    
    
}
