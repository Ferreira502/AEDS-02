import java.util.*;

public class Tenis 
{
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int n = 6;
        char[] palavra = new char[n];
        int cont = 0;

        for (int i = 0; i < n; i++) 
        {
            palavra[i] = sc.next().charAt(0);

            if (palavra[i] == 'V') 
            {
                cont++;
            }
        }

        if (cont == 3 || cont == 4 ) 
        {
            System.out.println("2");   
        }
        
        else if (cont == 1 || cont == 2) 
        {
            System.out.println("3");   
        }

        else if (cont == 5 || cont == 6) 
        {
            System.out.println("1");    
        }
        
        else 
        {
            System.out.println("-1");
        }
    }
}
