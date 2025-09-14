/*Muitos sites na internet adicionam uma sessão chamada 
“Perguntas mais Frequentes” que, como o nome já diz, contém as perguntas mais feitas pelos usuários que utilizam o site.
O portal do URI costuma receber muitas perguntas de seus usuários, então Neilor imaginou que seria uma boa ideia adicionar uma sessão de Perguntas mais Frequentes no site. 
Como o Neilor anda muito ocupado ultimamente, ele pediu a sua ajuda para adicionar essa sessão.

Dados os identificadores de perguntas feitas pelos usuários, diga o número de perguntas que serão adicionadas na nova sessão do site. 
Uma pergunta é classificada como “frequente” quando ela é feita ao menos K vezes.


Entrada
Haverá diversos casos de teste. Cada caso de teste inicia com dois inteiros N e K (1 ≤ N ≤ 1000, 1 ≤ K ≤ 100), indicando o número de perguntas realizadas, 
e o número de vezes que uma pergunta deve ser feita para ser considerada “frequente”, respectivamente.

Em seguida haverá N inteiros P (1 ≤ P ≤ 100), cada um indicando o número de uma determinada pergunta.

O último caso de teste é indicado quando N = K = 0, o qual não deverá ser processado.

Saída
Para cada caso de teste imprima uma linha, contendo um inteiro, indicando o número de perguntas que serão adicionadas na nova sessão do site.*/

// ENTRADA                           SAIDA
// 4 5                              32 33
// 20 33 25 32 99                   1 2 21 23 31 32 34 36 38 67 76 79 88 91 93 100
// 32 86 99 25 10
// 20 99 10 33 86
// 19 33 74 99 32

// 3 6
// 2 34 67 36 79 93
// 100 38 21 76 91 85
// 32 23 85 31 88 1

// 0 0

#include <stdio.h>

int main() 
{
    int N = 0, K = 0;
    
    while (1) 
    {
        scanf("%d %d", &N, &K);
        if (N == 0 && K == 0) break;
        
        int contagem[101] = {0};
        int P;
        
        for (int i = 0; i < N; i++) 
        {
            scanf("%d", &P);
            contagem[P]++;
        }
        
        int frequentes = 0;
        for (int i = 1; i <= 100; i++) 
        {
            if (contagem[i] >= K) 
            {
                frequentes++;
            }
        }
        
        printf("%d\n", frequentes);
    }
    
    return 0;
}