/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.bitsecure.core;

import static com.bitsecure.core.Blockchain.blockchain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.NoSuchPaddingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author rajeewa
 */

@ServerEndpoint("/hashcatcher")
public class hashcatcher {
    
    @OnOpen
    public void handleOpen()
    {
        System.out.println("client is now connected...");
    }
    
    
    
    @OnMessage
    public void handleMessage(String message1) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, Exception
    {
       String path="C:\\Users\\user\\Desktop\\bitsecure\\";
         //ArrayList<Block> blockchain = new ArrayList<Block>();
           try {
          FileInputStream fis = new FileInputStream(path+"arraylist");
          
          ObjectInputStream ois = new ObjectInputStream(fis);
         
          blockchain = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
          //e.printStackTrace();
      }
         
     
           System.out.println("recieve to hashc from client..."+message1);
           
           String replymessage="echo "+message1;
           System.out.println("sending to the client"+replymessage);
     
           String message2 = new String(Base64.getDecoder().decode(message1));
          
           decryptPIN dp=new decryptPIN();
           String message=dp.decrypt(message2);
           System.out.println("sending to the client"+message);
           
           
          // System.out.println("sending to the client"+message);
         String w[]=new String[1];
          //w[0]="t";
         //String id;
         
         //////decrypt algo
         int size=blockchain.size();
         
         
         
      if(size==0)
      {
          System.out.println("empty");
          
          
          BufferedWriter output = null;
          //String d=message.toString();
          try {
              ///////////////////////////////////////////////////////////////
              //BufferedWriter output = null;
              try {
                  File file = new File(path,"status");
                  output = new BufferedWriter(new FileWriter(file));
                  output.write("true");
              } catch ( IOException e ) {
                  e.printStackTrace();
              } finally {
                  if ( output != null ) {
                      output.close();
                  }
              }
              //////////////////////////////////////////////////////////////
              File file = new File(path,"hash");
              output = new BufferedWriter(new FileWriter(file));
              output.write(message);
          } catch ( IOException e ) {
              e.printStackTrace();
          } finally {
              if ( output != null ) {
                  output.close();
              }
          }
          
          
          
          
          
          
          
      }
      

     else
      {//System.out.println("com.mycompany.mavenproject1.hashcatcher.handleMessage()");
          
          for(int x=0;x<blockchain.size();x++)
          {
              //System.out.println("com.mycompany.mavenproject1.hashcatcher.handleMessage()");
              //id=blockchain.get(i).id;
              DecryptRSA d=new DecryptRSA();
              String cid=d.decrypt_id(x);
              
              if(!message.equals(cid))
              {
                  w[0]="t";
                  //break;
              }
              else
              {
                  
                  
                  w[0]="f";
                  break;
                  
                  
              }
              
              
          }
          
          if("t".equals(w[0]))
          {
              BufferedWriter output = null;
              //String d=message.toString();
              try {
                  ///////////////////////////////////////////////////////////////
                  //BufferedWriter output = null;
                  try {
                      File file = new File(path,"status");
                     // file.setWritable(true);
                      output = new BufferedWriter(new FileWriter(file));
                      output.write("true");
                  } catch ( IOException e ) {
                      e.printStackTrace();
                  } finally {
                      if ( output != null ) {
                          output.close();
                      }
                  }
                  //////////////////////////////////////////////////////////////
                  File file = new File(path,"hash");
                  output = new BufferedWriter(new FileWriter(file));
                  output.write(message);
              } catch ( IOException e ) {
                  e.printStackTrace();
              } finally {
                  if ( output != null ) {
                      output.close();
                  }
              }
              
              
          }
          else
          {
              System.out.println(message+" has already voted");
              BufferedWriter output = null;
              try {
                  File file = new File(path,"status");
                  output = new BufferedWriter(new FileWriter(file));
                  output.write("fraud");
              } catch ( IOException e ) {
                  e.printStackTrace();
              } finally {
                  if ( output != null ) {
                      output.close();
                  }
              }
              
              //System.exit(0);
              
          }
          
      }
      
                
            
             
             
       
          
              
    }
       
    
    @OnClose
     public void handleClose()
    {
          System.out.println("client is now disconnected...");
    }
     
     
     
    
     @OnError
      public void handleError(Throwable t)
    {
        t.printStackTrace();
    }
    
    
}
