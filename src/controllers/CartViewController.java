/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.helpers.CacheHelper;
import com.helpers.CustomSceneBuilder;
import com.helpers.EmailHelper;
import com.helpers.GeneralHelper;
import com.helpers.ItemsHelper;
import com.helpers.OrdersHelper;
import com.helpers.UserHelper;
import com.interfaces.IInitWrapper;
import com.models.Cart;
import com.models.Item;
import com.models.Order;
import com.models.User;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import scenes.ScenesHandler;

/**
 * FXML Controller class
 *
 * @author jgayle
 */
public class CartViewController implements IInitWrapper {

    @FXML
    private Label total = new Label();
    
    @FXML
    private Label taxes = new Label();
    
    @FXML
    private Label grandtotal = new Label();
    
    @FXML
    private Button orderitem = new Button();
    
    @FXML
    private Button emptyCartBtn = new Button();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Cart> items = ItemsHelper.cartItems;
        
        double gTotal = 0;
        
        for(Cart e : items){
            gTotal+=e.getTotalPrice();
        };
        
        total.setText("Total: USD $"+gTotal);
        
        taxes.setText("Taxes & Fees: 0%");
        
        grandtotal.setText("Grand Total: USD $"+gTotal);
        
        GeneralHelper.buttonHover(orderitem, "-fx-background-color:#43A047; -fx-background-radius: 30px;", "-fx-background-color: #1B5E20; -fx-background-radius: 30px;");
        GeneralHelper.buttonHover(emptyCartBtn, "-fx-background-color:#e53935; -fx-background-radius: 30px;", "-fx-background-color: #b71c1c; -fx-background-radius: 30px;");

    }
    
    @FXML
    private void addOrder(){
        
        ItemsHelper ih = new ItemsHelper();
        
        OrdersHelper oh = new OrdersHelper();
        
        ArrayList<Item> items = ih.GetAll();
        
        ArrayList<Cart> itemsInCart = ih.cartItems;
        
        ArrayList<Item> newItems = new ArrayList<Item>();
        
        int counter = 0;
        
        int totalPrepTime = 0;
        
        double grandTotal = 0;
        
        for(Cart crtI : itemsInCart){
            
            
            
            Item i = null;
            
            for(Item item : items){
                
                if(item.getItemId() == crtI.getItemId()){
                
                    //System.out.println("From cartView Found: "+crtI.getItemName());
                    
                    i = item;
                    break;
                }
                
            }
            
            if(i != null){
                
                //System.out.println("From cartView Access not null: "+crtI.getItemName());
                
                i.setQuantity(i.getQuantity() - crtI.getQuantity());
                
                if(ih.Edit(i)){
                    
                    i.setPrice(crtI.getTotalPrice());
                    
                    i.setQuantity(crtI.getQuantity());
                    
                    newItems.add(i);
                    
                    totalPrepTime += i.getPrepTime();
                    
                    grandTotal += crtI.getTotalPrice();
                    
                    counter++;
                }
                 
            }
            
        }
        
        if(counter > 0){
            
            
            UserHelper uh = new UserHelper();
            
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) 
                  (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            
            String onum = buffer.toString();
            
            Order order = new Order();
            
            User user = uh.getUserByEmail(CacheHelper.getUseremail());
            
            order.setItems(newItems);
            
            order.setUser(user);
            
            order.setTotalPrepTime(totalPrepTime);
            
            order.setTotalPrice(grandTotal);
            
            order.setOrderNumber(onum);
            
            if(oh.Add(order)){
                
                handleCartEmpting();
                
                String subject = "Order Detials: Thank you for doing business with.";
                               
                String to = order.getUser().getEmailAddress();
                
                List<String> nameList = order.getItems().stream().map(Item::getName).collect(Collectors.toList());
                
                Task t = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        String msg = "<div>"
                        + "<b>Order#:</b>"+order.getOrderNumber()+
                        "<br/><b>TotalPrice:</b> USD $"+order.getTotalPrice()+
                        "<br/><b>Total Prepartion Time:</b> "+order.getPrepTime()+" min"+
                        "<br/><b>Items: </b>"+String.join("<br/>",nameList)
                        + "</div>";

                        new EmailHelper(to,subject,msg);

                        ScenesHandler.AlertStage(new Stage());

                        return true;

                    }
                };
                
                new Thread(t).start();
                
                ScenesHandler.AlertStage(new Stage());
                AlertdialogController.showError("Your order has been processed. You will receive an email shortly.\n"+"Your Order#: "+onum);
                
                //System.out.println(t.getValue());
                
            }else{
                ScenesHandler.AlertStage(new Stage());
                AlertdialogController.showError("Failed to process order.");
            }
            
        }else{
            ScenesHandler.AlertStage(new Stage());
            AlertdialogController.showError("Order has already been processed.");
        }
        
        
        
    }
    
    @FXML
    private void emptyCart(){
        if(CustomSceneBuilder.AlertDialog("Are you sure you want to\nEmpty the cart?", "Dispose Cart Items")){
            handleCartEmpting();
            ScenesHandler.AlertStage(new Stage());
            AlertdialogController.showError("Items has been successfully deleted.");
            ScenesHandler.getCartStage().close();
        }
    }
    

    @Override
    public void initBindings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void activateListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void handleCartEmpting(){
        
        ArrayList<Cart> itms = ItemsHelper.cartItems;
        
        ArrayList<Cart> newCart = new ArrayList<Cart>();
        
        for(Cart c : itms){
            
            if(!c.getUserId().equals(CacheHelper.getUseremail())){
                newCart.add(c);
            }
            
        }
        
        ItemsHelper.cartItems = newCart;
        
        GeneralHelper.setCartCount();
        
        
    }
    
    
    
}
