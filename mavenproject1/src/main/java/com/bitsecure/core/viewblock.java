/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author rajeewa
 */
public class viewblock {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public void viewblock(){
String path="C:\\Users\\user\\Desktop\\bitsecure\\";
        try {
            FileInputStream fis = new FileInputStream(path+"arraylist");

            ObjectInputStream ois = new ObjectInputStream(fis);

            blockchain = (ArrayList) ois.readObject();
            ois.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }

      
        
        for(int i=0;i<blockchain.size();i++)
        {
            System.out.println("block: "+i+"{");
            System.out.println("Hash: "+blockchain.get(i).hash);
            System.out.println("Previous Hash: "+blockchain.get(i).previousHash);
            System.out.println("ID: "+blockchain.get(i).id);
            System.out.println("Vote: "+blockchain.get(i).data);
            System.out.println("result: "+blockchain.get(i).result);
               System.out.println("}\n"+"//////////////////////////////////////////////////////////////");
        }
    }

}
