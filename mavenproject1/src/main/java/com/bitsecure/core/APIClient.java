/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bitsecure.core;

import com.bitsecure.core.RMIserverint.invoke;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author rajeewa
 */
public class APIClient {

    public String[] getNodes() {
        String nodes = System.getenv("BITSECURE_NODES");
        return nodes.split(",");
    }

    public void send_blockchain() throws UnknownHostException {

        for (String node : getNodes()) {
            notifyNode(node);
        }

    }

    private void notifyNode(String node) {
        try {
            String myIp = InetAddress.getLocalHost().getHostAddress();
            URL url = new URL(node + "/v1/sync/notify?ip=" + myIp);
            InputStream is = url.openStream();
            is.read();
            is.close();
        } catch (java.io.IOException e) {
            System.err.println("Unable to notify node " + node);
            e.printStackTrace();
        }
    }


}
