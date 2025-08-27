import java.util.*;

class TP01Q03 
{

    // Funcao para comparar uma string recebida com a palavra "FIM"
    // Se a palavra for igual a "FIM", retorna 0; senao retorna um valor diferente
    public static int comparaString(String palavra)
    {
        String str1 = "FIM";
        return palavra.compareTo(str1); 
    }

    // Funcao que aplica o Ciframento de Cesar em uma string
    // Desloca cada caractere 3 posições para frente dentro da tabela ASCII
    public static String cesarString(String palavra)
    {   
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < palavra.length(); i++) 
        {
            char c = palavra.charAt(i); 
            int ascii = (int) c;        

            // Verifica se o caractere está no intervalo dos imprimíveis (32 a 126)
            if (ascii >= 32 && ascii <= 126) 
            {
                // Aplica o deslocamento de 3 posições no intervalo de 95 caracteres
                int novoAscii = 32 + ((ascii - 32 + 3) % 95);

                sb.append((char) novoAscii); 
            }
            else
            {
                sb.append(c);
            }
        }

        return sb.toString(); 
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        String palavra;
        String newPalavra;
        int resultado = 1; 

        while (resultado != 0) // Continua até o usuário digitar "FIM"
        {
            palavra = sc.nextLine();

            if (comparaString(palavra) == 0)
            {
                break; // Sai do loop
            }

            resultado = comparaString(palavra);   
            newPalavra = cesarString(palavra);    
            System.out.println("" + newPalavra);  
        }   
    }
}
