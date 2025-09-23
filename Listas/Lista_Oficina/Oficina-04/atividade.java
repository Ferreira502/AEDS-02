import java.util.Scanner;

public class QuickSort 
{
    static int comparacoes = 0;
    static int trocas = 0;

    public static void quickSort(int[] arr, int low, int high) 
    {
        if (low < high) 
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) 
    {
        int mid = low + (high - low) / 2;
        int pivot = arr[mid];

        int i = low;
        int j = high;

        while (i <= j) 
        {
            while (arr[i] < pivot) 
            {
                i++;
                comparacoes++;
            }
            comparacoes++;

            while (arr[j] > pivot) 
            {
                j--;
                comparacoes++;
            }
            comparacoes++;

            if (i <= j) 
            {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        return j;
    }

    private static void swap(int[] arr, int i, int j) 
    {
        if (i != j) 
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            trocas++;
        }
    }

    private static void printArray(int[] arr) 
    {
        for (int num : arr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o tamanho do array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Digite os elementos do array:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("\nArray original:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\nArray ordenado:");
        printArray(arr);

        System.out.println("\nEstatisticas:");
        System.out.println("Comparacoes: " + comparacoes);
        System.out.println("Trocas: " + trocas);

        sc.close();
    }
}