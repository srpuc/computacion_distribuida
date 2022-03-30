package calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Calculator {
    
    //SUMA
    @WebMethod(operationName = "suma")
    @WebResult(name = "resultado")
    int sum(@WebParam(name = "sumando1") int a, @WebParam(name = "sumando2") int b);
    int sum(int[] nums);
    
    //RESTA
    @WebMethod(operationName = "resta")
    @WebResult(name = "resultado")
    int resta(@WebParam(name = "minuendo") int a, @WebParam(name = "sustraendo") int b);
    
    //MULT
    @WebMethod(operationName = "mult")
    @WebResult(name = "resultado")
    int mult(@WebParam(name = "multiplicando") int a, @WebParam(name = "multiplicador") int b);
    
    //DIV
    @WebMethod(operationName = "div")
    @WebResult(name = "resultado")
    double div(@WebParam(name = "dividendo") int a, @WebParam(name = "divisor") int b);
    
    //POW
    @WebMethod(operationName = "pow")
    @WebResult(name = "resultado")
    int pow(@WebParam(name = "base") int a, @WebParam(name = "exponente") int b);
    
    //SQRT
    @WebMethod(operationName = "sqrt")
    @WebResult(name = "result")
    double sqrt(@WebParam(name = "radicando") int a);
    
    //LN
    @WebMethod(operationName = "ln")
    @WebResult(name = "resultado")
    double ln(@WebParam(name = "num") int a);
    
    //MAX
    @WebMethod(operationName = "max")
    @WebResult(name = "result")
    int max(@WebParam(name = "list") int [] l);
    
    //MIN
    @WebMethod(operationName = "min")
    @WebResult(name = "result")
    int min (@WebParam(name = "list") int [] l);
    
    //MEDIA
    @WebMethod(operationName = "media")
    @WebResult(name = "result")
    double media(@WebParam(name = "list") int [] l);
    
    //MEDIANA
    @WebMethod(operationName = "mediana")
    @WebResult(name = "result")
    int mediana(@WebParam(name = "list") int [] l);
    
    //MODA
    @WebMethod(operationName = "moda")
    @WebResult(name = "result")
    int moda(@WebParam(name = "moda") int [] l);
    
    //DESVIACION TIPICA
    @WebMethod(operationName = "dv")
    @WebResult(name = "result")
    double dv(@WebParam(name = "list") int [] l);
    
}
