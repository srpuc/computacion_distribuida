import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class server {
    
    public static void main(String[] args) {

        //var declaration
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String portNum, registryURL, ip, id, option;
        boolean local;

        try {
            
            //local or remote
            System.out.println("Do you want to create a registry in this machine (y/n): ");
            option = (br.readLine()).trim();

            //port selection
            System.out.println("Enter RMIregistry port number:");
            portNum = (br.readLine()).trim();

            //create local registry
            if (option.equals("y")) {
                int RMIPortNum = Integer.parseInt(portNum);
                startRegistry(RMIPortNum);
            }

            //object and registry
            ratioImpl exportedObj = new ratioImpl();
            if(!option.equals("y")){
                System.out.println("Introduce IP of the RMI registry machine: ");
                ip = (br.readLine()).trim();
            }
            else {
                ip = "localhost";
            }
            
            System.out.println("Introduce object ID: ");
            id = (br.readLine()).trim();
            registryURL = "rmi://" + ip + ":" + portNum + "/ratio" + id;
            Naming.rebind(registryURL, exportedObj);

            //show registry
            System.out.println("Server registred.\nRegistry currently contais:");
            listRegistry(registryURL);
            System.out.println("Ratio server is up and running.");

        } catch (Exception re) {
            System.out.println("Exception in server: " + re);
        }
    }

    //auxiliary fucntions for registry management
    private static void startRegistry(int RMIPortNum) throws RemoteException{

        try {

            //check if registry is created
            Registry regsitry = LocateRegistry.getRegistry(RMIPortNum);
            regsitry.list();

        } catch (RemoteException e) {

            //create registry
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);
            LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);

        }
    }

    private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {

        //print registry
        String [] names = Naming.list(registryURL);
        for (String name : names)
            System.out.println(name);

    }

}
