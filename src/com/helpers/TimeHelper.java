/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jorda
 */
public class TimeHelper {
    
    private int counter = 0;
    
    private int secCounter = 0;
    
    private int sec = 60;
    
    private int period = 60000;
    
    private int secPeriod = 1000;
    
    private int orderTime;
    
    private StringProperty display = new SimpleStringProperty();
    
    
    public TimeHelper(int orderTime1, StringProperty sp){
        orderTime = orderTime1;
        sp.bind(display);
    }
    
    public void createThreads(){
        
        Timer timer = new java.util.Timer();

            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                           if(orderTime <= 0){
                               System.out.println("Order Complete");
                               timer.cancel();
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
                                   System.out.println("no sec left");
                                   timer2.cancel();
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
            counter = 0;
        }
        
        counter+=1;
           
    }
    
    private void secHandler(){
        
        if(secCounter == 1){
            sec-=1;
            display.set((orderTime-1 <= 0 ? 0 : orderTime-1)+":"+sec);
            System.out.println(display.get());
            secCounter = 0;
        }
        
        if(sec == 0){
            sec = 60;
        }
        
        secCounter+=1;
    }
    
}
