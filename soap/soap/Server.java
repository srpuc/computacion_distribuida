package soap;

import calculator.Calculator;
import calculator.CalculatorImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import text.Text;
import text.TextImpl;
import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        Text t       = new TextImpl();
        String address_c = "http://localhost:8080/server/calculator";
        String address_t = "http://localhost:8080/server/text";
        Endpoint ep1 = Endpoint.publish(address_c, c);
        Endpoint ep2 = Endpoint.publish(address_t, t);
        System.out.println("Server is ready.");
        
        //Var lectura
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            
            //Lectura entrada
            System.out.println("Introduce stop para detener el servidor:");
            try { str = br.readLine();}catch(IOException e){ System.out.println("Failed terminal read: " + e.getMessage()); return; }
        
            //comprobamos salida
            if (str.equals("stop")){
                ep1.stop();
                ep2.stop();
                System.exit(0);
            }
            
        }
    }
}
