/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author jorda
 */
public class FileHelper {
    
    private static String absolutePath = "/database/structure/files/";
    
    public static int Write(String data, String fileName)throws IOException {
        
        try{
            
            absolutePath+=fileName;
        
            String path = absolutePath;
        
            FileOutputStream os = new FileOutputStream(path);
        
            byte[] strToBytes = data.getBytes();
        
            os.write(strToBytes);
 
            os.close();
        
            return 0;
            
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
