import java.util.*;

class TP01Q03 
{
    // Metodo que aplica a cifra de Cesar com deslocamento de 3 em caracteres imprimiveis ASCII
    public static String cesarString(String palavra)
    {   
        String resultado = ""; 

        for (int i = 0; i < palavra.length(); i++)
        {
            char c = palavra.charAt(i);       
            int ascii = (int) c;             

            // Verifica se o caractere esta dentro da faixa de caracteres imprimiveis
            if (ascii >= 32 && ascii <= 126) 
            {
                int novoAscii = 32 + ((ascii - 32 + 3) % 95);
                resultado = resultado + (char) novoAscii;
            }
            else
            {
                resultado = resultado + c;
            }
        }

        return resultado;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); 

        while (sc.hasNextLine()) 
        {
            String palavra = sc.nextLine();

            // Verifica se a entrada e "FIM" usando apenas charAt
            if (palavra.length() == 3 &&
                palavra.charAt(0) == 'F' &&
                palavra.charAt(1) == 'I' &&
                palavra.charAt(2) == 'M') 
            {
                break;
            }

            String newPalavra = cesarString(palavra);
            System.out.println(newPalavra);
        }   

        sc.close();
    }
}
