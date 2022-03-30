package soap;

import calculator.Calculator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import text.Text;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws MalformedURLException {
        
        //Calculator Service
        URL wsdlURL = new URL("http://localhost:8080/server/calculator?wsdl");
        QName SERVICE_NAME = new QName("http://calculator/", "Calculator");
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        Calculator client1 = service.getPort(Calculator.class);
        
        //Text Service
        wsdlURL = new URL("http://localhost:8080/server/text?wsdl");
        SERVICE_NAME = new QName("http://text/", "Text");
        service = Service.create(wsdlURL, SERVICE_NAME);
        Text client2 = service.getPort(Text.class);
        
        //Read Loop
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double result_d;
        String result_str = "";
        while(true){
            
            //Reset
            result_d = 0;
            
            //Lectura de comando
            System.out.println("Introduce comando:");
            try { str = br.readLine();
            }catch(IOException e){ System.out.println("Failed terminal read: " + e.getMessage()); return; }

            //Procesamos comando
            String [] comando = str.split(" ", 3);
            switch(comando[0]){
                
                //SALIR
                case "exit":
                    return;
                
                //SERVICIOS TEXT
                case "text":
                    if(comando.length < 3){ System.out.println("Faltan argumentos para este comando."); continue;}
                    switch(comando[1]){
                        case "contar_palabras":     result_d = client2.count(           comando[2]); break;
                        case "contar_char":         result_d = client2.count_char(      comando[2]); break;
                        case "contar_frases":       result_d = client2.count_sentence(  comando[2]); break;
                        case "n_repeticiones":      
                            String [] rep = comando[2].split(" ", 2);
                            result_d = client2.count_word(rep[1], rep[0]);        
                            break;
                        case "palabra_mas_usada":   result_str = client2.most_word(     comando[2]); break;
                        case "palabra_menos_usada": result_str = client2.least_word(    comando[2]); break;
                        case "reemplazar":    
                            String [] r = comando[2].split(" ", 3);
                            result_str = client2.replace(r[2], r[0], r[1]);    
                            break;
                        default:
                            System.out.println("Comando invalido, debe serguir la estructura: [Nombre del servicio] [Operacion] [Datos] \nIntroduzca 'help' para mas detalles.\nIntroduzca 'exit' para salir de la aplicacion.");
                            continue;
                    }
                    break;
                    
                //SERVICIOS CALCULATOR
                case "calculator": 
                    //String [] params = comando[2].split(" ");
                    int [] params = Arrays.stream( comando[2].split(" ") ).mapToInt(Integer::parseInt).toArray();
                    switch(comando[1]){
                        case "suma":    result_d = client1.sum(       params[0], params[1]); break;
                        case "resta":   result_d = client1.resta(     params[0], params[1]); break;
                        case "mult":    result_d = client1.mult(      params[0], params[1]); break;
                        case "div":     result_d = client1.div(       params[0], params[1]); break;
                        case "pow":     result_d = client1.pow(       params[0], params[1]); break;
                        case "sqrt":    result_d = client1.sqrt(      params[0]); break;
                        case "ln":      result_d = client1.ln(        params[0]); break;
                        case "max":     result_d = client1.max(       params );   break;
                        case "min":     result_d = client1.min(       params );   break;
                        case "media":   result_d = client1.media(     params );   break;
                        case "mediana": result_d = client1.mediana(   params );   break;
                        case "moda":    result_d = client1.moda(      params );   break;
                        case "desv":    result_d = client1.dv(        params );   break;
                        default: 
                            System.out.println("Comando invalido, debe serguir la estructura: [Nombre del servicio] [Operacion] [Datos] \nIntroduzca 'help' para mas detalles.\nIntroduzca 'exit' para salir de la aplicacion.");
                            continue;
                    }
                    break;
                          
                //MOSTRAR AYUDA
                case "help":
                    if(comando.length != 2){ System.out.println("Comando invalido, debe seguir la estructura: 'help' [Nombre del servicio] \nServicios disponibles: 'text', 'calculator'"); continue;}
                    switch(comando[1]){
                        case "calculator":  System.out.println ("suma  'int' 'int': return 'int'\n" +
                                                                "resta 'int' 'int': return 'int'\n" +
                                                                "mult  'int' 'int': return 'int'\n" +
                                                                "div   'int' 'int': return 'double'\n" +
                                                                "pow   'int' 'int': return 'int'\n" +
                                                                "sqrt  'int': return 'double'\n" +
                                                                "ln    'int': return 'double'\n" +
                                                                "max     [int]: return 'int'\n" +
                                                                "min     [int]: return 'int'\n" +
                                                                "media   [int]: return 'double'\n" +
                                                                "mediana [int]: return 'int'\n" +
                                                                "moda    [int]: return 'int'\n" +
                                                                "dv      [int]: return 'double'"); 
                            break;
                        case "text":        System.out.println ("contar_palabras     'str': return 'str'\n" +
                                                                "contar_char         'str': return 'str'\n" +
                                                                "contar_frases       'str': return 'str'\n" +
                                                                "palabra_mas_usada   'str': return 'str'\n" +
                                                                "palabra_menos_usada 'str': return 'str'\n" +
                                                                "n_repeticiones      'str' 'str':       return 'str'\n" +
                                                                "reemplazar          'str' 'str' 'str': return 'str'"); 
                            break;
                        default:            System.out.println("Servicio no reconocido."); break;
                    }
                    break;
                
                //COMANDO NO RECONOCIDO
                default:
                    System.out.println("Comando invalido, debe serguir la estructura: [Nombre del servicio] [Operacion] [Datos] \nIntroduzca 'help' para mas detalles.\nIntroduzca 'exit' para salir de la aplicacion.");
                    continue;
            }
        
            
            //Imprimir resultados
            if (comando.length > 2)
            if (comando[1].equals("palabra_mas_usada") || comando[1].equals("palabra_menos_usada") || comando[1].equals("reemplazar"))
                System.out.println(result_str);
            else
                System.out.println(result_d);
            
        }
        
    }
    
}
