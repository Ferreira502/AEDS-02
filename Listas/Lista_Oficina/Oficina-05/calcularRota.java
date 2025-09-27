import java.util.Scanner;

class calcularRota 
{
    static class Cidade 
    {
        String nome;    
        Vizinhos[] vizinhos;

        Cidade(String nome)
        {
            this.nome = nome;
        }
    }

    static class Vizinhos
    {
        String cidadeProxima;
        int distancia;

        Vizinhos(String cidadeProxima, int distancia)
        {
            this.cidadeProxima = cidadeProxima;
            this.distancia = distancia;
        }
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int k = 0;
            
        n = sc.nextInt();
        k = sc.nextInt();

        Cidade[] cidades = new Cidade[n];
        
        for (int i = 0; i < n; i++) 
        {
            String nome = sc.nextLine();
            cidades[i] = new Cidade(nome);
        }
    }
}
