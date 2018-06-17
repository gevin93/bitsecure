/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import static com.bitsecure.core.Blockchain.blockchain;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author rajeewa
 */
public class tally {
    
    public void tally1(String msg) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
    {
        String path="C:\\Users\\user\\Desktop\\bitsecure\\";
         
        Connection conn=null;
         Class.forName("com.mysql.jdbc.Driver");
        try {
             String selectc;
             
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/result","root","");
            
              String query = "SELECT candi_id FROM `res_table`";
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(query);
               //String rs=null;
                
        
           List arr=new ArrayList();
while (rs.next()) {
    arr.add(rs.getString(1));
} 


int[] tallycount=new int[arr.size()];


 try {
          FileInputStream fis = new FileInputStream(path+"arraylist");
          
          ObjectInputStream ois = new ObjectInputStream(fis);
         
          blockchain = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
          //e.printStackTrace();
      }

       boolean r=blockchain.isEmpty();
      
      if (r==true)
      {
         for(int i=0;i<arr.size();i++)
          {
              
              
              selectc=(String) arr.get(i);
                if(selectc.equals(msg))
                {
                    tallycount[i]++;
                }
                
          }
         StringBuilder builder = new StringBuilder();

        for (Integer integer : tallycount) {
            if (builder.length() > 0) {
                builder.append(",");
         }
         builder.append(integer);
        }
          String tallyresult = builder.toString();
            
              
          
                     EncryptRSA ac = new EncryptRSA();
		//PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		PublicKey publicKey = ac.getPublic(path+"publicKey");
		
		//String msg = "saman gunwardhana";
		String encrypted_tally = ac.encryptText(tallyresult,publicKey);
                //take this string to a file.and get that encrypted msg into the blockchain by reading the text file
               
                
                
                
                
                BufferedWriter output = null;
          
                    try {
                      //  File file = new File(";
                        output = new BufferedWriter(new FileWriter(path+"tally"));
                        output.write(encrypted_tally);
                        } catch ( IOException e ) {
                        e.printStackTrace();
                       } finally {
                      if ( output != null ) {
                       output.close();
                    }
                    }
                
                // int s1=blockchain.size();
                
                //encrypted_tally= blockchain.add(s1).result;
        }
      
      else
      {
           DecryptRSA d=new DecryptRSA();
           String d_res=d.resout();
                
           String[] data = d_res.split(",");
        
            for(int i=0;i<data.length;i++)
          {
              selectc=(String) arr.get(i);
                if(selectc.equals(msg))
                {
                    
                    String n=data[i];
                    int n1=Integer.parseInt(n);
                    n1++;
                    String ns=Integer.toString(n1);
                    data[i]=ns;
                    
                }
                
          }
          
         StringBuilder builder = new StringBuilder();

        for (String string : data) {
            if (builder.length() > 0) {
                builder.append(",");
         }
         builder.append(string);
        }
          String tallyresult = builder.toString();
           
        
                 EncryptRSA ac = new EncryptRSA();
		//PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		PublicKey publicKey = ac.getPublic(path+"publicKey");
		
		//String msg = "saman gunwardhana";
		String encrypted_tally = ac.encryptText(tallyresult,publicKey);
                //take this string to a file.and get that encrypted msg into the blockchain by reading the text file
               
                
                BufferedWriter output = null;
          
                    try {
                      //  File file = new File(";
                        output = new BufferedWriter(new FileWriter(path+"tally"));
                        output.write(encrypted_tally);
                        } catch ( IOException e ) {
                        e.printStackTrace();
                       } finally {
                      if ( output != null ) {
                       output.close();
                    }
                    }

                
                
                
             
                
                
       
           
      }
 
 


        }catch (Exception e) {
          e.printStackTrace();
        }
        
   ///////////////////////////////////////////////////////////////////////     
        
        
        
        
      

                
                
                
             
                
                
       
           
      }
   
    }
            

