import java.util.Scanner;

public class grid {

    public static int ultrapassagem(int n, int[] largada, int[] chegada) 
    {
        int cont = 0;
        
        int[] posChegada = new int[n + 1];

        for (int k = 0; k < n; k++) 
        {
            posChegada[chegada[k]] = k;
        }
        
        for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                if (posChegada[largada[j]] < posChegada[largada[i]]) 
                {
                    cont++;
                }
            }
        }
        return cont;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNext()) 
        {
            int n = sc.nextInt();
            if (n == 0) 
            {
                break;
            }

            int[] largada = new int[n];
            int[] chegada = new int[n];
            
            for (int i = 0; i < n; i++) 
            {
                largada[i] = sc.nextInt();
            }

            for (int j = 0; j < n; j++) 
            {
                chegada[j] = sc.nextInt();
            }

            int resultado = ultrapassagem(n, largada, chegada);
            System.out.println(resultado);
        }
        sc.close();
    }
}