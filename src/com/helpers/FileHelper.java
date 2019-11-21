/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author jorda
 */
public class FileHelper {
    
    private String absolutePath = Paths.get("").toAbsolutePath().toString()+"/src/database/structure/files/";
    
    public boolean create(String path) throws IOException{
        try{
            
            File file = new File(path);
            
            return file.createNewFile();
            
        }catch(Exception e){
            return false;
        }
    }
    
    public int write(String data, String fileName)throws IOException {
        
        try{
            
            
            absolutePath+=fileName;
        
            String path = absolutePath;
            
            File file = new File(path);
            
            if(!file.exists()){
                create(path);
            }
            
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
    
    public String read(String fileName)throws IOException {
        
        try{
            
            String line = "";
            
            String data = "";
            
            absolutePath+=fileName;
        
            String path = absolutePath;
            
            File file = new File(path);
            
            if(!file.exists()){
                create(path);
            }
            
            FileReader fileReader = new FileReader(path);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                data+=line;
            }   

            // Always close files.
            bufferedReader.close();
        
            return data;
            
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
