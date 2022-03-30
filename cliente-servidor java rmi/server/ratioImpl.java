import java.rmi.*;
import java.rmi.server.*;

public class ratioImpl extends UnicastRemoteObject
    implements ratioInterface{
    
    public ratioImpl() throws RemoteException {
        super();
    }

    public double ratio(int n, int m) throws RemoteException {

        // n: number of pairs over total amount
        // m: total amount of pairs

        double ratio;
        double x, y;

        int counter = 0;

        //loop for n pairs
        for( int i = 0; i < n; i++ ){
            x = Math.random();
            y = Math.random();

            //check pair function and increase counter
            if( (x*x + y*y) <= 1 )
                counter += 1;
        }

        //ratio of valid pairs over total amount of pairs
        ratio = (double)counter / m;

        return ratio;
    }

}
