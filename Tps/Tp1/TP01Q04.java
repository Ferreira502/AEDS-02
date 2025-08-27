import java.util.*;

class TP01Q04 
{

    public static String random ( String palavra )
    {
        // Gera duas letras minusculas aleatorias
        Random gerador = new Random();
        gerador.setSeed(4);
        char letra  = (char)('a' + Math.abs(gerador.nextInt()) % 26);
        char letra1 = (char)('a' + Math.abs(gerador.nextInt()) % 26);

        String resultado = ""; 
        
        for (int i = 0; i < palavra.length(); i++)
            {
                char c = palavra.charAt(i);
                if ( c == letra )
                {
                    resultado = resultado + letra1;
                }
                else
                {
                    resultado = resultado + c;
                }
            }
        return resultado;
    }
    
    
    public static int comparaString(String palavra)
    {
        String str1 = "FIM";
        return palavra.compareTo(str1); 
    }

    // Método principal
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        String palavra;        
        String newPalavra;     
        int resultado = 1;    

        // Laço que continua enquanto a palavra digitada for diferente de "FIM"
        while (resultado != 0) 
        {
            palavra = sc.nextLine();

            if (comparaString(palavra) == 0) // Verifica se a palavra é "FIM"
            {
                break;
            }
            
            resultado = comparaString(palavra);
            newPalavra = random(palavra);
            System.out.println(newPalavra);
        }   
    }
}