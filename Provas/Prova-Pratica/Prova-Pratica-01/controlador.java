import java.util.*;

public class controlador 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] oeste = new String[n];
        String[] norte = new String[n];
        String[] sul   = new String[n];
        String[] leste = new String[n];

        int contO = 0;
        int contN = 0;
        int contS = 0;
        int contL = 0;

        for (int i = 0; i < n; i++) 
        {
            String codigo = sc.nextLine().trim();
            char d = codigo.charAt(0);

            if (d == 'O') 
            {
                oeste[contO] = codigo;
                contO++;
            } 
            else if (d == 'N') 
            {
                norte[contN] = codigo;
                contN++;
            } 
            else if (d == 'S') 
            {
                sul[contS] = codigo;
                contS++;
            } 
            else if (d == 'L') 
            {
                leste[contL] = codigo;
                contL++;
            }
        }

        for (int i = 0; i < contO; i++) 
        {
            System.out.println(oeste[i]);
        }

        int iN = 0;
        int iS = 0;
        while (iN < contN || iS < contS) 
        {
            if (iN < contN)
            {
                System.out.println(norte[iN]);
                iN++;
            }
            if (iS < contS)
            {
                System.out.println(sul[iS]);
                iS++;
            }
        }

        for (int i = 0; i < contL; i++) 
        {
            System.out.println(leste[i]);
        }

        sc.close();
    }
}
