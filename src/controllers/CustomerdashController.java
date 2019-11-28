/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.ItemsHelper;
import com.interfaces.IDisplayUserError;
import com.interfaces.IInitWrapper;
import com.models.Cart;
import com.models.Item;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import scenes.ScenesHandler;

/**
 * FXML Controller class
 *
 * @author jgayle
 */
public class CustomerdashController implements IInitWrapper, IDisplayUserError {

    @FXML
    private Label cartcount = new Label();
    
    @FXML
    private Button cardadd = new Button();
    
    @FXML
    private ImageView viewcartitems = new ImageView();
    
    @FXML
    private TilePane tpane = new TilePane();
    
    private StringProperty error = new SimpleStringProperty();
    
    public static StringProperty customerDashIntProp = new SimpleStringProperty();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBindings();
    }    

    @Override
    public void initBindings() {
        cartcount.textProperty().bindBidirectional(customerDashIntProp);
    }

    @Override
    public void activateListeners() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ItemsHelper.cartItems.forEach(e -> System.out.println(e.getQuantity())); 
        
    }
    
    
}
