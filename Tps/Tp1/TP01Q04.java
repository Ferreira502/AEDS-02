import java.util.*;

class TP01Q04 
{
    //metodo para trocar as palavras aleatorias
    public static String random (String palavra, Random gerador)
    {
        char letra  = (char)('a' + Math.abs(gerador.nextInt()) % 26);
        char letra1 = (char)('a' + Math.abs(gerador.nextInt()) % 26);

        char resultado[] = new char[palavra.length()];
            
        for (int i = 0; i < palavra.length(); i++)
        {
            resultado[i] = palavra.charAt(i);
            for (int j = 0; j < palavra.length(); j++)
            {
                if (resultado[j] == letra)
                {
                    resultado[j] = letra1;
                }
            }
        }
        return new String(resultado);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in); 
        Random gerador = new Random();
        gerador.setSeed(4);
    
        while (sc.hasNextLine()) 
        {
            String palavra = sc.nextLine();

            if (palavra.length() == 3 &&
                palavra.charAt(0) == 'F' &&
                palavra.charAt(1) == 'I' &&
                palavra.charAt(2) == 'M') 
            {
                break;
            }
            
            String newPalavra = random(palavra, gerador);
            System.out.println(newPalavra);
        }

        sc.close();
    }
}