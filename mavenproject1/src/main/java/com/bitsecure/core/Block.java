package com.bitsecure.core;

//import static blockchain.Blockchain.blockchain;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Date;
import javax.crypto.NoSuchPaddingException;

public class Block implements Serializable{
    


 
	public String hash;
	public String previousHash; 
	public String data; //our data will be a simple message.
         public String result;
         public String id;
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
       
    
	
	//Block Constructor.  
	public Block(String data,String previousHash ) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception {
            ////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////
            String path="C:\\Users\\user\\Desktop\\bitsecure\\";
                
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
                     //String cid="0";
                    e.printStackTrace();
                }

            String cid =  sb.toString();
               EncryptRSA ac = new EncryptRSA();
		//PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
		PublicKey publicKey = ac.getPublic(path+"publicKey");
		
		//String msg = "saman gunwardhana";
		String encrypted_cid = ac.encryptText(cid,publicKey);
            ////////////////////////////////////////////////
            
              StringBuilder sb1=new StringBuilder();
            try { 
                    FileReader textFileReader = new FileReader(path+"tally"); 
                    BufferedReader bufReader = new BufferedReader(textFileReader);

 

                    char[] buffer = new char[8096];


                    int numberOfCharsRead = bufReader.read(buffer); 
                    {
                     sb1.append(String.valueOf(buffer, 0, numberOfCharsRead)); 
                     numberOfCharsRead = textFileReader.read(buffer);

                    } 

                    while (numberOfCharsRead != -1) 
                    { 
                        sb1.append(String.valueOf(buffer, 0, numberOfCharsRead)); 
                        numberOfCharsRead = textFileReader.read(buffer); 
                    }

                    bufReader.close();
                 } 

                catch (IOException e) 
                { 
                     //String cid="0";
                    e.printStackTrace();
                }

            String tally =  sb1.toString();

             ///////////////////////////////////////////////////
                
                
                this.id=encrypted_cid;
		this.data = data;
                this.result=tally;
		this.previousHash = previousHash;
		this.timeStamp =new Date().getTime();
                
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data + result 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty)
        {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
                System.out.println("{");
		System.out.println("hash: " + hash);
                System.out.println("previous hash: " + previousHash);
                System.out.println("data: "+data);
                System.out.println("result: "+result);
                System.out.println("id: "+id);
                System.out.println("timestamp: "+timeStamp);
                System.out.println("nonce: "+nonce);
                System.out.println("}");
                
                
                
                
                
	}
        
}

