/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.CacheHelper;
import com.helpers.RuntimeHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
    
    @FXML
    private Button delBtn = new Button();
    
    @FXML
    private Button editBtn = new Button();
    
    @FXML 
    private ImageView logout = new ImageView();
    
    @FXML 
    private ImageView addUserBtn = new ImageView();
    
    @FXML 
    private ImageView refreshBtn = new ImageView();
    
    @FXML
    private TextField searcharea = new TextField();
    
    @FXML
    private TilePane tpane = new TilePane();
    
    @FXML
    private VBox vpane = new VBox();
    
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
        activateListeners();
        
    }

    @Override
    public void activateListeners() {
        setListeners();
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
        
        username.textProperty().bindBidirectional(uNameBinder);
        
        usertype.textProperty().bindBidirectional(uTypeBinder);
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
    
    
    @FXML
    private void openAddItemDialog(){
        
        ScenesHandler.ItemStage(new Stage());
        
    }
    
    @FXML
    private void signOff(){
        
        CacheHelper.setUseremail("");
        
        CacheHelper.setUsername("");
        
        CacheHelper.setUsertype(null);
        
        ScenesHandler.getDashboardStage().close();
        
        ScenesHandler.LoginStage(new Stage());
        
    }
    
    @FXML
    private void edit(){
        
    }
    
    @FXML
    private void delete(){
        
    }
    
    @FXML
    private void addUser(){
        
    }
    
    @FXML
    private void refresh(){
        Parent p = tpane.getParent().getParent().getParent();
        RuntimeHelper.loadItemsPartial(p);
        
        Parent vp = vpane.getParent().getParent().getParent();
        RuntimeHelper.loadIOrdersPartial(vp);
    }
    
    
}
