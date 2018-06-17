/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rajeewa
 */
public class insertion {
    
    public void insert_data() throws FileNotFoundException, IOException, ClassNotFoundException, Exception{
        
        String path="C:\\Users\\user\\Desktop\\bitsecure\\";
        
            FileInputStream fis = new FileInputStream(path+"arraylist");

            ObjectInputStream ois = new ObjectInputStream(fis);

            viewblock.blockchain = (ArrayList) ois.readObject();
            ois.close();
            
           int last_index= viewblock.blockchain.size()-1;
           String data = viewblock.blockchain.get(last_index).data;
        
        BufferedWriter output = null;  
        try{
       
            output = new BufferedWriter(new FileWriter(path+"setdata"));
            output.write(data);
        } catch ( IOException e ) {
                        e.printStackTrace();
                       } finally {
                      if ( output != null ) {
                       output.close();
                    }

        }
        
        DecryptRSA d=new DecryptRSA();
        String d_msg=d.m();
        
        //write to database
  //////////////////////////////////////////////////////////////////////////////////////////////
        Connection conn=null;
         Class.forName("com.mysql.jdbc.Driver");
        try {
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/result","root","");
            
              String query = "SELECT * FROM `res_table` WHERE `candi_id`='"+d_msg+"'";
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(query);
               //String rs=null;
                
         if(rs.next())
         {
          
            
            String query2 = "SELECT * FROM `res_table` WHERE `candi_id`='"+d_msg+"'";
            Statement st1 = conn.createStatement();
            ResultSet rs1= st1.executeQuery(query2);
            
         if(rs1.next()){
             String q= rs1.getObject(2).toString();
             int val=Integer.parseInt(q);
             int ans=val+1;
             String answ=Integer.toString(ans);
             
             String sql2="update `res_table` set vote='"+answ+"' where candi_id='"+d_msg+"'";
             
             
             PreparedStatement pst = conn.prepareStatement(sql2);
             pst.execute();
            }
         
         }
         
         else
         {
            String sql="INSERT INTO `res_table` (`candi_id`, `vote`) VALUES ('"+d_msg+"','1');";
             
             Statement st2 = conn.createStatement();
             st2.executeUpdate(sql);        
            
         }
            
            
            
            
            
        } catch (Exception e) {
          e.printStackTrace();
        }finally{conn.close();}
      
      
      
    }
    
    
}
