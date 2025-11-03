#include <stdio.h>
#include <string.h>

int main() {
    int N, M;
    
    while (scanf("%d %d", &N, &M) != EOF) {
        // Verificar se é o fim da entrada
        if (N == 0 && M == 0) break;
        
        int tabuleiro[N][M];
        int resultado[N][M];
        
        // Ler o tabuleiro
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                scanf("%d", &tabuleiro[i][j]);
            }
        }
        
        // Calcular o resultado
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tabuleiro[i][j] == 1) {
                    resultado[i][j] = 9;
                } else {
                    int count = 0;
                    
                    // Verificar todas as 8 direções adjacentes
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue; // pular a própria célula
                            
                            int ni = i + di;
                            int nj = j + dj;
                            
                            // Verificar se está dentro dos limites do tabuleiro
                            if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                                if (tabuleiro[ni][nj] == 1) {
                                    count++;
                                }
                            }
                        }
                    }
                    resultado[i][j] = count;
                }
            }
        }
        
        // Imprimir o resultado
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                printf("%d", resultado[i][j]);
            }
            printf("\n");
        }
    }
    
    return 0;
}