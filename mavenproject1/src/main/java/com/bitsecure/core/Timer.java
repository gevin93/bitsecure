/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rajeewa
 */
public class Timer {

    public void run() 
    {
        int time=20;
        int TimeCounter = 1;
        
        try {
            
            
                while(time==TimeCounter)
                {
                   Thread.sleep(10);
                    TimeCounter++;
                    
                    if(time==TimeCounter)
                    {
                         
                        System.exit(0);
                    }
                    
                        
             }
        } catch (Exception e) {
       }
       
    
    }
    
  }
