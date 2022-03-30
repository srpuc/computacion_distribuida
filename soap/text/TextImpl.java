package text;

import javax.jws.WebService;

@WebService(
        endpointInterface = "text.Text",
        serviceName = "Text"
)
public class TextImpl implements Text {
    
    //CONTAR PALABRAS
    @Override
    public int count(String str){
        return str.split(" ").length;
    }
    
    //CONTAR CARACTERES
    @Override
    public int count_char(String str){
        return str.length();
    }
    
    //CONTAR FRASES
    @Override
    public int count_sentence(String str){
        return str.split("\\.").length;
    }
    
    //VECES PALABRA
    @Override
    public int count_word(String str, String word){
        return (str.split(word, -1).length) - 1;
    }
    
    //PALABRA MAS USADA
    @Override
    public String most_word(String str){
        String [] words = str.split(" ");
        int [] word_times = new int[words.length];
        int max = 0;
        for( int i = 0; i < words.length; i++){
            word_times[i] = str.split(words[i], -1).length - 1;
            if( word_times[i] >= word_times[max])
                max = i;
        }
        return words[max];
    }
    
    //PALABRA MENOS USADA
    @Override
    public String least_word(String str){
        String [] words = str.split(" ");
        int [] word_times = new int[words.length];
        int min = 0;
        for( int i = 0; i < words.length ; i++){
            word_times[i] = str.split(words[i], -1).length - 1;
            if( word_times[i] <= word_times[min] )
                min = i;
        }
        return words[min];
    }
    
    //REEMPLAZAR
    @Override
    public String replace(String str, String sub1, String sub2){
        return str.replace(sub1, sub2);
    }
}
