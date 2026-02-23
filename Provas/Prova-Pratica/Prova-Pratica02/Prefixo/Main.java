import java.util.*;
import java.io.*;

public class Main 
{

    // função para retornar a precedência de um operador
    public static int precedencia(char op) 
    {
        switch (op) {
            case '^': return 3;
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
            default: return 0;
        }
    }

    // Função que converte expressão infixa para posfixa usando pilha manual
    public static String infixaParaPosfixa(String exp) 
    {
        char[] pilha = new char[exp.length()]; // pilha manual
        int topo = -1; // índice do topo
        char[] saida = new char[exp.length() * 2]; // buffer da saída
        int k = 0; // posição na saída

        for (int i = 0; i < exp.length(); i++) 
        {
            char c = exp.charAt(i);

            // Ignorar espaços
            if (c == ' ') continue;

            // Se for letra ou dígito, vai direto para a saída
            if (Character.isLetterOrDigit(c)) 
            {
                saida[k++] = c;
            }

            // Se for parêntese abrindo, empilha
            else if (c == '(') 
            {
                pilha[++topo] = c;
            }

            // Se for parêntese fechando, desempilha até achar '('
            else if (c == ')') 
            {
                while (topo >= 0 && pilha[topo] != '(') 
                {
                    saida[k++] = pilha[topo--];
                }
                if (topo >= 0 && pilha[topo] == '(') 
                {
                    topo--; // remove '(' da pilha
                }
            }

            // Se for operador
            else 
            {
                while (topo >= 0 && precedencia(pilha[topo]) >= precedencia(c)) 
                {
                    // '^' é associativo à direita → não desempilha operadores iguais
                    if (c == '^' && pilha[topo] == '^') break;
                    saida[k++] = pilha[topo--];
                }
                pilha[++topo] = c;
            }
        }

        // desempilha o resto
        while (topo >= 0) 
        {
            saida[k++] = pilha[topo--];
        }

        return new String(saida, 0, k);
    }

    public static void main(String[] args) throws IOException 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // limpar quebra de linha

        for (int i = 0; i < n; i++) 
        {
            String expressao = sc.nextLine().trim();
            System.out.println(infixaParaPosfixa(expressao));
        }

        sc.close();
    }
}