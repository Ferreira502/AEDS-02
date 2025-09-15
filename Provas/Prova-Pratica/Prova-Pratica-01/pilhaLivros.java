import java.util.*;

public class pilhaLivros 
{

    static int push(String[] pilha, int topo, String valor) 
    {
        pilha[topo] = valor;
        topo++;
        return topo;
    }

    static int pop(int topo) 
    {
        if (topo > 0) 
        {
            topo--;
        }
        return topo;
    }

    static String extrairCodigo(char[] linha) 
    {
        String valor = "";
        for (int i = 5; i < linha.length; i++)
         {
            if (linha[i] == '\0') break;
            valor += linha[i];
        }
        return valor;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); 

        String[] pilha = new String[n];
        int topo = 0;

        for (int i = 0; i < n; i++) 
        {
            String entrada = sc.nextLine();

            char[] chars = entrada.toCharArray();

            if (chars.length >= 3 &&
                chars[0] == 'P' && chars[1] == 'O' && chars[2] == 'P') 
                {

                topo = pop(topo);

            } 
            else if (chars.length >= 4 &&
                       chars[0] == 'P' && chars[1] == 'U' &&
                       chars[2] == 'S' && chars[3] == 'H') 
                       {

                String codigo = extrairCodigo(chars);
                topo = push(pilha, topo, codigo);
            }
        }

        if (topo == 0) 
        {

            System.out.println("Pilha vazia");
        } 
        else 
        {
            for (int i = topo - 1; i >= 0; i--) 
            {
                System.out.println(pilha[i]);
            }
        }

        sc.close();
    }
}
