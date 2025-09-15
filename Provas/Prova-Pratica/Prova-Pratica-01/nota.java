import java.util.*;

class nota 
{
    public static int corte(int[] array, int n, int k)
    {
        int cont = 0;
        int aux = 0;
        for ( int i = 1; i < n; i++)
            {
                for ( int j = 0; j < n - 1; j++)
                    {
                        if ( array [j] < array[j + 1] )
                        {
                            aux = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = aux;
                        }
                    }
            }

        return array[k - 1];
    }
    
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = 0, k = 0;
    
        n = sc.nextInt();
        k = sc.nextInt();

        int[] array = new int[n];
        
        for ( int i = 0; i < n; i++ )
            {
                array[i] = sc.nextInt();
            }
    
        int resultado = corte(array, n, k);
        
        System.out.println(resultado);
    }
}