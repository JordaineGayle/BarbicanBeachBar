/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import com.enums.Enums;
import com.models.Item;
import com.models.Order;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

/**
 *
 * @author Jordaine Gayle
 */
public class TimeHelper {
    
    private int counter = 0;
    
    private int secCounter = 0;
    
    private int sec = 60;
    
    private int period = 60000;
    
    private int secPeriod = 1000;
    
    private int orderTime;
    
    private StringProperty display = new SimpleStringProperty();
    
    private int orderId;
    
    private OrdersHelper oh = new OrdersHelper();
    
    
    public TimeHelper(int orderTime1, StringProperty sp, int orderId1){
        orderTime = orderTime1;
        orderId=orderId1;
        sp.bind(display);
    }
    
    public void createThreads(){
        
        Timer timer = new java.util.Timer();

            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                           if(orderTime <= 0){
                               //System.out.println("Order Complete");
                               Order order = oh.GetSingle(orderId);
                               order.setOrderStatus(Enums.Status.Completed);
                               
                               String subject = "Your order is completed and ready for pickup";
                               
                               String to = order.getUser().getEmailAddress();
                               
                               List<String> nameList = order.getItems().stream().map(Item::getName).collect(Collectors.toList());
                               
                               String msg = "<div>"
                                       + "<b>Order#:</b>"+order.getOrderNumber()+
                                       "<br/><b>TotalPrice:</b> USD $"+order.getTotalPrice()+
                                       "<br/><b>Items: </b>"+String.join("<br/>",nameList)
                                       + "</div>";
                               
                               new EmailHelper("freshcode9@gmail.com",subject,msg);
                               
                               oh.Edit(order);
                               timer.cancel();
                               timer.purge();
                           }
                           minusHandler();
                        }
                    });
                }
                
            }, 0,period);
            
            
            
             Timer timer2 = new java.util.Timer();

                timer2.schedule(new TimerTask() {
                    public void run() {
                        Platform.runLater(new Runnable() {
                            public void run() {
                               if(orderTime <= 0){
                                   timer2.cancel();
                                   timer2.purge();
                               }
                               secHandler();
                            }
                        });

                        
                    }

                }, 0,secPeriod);
    }
    
    private void minusHandler(){

        if(counter==1){
            orderTime-=1;
            
            
            Order item = oh.GetSingle(orderId);
            
            item.setTotalPrepTime(orderTime);
            
            oh.Edit(item);
            
            counter = 0;
        }
        
        counter+=1;
           
    }
    
    private void secHandler(){
        
        if(secCounter == 1){
            sec-=1;
            display.set((orderTime-1 <= 0 ? 0 : orderTime-1)+":"+sec);
            //System.out.println(display.get());
            secCounter = 0;
        }
        
        if(sec == 0){
            sec = 60;
        }
        
        secCounter+=1;
    }
    
}
