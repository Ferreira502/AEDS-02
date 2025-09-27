import java.util.*;

class lab05
{
    public static void swap(int[] array, int i, int j) 
    {
       int aux = array[i];
       array[i] = array[j];
       array[j] = aux;
    }

    public static void QuickSortFirstPivot(int[] array, int left, int right) 
    {
        if (left >= right) return;

        int pivot = array[left];
        int i = left, j = right;

        while (i <= j) 
        {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) 
            {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (left < j) QuickSortFirstPivot(array, left, j);
        if (i < right) QuickSortFirstPivot(array, i, right);
    }

     public static void QuickSortLastPivot(int[] array, int left, int right) 
     {
        if (left >= right) return;

        int pivot = array[right];
        int i = left, j = right;

        while (i <= j) 
        {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) 
            {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (left < j) QuickSortLastPivot(array, left, j);
        if (i < right) QuickSortLastPivot(array, i, right);
    }
    // public static void QuickSortRandomPivot(int[] array, int left, int right) {}
    
    public static void QuickSortMedianOfThree(int[] array, int left, int right) 
    {
        if (left >= right) return;

        int mid = (left + right) / 2;

        if (array[left] > array[mid]) swap(array, left, mid);
        if (array[left] > array[right]) swap(array, left, right);
        if (array[mid] > array[right]) swap(array, mid, right);

        int pivot = array[mid];

        int i = left, j = right;

        while (i <= j) 
        {
            while (array[i] < pivot) i++;
            while (array[j] > pivot) j--;
            if (i <= j) 
            {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (left < j) QuickSortMedianOfThree(array, left, j);
        if (i < right) QuickSortMedianOfThree(array, i, right);
    }

    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o tamanho array: ");
        int n = sc.nextInt();
        int resultado = 0;
        
        int[] array = new int[n];
        System.out.print("Digite os valores do array: ");
        for (int i = 0; i < n; i++) 
        {
            array[i] = sc.nextInt();
        }

        System.out.println("Escolha o algoritmo de ordenacao" );
        System.out.println("1 - QuickSort ( Mediana de tres )");
        System.out.println("2 - QuickSort ( Primeiro pivo )"  );
        System.out.println("3 - QuickSort ( Ultimo pivo )"    );
        System.out.println("4 - QuickSort ( Pivo aleatorio )" );

        int opcao = sc.nextInt();  
        switch(opcao)
        {
            case 1:
                resultado = QuickSortMedianOfThree(array, 0, array.length - 1);
                System.out.println("" + resultado);
                break;
            case 2:
                QuickSortFirstPivot(array, 0, array.length - 1);
                break;
            case 3:
                QuickSortLastPivot(array, 0, array.length - 1);
                break;
            // case 4:
            //     QuickSortRandomPivot(array, 0, array.length - 1);
            //     break;
            default:
                System.out.println("Numero invalido");
                break;
        }
        
    }
}