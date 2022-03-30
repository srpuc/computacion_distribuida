/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Timer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class server {
    public static void main(String[] args) throws Exception {
        
        //RABBITMQ CONNECTION
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        
        //OUT CHANNEL AND EXCHANGE
        Channel channel_out = connection.createChannel();
        channel_out.exchangeDeclare("server_out", "direct");
        
        //PERIODIC UPDATES
        Valor v = new Valor(channel_out);
        Timer timer = new Timer();
        timer.schedule(v, 0, 60000);
        
        //notify server is ready by terminal
        System.out.println( "server is up and running." );
       
        //IN QUEUE (default exchange)
        Channel channel_in = connection.createChannel();
        channel_in.queueDeclare("server_in", false, false, false, null);
        
        
        //INPUT PROCESSING
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                
                //message string processing
                String message = new String(delivery.getBody(), "UTF-8");
                String header = message.split("\n", 2)[0];
                String action = header.split(":")[0];
                String user   = header.split(":")[1];
                String data   = "";
                if(message.split("\n",2).length > 1)
                    data   = message.split("\n", 2)[1];
                
                //print in message
                System.out.println(" [.] Recibido '" + header + "'");
                
                //execute corresponding task
                switch(action){
                    case "connection": 
                        v.sendTable(user); 
                        break;
                    case "desconectar":
                        v.rmUser(user);
                        break;
                    case "add":
                        v.addAlerta(user, data);
                        v.sendAlertas(user);
                        break;
                    case "delete":
                        v.rmAlerta(user, data);
                        v.sendAlertas(user);
                        break;
                }
                
            } catch (Exception ex) { ex.printStackTrace(); }
        };
        channel_in.basicConsume("server_in", true, deliverCallback, consumerTag -> { });
        
        
        
        //CLOSE SERVER
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        
        while(!s.equals("quit")){
            System.out.println("Introduzca 'quit' para cerrar el servidor.");
            s = br.readLine();
        }
        
        channel_in.queueDelete("server_in");
        channel_in.exchangeDelete("server_out", false);
        channel_in.close();
        connection.close();
        
        System.exit(0);
        
    }
    
}
