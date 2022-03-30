package text;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Text {
    
    //CONTAR PALABRAS
    @WebMethod(operationName = "contar")
    @WebResult(name = "result")
    int count(@WebParam(name = "str") String str);
    
    //CONTAR CARACTERES
    @WebMethod(operationName = "contar_char")
    @WebResult(name = "result")
    int count_char(@WebParam(name = "str") String str);
    
    //CONTAR FRASES
    @WebMethod(operationName = "contar_frase")
    @WebResult(name = "result")
    int count_sentence(@WebParam(name = "str") String str);
    
    //VECES PALABRA
    @WebMethod(operationName = "contar_palabra")
    @WebResult(name = "result")
    int count_word(@WebParam(name = "str") String str, @WebParam(name = "word") String word);
    
    //PALABRA MAS USADA
    @WebMethod(operationName = "mas_palabra")
    @WebResult(name = "result")
    String most_word(@WebParam(name = "str") String str);
    
    //PALABRA MENOS USADA
    @WebMethod(operationName = "menos_palabra")
    @WebResult(name = "result")
    String least_word(@WebParam(name = "str") String str);
    
    //REEMPLAZAR
    @WebMethod(operationName = "reemplazar")
    @WebResult(name = "result")
    String replace(@WebParam(name = "str") String str, @WebParam(name = "sub1") String sub1, @WebParam(name = "sub2") String sub2);
    
}
