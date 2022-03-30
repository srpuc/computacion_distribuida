package server;

import java.io.IOException;

import java.util.HashMap;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.rabbitmq.client.Channel;
import java.util.ArrayList;
import java.util.Map;

//CLASS
public class Valor extends TimerTask {
    
    //CLASS VARIABLES
    private Element e = null;
    private Valor v = null;
    private Channel ch = null;
    private HashMap<String, ArrayList<String>> users = null;
    
    //CONSTRUCTOR
    public Valor(Channel channel) throws Exception{
        this.v = this;
        this.ch = channel;
        this.users = new HashMap();
    }
    
    
    //FUNCION CADA 60S
    public void run() {
        
        //Update WebPage Element
        try {
            this.e = Jsoup.connect("https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice").get().getElementById("aspnetForm");
            notifyAlerta("");
        } catch (Exception ex) {
            ex.printStackTrace(); }
        
    }
    
    
    //GET DATA
    public float getValor() throws InterruptedException{
        while(this.e == null) { TimeUnit.SECONDS.sleep(1); }
        return Float.parseFloat( this.e.getElementById("ctl00_Contenido_tblÍndice").select("tr").get(1).select("td").get(2).text().replace(".", "").replace(",", ".") );
    }
    public HashMap<String, Float> getValores(){
        HashMap<String, Float> index = new HashMap<String, Float>();
        for ( Element row : this.e.getElementById("ctl00_Contenido_tblAcciones").select("tr").subList(1, this.e.getElementById("ctl00_Contenido_tblAcciones").select("tr").size() ) )
            index.put( row.select("td").get(0).text(), Float.parseFloat( (row.select("td").get(1).text().replace(".", "").replace(",", "."))));
        return index;
    }

    //SEND DATA
    public void sendTable(String user) throws Exception {      
        String str = "values:" + user + "\n" + 
                     "IBEX35|" + this.v.getValor() + "\n";
        
        for(Map.Entry<String, Float> entry : this.v.getValores().entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            str = str + key + "|" + value + "\n";
        }
        
        ch.basicPublish("server_out", user, null, str.getBytes("UTF-8"));
        
        if(user.equals(""))
            System.out.println(" [x] Table has been sent" );
        else
            System.out.println(" [x] Table has been sent to " + user);
    }
    public void sendAlertas(String user) throws Exception{
        String str = "alertas:" + user + "\n";
        if( !this.users.isEmpty() ){
            for(String s : this.users.get(user))
                str = str + s;
            ch.basicPublish("server_out", user, null, str.getBytes("UTF-8"));
        }
    }
    
    
    //NOTIFICAR CUMPLIMIENTO ALERTA
    public void notifyAlerta(String user) throws Exception{
        
        //we go over every user with alerts
        for(Map.Entry<String, ArrayList<String>> entry : this.users.entrySet() ){
            
            System.out.println(entry.getKey());
            ArrayList<String> tmp = new ArrayList();
            
            //we go over every alert for each user
            for(String s : entry.getValue()){
                
                System.out.print("    " + s);
                String[] data = s.split("\\|");
                
                if(data[1].equals("compra")){
                    if(this.v.getValores().get(data[0]) > Float.parseFloat(data[2])){
                        //send notify
                        String str = "notificacion:" + entry.getKey() + "\n" +
                                     "El valor de " + data[0] + " esta por debajo de " + data[2];
                        ch.basicPublish("server_out", entry.getKey(), null, str.getBytes("UTF-8"));
                        tmp.add(s);
                    }
                }
                else{
                    if(this.v.getValores().get(data[0]) < Float.parseFloat(data[2])){
                        //send alert
                        String str = "notificacion:" + entry.getKey() + "\n" +
                                     "El valor de " + data[0] + " esta por encima de " + data[2];
                        ch.basicPublish("server_out", entry.getKey(), null, str.getBytes("UTF-8"));
                        tmp.add(s);
                    }
                }
                
            }
            
            if(entry.getValue().size() != 0){
                for(String s : tmp){
                    rmAlerta(entry.getKey(), s);
                }
                this.sendAlertas(entry.getKey());
            }
        }  
    }
 
    
    //GESTION ALERTAS SERVIDOR
    public void addAlerta(String user, String data) throws Exception{
        if(!this.users.containsKey(user))
            this.users.put(user, new ArrayList() );
        this.users.get(user).add(data); 
    }
    public void rmAlerta(String user, String data) throws Exception{
        int size = this.users.get(user).size();
        for( int i = size-1; i >= 0; i-- )
            if( this.users.get(user).get(i).equals(data) )
                this.users.get(user).remove(i);
        
    }

    
    //REMOVE USER
    public void rmUser(String user){
        this.users.remove(user);
    }
}