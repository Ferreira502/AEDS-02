import java.util.Scanner;

public class noel 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine(); 

        String[] linguagem = new String[100];
        String[] saudacoes = new String[100];

        for (int i = 0; i < n; i++) 
        {
            linguagem[i] = sc.nextLine();
            saudacoes[i] = sc.nextLine();
        }

        int m = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < m; i++) 
        {
            String nomeCrianca = sc.nextLine();
            String nomeLinguagem = sc.nextLine();


            String saudacao = "";
            for (int j = 0; j < n; j++) 
            {
                if (stringsIguais(linguagem[j], nomeLinguagem)) 
                {
                    saudacao = saudacoes[j];
                    break;
                }
            }

            System.out.println(nomeCrianca);
            System.out.println(saudacao);
            System.out.println();
        }

        sc.close();
    }

    public static boolean stringsIguais(String a, String b) 
    {
        int lenA = a.length();
        int lenB = b.length();

        if (lenA != lenB) return false;

        for (int i = 0; i < lenA; i++) 
        {
            if (a.charAt(i) != b.charAt(i)) 
            {
                return false;
            }
        }
        return true;
    }
}
