#include <stdio.h>

int main() {
    int N, M;
    int tabuleiro[100][100];
    int fim = 0; // controle do fim da leitura

    while (fim == 0) 
    {
        char entrada[10];
        scanf("%s", entrada);

        // verifica se é "FIM"
        if (entrada[0] == 'F') 
        {
            fim = 1;
        } 
        else 
        {
            // le o restante dos valores
            N = entrada[0] - '0';
            for (int i = 1; entrada[i] != '\0'; i++) 
            {
                if (entrada[i] >= '0' && entrada[i] <= '9')
                {
                    N = N * 10 + (entrada[i] - '0');
                }
            }
            scanf("%d", &M);

            // lê o tabuleiro
            for (int i = 0; i < N; i++) 
            {
                for (int j = 0; j < M; j++) 
                {
                    scanf("%d", &tabuleiro[i][j]);
                }
            }

            // gera a saída
            for (int i = 0; i < N; i++) 
            {
                for (int j = 0; j < M; j++) 
                {
                    if (tabuleiro[i][j] == 1) 
                    {
                        printf("9");
                    } 
                    else 
                    {
                        int cont = 0;
                        for (int di = -1; di <= 1; di++) 
                        {
                            for (int dj = -1; dj <= 1; dj++) 
                            {
                                if (!(di == 0 && dj == 0)) 
                                {
                                    int ni = i + di;
                                    int nj = j + dj;
                                    if (ni >= 0 && ni < N && nj >= 0 && nj < M) 
                                    {
                                        if (tabuleiro[ni][nj] == 1) 
                                        {
                                            cont = cont + 1;
                                        }
                                    }
                                }
                            }
                        }
                        printf("%d", cont);
                    }
                }
                printf("\n");
            }
        }
    }

    return 0;
}