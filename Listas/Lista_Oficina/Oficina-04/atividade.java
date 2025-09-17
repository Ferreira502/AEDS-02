import java.util.*;

class atividade 
{
    public static void quicksort(int[] array, int esq, int dir) 
    {
        int i = esq, j = dir;
        int pivo = array[(dir + esq) / 2];
        while (i <= j) 
        {
            while (array[i] < pivo) i++;
            while (array[j] > pivo) j--;
            if (i <= j) 
            {
                int aux = array[i];
                array[i] = array[j];
                array[j] = aux;
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(array, esq, j);
        if (i < dir)  quicksort(array, i, dir);
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] array = new int[n];
        
        for (int i = 0; i < n; i++)
        {
            array[i] = sc.nextInt();
        }

        quicksort(array, 0, n - 1);
        
        for (int i = 0; i < array.length; i++) 
        {
            System.out.print(array[i]);
            if (i < array.length - 1) 
            {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}