import java.util.*;

class QuickSortTempo
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
    
    public static void QuickSortRandomPivot(int[] array, int left, int right) 
    {
        if (left >= right) return;

        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        swap(array, left, pivotIndex);

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

        if (left < j) QuickSortRandomPivot(array, left, j);
        if (i < right) QuickSortRandomPivot(array, i, right);
    }

    
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

    public static int[] gerarOrdenado(int n) 
    {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i;
        return array;
    }

    public static int[] gerarQuaseOrdenado(int n) 
    {
        int[] array = gerarOrdenado(n);
        Random rand = new Random();
        for (int i = 0; i < n / 10; i++) 
        { 
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            swap(array, a, b);
        }
        return array;
    }

    public static int[] gerarAleatorio(int n) 
    {
        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = rand.nextInt(n * 10);
        return array;
    }

    public static void testar(int[] original, String tipoArray) 
    {
        int n = original.length;
        System.out.println("\n--- Testando com array " + tipoArray + " de tamanho " + n + " ---");

        int[] arr1 = Arrays.copyOf(original, n);
        int[] arr2 = Arrays.copyOf(original, n);
        int[] arr3 = Arrays.copyOf(original, n);
        int[] arr4 = Arrays.copyOf(original, n);

        long start, end;
        double tempo; // agora em double

        start = System.nanoTime();
        QuickSortMedianOfThree(arr1, 0, n - 1);
        end = System.nanoTime();
        tempo = (end - start) / 1_000_000.0;
        System.out.printf("Mediana de tres: %.6f ms%n", tempo);

        start = System.nanoTime();
        QuickSortFirstPivot(arr2, 0, n - 1);
        end = System.nanoTime();
        tempo = (end - start) / 1_000_000.0;
        System.out.printf("Primeiro pivo: %.6f ms%n", tempo);

        start = System.nanoTime();
        QuickSortLastPivot(arr3, 0, n - 1);
        end = System.nanoTime();
        tempo = (end - start) / 1_000_000.0;
        System.out.printf("Ultimo pivo: %.6f ms%n", tempo);

        start = System.nanoTime();
        QuickSortRandomPivot(arr4, 0, n - 1);
        end = System.nanoTime();
        tempo = (end - start) / 1_000_000.0;
        System.out.printf("Pivo aleatorio: %.6f ms%n", tempo);
    }


    public static void main (String[] args)
    {
        int[] tamanhos = {100, 1000, 10000};

        for (int n : tamanhos) 
        {
            testar(gerarOrdenado(n), "ordenado");
            testar(gerarQuaseOrdenado(n), "quase ordenado");
            testar(gerarAleatorio(n), "aleatório");
        }
        
    }
}