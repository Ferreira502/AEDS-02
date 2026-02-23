import java.util.*;

class TP01Q01 
{
    // Verifica se uma palavra eh um palindromo.
    public static boolean ehPalindromo(String palavra) 
    {
        int tamanho = palavra.length();
        for (int i = 0; i < tamanho / 2; i++) 
        {
            if (palavra.charAt(i) != palavra.charAt(tamanho - 1 - i)) 
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) 
        {
            String entrada = sc.nextLine();

            // Verifica se a entrada eh "FIM" para encerrar o programa.
            if (entrada.length() == 3 &&
                entrada.charAt(0) == 'F' &&
                entrada.charAt(1) == 'I' &&
                entrada.charAt(2) == 'M') 
            {
                break;
            }

            if (ehPalindromo(entrada)) 
            {
                System.out.println("SIM");
            } 
            else 
            {
                System.out.println("NAO");
            }
        }

        sc.close();
    }
}