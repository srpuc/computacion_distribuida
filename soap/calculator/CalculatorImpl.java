package calculator;

import javax.jws.WebService;
import java.util.Arrays;

@WebService(
        endpointInterface = "calculator.Calculator",
        serviceName = "Calculator"
)
public class CalculatorImpl implements Calculator {
    
    //SUMA
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public int sum(int[] nums) {
        return Arrays.stream(nums).sum();
    }
    
    //RESTA
    @Override
    public int resta(int a, int b){
        return a-b;
    }
    
    //MULTIPLICACION
    @Override
    public int mult(int a, int b){
        return a*b;
    }
    
    //DIVISION
    @Override
    public double div(int a, int b){
        return (double)((double)a/(double)b);
    }
    
    //POTENCIA
    @Override
    public int pow(int a, int b){
        //int result = 1;
        //for( int i = b; i > 0; i--)
        //    result = result * a;
        return (int)Math.pow(a, b);
    }
    
    //SQRT
    @Override
    public double sqrt(int a){
        return Math.sqrt(a);
    }
    
    //LN
    @Override
    public double ln(int a){
        return Math.log(a);
    }
    
    //MAX
    @Override
    public int max(int [] l){
        return Arrays.stream(l).max().getAsInt();
    }
    
    //MIN
    @Override
    public int min(int [] l){
        return Arrays.stream(l).min().getAsInt();
    }
    
    //MEDIA
    @Override
    public double media(int [] l){
        return Arrays.stream(l).average().getAsDouble();
    }
    
    //MEDIANA
    @Override
    public int mediana(int [] l){
        Arrays.sort(l);
        if (l.length % 2 == 0)
            return (l[l.length/2] + l[l.length/2 - 1])/2;
        else
            return l[l.length/2];
    }
    
    //MODA
    @Override
    public int moda(int [] l){
        int maxValue = 0, maxCount = 0, count;
        for(int i = 0; i < l.length; i++){
            count = 0;
            for(int j = 0; j < l.length; j++)
                if(l[j] == l[i])
                    ++count;
            if (count > maxCount){
                maxCount = count;
                maxValue = l[i];
            }
        }
        return maxValue;
    }
    
    //DV
    @Override
    public double dv(int [] l){
        double media = Arrays.stream(l).average().getAsDouble();
        double sum = 0;
        for(int i = 0; i < l.length; i++)
            sum += Math.pow( (l[i] - media) , 2);
        return Math.sqrt(sum/(l.length));
    }
    
}
