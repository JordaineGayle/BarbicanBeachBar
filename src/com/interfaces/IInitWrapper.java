/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces;

import javafx.fxml.Initializable;

/**
 *
 * @author jorda
 */
public interface IInitWrapper extends Initializable{
    
    public void initBindings();
    
    public void activateListeners();
    
}
