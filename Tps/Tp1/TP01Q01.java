import java.util.*;

class TP01Q01
{
    // Função que compara a palavra recebida com "FIM"
    // Se forem iguais retorna 0, caso contrário retorna valor != 0
    public static int comparaString(String palavra)
    {
        String str1 = "FIM";
        return palavra.compareTo(str1);
    }
    
    // Função que verifica se uma string é palíndromo
    // Percorre apenas metade da string e compara extremos
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

    // Função que gerencia a leitura e saída dos resultados
    public static void print()
    {
        Scanner sc = new Scanner(System.in);
        int resultado = 1;

        while (resultado != 0)
        {
            String palavra = sc.nextLine(); 
            resultado = comparaString(palavra);

            if (resultado != 0) // só entra aqui se NÃO for "FIM"
            {
                if (ehPalindromo(palavra)) 
                {
                    System.out.println("SIM");
                } 
                else 
                {
                    System.out.println("NAO");
                }  
            }
        }

        sc.close(); 
    }
    
    public static void main(String[] args)
    {
        print();
    }
}
