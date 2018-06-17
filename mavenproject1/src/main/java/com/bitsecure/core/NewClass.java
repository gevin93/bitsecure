/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import java.security.NoSuchAlgorithmException;
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

@ServerEndpoint("/NewClass")
public class NewClass {
  

  
    
    
    @OnOpen
    public void handleOpen()
    {
        System.out.println("clientRSA is now connected...");
    }
    
    
    
    @OnMessage
    public void handleMessage(String message1) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
    {
       // 
          System.out.println("recieve from client..."+message1);
             tally t=new tally();
            t.tally1(message1);
  
   EncryptRSA s=new EncryptRSA();
   
  
   
   filecr c=new filecr();
   c.m(message1);
   
   
   
    }
       
    
    
    
    @OnClose
     public void handleClose()
    {
          System.out.println("clientRSA is now disconnected...");
    }
     
     
     
    
     @OnError
      public void handleError(Throwable t)
    {
        t.printStackTrace();
    }
     
}
