import java.util.*;

class Cesar 
{

    public static int comparaString ( String palavra)
    {
       String str1 = "FIM";
       return palavra.compareTo(str1);
    }

    public static String cesarString(String palavra)
    {   

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < palavra.length(); i++)
        {
            char c = palavra.charAt(i);
            int ascii = (int) c;

            if (ascii >= 32 && ascii <= 126) 
            {
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

        while (resultado != 0) 
        {
            // System.out.println("Digite uma palavra: ");
            palavra = sc.nextLine();

            if (comparaString(palavra) == 0) 
            {
                break;
            }

            resultado = comparaString(palavra);
            newPalavra = cesarString(palavra);
            System.out.println("" + newPalavra);
        }   
    }
}
