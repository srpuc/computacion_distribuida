import java.io.*;
import java.rmi.*;

public class client {
    
    public static void main(String[] args) {
        
        int m = 100000000;
        double value = 0;

        try {
            
            //var declaration
            String hostName, portNum, registryURL;
            int numThreads;

            //input var
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);

            //ask for host name
            System.out.println("Enter the RMIregistry host name: ");
            hostName = br.readLine();

            //ask for port
            System.out.println("Enter the RMIregistry port number: ");
            portNum = br.readLine();

            //display number of available objects
            registryURL = "rmi://" + hostName + ":" + portNum + "/" ;
            String [] names = Naming.list(registryURL);
            numThreads = names.length;
            System.out.println("Number of remote objects: " + numThreads);
            for (String name : names)
                System.out.println(name);

            Thread th[] = new Thread[numThreads];
            ratioInterface ratioObjs[] = new ratioInterface[numThreads];

            for (int i = 0; i < numThreads; i++){
                ratioObjs[i] = (ratioInterface)Naming.lookup(names[i]);
                th[i] = new clientThread(names[i], (int)(m/numThreads) , m, ratioObjs[i]);
            }

            for (int i = 0; i < numThreads; i++)
                th[i].start();

            for (int i = 0; i < numThreads; i++)
                th[i].join();

            for (int i = 0; i < numThreads; i++){
                value += ((clientThread) th[i]).getResult();
                System.out.println(value);
            }

            System.out.println("Value: " + value);
            System.out.println("PI: " + value * 4);

        } catch (Exception e) {
            System.out.println("Exception in client: " + e);
        }

    }

}
