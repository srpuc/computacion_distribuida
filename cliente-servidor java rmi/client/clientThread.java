import java.rmi.RemoteException;

public class clientThread extends Thread{
    
    int n,m;
    double result;
    ratioInterface ratioObj;

    public clientThread(String str, int n, int m, ratioInterface ratioObj){
        super(str);
        this.n = n;
        this.m = m;
        this.ratioObj = ratioObj;
    }

    public void run() {
        try {
            this.result = this.ratioObj.ratio(n, m);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public double getResult(){
        return result;
    }

}
