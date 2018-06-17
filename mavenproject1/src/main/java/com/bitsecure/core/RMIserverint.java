/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author rajeewa
 */
public class RMIserverint {
    // Creating a Search interface

public interface invoke extends Remote
{
    // Declaring the method prototype
    public void method(String IP) throws RemoteException;
}
    
}
