/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import static com.bitsecure.core.Blockchain.blockchain;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author rajeewa
 */
public class compare {
    
    //before copy to all machines compare first block of the copy and the original  
    
public boolean cmp() throws FileNotFoundException, IOException{
   String path="C:\\Users\\user\\Desktop\\bitsecure\\";
    
    ArrayList<Block> blockchain1 = new ArrayList<Block>();
    try {
          FileInputStream fis = new FileInputStream(path+"arraylist");
          
          ObjectInputStream ois = new ObjectInputStream(fis);
         
          blockchain = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
         // e.printStackTrace();
      }

    
       try {
          FileInputStream fis = new FileInputStream(path+"copy_array");
          
          ObjectInputStream ois = new ObjectInputStream(fis);
         
        blockchain1 = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
  String h1,h2;   
boolean tf = true;  
       
for(int i=0;i<blockchain1.size()-1;i++)
{   
    h1=blockchain.get(i).hash;
    h2=blockchain1.get(i).hash;
    
    if(h1 == null ? h2 == null : h1.equals(h2))
    {
      // replace();
        //System.exit(0);
         continue;
        //return true;
       
           
     }
    else
    {
       // System.out.println(blockchain1.get(0).hash);
        //System.out.println(blockchain.get(0).hash);
         replace();
        tf=false;
        break;
    }
   
}


     return tf;
   
}

public void replace() throws FileNotFoundException, IOException
{
    String path="C:\\Users\\user\\Desktop\\bitsecure\\";
    ArrayList<Block> blockchain1 = new ArrayList<Block>();
     try {
          FileInputStream fis = new FileInputStream(path+"copy_array");
          
          ObjectInputStream ois = new ObjectInputStream(fis);
         
        blockchain1 = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
          e.printStackTrace();
      }
    
    
    
    
                FileOutputStream fosq = new FileOutputStream(path+"arraylist");
                ObjectOutputStream oos = new ObjectOutputStream(fosq);
                oos.writeObject(blockchain1);
                oos.close();

}
  

    
}
