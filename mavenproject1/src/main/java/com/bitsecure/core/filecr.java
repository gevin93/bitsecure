/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author rajeewa
 */
public class filecr {
 
        
	
    
            
               
   
      public void m(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception{
            String path="C:\\Users\\user\\Desktop\\bitsecure\\";
            ////////////////////////////////////////////////////////////
		EncryptRSA ac = new EncryptRSA();
		//PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		PublicKey publicKey = ac.getPublic(path+"publicKey");
		
		//String msg = "saman gunwardhana";
		String encrypted_msg = ac.encryptText(msg,publicKey);
		//String decrypted_msg = ac.decryptText(encrypted_msg, privateKey);
		//System.out.println("Original Message: " + msg + "\nEncrypted Message: " + encrypted_msg + "\nDecrypted Message: " + decrypted_msg);
		
                
                          StringBuilder sb=new StringBuilder();
try { 
    FileReader textFileReader = new FileReader(path+"hash"); 
    BufferedReader bufReader = new BufferedReader(textFileReader);

 

    char[] buffer = new char[8096];


    int numberOfCharsRead = bufReader.read(buffer); 
     {
        sb.append(String.valueOf(buffer, 0, numberOfCharsRead)); 
       numberOfCharsRead = textFileReader.read(buffer);

    } 

    while (numberOfCharsRead != -1) 
    { 
        sb.append(String.valueOf(buffer, 0, numberOfCharsRead)); 
        numberOfCharsRead = textFileReader.read(buffer); 
    }

    bufReader.close();
    } 

    catch (IOException e) 
    { 

        e.printStackTrace();
    }

            String msg1 =  sb.toString();
            
                 BufferedWriter output = null;
        try {
            File file = new File(path,"vote");
            output = new BufferedWriter(new FileWriter(file));
            output.write(encrypted_msg);
              
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
        
      Blockchain b=new Blockchain();
                b.call();
        
      }      
        
}
