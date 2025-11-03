import java.util.*;

class MenorPilha 
{
    public int[] elementos;
    public int topo;
    public int capacidade;

    public MenorPilha(int capacidade) 
    {
        this.topo = 0;
        this.capacidade = capacidade;
        this.elementos = new int[capacidade];
    }

    public void push(int x) 
    {
        if (topo == capacidade) 
        {
            System.out.println("Pilha esta cheia");
            return;
        }
        elementos[topo++] = x;
    }

    public void pop() 
    {
        if (topo == 0) 
        {
            System.out.println("Pilha esta vazia");
            return;
        }
        topo--;
        System.out.println("Elemento removido: " + elementos[topo]);
    }

    public void mid() 
    {
        if (topo == 0) 
        {
            System.out.println("Pilha esta vazia");
        } 
        else 
        {
            System.out.println("Topo da pilha: " + elementos[topo - 1]);
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int tamanho = sc.nextInt();
        MenorPilha pilha = new MenorPilha(tamanho);

        for (int i = 0; i < tamanho; i++) 
        {
            String palavra = sc.nextLine();
            String[] separar = palavra.split(" ");
            String comando = separar[0];

            if (comando.equals("PUSH")) 
            {
                int valor = Integer.parseInt(separar[1]);
                pilha.push(valor);
            } 
            else if (comando.equals("POP")) 
            {
                pilha.pop();
            } 
            else if (comando.equals("MID")) 
            {
                pilha.mid();
            }
        }
    }
}