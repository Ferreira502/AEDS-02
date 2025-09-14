/*
 * A família toda ficou excitada pela novidade. Todos sabiam que o meu avô tinha
 * sido um excelente jogador de bridge por décadas, mas quando foi anunciado que
 * ele estaria no Guinness Book, o livro dos recordes, como o jogador de bridge
 * de maior sucesso de todos os tempos, wow, aquilo foi surpreendente.
 * 
 * A Associação Internacional de Bridge (AIB) tem mantido, por diversos anos, um
 * ranking semanal dos melhores jogadores do mundo. Considerando que cada
 * aparição em um ranking semanal constitui um ponto para o jogador, meu avô foi
 * nominado o melhor jogador de todos os tempos porque ele conseguiu o maior
 * número de pontos.
 * 
 * Tendo muitos amigos que também estavam competindo com ele, meu avô está
 * extremamente curioso para saber que jogador(es) ficou(aram) com o segundo
 * lugar. Ele precisa de um programa, o qual, dada uma lista com os ranking
 * semanais, descubra que jogador(es) ficou(aram) com o segundo lugar, de acordo
 * com o número de pontos.
 * 
 * Entrada A entrada contém diversos casos de teste. Jogadores são identificados
 * por inteiros de 1 a 10000. A primeira linha de um caso de teste contém dois
 * inteiros N e M, indicando, respectivamente, o número de rankings disponíveis
 * (2 ≤ N ≤ 500) e o número de jogadores em cada ranking (2 ≤ M ≤ 500). Cada uma
 * das próximas N linhas contém a descrição de um ranking semanal. Cada
 * descrição é composta por uma sequência de M inteiros, separados por um espaço
 * em branco, identificando os jogadores que apareceram naquele ranking semanal.
 * Você pode assumir que:
 * 
 * em cada caso de teste há exatamente um melhor jogador e ao menos um segundo
 * melhor jogador, cada ranking semanal consiste de M jogadores distintos. O
 * final da entrada é indicado por N = M = 0.
 * 
 * Saída Para cada caso de teste da entrada seu programa deve produzir uma linha
 * de saída, contendo o identificador do jogador que é o segundo melhor, em
 * número de aparições nos rankings. Se há um empate para segundo lugar, imprima
 * os identificadores de todos os segundo colocados, em ordem crescente. Cada
 * identificador produzido deve ser seguido por um espaço em branco.
 */

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        while (true) 
        {
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            if (N == 0 && M == 0) break;
            
            // Array para armazenar a contagem de aparições de cada jogador
            // Os jogadores são de 1 a 10000
            int[] contagem = new int[10001];
            
            // Ler os N rankings
            for (int i = 0; i < N; i++) 
            {
                for (int j = 0; j < M; j++) 
                {
                    int jogador = sc.nextInt();
                    contagem[jogador]++;
                }
            }
            
            // Encontrar a maior e segunda maior contagem
            int maxContagem = 0;
            int segundoMax = 0;
            
            for (int i = 1; i <= 10000; i++) 
            {
                if (contagem[i] > maxContagem) 
                {
                    segundoMax = maxContagem;
                    maxContagem = contagem[i];
                } 
                else if (contagem[i] > segundoMax && contagem[i] < maxContagem) 
                {
                    segundoMax = contagem[i];
                }
            }
            
            // Se todos têm a mesma contagem (segundoMax == 0), então o melhor é o menor ID
            // e os segundos são todos os outros jogadores com a mesma contagem
            if (segundoMax == 0) 
            {
                int menorID = 10001;
                for (int i = 1; i <= 10000; i++) 
                {
                    if (contagem[i] > 0 && i < menorID) 
                    {
                        menorID = i;
                    }
                }
                
                // Imprimir todos os jogadores exceto o menor ID
                for (int i = 1; i <= 10000; i++) 
                {
                    if (contagem[i] > 0 && i != menorID) 
                    {
                        System.out.print(i + " ");
                    }
                }
            } 
            else 
            {
                // Imprimir todos os jogadores com segunda maior contagem em ordem crescente
                for (int i = 1; i <= 10000; i++) 
                {
                    if (contagem[i] == segundoMax) 
                    {
                        System.out.print(i + " ");
                    }
                }
            }
            
            System.out.println();
        }
        
        sc.close();
    }
}