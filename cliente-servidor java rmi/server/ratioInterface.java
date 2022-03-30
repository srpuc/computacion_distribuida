import java.rmi.*;

public interface ratioInterface extends Remote {

    public double ratio(int n, int m)
        throws java.rmi.RemoteException;

}