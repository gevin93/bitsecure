/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;




import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author rajeewa
 */
public class Blockchain {

   public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    
    String path="C:\\Users\\user\\Desktop\\bitsecure\\";
    
    

  public void call() throws IOException, NoSuchPaddingException, Exception
  {
      insertion i=new insertion();
      
      
      
      
      // Thread.sleep(1);
      ///////////////////////////////////////////////////////
      StringBuilder sb = new StringBuilder();
      try {
          FileReader textFileReader = new FileReader(path+"vote.txt");
          //save file with another name
          BufferedReader bufReader = new BufferedReader(textFileReader);
          
          char[] buffer = new char[8096];
          
          int numberOfCharsRead = bufReader.read(buffer);
          {
              sb.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
              
          }
          
          while (numberOfCharsRead != -1) {
              sb.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
          }
          
          bufReader.close();
      } catch (IOException f) {
          
          f.printStackTrace();
      }
      String encrName = sb.toString();
      ////////////////////////////////////////////////////////////////////////////////////////////
      /*
      StringBuilder sb1 = new StringBuilder();
      try {
          FileReader textFileReader = new FileReader("/home/rajeewa/Desktop/ledger/genesis_block");
          BufferedReader bufReader = new BufferedReader(textFileReader);
          
          char[] buffer = new char[8096];
          
          int numberOfCharsRead = bufReader.read(buffer);
          {
              sb1.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
              
          }
          
          while (numberOfCharsRead != -1) {
              sb1.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
          }
          
          bufReader.close();
      } catch (IOException f1) {
          
          f1.printStackTrace();
      }
      String gen_hash = sb1.toString();
      */
      /////////////////////////////////////////////////////////////////////////////////////////////
      //  boolean r=blockchain.isEmpty();
      try {
        // FileInputStream fis = new FileInputStream("C:\\Users\\user\\Dropbox\\arraylist");
           FileInputStream fis = new FileInputStream(path+"arraylist");
        //  fis.available();
          ObjectInputStream ois = new ObjectInputStream(fis);
         
          blockchain = (ArrayList) ois.readObject();
          ois.close();
      } catch(Exception e) {
          //e.printStackTrace();
      }
      
      
      
      //take value from file tally
      
      boolean r=blockchain.isEmpty();
      
      if (r==true) {
          /////////////////////////////////////////////////////////////////////////////
           StringBuilder sb1 = new StringBuilder();
           try {
          FileReader textFileReader = new FileReader(path+"genesis_block");
          BufferedReader bufReader = new BufferedReader(textFileReader);
          
          char[] buffer = new char[8096];
          
          int numberOfCharsRead = bufReader.read(buffer);
          {
              sb1.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
              
          }
          
          while (numberOfCharsRead != -1) {
              sb1.append(String.valueOf(buffer, 0, numberOfCharsRead));
              numberOfCharsRead = textFileReader.read(buffer);
          }
          
          bufReader.close();
      } catch (IOException f1) {
          
          f1.printStackTrace();
      }
      String gen_hash = sb1.toString();
          
          
          //////////////////////////////////////////////////////////////////////////////
          
          
          tally t=new tally();
          
         
          blockchain.add(new Block(encrName,"0"));
          System.out.println("Generating block 0");
          blockchain.get(0).mineBlock(difficulty);
          
          try {
             // FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Dropbox\\arraylist");
              FileOutputStream fos = new FileOutputStream(path+"arraylist");
             
              ObjectOutputStream oos = new ObjectOutputStream(fos);
              oos.writeObject(blockchain);
              
               FileOutputStream fos1 = new FileOutputStream( "C:\\Users\\user\\Desktop\\bitsecure\\array_duplicate");
              ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
              oos1.writeObject(blockchain);
              
             
                   APIClient objrmi=new APIClient();
           objrmi.send_blockchain();
            
              oos.close();
              oos1.close();
          
            
        
           
              
              
              
              
           
              i.insert_data();
            
          } catch(Exception e) {
              //e.printStackTrace();
          }
          
          

          System.out.println("block chain valid:"+ isChainValid());
           viewblock v=new viewblock();
                v.viewblock();
          
      
      }

      
      
      else
      {
          
          compare c=new compare();
         boolean st=c.cmp();
        
         if(st==true){
           int s1=blockchain.size();
           
           //Timer t= new Timer();
           //t.run();
  
          blockchain.add(new Block(encrName, blockchain.get(blockchain.size() - 1).hash));
          System.out.println("Generating block "+ s1);
          blockchain.get(s1).mineBlock(difficulty);
          String vote= blockchain.get(s1).data;
         
           
          
         
          if(isChainValid()==true)
          {
            try {
                  
                
                //FileOutputStream fos = new FileOutputStream("C:\\Users\\user\\Dropbox\\arraylist");
                 FileOutputStream fos = new FileOutputStream(path+"arraylist");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(blockchain);
                
              FileOutputStream fos1 = new FileOutputStream( "C:\\Users\\user\\Desktop\\bitsecure\\array_duplicate");
              ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
              oos1.writeObject(blockchain);
              
                APIClient objrmi=new APIClient();
                 objrmi.send_blockchain();
                oos.close(); 
               
            
              
        //      
              
                
              
              FileOutputStream cp = new FileOutputStream(path+"copy_array");
              ObjectOutputStream cp1 = new ObjectOutputStream(cp);
              cp1.writeObject(blockchain);
              cp1.close();
              
               i.insert_data();
              
        
            } catch(Exception e) {
                e.printStackTrace();
             }

                System.out.println("block chain valid:"+ isChainValid());
                viewblock v=new viewblock();
                v.viewblock();
          
         
           }
          else
          {
                System.out.println("block chain valid:"+ isChainValid());
                viewblock v=new viewblock();
                v.viewblock();
          }
         
         }
         else
         {
             System.out.println("blockchain doesn't match with previous one.replacing...\n done!");
             
         }
         
        
      }
      
     
//////////////////////////////////////////////////////
  }
        
        public static Boolean isChainValid() {
            Block currentBlock;
        Block previousBlock;
           
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);//////////
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
  
    }
    
    
}
    
  ///////////////////////////////////////////////////////////////////////////////////////////////////
           
        
        
      /////////////////////////////////////////////////////////
        
    
    
    
    
     
   


