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
 * @author //Amoy Scarlett
 */
public class FileHelper {
    
    private String absolutePathOriginal = PathHelper.fileAbsPath;
    
    private String absolutePath = PathHelper.fileAbsPath;
    
    private String fname;
    
    public FileHelper(){
        fname = "";
    }
    
    public FileHelper(String fileName){
        this.fname = fileName;
    }
    
    
    public boolean create() throws IOException{
        try{
            
            File dir = new File(absolutePathOriginal);
            
            if (!dir.exists()){
                dir.mkdirs();
            }
            
            File file = new File(absolutePath+fname);
            
            return file.createNewFile();
            
        }catch(Exception e){
            return false;
        }
    }
    
    public int write(String data)throws IOException {
        
        try{
            
            String path = absolutePath+fname;
            
            File file = new File(path);
            
            if(!file.exists()){
                create();
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
    
    public String read()throws IOException {
        
        try{
            
            String line = "";
            
            String data = "";
            
            String path = absolutePath+fname;;
            
            File file = new File(path);
            
            if(!file.exists()){
                create();
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
    
    public void setFileName(String fname){
        this.fname = fname;
    }
    
    public String getFileName(){
        return absolutePath;
    }
}
