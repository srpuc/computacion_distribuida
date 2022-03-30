/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

//clase escuchar

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class listen {
    
    window win;
    MulticastSocket s;
    InetAddress group;
    
    public listen(window win) {
        
        this.win = win;
        this.s = null;
        this.group = null;
        
    }
    
    public void listen(){
        
        try{
            
            //connect to multicast
            this.group = InetAddress.getByName("224.0.0.100");
            s = new MulticastSocket(6703);
            s.joinGroup(group);
            
            //listen for messages
            while(true){
                byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                String msg = new String(messageIn.getData(), 0, messageIn.getLength());
                win.printMsg(msg);
                //System.out.println("Received:" + new String(messageIn.getData()));
            }
            
        }catch(SocketException e){ System.out.println("Socket: " + e.getMessage());
        }catch(IOException e){ System.out.println("IO: " + e.getMessage());
        }finally{ if(s != null) s.close(); }
        
    }
    
}