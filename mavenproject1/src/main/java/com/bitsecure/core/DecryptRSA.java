package com.bitsecure.core;

import static com.bitsecure.core.Blockchain.blockchain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class DecryptRSA {
    String path="C:\\Users\\user\\Desktop\\bitsecure\\";
	private Cipher cipher;
	
	public DecryptRSA() throws NoSuchAlgorithmException, NoSuchPaddingException{
		this.cipher = Cipher.getInstance("RSA");
	}
	//https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
	public PrivateKey getPrivate(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}
	//https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
	/*
        public PublicKey getPublic(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
	*/
        /*
	public void encryptFile(byte[] input, File output,PublicKey key ) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
    }
        */
	
	public void decryptFile(byte[] input, File output,PrivateKey key ) throws IOException, GeneralSecurityException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		writeToFile(output, this.cipher.doFinal(input));
    }
	
	private void writeToFile(File output, byte[] toWrite) throws IllegalBlockSizeException, BadPaddingException, IOException{
		FileOutputStream fos = new FileOutputStream(output);
		fos.write(toWrite);
		fos.flush();
		fos.close();
	}
	
        /*
	public String encryptText(String msg,  PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
	}
        */
	
	public String decryptText(String msg, PrivateKey key) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
	}
	
	public byte[] getFileInBytes(File f) throws IOException{
		FileInputStream fis = new FileInputStream(f);
		byte[] fbytes = new byte[(int) f.length()];
		fis.read(fbytes);
		fis.close();
		return fbytes;
	}

        /////////////////////////////////////////////////////////////////////////////////////
	public String resout() throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
        {
            	DecryptRSA ac = new DecryptRSA();
		PrivateKey privateKey = ac.getPrivate(path+"privateKey");
                
                String en_res=blockchain.get(blockchain.size() - 1).result;
            
            String decrypted_res = ac.decryptText(en_res, privateKey);
             
		//System.out.println("Original Message: " + msg + "\nEncrypted Message: " + encrypted_msg + "\nDecrypted Message: " + decrypted_msg);
		
                return decrypted_res;

                
        
        }
        
        public String decrypt_id(int x) throws NoSuchAlgorithmException, NoSuchPaddingException, Exception
        {
            	DecryptRSA ac = new DecryptRSA();
		PrivateKey privateKey = ac.getPrivate(path+"privateKey");
                
                String en_id=blockchain.get(x).id;
            
            String decrypted_id = ac.decryptText(en_id, privateKey);
             
		//System.out.println("Original Message: " + msg + "\nEncrypted Message: " + encrypted_msg + "\nDecrypted Message: " + decrypted_msg);
		
                return decrypted_id;

                
        
        }
    //////////////////////////////////////////////////////////////////////////////////////////////    
        
        
        
        
        
        
        
        
        
        
        
        
        public String m() throws Exception {
		DecryptRSA ac = new DecryptRSA();
		PrivateKey privateKey = ac.getPrivate(path+"privateKey");
		//PublicKey publicKey = ac.getPublic("KeyPair/publicKey");
		
		
                
                          
          StringBuilder sb=new StringBuilder();
try { 
    FileReader textFileReader = new FileReader(path+"setdata"); 
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

            String msg =  sb.toString();

		//String encrypted_msg = ac.encryptText(msg,publicKey);
		String decrypted_msg = ac.decryptText(msg, privateKey);
             
		//System.out.println("Original Message: " + msg + "\nEncrypted Message: " + encrypted_msg + "\nDecrypted Message: " + decrypted_msg);
		
                return decrypted_msg;
                
      /*          
                 BufferedWriter output = null;
        try {
            File file = new File("/home/rajeewa/Desktop/decrypted","decr");
            output = new BufferedWriter(new FileWriter(file));
            output.write(decrypted_msg);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
          */
        
        }
}
