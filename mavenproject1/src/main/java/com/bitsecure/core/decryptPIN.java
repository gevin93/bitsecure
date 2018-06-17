/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

/**
 *
 * @author rajeewa
 */
public class decryptPIN {
    
    /**
     *
     * @param pn
     * @return
     */
    public String decrypt(String pn)
    {
        String key="asdcfggds";
        String pns=pn.toString();
        char key_letter=key.charAt(3);
        
        int number;
           
            if(key_letter=='a')
            {
                number=1;
            }

            else if(key_letter=='b')
            {
                number=2;
            }

            else if(key_letter=='c')
            {
                number=3;
            }

            else if(key_letter=='d')
            {   
                number=4;
            }

            else if(key_letter=='e')
            {
                number=5;
            }

            else if(key_letter=='f')
            {
                number=6;
            }

            else if(key_letter=='g')
            {
                number=7;
            }   

            else if(key_letter=='h')
            {
                number=8;
            }

            else if(key_letter=='i')
            {
                number=9;
            }

            else if(key_letter=='j')
            {
                number=10;
            }

            else if(key_letter=='k')
            {
                number=11;
            }   

            else if(key_letter=='l')
            {
                number=12;
            }

            else if(key_letter=='m')
            {
                number=13;
            }

            else if(key_letter=='n')
            {
               number=14;
            }

            else if(key_letter=='o')
            {
                number=15;
            }

            else
            {
                number=16;
            }

        
        String n1=Character.toString(pns.charAt(0));
        String n2=Character.toString(pns.charAt(1));
        String n3=Character.toString(pns.charAt(2));
        
        int nn1=Integer.parseInt(n1);
        int nn2=Integer.parseInt(n2);
        int nn3=Integer.parseInt(n3);
        
        int nn11=nn1-number;
        int nn22=nn2-number;
        int nn33=nn3-number;
        
        String ans1=Integer.toString(nn11);
        String ans2=Integer.toString(nn22);
        String ans3=Integer.toString(nn33);
        
        String res=ans1+""+ans2+""+ans3;
        return res;
        
    }
    
}
